package com.quantitymeasurement;

/**
 * Generic Quantity class - handles equality, conversion, addition,
 * subtraction and division for any IMeasurable unit type.
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

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(convertedValue, targetUnit);
    }

    // -------------------- ADDITION --------------------

    private Quantity<U> addWithTargetUnit(Quantity<U> other, U targetUnit) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double sumInBase = this.toBaseUnit() + other.toBaseUnit();
        double resultValue = targetUnit.convertFromBaseUnit(sumInBase);
        return new Quantity<>(resultValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return addWithTargetUnit(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        return addWithTargetUnit(other, targetUnit);
    }

    // -------------------- SUBTRACTION --------------------

    /**
     * Private utility - core subtraction logic reused by both subtract() overloads.
     */
    private Quantity<U> subtractWithTargetUnit(Quantity<U> other, U targetUnit) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cannot subtract different measurement categories");
        double differenceInBase = this.toBaseUnit() - other.toBaseUnit();
        double resultValue = targetUnit.convertFromBaseUnit(differenceInBase);
        return new Quantity<>(resultValue, targetUnit);
    }

    /**
     * Subtract - result in unit of first operand (implicit).
     */
    public Quantity<U> subtract(Quantity<U> other) {
        return subtractWithTargetUnit(other, this.unit);
    }

    /**
     * Subtract - result in explicitly specified target unit.
     */
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        return subtractWithTargetUnit(other, targetUnit);
    }

    // -------------------- DIVISION --------------------

    /**
     * Divide this quantity by another - returns dimensionless scalar.
     * Throws ArithmeticException if divisor is zero.
     */
    public double divide(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Cannot divide different measurement categories");
        double otherBase = other.toBaseUnit();
        if (Double.compare(otherBase, 0.0) == 0)
            throw new ArithmeticException("Division by zero is not allowed");
        return this.toBaseUnit() / otherBase;
    }

    public double getValue() { return value; }
    public U getUnit() { return unit; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Quantity<?> other = (Quantity<?>) obj;
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