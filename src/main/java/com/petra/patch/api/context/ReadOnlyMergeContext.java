package com.petra.patch.api.context;

import com.petra.patch.api.CustomMerge;

/**
 * Created by amarees on 3/7/16.
 */
public interface ReadOnlyMergeContext {

	CustomMerge lookupCustomMerge(Class<?> clazz);

	CustomMerge lookupCustomMerge(Class<?> clazz, String fieldName);

}
