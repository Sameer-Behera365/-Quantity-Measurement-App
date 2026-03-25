package com.quantitymeasurement;

import java.util.function.DoubleBinaryOperator;

/**
 * Generic Quantity class - handles equality, conversion, addition,
 * subtraction and division for any IMeasurable unit type.
 * UC13: Centralized arithmetic logic via ArithmeticOperation enum and helper methods.
 */
public class Quantity<U extends IMeasurable> {
    private static final double EPSILON = 1e-4;
    private final double value;
    private final U unit;

    // ==================== ARITHMETIC OPERATION ENUM ====================

    /**
     * Private enum using lambda expressions (DoubleBinaryOperator) to
     * dispatch arithmetic operations. Clean, scalable, DRY.
     */
    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (Double.compare(b, 0.0) == 0)
                throw new ArithmeticException("Division by zero is not allowed");
            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public double compute(double a, double b) {
            return operator.applyAsDouble(a, b);
        }
    }

    // ==================== CONSTRUCTOR ====================

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");
        this.value = value;
        this.unit = unit;
    }

    // ==================== PRIVATE HELPERS ====================

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    /**
     * Centralized validation helper - used by all arithmetic operations.
     * Validates null, category compatibility, and target unit if required.
     */
    private void validateArithmeticOperands(Quantity<U> other,
                                            U targetUnit,
                                            boolean targetUnitRequired) {
        if (other == null)
            throw new IllegalArgumentException("Operand cannot be null");
        if (!this.unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException(
                    "Cannot perform arithmetic on different measurement categories");
        if (!Double.isFinite(other.value))
            throw new IllegalArgumentException("Operand value must be finite");
        if (targetUnitRequired && targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
    }

    /**
     * Core arithmetic helper - converts both operands to base unit,
     * performs operation using enum dispatch, returns base-unit result.
     */
    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        double thisBase = this.toBaseUnit();
        double otherBase = other.toBaseUnit();
        return operation.compute(thisBase, otherBase);
    }

    /**
     * Rounds result to two decimal places for consistency.
     */
    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    // ==================== CONVERSION ====================

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(convertedValue, targetUnit);
    }

    // ==================== ADDITION ====================

    public Quantity<U> add(Quantity<U> other) {
        validateArithmeticOperands(other, this.unit, false);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double resultValue = roundToTwoDecimals(
                this.unit.convertFromBaseUnit(baseResult));
        return new Quantity<>(resultValue, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double resultValue = roundToTwoDecimals(
                targetUnit.convertFromBaseUnit(baseResult));
        return new Quantity<>(resultValue, targetUnit);
    }

    // ==================== SUBTRACTION ====================

    public Quantity<U> subtract(Quantity<U> other) {
        validateArithmeticOperands(other, this.unit, false);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double resultValue = roundToTwoDecimals(
                this.unit.convertFromBaseUnit(baseResult));
        return new Quantity<>(resultValue, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double resultValue = roundToTwoDecimals(
                targetUnit.convertFromBaseUnit(baseResult));
        return new Quantity<>(resultValue, targetUnit);
    }

    // ==================== DIVISION ====================

    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    // ==================== GETTERS ====================

    public double getValue() { return value; }
    public U getUnit() { return unit; }

    // ==================== OBJECT METHODS ====================

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