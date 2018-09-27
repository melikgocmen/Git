package hu.bme.mit.swsv.ris.common;

import com.google.gson.annotations.SerializedName;

/**
 * Defines the possible sides of a turnout.
 */
public enum Side {
	@SerializedName("facing")
	FACING,

	@SerializedName("straight")
	STRAIGHT,

	@SerializedName("divergent")
	DIVERGENT
}
