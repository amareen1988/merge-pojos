package com.petra.patch.api.context;

import com.petra.patch.api.CustomMerge;

/**
 * Created by amarees on 3/7/16.
 */
public interface MergeContext extends ReadOnlyMergeContext {

	<T> void registerCustomMerge(Class<T> type, CustomMerge<T> customMerge);

	<T> void registerCustomMerge(Class<?> type, String fieldName, CustomMerge<T> customMerge);
}
