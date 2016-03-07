package com.petra.patch.api;

/**
 * Created by amarees on 3/6/16.
 */
public interface BasicMergeFacade {

	<T> T merge(T source, T target);
}
