package com.petra.patch.api.facade;

/**
 * Created by amarees on 3/6/16.
 */
public interface BasicMergeFacade {

	<T> T merge(T source, T target);
}
