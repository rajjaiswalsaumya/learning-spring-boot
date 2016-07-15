package org.cdac.annotations.processor;

import org.cdac.annotations.Consumer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Sam on 7/15/2016.
 */
@Component
public class ConsumerAnnotationProcessor implements ApplicationContextAware, InitializingBean {
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {

        final Map<String, Object> myConsumers = applicationContext.getBeansWithAnnotation(Consumer.class);

        for (final Object myFoo : myConsumers.values()) {
            final Class<? extends Object> fooClass = myFoo.getClass();
            final Consumer annotation = fooClass.getAnnotation(Consumer.class);
            System.out.println("Found consumer class: " + fooClass + ", with tags: " + annotation.value());
        }
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }
}

