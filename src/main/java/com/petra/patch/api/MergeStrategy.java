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
	SOURCE_NOT_NULL,

	/**
	 * A field in source overwrites its corresponding in target
	 * if the field in source is not null and not empty
	 */
	SOURCE_NOT_NULL_NOT_EMPTY,

	/**
	 * A field in source overwrites its corresponding in target
	 * if the field in target is null
	 */
	TARGET_IS_NULL,

	/**
	 * A field in source overwrites its corresponding in target
	 * if the field in target is  null or empty
	 */
	TARGET_IS_NULL_OR_EMPTY,


}
