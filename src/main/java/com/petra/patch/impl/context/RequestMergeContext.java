package com.petra.patch.impl.context;

import com.petra.patch.api.CustomMerge;
import com.petra.patch.api.context.MergeContext;
import com.petra.patch.api.context.ReadOnlyMergeContext;

/**
 * Created by amarees on 3/7/16.
 */
public class RequestMergeContext implements MergeContext {

	private final ReadOnlyMergeContext readOnlyMergeContext;
	private final MergeContext higherPrecedenceMergeContext = new MergeContextImpl();

	public RequestMergeContext(ReadOnlyMergeContext readOnlyMergeContext) {
		this.readOnlyMergeContext = readOnlyMergeContext;
	}

	public <T> void registerCustomMerge(Class<T> type, CustomMerge<T> customMerge) {
		higherPrecedenceMergeContext.registerCustomMerge(type, customMerge);
	}

	public <T> void registerCustomMerge(Class<?> type, String fieldName, CustomMerge<T> customMerge) {
		higherPrecedenceMergeContext.registerCustomMerge(type, fieldName, customMerge);
	}

	public CustomMerge lookupCustomMerge(Class<?> clazz) {
		CustomMerge customMerge = higherPrecedenceMergeContext.lookupCustomMerge(clazz);
		if (customMerge == null) {
			customMerge = readOnlyMergeContext.lookupCustomMerge(clazz);
		}
		return customMerge;
	}

	public CustomMerge lookupCustomMerge(Class<?> clazz, String fieldName) {
		CustomMerge customMerge = higherPrecedenceMergeContext.lookupCustomMerge(clazz, fieldName);
		if (customMerge == null) {
			customMerge = readOnlyMergeContext.lookupCustomMerge(clazz, fieldName);
		}
		return customMerge;
	}
}
