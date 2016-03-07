package com.petra.patch.impl;

import static com.petra.patch.MergeStrategy.NOT_NULL_NOT_EMPTY;

import com.petra.patch.MergeStrategy;
import com.petra.patch.api.StrategyBasedMergeFacade;

/**
 * Created by amarees on 2/28/16.
 */
public abstract class StrategyBasedMergeFacadeImpl implements StrategyBasedMergeFacade {

	public <T> T merge(T source, T target) {
		return merge(source, target, NOT_NULL_NOT_EMPTY);
	}

	public abstract <T> T merge(T source, T target, MergeStrategy mergeStrategy);
}
