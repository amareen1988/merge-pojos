package com.petra.patch.impl.facade;

import static com.petra.patch.api.MergeStrategy.NOT_NULL_NOT_EMPTY;

import com.petra.patch.api.facade.StrategyBasedMergeFacade;
import com.petra.patch.api.MergeStrategy;

/**
 * Created by amarees on 2/28/16.
 */
public abstract class StrategyBasedMergeFacadeImpl implements StrategyBasedMergeFacade {

	public <T> T merge(T source, T target) {
		return merge(source, target, NOT_NULL_NOT_EMPTY);
	}

	public abstract <T> T merge(T source, T target, MergeStrategy mergeStrategy);
}
