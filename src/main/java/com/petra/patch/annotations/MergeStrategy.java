package com.petra.patch.annotations;

import static com.petra.patch.api.MergeStrategy.SOURCE_NOT_NULL;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by amarees on 3/7/16.
 */

/**
 * This annotation is needed to specify how a certain field should be merged.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD})
public @interface MergeStrategy {

	com.petra.patch.api.MergeStrategy strategy() default SOURCE_NOT_NULL;
}
