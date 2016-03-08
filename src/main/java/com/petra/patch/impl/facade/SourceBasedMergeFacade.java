package com.petra.patch.impl.facade;

import com.petra.patch.api.facade.BasicMergeFacade;

/**
 * Created by amarees on 3/7/16.
 */
public class SourceBasedMergeFacade implements BasicMergeFacade {

	@Override
	public <T> T merge(T source, T target) {
		return source;
	}
}
