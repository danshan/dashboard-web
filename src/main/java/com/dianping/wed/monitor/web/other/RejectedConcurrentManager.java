package com.dianping.wed.monitor.web.other;

import com.dianping.combiz.spring.util.LionConfigUtils;
import com.dianping.wed.monitor.exception.ThreadPoolFailedToStartException;
import org.apache.log4j.Logger;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * Author: liangjun.zhong
 * Date: 13-12-4
 * Time: PM7:14
 * To change this template use File | Settings | File Templates.
 */
public class RejectedConcurrentManager {

    private static Logger logger = Logger.getLogger(RejectedConcurrentManager.class);

    private static final int corePoolSize = Integer.parseInt(LionConfigUtils.getProperty("shop-web.downgrading.threadPool.corePoolSize", "30"));
    private static final int maximumPoolSize = Integer.parseInt(LionConfigUtils.getProperty("shop-web.downgrading.threadPool.maximumPoolSize", "40"));
    private static final long keepAliveTime = Integer.parseInt(LionConfigUtils.getProperty("shop-web.downgrading.threadPool.keepAliveTime", "2000"));
    private static final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
    private static final int workQueueCapacity = Integer.parseInt(LionConfigUtils.getProperty("shop-web.downgrading.threadPool.workQueueCapacity", "3000"));
    private static final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(workQueueCapacity);
    private static final RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

    private static volatile ExecutorService executor;
    private static volatile ThreadPoolStatus threadPoolStatus;

    private enum ThreadPoolStatus {
        ALIVE, SHUTDOWN;
    };


    private static ExecutorService getExecutorService() throws ThreadPoolFailedToStartException {
        if(executor == null){
            synchronized (RejectedConcurrentManager.class) {
                if(executor == null) {


                    executor = new ThreadPoolExecutor(corePoolSize,
                            maximumPoolSize, keepAliveTime, timeUnit, workQueue, handler);

                    threadPoolStatus = ThreadPoolStatus.ALIVE;
                    logger.info("executorService for RejectedConcurrentManager has been initialized");

                    // adds a shutdown hook to shut down the executorService
                    Thread shutdownHook = new Thread() {
                        @Override
                        public void run() {
                            synchronized (RejectedConcurrentManager.class) {
                                if (threadPoolStatus == ThreadPoolStatus.ALIVE) {
                                    executor.shutdown();
                                    threadPoolStatus = ThreadPoolStatus.SHUTDOWN;
                                    logger.info("executorService for RejectedConcurrentManager has been shut down");
                                }
                            }
                        }
                    };
                    Runtime.getRuntime().addShutdownHook(shutdownHook);
                    logger.info("successfully add shutdown hook to the executorService for RejectedConcurrentManager");
                }
            }
        }
        if(executor == null){
            threadPoolStatus = ThreadPoolStatus.SHUTDOWN;
            logger.error("can not init ExecutorService for RejectedConcurrentManager");
            throw new ThreadPoolFailedToStartException("can not init executorService for RejectedConcurrentManager");
        }

        return executor;
    }


    /**
     * delegate task to RejectedConcurrentManager to execute asynchronously
     * Note that if it's too busy it will discard the current task ;
     * and if it failed to init it's thread pool, it will discard all the tasks
     * @param task
     */
    public static void delegateTaskAsyncUnSafely(Runnable task){

        if(task==null)
            return;

        final Runnable r = task;
        Runnable wrappedTask = new Runnable() {
            public void run() {
                try{
                    r.run();
                }catch (Exception e){
                    logger.error("RejectedConcurrentManager: something wrong with the task", e);
                }
            }
        };
        try {

            ExecutorService executorService = getExecutorService();
            executorService.execute(wrappedTask);

        } catch (ThreadPoolFailedToStartException e) {
            logger.error("RejectedConcurrentManager's thread pool is dead, it will discard all the tasks", e);
        } catch (RejectedExecutionException e) {
            logger.info("RejectedConcurrentManager is too busy, it will discard some tasks", e);
        }
    }



}
