package com.petra.patch.impl.facade;

import com.petra.patch.api.facade.BasicMergeFacade;
import com.petra.patch.impl.MergeStrategy;

import java.util.Map;

/**
 * Created by amarees on 3/7/16.
 */
public class BasicMergeFacadeMux extends StrategyBasedMergeFacadeImpl {

	private final Map<MergeStrategy, BasicMergeFacade> facadeMap;

	public BasicMergeFacadeMux(Map<MergeStrategy, BasicMergeFacade> facadeMap) {
		this.facadeMap = facadeMap;
	}

	@Override
	public <T> T merge(T source, T target, MergeStrategy mergeStrategy) {
		final BasicMergeFacade basicMergeFacade = facadeMap.get(mergeStrategy);
		if (basicMergeFacade == null) {
			throw new RuntimeException("no merge facade found for: " + mergeStrategy);
		}
		return basicMergeFacade.merge(source, target);
	}
}
