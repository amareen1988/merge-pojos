package com.petra.patch.api;

import com.petra.patch.MergeStrategy;
import com.petra.patch.api.BasicMergeFacade;

/**
 * Created by amarees on 2/28/16.
 */
public interface StrategyBasedMergeFacade extends BasicMergeFacade {

	<T> T merge(T source, T target, MergeStrategy mergeStrategy);
}
