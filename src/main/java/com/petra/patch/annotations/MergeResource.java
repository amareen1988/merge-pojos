package com.petra.patch.annotations;

import static com.petra.patch.MergeStrategy.NOT_NULL_NOT_EMPTY;

import com.petra.patch.MergeStrategy;

/**
 * Created by amarees on 3/5/16.
 */
public @interface MergeResource {

	MergeStrategy strategy() default NOT_NULL_NOT_EMPTY;
}
