package com.petra.patch.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by amarees on 3/5/16.
 */

/**
 * A pojo/resource that potentially embeds other fields should be annotated with @MergeableResource
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MergeableResource {

}
