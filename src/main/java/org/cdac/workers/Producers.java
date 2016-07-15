package org.cdac.workers;

import org.cdac.annotations.Producer;
import org.cdac.annotations.ProducerAction;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Sam on 7/15/2016.
 */

@Producer
public class Producers {

    @ProducerAction
    public Function< Runnable> performTask() {
        return () -> () -> System.out.println("I am producer action");
    }
}
