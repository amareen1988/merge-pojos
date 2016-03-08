package com.petra.patch.impl.facade;

import static com.petra.patch.api.MergeStrategy.SOURCE_NOT_NULL_NOT_EMPTY;

import com.petra.patch.api.facade.StrategyBasedMergeFacade;
import com.petra.patch.api.MergeStrategy;

/**
 * Created by amarees on 2/28/16.
 */
public abstract class StrategyBasedMergeFacadeImpl implements StrategyBasedMergeFacade {

	private final MergeStrategy defaultStrategy;

	public StrategyBasedMergeFacadeImpl(MergeStrategy defaultStrategy) {
		this.defaultStrategy = defaultStrategy;
	}

	public <T> T merge(T source, T target) {
		return merge(source, target, defaultStrategy);
	}

	public abstract <T> T merge(T source, T target, MergeStrategy mergeStrategy);
}
