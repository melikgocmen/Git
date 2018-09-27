package hu.bme.mit.swsv.ris.common;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Represents an immutable triple with three generic fields corresponding to the
 * three sides of a turnout: facing, straight and divergent.
 *
 * @param <T> Type of the fields.
 */
public final class SideTriple<T> {
	private final T facing;
	private final T straight;
	private final T divergent;

	private volatile int hashCode = 0;

	private SideTriple(final T facing, final T straight, final T divergent) {
		notNull(facing);
		notNull(straight);
		notNull(divergent);
		this.facing = facing;
		this.straight = straight;
		this.divergent = divergent;
	}

	/**
	 * Initializes a new triple with the three fields.
	 *
	 * @param facing Facing field
	 * @param straight Straight field
	 * @param divergent Divergent field
	 * @return A new triple
	 */
	public static <T> SideTriple<T> of(final T facing, final T straight, final T divergent) {
		return new SideTriple<>(facing, straight, divergent);
	}

	/**
	 * Gets the facing field.
	 *
	 * @return Facing field
	 */
	public T getFacing() {
		return facing;
	}

	/**
	 * Gets the straight field.
	 *
	 * @return Straight field
	 */
	public T getStraight() {
		return straight;
	}

	/**
	 * Gets the divergent field.
	 *
	 * @return Divergent field
	 */
	public T getDivergent() {
		return divergent;
	}

	/**
	 * Gets a field corresponding to a given side. If an unknown side is given,
	 * an IllegalArgumentException is thrown.
	 *
	 * @param side Side of the field to be get
	 * @return Field corresponding to the side
	 */
	public T get(final Side side) {
		switch (side) {
		case FACING:
			return facing;
		case STRAIGHT:
			return straight;
		case DIVERGENT:
			return divergent;
		default:
			throw new AssertionError("Unknown side: " + side + ".");
		}
	}

	/**
	 * Creates a copy of the triple and sets the facing field. The original
	 * triple is not modified.
	 *
	 * @param facing Facing field to set
	 * @return New triple with the facing field set
	 */
	public SideTriple<T> withFacing(final T facing) {
		if (this.facing == facing) {
			return this;
		} else {
			return of(facing, this.straight, this.divergent);
		}
	}

	/**
	 * Creates a copy of the triple and sets the straight field. The original
	 * triple is not modified.
	 *
	 * @param straight Straight field to set
	 * @return New triple with the straight field set
	 */
	public SideTriple<T> withStraight(final T straight) {
		if (this.straight == straight) {
			return this;
		} else {
			return of(this.facing, straight, this.divergent);
		}
	}

	/**
	 * Creates a copy of the triple and sets the divergent field. The original
	 * triple is not modified.
	 *
	 * @param divergent Divergent field to set
	 * @return New triple with the divergent field set
	 */
	public SideTriple<T> withDivergent(final T divergent) {
		if (this.divergent == divergent) {
			return this;
		} else {
			return of(this.facing, this.straight, divergent);
		}
	}

	/**
	 * Sets a field corresponding to a given side. If an unknown side is given,
	 * an IllegalArgumentException is thrown.
	 *
	 * @param side Side of the field to be set
	 * @param value Value to be set
	 */
	public SideTriple<T> with(final Side side, final T value) {
		switch (side) {
		case FACING:
			return withFacing(value);
		case STRAIGHT:
			return withStraight(value);
		case DIVERGENT:
			return withDivergent(value);
		default:
			throw new AssertionError("Unknown side: " + side + ".");
		}
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj instanceof SideTriple<?>) {
			final SideTriple<?> that = (SideTriple<?>) obj;
			return this.getFacing().equals(that.getFacing()) && this.getStraight().equals(that.getStraight())
					&& this.getDivergent().equals(that.getDivergent());
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int result = hashCode;
		if (result == 0) {
			result = (31 * facing.hashCode() + straight.hashCode()) * 31 + divergent.hashCode();
			hashCode = result;
		}
		return hashCode;
	}

	@Override
	public String toString() {
		return String.format("(Facing: %s, Straight: %s, Divergent: %s)", facing, straight, divergent);
	}
}