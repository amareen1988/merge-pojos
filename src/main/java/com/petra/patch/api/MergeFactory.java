package com.petra.patch.api;

import com.petra.patch.api.facade.CustomizableMergeFacade;

/**
 * Created by amarees on 2/28/16.
 */
public interface MergeFactory extends CustomizableMerge<MergeFactory> {

	CustomizableMergeFacade facade();

	MergeFactory setDefaultStrategy(MergeStrategy defaultStrategy);
}
