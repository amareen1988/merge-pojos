package com.petra.patch.impl;

import static com.petra.patch.api.MergeStrategy.NOT_NULL;
import static com.petra.patch.api.MergeStrategy.NOT_NULL_NOT_EMPTY;
import static com.petra.patch.api.MergeStrategy.SOURCE;
import static com.petra.patch.api.MergeStrategy.TARGET;

import com.petra.patch.api.CustomMerge;
import com.petra.patch.api.MergeFactory;
import com.petra.patch.api.facade.CustomizableMergeFacade;
import com.petra.patch.impl.MergeFactoryImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by amarees on 2/28/16.
 */
public class MergeIntegrationITCase {

	MergeFactory factory;

	@Before
	public void setUp() {
		factory = new MergeFactoryImpl();
	}

	// TODO: A customized merge can still use strategies ?? Answer: NO
	@Test
	public void customizedViaFactoryMergeTwoPojos() {

		DummyPojo pojo1 = new DummyPojo();
		DummyPojo pojo2 = new DummyPojo();

		DummyPojo result = factory.customize(DummyPojo.class, new CustomMerge<DummyPojo>() {

			public DummyPojo merge(DummyPojo source, DummyPojo target) {
				return new DummyPojo().setY("This is Y").setX(1000);
			}
		}).facade().merge(pojo1, pojo2);

		assert result.getX().equals(1000);
		assert result.getY().equalsIgnoreCase("This is Y");
	}

	@Test
	public void customizedViaFacadeMergeTwoPojos() {

		DummyPojo pojo1 = new DummyPojo();
		DummyPojo pojo2 = new DummyPojo();

		DummyPojo result = factory.facade().customize(DummyPojo.class, new CustomMerge<DummyPojo>() {

			public DummyPojo merge(DummyPojo source, DummyPojo target) {
				return new DummyPojo().setY("This is Y").setX(1000);
			}
		}).merge(pojo1, pojo2);

		assert result.getX().equals(1000);
		assert result.getY().equalsIgnoreCase("This is Y");
	}

	@Test
	public void mergeTwoPojosWithSourceStrategyNoCustomization() {

		DummyPojo pojo1 = new DummyPojo();
		DummyPojo pojo2 = new DummyPojo();
		pojo1.setX(1000);
		pojo1.setY("This is Y");

		DummyPojo result = factory.facade().merge(pojo1, pojo2, SOURCE);

		assert result.getX().equals(1000);
		assert result.getY().equalsIgnoreCase("This is Y");
	}

	@Test
	public void mergeTwoPojosWithTargetStrategyNoCustomization() {

		DummyPojo pojo1 = new DummyPojo();
		DummyPojo pojo2 = new DummyPojo();
		pojo2.setX(1000);
		pojo2.setY("This is Y");

		DummyPojo result = factory.facade().merge(pojo1, pojo2, TARGET);

		assert result.getX().equals(1000);
		assert result.getY().equalsIgnoreCase("This is Y");
	}

	@Test
	public void twoFacadeInstancesAreNotEqual() {
		CustomizableMergeFacade facade1 = factory.facade();
		CustomizableMergeFacade facade2 = factory.facade();
		assert !facade1.equals(facade2);
	}

	@Test
	public void twoFacadeInstancesHaveTheSameInitialMergeContext() {
		CustomizableMergeFacade facade1 = factory.facade();
		CustomizableMergeFacade facade2 = factory.facade();
		assert facade1.getMergeContext().equals(facade2.getMergeContext());
	}

	@Test
	public void mergeSourceToTargetNotNullStrategyBothObjectsNotNull() {
		DummyPojo pojo1 = new DummyPojo();
		DummyPojo pojo2 = new DummyPojo();
		pojo1.setX(1000);
		pojo1.setY("This is X");

		DummyPojo result = factory.facade().merge(pojo1, pojo2, NOT_NULL);

		assert result.getX().equals(1000);
		assert result.getY().equalsIgnoreCase("This is X");
	}

	@Test
	public void mergeSourceToTargetNotNullStrategyTargetIsNull() {
		DummyPojo pojo1 = new DummyPojo();
		DummyPojo pojo2 = null;
		pojo1.setX(1000);
		pojo1.setY("This is X");

		DummyPojo result = factory.facade().merge(pojo1, pojo2, NOT_NULL);

		assert result.getX().equals(1000);
		assert result.getY().equalsIgnoreCase("This is X");
	}

	@Test
	public void mergeSourceToTargetNotNullStrategySourceIsNull() {
		DummyPojo pojo1 = null;
		DummyPojo pojo2 = new DummyPojo();
		pojo2.setX(1000);
		pojo2.setY("This is X");

		DummyPojo result = factory.facade().merge(pojo1, pojo2, NOT_NULL);

		assert result.getX().equals(1000);
		assert result.getY().equalsIgnoreCase("This is X");
	}

	@Test
	public void mergeSourceToTargetNotNullStrategyBothAreNull() {
		DummyPojo pojo1 = null;
		DummyPojo pojo2 = null;

		DummyPojo result = factory.facade().merge(pojo1, pojo2, NOT_NULL);

		assert result == null;
	}

	@Test
	public void mergeSourceToTargetNoStrategyBothAreNull() {
		DummyPojo pojo1 = null;
		DummyPojo pojo2 = null;

		DummyPojo result = factory.facade().merge(pojo1, pojo2);

		assert result == null;
	}

	@Test
	public void mergeSourceToTargetWithTargetStrategyBothAreNull() {
		DummyPojo pojo1 = null;
		DummyPojo pojo2 = null;

		DummyPojo result = factory.facade().merge(pojo1, pojo2, TARGET);

		assert result == null;
	}

	@Test
	public void mergeSourceToTargetWithSourceStrategyBothAreNull() {
		DummyPojo pojo1 = null;
		DummyPojo pojo2 = null;

		DummyPojo result = factory.facade().merge(pojo1, pojo2, SOURCE);

		assert result == null;
	}

	@Test
	public void mergeSourceToTargetWithNotNullNotEmptyStrategyBothAreNull() {
		DummyPojo pojo1 = null;
		DummyPojo pojo2 = null;

		DummyPojo result = factory.facade().merge(pojo1, pojo2, NOT_NULL_NOT_EMPTY);

		assert result == null;
	}

	class DummyPojo {

		private Integer x;
		private String y;

		public Integer getX() {
			return x;
		}

		public DummyPojo setX(Integer x) {
			this.x = x;
			return this;
		}

		public String getY() {
			return y;
		}

		public DummyPojo setY(String y) {
			this.y = y;
			return this;
		}
	}
}