package com.petra.patch.impl;

import com.petra.patch.api.CustomMerge;
import com.petra.patch.api.MergeFactory;
import com.petra.patch.api.context.MergeContext;
import com.petra.patch.api.facade.CustomizableMergeFacade;
import com.petra.patch.impl.context.MergeContextImpl;
import com.petra.patch.impl.facade.CustomizableMergeFacadeImpl;
import com.petra.patch.impl.facade.DefaultMergeFacadeImpl;

/**
 * Created by amarees on 2/28/16.
 */

// TODO: Allow annotation config
// TODO: Allow static field config via factory
// TODO: Allow dynamic field request config via facade

public class MergeFactoryImpl implements MergeFactory {


	/**
	 * Singleton static context
	 */
	private final MergeContext mergeContext = new MergeContextImpl();

	/**
	 * Creates prototype instance of a CustomizableMergeFacade
	 * in order to merge two resources.
	 *
	 * @return
	 */
	public CustomizableMergeFacade facade() {
		return new CustomizableMergeFacadeImpl(mergeContext, new DefaultMergeFacadeImpl());
	}

	public <T> MergeFactory customize(Class<T> type, CustomMerge<T> customMerge) {
		mergeContext.registerCustomMerge(type, customMerge);
		return this;
	}

	public <T> MergeFactory customize(Class<?> type, String fieldName, CustomMerge<T> customMerge) {
		mergeContext.registerCustomMerge(type, fieldName, customMerge);
		return this;
	}
}
