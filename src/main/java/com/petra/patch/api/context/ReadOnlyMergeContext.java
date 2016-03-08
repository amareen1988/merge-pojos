package com.petra.patch.api.context;

import com.petra.patch.api.CustomMerge;
import com.petra.patch.api.MergeStrategy;

import java.lang.reflect.Field;

/**
 * Created by amarees on 3/7/16.
 */
public interface ReadOnlyMergeContext {

	CustomMerge lookupCustomMerge(Class<?> clazz);

	CustomMerge lookupCustomMerge(Class<?> clazz, String fieldName);

	/*Boolean isMergeableResource(Field field);

	Boolean isMergeID(Field field);

	MergeStrategy getMergeStrategy(Field field);*/
}
