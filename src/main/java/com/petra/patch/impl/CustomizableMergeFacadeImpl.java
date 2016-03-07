package com.petra.patch.impl;

import com.petra.patch.MergeStrategy;
import com.petra.patch.api.CustomMerge;
import com.petra.patch.api.CustomizableMergeFacade;
import com.petra.patch.api.StrategyBasedMergeFacade;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amarees on 3/5/16.
 */

// TODO: Should have two instances, decorating one another
// TODO: Once instance should be static context, another should be dynamic context
public class CustomizableMergeFacadeImpl implements CustomizableMergeFacade {

	private final StrategyBasedMergeFacade mergeFacade;
	private final Map<Class<?>, CustomMerge<?>> customMergeMap = new HashMap<Class<?>, CustomMerge<?>>();
	private final Map<ClassField, CustomMerge> classFieldCustomMergeMap = new HashMap<ClassField, CustomMerge>();

	public CustomizableMergeFacadeImpl(final StrategyBasedMergeFacade mergeFacade) {
		this.mergeFacade = mergeFacade;
	}

	public <T> T merge(T source, T target) {
		Class<?> clazz = extractType(source, target);
		final CustomMerge customMerge = lookupCustomMerge(clazz);
		if (customMerge == null) {
			return mergeFacade.merge(source, target);
		}
		return (T) customMerge.merge(source, target);
	}

	public <T> T merge(T source, T target, MergeStrategy mergeStrategy) {
		Class<?> clazz = extractType(source, target);
		final CustomMerge customMerge = lookupCustomMerge(clazz);
		if (customMerge == null) {
			return mergeFacade.merge(source, target, mergeStrategy);
		}
		// Ignore merge strategy if custom merge found
		return (T) customMerge.merge(source, target);
	}

	private CustomMerge lookupCustomMerge(Class<?> clazz) {
		return customMergeMap.get(clazz);
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
		register(type, customMerge);
		return this;
	}

	private <T> void register(Class<T> type, CustomMerge<T> customMerge) {
		customMergeMap.put(type, customMerge);
	}

	public <T> CustomizableMergeFacade customize(Class<?> type, String fieldName, CustomMerge<T> customMerge) {
		final ClassField classField = classField(type, fieldName);
		register(customMerge, classField);
		return this;
	}

	private <T> void register(CustomMerge<T> customMerge, ClassField classField) {
		classFieldCustomMergeMap.put(classField, customMerge);
	}

	private ClassField classField(Class<?> type, String fieldName) {
		return new ClassField(type, fieldName);
	}

	public StrategyBasedMergeFacade getMergeFacade() {
		return mergeFacade;
	}
}
