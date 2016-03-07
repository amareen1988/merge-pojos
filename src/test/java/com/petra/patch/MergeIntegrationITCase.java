package com.petra.patch;

import com.petra.patch.api.CustomMerge;
import com.petra.patch.api.MergeFactory;
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

	@Test
	public void mergeTwoPojos() {

		DummyPojo pojo1 = new DummyPojo();
		DummyPojo pojo2 = new DummyPojo();
		pojo2.setX(10);
		pojo2.setY("123");


		DummyPojo result = factory.customize(DummyPojo.class, new CustomMerge<DummyPojo>() {

			public DummyPojo merge(DummyPojo source, DummyPojo target) {
				return null;
			}
		}).facade().customize(DummyPojo.class, new CustomMerge<DummyPojo>() {

			public DummyPojo merge(DummyPojo source, DummyPojo target) {
				return null;
			}
		}).customize(DummyPojo.class, "x", new CustomMerge<Integer>() {

			public Integer merge(Integer source, Integer target) {
				return null;
			}
		}).merge(pojo1, pojo2);
		// TODO: A customized merge can still use strategies ?? Answer: NO
	}

	class DummyPojo {

		Integer x;
		String y;

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