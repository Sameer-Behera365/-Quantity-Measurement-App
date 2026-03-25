package com.quantitymeasurement;

/**
 * Generic Quantity class - replaces QuantityLength and QuantityWeight.
 * Works with any IMeasurable unit type.
 * Single Responsibility: handles value comparison, conversion, and addition.
 */
public class Quantity<U extends IMeasurable> {
    private static final double EPSILON = 1e-4;
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");
        this.value = value;
        this.unit = unit;
    }

    /**
     * Converts to base unit using the unit's conversion method.
     */
    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    /**
     * Converts this quantity to the target unit.
     * Returns a new Quantity instance (immutability).
     */
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(convertedValue, targetUnit);
    }

    /**
     * Private utility - core addition logic reused by both add() overloads.
     */
    private Quantity<U> addWithTargetUnit(Quantity<U> other, U targetUnit) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double sumInBase = this.toBaseUnit() + other.toBaseUnit();
        double resultValue = targetUnit.convertFromBaseUnit(sumInBase);
        return new Quantity<>(resultValue, targetUnit);
    }

    /**
     * Add - result in unit of first operand.
     */
    public Quantity<U> add(Quantity<U> other) {
        return addWithTargetUnit(other, this.unit);
    }

    /**
     * Add - result in explicitly specified target unit.
     */
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        return addWithTargetUnit(other, targetUnit);
    }

    public double getValue() { return value; }
    public U getUnit() { return unit; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Quantity<?> other = (Quantity<?>) obj;
        // Cross-category prevention - unit class must match
        if (!this.unit.getClass().equals(other.unit.getClass())) return false;
        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}