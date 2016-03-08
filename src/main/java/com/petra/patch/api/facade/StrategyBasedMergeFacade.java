package com.petra.patch.api.facade;

import com.petra.patch.api.MergeStrategy;

/**
 * Created by amarees on 2/28/16.
 */
public interface StrategyBasedMergeFacade extends BasicMergeFacade {

	<T> T merge(T source, T target, MergeStrategy mergeStrategy);
}
