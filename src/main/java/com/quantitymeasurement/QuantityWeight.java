package com.quantitymeasurement;

public class QuantityWeight {
    private static final double EPSILON = 1e-4;
    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");
        this.value = value;
        this.unit = unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new QuantityWeight(convertedValue, targetUnit);
    }

    private QuantityWeight addWithTargetUnit(QuantityWeight other, WeightUnit targetUnit) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double sumInBase = this.toBaseUnit() + other.toBaseUnit();
        double resultValue = targetUnit.convertFromBaseUnit(sumInBase);
        return new QuantityWeight(resultValue, targetUnit);
    }

    public QuantityWeight add(QuantityWeight other) {
        return addWithTargetUnit(other, this.unit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
        return addWithTargetUnit(other, targetUnit);
    }

    public double getValue() { return value; }
    public WeightUnit getUnit() { return unit; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityWeight other = (QuantityWeight) obj;
        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit.name();
    }
}