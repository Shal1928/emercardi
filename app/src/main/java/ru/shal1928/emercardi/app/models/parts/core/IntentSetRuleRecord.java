package ru.shal1928.emercardi.app.models.parts.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Danila on 02.11.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface IntentSetRuleRecord {
    String value();
    String[] setModelMethods() default "";
}
