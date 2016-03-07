package com.petra.patch.api;

/**
 * Created by amarees on 3/5/16.
 */
public interface CustomMerge<T> {

	T merge(T source, T target);

}
