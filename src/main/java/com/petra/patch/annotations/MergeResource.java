package com.petra.patch.annotations;

import static com.petra.patch.api.MergeStrategy.SOURCE_NOT_NULL_NOT_EMPTY;

import com.petra.patch.api.MergeStrategy;

/**
 * Created by amarees on 3/5/16.
 */
public @interface MergeResource {

	MergeStrategy strategy() default SOURCE_NOT_NULL_NOT_EMPTY;
}
