package org.cdac.annotations;

import java.lang.annotation.*;

/**
 * Created by Sam on 7/15/2016.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConsumerAction {
    int parallelism() default 1;
}