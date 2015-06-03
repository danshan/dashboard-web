package com.dianping.wed.monitor.web.other;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author cong.yu
 *
 */
public class ExecutorServiceFactoryBean implements FactoryBean, DisposableBean {

    private int corePoolSize;

    private int maximumPoolSize;

    private long keepAliveTime;

    private int blockingQueueCapacity;

    private ThreadPoolExecutor executorService;

    private Status status;

    private Logger logger = Logger.getLogger(ExecutorServiceFactoryBean.class);

    enum Status {
        ALIVE, SHUTDOWN
    };

    @Override
    public Object getObject() throws Exception {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(corePoolSize,
                    maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(blockingQueueCapacity),
                    new ThreadPoolExecutor.CallerRunsPolicy());

            logger.info("executorService has been initialized");

            // adds a shutdown hook to shut down the executorService
            Thread shutdownHook = new Thread() {
                @Override
                public void run() {
                    synchronized (this) {
                        if (status == Status.ALIVE) {
                            executorService.shutdown();
                            status = Status.SHUTDOWN;
                            logger.info("excecutorService has been shut down");
                        }
                    }
                }
            };
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            logger.info("successfully add shutdown hook");
        }
        return executorService;
    }

    @Override
    public Class getObjectType() {
        return ExecutorService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public void setBlockingQueueCapacity(int blockingQueueCapacity) {
        this.blockingQueueCapacity = blockingQueueCapacity;
    }

    @Override
    public void destroy() throws Exception {
        synchronized (this) {
            if (status == Status.ALIVE) {
                executorService.shutdown();
                status = Status.SHUTDOWN;
                logger.info("excecutorService has been shut down");
            }
        }
    }

}
