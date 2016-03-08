package com.petra.patch.impl.facade;

import com.petra.patch.api.CustomMerge;
import com.petra.patch.api.MergeStrategy;
import com.petra.patch.api.context.MergeContext;
import com.petra.patch.api.context.ReadOnlyMergeContext;
import com.petra.patch.api.facade.CustomizableMergeFacade;
import com.petra.patch.api.facade.StrategyBasedMergeFacade;

/**
 * Created by amarees on 3/5/16.
 */

// TODO: Should have two instances, decorating one another
// TODO: Once instance should be static context, another should be dynamic context
public class CustomizableMergeFacadeImpl implements CustomizableMergeFacade {

	private final StrategyBasedMergeFacade mergeFacade;

	private final MergeContext mergeContext;

	public CustomizableMergeFacadeImpl(MergeContext mergeContext, final StrategyBasedMergeFacade mergeFacade) {
		this.mergeFacade = mergeFacade;
		this.mergeContext = mergeContext;
	}

	public <T> T merge(T source, T target) {
		if (source == null && target == null) {
			return null;
		}
		Class<?> clazz = extractType(source, target);
		final CustomMerge customMerge = lookupCustomMerge(clazz);
		if (customMerge == null) {
			return mergeFacade.merge(source, target);
		}
		return (T) customMerge.merge(source, target);
	}

	public <T> T merge(T source, T target, MergeStrategy mergeStrategy) {
		if (source == null && target == null) {
			return null;
		}
		Class<?> clazz = extractType(source, target);
		final CustomMerge customMerge = lookupCustomMerge(clazz);
		if (customMerge == null) {
			return mergeFacade.merge(source, target, mergeStrategy);
		}
		// Ignore merge strategy if custom merge found
		return (T) customMerge.merge(source, target);
	}

	private CustomMerge lookupCustomMerge(Class<?> clazz) {
		return mergeContext.lookupCustomMerge(clazz);
		//
	}

	private <T> Class<?> extractType(T source, T target) {
		if (source != null) {
			return source.getClass();
		}
		if (target != null) {
			return target.getClass();
		}
		throw new RuntimeException("Both source and target are null");
	}

	public <T> CustomizableMergeFacade customize(Class<T> type, CustomMerge<T> customMerge) {
		mergeContext.registerCustomMerge(type, customMerge);

		return this;
	}


	public <T> CustomizableMergeFacade customize(Class<?> type, String fieldName, CustomMerge<T> customMerge) {
		mergeContext.registerCustomMerge(type, fieldName, customMerge);
		return this;
	}

	public StrategyBasedMergeFacade getMergeFacade() {
		return mergeFacade;
	}

	public ReadOnlyMergeContext getMergeContext() {
		return mergeContext;
	}
}
