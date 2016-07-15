package org.cdac.workers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Sam on 7/15/2016.
 */
public class ProducerFactory implements InitializingBean, DisposableBean {
    ForkJoinPool forkJoinPool;
    BlockingQueue queue;
    int failedShutDownAttempt = 3;
    int parallelism = 1;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public ProducerFactory(BlockingQueue queue, int parallelism) {
        this.queue = queue;
        this.parallelism = parallelism;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (forkJoinPool == null) {
            forkJoinPool = new ForkJoinPool(parallelism);
            logger.debug("Starting Producer pool");
        }
    }

    public void assignTasks(Function function) {
        if (function != null) {
            Supplier<Runnable> supplier = (Supplier<Runnable>) function;
            forkJoinPool.submit(supplier.get());
        }
    }


    private void tryShutDownPool() throws Exception {
        if (!forkJoinPool.isShutdown()) {
            forkJoinPool.shutdown();
            forkJoinPool.awaitTermination(100, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void destroy() throws Exception {
        logger.debug("About to destroy bean");
        if (forkJoinPool.isQuiescent() && !forkJoinPool.isTerminated()) {
            logger.debug("Destroying producer pool");
            forkJoinPool.shutdown();
            int attempt = 0;
            while (!forkJoinPool.isShutdown() && attempt < failedShutDownAttempt) {
                try {
                    tryShutDownPool();
                } catch (Exception e) {
                    attempt++;
                }
            }
            if (!forkJoinPool.isTerminated()) {
                forkJoinPool.shutdownNow();
            }
        }
    }
}
