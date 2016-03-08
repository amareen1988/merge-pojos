package com.petra.patch.impl.context;

import com.petra.patch.api.CustomMerge;
import com.petra.patch.api.context.MergeContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amarees on 3/7/16.
 */
public class MergeContextImpl implements MergeContext {

	private final Map<Class<?>, CustomMerge<?>> customMergeMap = new HashMap<>();
	private final Map<ClassField, CustomMerge> classFieldCustomMergeMap = new HashMap<>();

	public <T> void registerCustomMerge(Class<T> type, CustomMerge<T> customMerge) {
		register(type, customMerge);
	}

	public <T> void registerCustomMerge(Class<?> type, String fieldName, CustomMerge<T> customMerge) {
		final ClassField classField = classField(type, fieldName);
		register(customMerge, classField);
	}

	public CustomMerge lookupCustomMerge(Class<?> clazz) {
		return lookup(clazz);
	}

	private CustomMerge<?> lookup(Class<?> clazz) {
		return customMergeMap.get(clazz);
	}

	public CustomMerge lookupCustomMerge(Class<?> clazz, String fieldName) {
		return lookup(clazz, fieldName);
	}

	private CustomMerge lookup(Class<?> clazz, String fieldName) {
		return classFieldCustomMergeMap.get(new ClassField(clazz, fieldName));
	}

	private <T> void register(Class<T> type, CustomMerge<T> customMerge) {
		customMergeMap.put(type, customMerge);
	}

	private <T> void register(CustomMerge<T> customMerge, ClassField classField) {
		classFieldCustomMergeMap.put(classField, customMerge);
	}

	private ClassField classField(Class<?> type, String fieldName) {
		return new ClassField(type, fieldName);
	}

	class ClassField {

		private final String fieldName;

		private final Class<?> clazz;

		public ClassField(Class<?> clazz, String fieldName) {
			if (fieldName == null) {
				throw new IllegalArgumentException("fieldName is null");
			}
			if (clazz == null) {
				throw new IllegalArgumentException("clazz is null");
			}
			this.fieldName = fieldName;
			this.clazz = clazz;
		}

		public String getFieldName() {
			return fieldName;
		}

		public Class<?> getClazz() {
			return clazz;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof ClassField)) {
				return false;
			}
			ClassField that = (ClassField) obj;
			return this.getClazz().equals(that.getClazz()) && this.getFieldName().equals(that.getFieldName());
		}

		@Override
		public int hashCode() {
			return clazz.hashCode() + fieldName.hashCode();
		}
	}
}
