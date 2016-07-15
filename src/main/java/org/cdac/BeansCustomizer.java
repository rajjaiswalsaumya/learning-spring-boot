package org.cdac;

import org.cdac.workers.ProducerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Sam on 7/15/2016.
 */
@Configuration
public class BeansCustomizer {

    @Configuration
    public static class CustomBeansCustomizer {

        @Bean(destroyMethod = "destroy")
        public ProducerFactory producerFactory() {
            BlockingQueue queue = new PriorityBlockingQueue();
            ProducerFactory factory = new ProducerFactory(queue, 10);
            return factory;
        }

    }


    @Configuration
    public static class EmbeddedCustomBeansCustomizer {

    }

}
