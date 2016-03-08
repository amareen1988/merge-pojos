package com.petra.patch.api;

/**
 * Created by amarees on 3/5/16.
 */
public enum MergeStrategy {
	/**
	 * Returns the source always.
	 */
	SOURCE,

	/**
	 * Returns the target always.
	 */
	TARGET,

	/**
	 * A field in source overwrites its corresponding in target
	 * if the field in source is not null
	 */
	NOT_NULL,

	/**
	 * A field in source overwrites its corresponding in target
	 * if the field in source is not null and not empty
	 */
	NOT_NULL_NOT_EMPTY;
}
