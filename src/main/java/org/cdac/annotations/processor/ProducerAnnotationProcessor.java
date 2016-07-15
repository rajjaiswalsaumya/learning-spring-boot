package org.cdac.annotations.processor;

import org.cdac.annotations.Producer;
import org.cdac.annotations.ProducerAction;
import org.cdac.workers.ProducerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Sam on 7/15/2016.
 */

@Component
public class ProducerAnnotationProcessor implements ApplicationContextAware,
        InitializingBean {
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        final Map<String, Object> myProducers = applicationContext.getBeansWithAnnotation(Producer.class);
        ProducerFactory factory = applicationContext.getBean(ProducerFactory.class);
        factory.afterPropertiesSet();
        for (final Object producer : myProducers.values()) {
            final Class<? extends Object> producerClass = producer.getClass();


            for (final Method method : producerClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(ProducerAction.class)) {
                    //factory.assignTasks(method);
                    Function<Supplier<Runnable>> f = method;
                }
            }
        }
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }
}

