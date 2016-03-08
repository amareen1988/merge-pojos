package com.petra.patch.impl.facade;

import static com.petra.patch.api.MergeStrategy.SOURCE;
import static com.petra.patch.api.MergeStrategy.TARGET;
import static java.util.Arrays.asList;

import com.petra.patch.api.MergeStrategy;
import com.petra.patch.api.facade.BasicMergeFacade;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by amarees on 3/7/16.
 */
public class NotNullMergeFacade implements BasicMergeFacade {

	private final Map<MergeStrategy, BasicMergeFacade> facadeMap;

	public NotNullMergeFacade(Map<MergeStrategy, BasicMergeFacade> facadeMap) {
		this.facadeMap = facadeMap;
	}

	public <T> T merge(T source, T target) {
		// TODO: find a way to either clone target or instantiate an instance.

		if (source == null) {
			return lookupFacade(TARGET).merge(source, target);
		}

		if (target == null) {
			return lookupFacade(SOURCE).merge(source, target);
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

	private BasicMergeFacade lookupFacade(MergeStrategy strategy) {
		final BasicMergeFacade facade = getFacadeMap().get(strategy);
		if (facade == null) {
			throw new RuntimeException("No merge facade found for strategy: " + strategy);
		}
		return facade;
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

	public Map<MergeStrategy, BasicMergeFacade> getFacadeMap() {
		return facadeMap;
	}
}
