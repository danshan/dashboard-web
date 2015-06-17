package com.dianping.wed.monitor.web.other;

import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.avatar.tracker.SqlDetail;
import com.dianping.avatar.tracker.TrackerContext;
import com.dianping.combiz.spring.util.LionConfigUtils;
import com.dianping.w3c.pagelet.Controller;
import com.dianping.w3c.pagelet.ExecutionManagerType;
import com.dianping.w3c.pagelet.Result;
import com.dianping.w3c.pagelet.concurrent.ConcurrentManager;
import com.dianping.w3c.pagelet.concurrent.ResultWithTrace;
import com.dianping.w3c.pagelet.concurrent.TemplateRenderingTaskWithTrace;
import com.dianping.w3c.pagelet.exception.PageletException;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author cong.yu
 *
 */
public class DefaultConcurrentManager implements ConcurrentManager {

    private Logger logger = Logger.getLogger(DefaultConcurrentManager.class);

    private Map<String, Future<ResultWithTrace>> executionResult;

    private ExecutorService executorService;

    private Controller controller;

    private long timeout;

    private boolean executed = false;

    private boolean traceEnabled = false;

    public DefaultConcurrentManager() {
        this.timeout = Integer.parseInt(LionConfigUtils.getProperty("shop-web.pagelet.concurrent.timeout", "2000"));
        executionResult = new HashMap<String, Future<ResultWithTrace>>();
    }

    public Result template(String name) {
        if (!executed) {
            throw new IllegalStateException("the module:" + name + " has not been executed");
        }
        try {
            Future<ResultWithTrace> resultFuture = executionResult.get(name);
            if (resultFuture == null) {
                throw new PageletException("no templage:" + name + " found in the current module");
            }
            ResultWithTrace resultWithTrace = resultFuture.get(timeout,
                    TimeUnit.MILLISECONDS);
            if (traceEnabled) {
                // get trace info
                TrackerContext pageletTrackerContext = resultWithTrace.getTrackerContext();
                TrackerContext mainTrackerContext = ExecutionContextHolder.getTrackerContext();
                // copy remoting info
                for (TrackerContext trackerContext : pageletTrackerContext.getRemoteContexts()) {
                    mainTrackerContext.getRemoteContexts().add(trackerContext);
                }
                // copy sql info
                for (SqlDetail sqlDetail : pageletTrackerContext.getSqlExecutionTrace().getDetails()) {
                    mainTrackerContext.getSqlExecutionTrace().addDetail(sqlDetail);
                }
                // copy cache info
                mainTrackerContext.getCacheExecutionTrace().getRelatedKeys()
                        .putAll(pageletTrackerContext.getCacheExecutionTrace().getRelatedKeys());
            }
            // return the result
            return resultWithTrace.getResult();
        } catch (Exception e) {
            logger.error(name + " error:", new PageletException(name + " error:",e));
            return new Result("", new HashMap<String, Object>());
        }
    }

    public void execute(String style, Map<String, Object> params, String... templateKeys) {
        if (templateKeys == null) {
            throw new PageletException("templateKeys cannot be null");
        }
        for (String templateKey : templateKeys) {
            try {
                executionResult.put(templateKey, executorService.submit(new TemplateRenderingTaskWithTrace(controller, templateKey, style, params)));
            } catch (Exception e) {
                logger.error(templateKey + " error:", e);
            }
        }
        executed = true;

    }

    public void execute(Map<String, Object> params, String... templateKeys) {
        execute(null, params, templateKeys);
    }

    public String signal() {
        return ExecutionManagerType.CONCURRENT.value;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setTraceEnabled(boolean traceEnabled) {
        this.traceEnabled = traceEnabled;
    }

}