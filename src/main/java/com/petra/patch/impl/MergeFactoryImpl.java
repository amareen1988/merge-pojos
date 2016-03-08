package com.petra.patch.impl;

import static com.petra.patch.api.MergeStrategy.NOT_NULL;
import static com.petra.patch.api.MergeStrategy.SOURCE;
import static com.petra.patch.api.MergeStrategy.TARGET;
import static java.util.Collections.unmodifiableMap;

import com.petra.patch.api.CustomMerge;
import com.petra.patch.api.MergeFactory;
import com.petra.patch.api.MergeStrategy;
import com.petra.patch.api.context.MergeContext;
import com.petra.patch.api.facade.BasicMergeFacade;
import com.petra.patch.api.facade.CustomizableMergeFacade;
import com.petra.patch.impl.context.MergeContextImpl;
import com.petra.patch.impl.facade.BasicMergeFacadeMux;
import com.petra.patch.impl.facade.CustomizableMergeFacadeImpl;
import com.petra.patch.impl.facade.NotNullMergeFacade;
import com.petra.patch.impl.facade.SourceBasedMergeFacade;
import com.petra.patch.impl.facade.TargetBasedMergeFacade;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amarees on 2/28/16.
 */

// TODO: Allow annotation config
// TODO: Allow static field config via factory
// TODO: Allow dynamic field request config via facade

public class MergeFactoryImpl implements MergeFactory {

	private static final Map<MergeStrategy, BasicMergeFacade> facadeMap = new HashMap<>();

	static {
		facadeMap.put(SOURCE, new SourceBasedMergeFacade());
		facadeMap.put(TARGET, new TargetBasedMergeFacade());
		facadeMap.put(NOT_NULL, new NotNullMergeFacade(unmodifiableMap(facadeMap)));
	}

	private static Map<MergeStrategy, BasicMergeFacade> getFacadeMap() {
		return facadeMap;
	}

	/**
	 * A singleton instance for a static merge context
	 */
	private final MergeContext mergeContext = new MergeContextImpl();

	/**
	 * Creates a prototype instance of a CustomizableMergeFacade in order to merge two resources.
	 *
	 * @return
	 */
	public CustomizableMergeFacade facade() {
		return new CustomizableMergeFacadeImpl(mergeContext, new BasicMergeFacadeMux(unmodifiableMap(facadeMap)));
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
