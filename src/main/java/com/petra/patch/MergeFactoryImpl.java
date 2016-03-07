package com.petra.patch;

import com.petra.patch.api.CustomMerge;
import com.petra.patch.api.CustomizableMergeFacade;
import com.petra.patch.api.MergeFactory;
import com.petra.patch.api.StrategyBasedMergeFacade;
import com.petra.patch.impl.StrategyBasedMergeFacadeImpl;

/**
 * Created by amarees on 2/28/16.
 */

// TODO: Allow annotation config
// TODO: Allow static field config via factory
// TODO: Allow dynamic field request config via facade

public class MergeFactoryImpl implements MergeFactory {

	public CustomizableMergeFacade facade() {
		return null;
	}

	public <T> MergeFactory customize(Class<T> type, CustomMerge<T> customMerge) {
		return null;
	}

	public <T> MergeFactory customize(Class<?> type, String fieldName, CustomMerge<T> customMerge) {
		return null;
	}
}
