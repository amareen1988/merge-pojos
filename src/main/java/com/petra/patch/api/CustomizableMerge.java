package com.petra.patch.api;

/**
 * Created by amarees on 3/6/16.
 */
public interface CustomizableMerge<RETURN_TYPE> {

	<T> RETURN_TYPE customize(Class<T> type, CustomMerge<T> customMerge);

	<T> RETURN_TYPE customize(Class<?> type, String fieldName, CustomMerge<T> customMerge);

}
