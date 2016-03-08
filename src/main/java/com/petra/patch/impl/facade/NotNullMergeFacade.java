package com.petra.patch.impl.facade;

import static com.petra.patch.impl.MergeStrategy.SOURCE;
import static com.petra.patch.impl.MergeStrategy.TARGET;
import static java.util.Arrays.asList;

import com.petra.patch.api.facade.BasicMergeFacade;
import com.petra.patch.impl.MergeFactoryImpl;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by amarees on 3/7/16.
 */
public class NotNullMergeFacade implements BasicMergeFacade {

	public <T> T merge(T source, T target) {
		// TODO: find a way to either clone target or instantiate an instance.
		if (source == null && target == null) {
			return null;
		}

		if (source == null) {
			return MergeFactoryImpl.getFacadeMap().get(TARGET).merge(source, target);
		}

		if (target == null) {
			return MergeFactoryImpl.getFacadeMap().get(SOURCE).merge(source, target);
		}

		Set<Field> fields = getAllFieldsForClass(source.getClass());
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				final Object fieldValue = field.get(source);
				if (fieldValue != null) {
					field.set(target, fieldValue);
				}
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Failed while attempting to copy value from source to target", e);
			}
		}
		return target;
	}

	public Set<Field> getAllFieldsForClass(Class<?> clazz) {
		if (clazz == null) {
			return Collections.emptySet();
		}
		Set<Field> allFields = new HashSet<>();
		// Get parent's fields and ignore synthetic
		Set<Field> parentFields = getAllFieldsForClass(clazz.getSuperclass()).stream().filter(field -> !(field.isSynthetic()))
				.collect(Collectors.toSet());
		allFields.addAll(parentFields);

		// Get declared fields and ignore synthetic
		final Set<Field> declaredFields = asList(clazz.getDeclaredFields()).stream().filter(field -> !(field.isSynthetic()))
				.collect(Collectors.toSet());
		allFields.addAll(declaredFields);
		return allFields;
	}
}
