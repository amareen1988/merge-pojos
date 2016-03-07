package com.petra.patch.impl;

/**
 * Created by amarees on 3/6/16.
 */
public class ClassField {

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
