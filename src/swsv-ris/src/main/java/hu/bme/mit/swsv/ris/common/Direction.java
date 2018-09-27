package hu.bme.mit.swsv.ris.common;

import com.google.gson.annotations.SerializedName;

/**
 * Defines the possible directions of a turnout.
 */
public enum Direction {
	@SerializedName("straight")
	STRAIGHT,

	@SerializedName("divergent")
	DIVERGENT
}
