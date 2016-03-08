package com.petra.patch.api.facade;

import com.petra.patch.api.CustomizableMerge;
import com.petra.patch.api.context.ReadOnlyMergeContext;

/**
 * Created by amarees on 3/6/16.
 */
public interface CustomizableMergeFacade extends StrategyBasedMergeFacade, CustomizableMerge<CustomizableMergeFacade> {

	ReadOnlyMergeContext getMergeContext();

}
