package com.quantitymeasurement;

/**
 * QuantityMeasurementApp - main application class.
 * QuantityLength delegates all conversion logic to LengthUnit (SRP).
 */
public class QuantityMeasurementApp {

    /**
     * Immutable length measurement.
     * Delegates all conversion to LengthUnit methods.
     */
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");
            this.value = value;
            this.unit = unit;
        }

        /**
         * Delegates to LengthUnit for base unit conversion.
         */
        private double toBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        /**
         * Converts this measurement to the target unit.
         */
        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
            double baseValue = unit.convertToBaseUnit(value);
            double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
            return new QuantityLength(convertedValue, targetUnit);
        }

        /**
         * Private utility - core addition logic for both add() overloads.
         */
        private QuantityLength addWithTargetUnit(QuantityLength other, LengthUnit targetUnit) {
            if (other == null) throw new IllegalArgumentException("Operand cannot be null");
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
            double sumInBase = this.toBaseUnit() + other.toBaseUnit();
            double resultValue = targetUnit.convertFromBaseUnit(sumInBase);
            return new QuantityLength(resultValue, targetUnit);
        }

        /**
         * UC6 - Add, result in unit of first operand.
         */
        public QuantityLength add(QuantityLength other) {
            return addWithTargetUnit(other, this.unit);
        }

        /**
         * UC7 - Add, result in explicitly specified target unit.
         */
        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            return addWithTargetUnit(other, targetUnit);
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
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

    // Static conversion API
    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        if (sourceUnit == null || targetUnit == null)
            throw new IllegalArgumentException("Source and target units cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be a finite number");
        return new QuantityLength(value, sourceUnit).convertTo(targetUnit).getValue();
    }

    // Static add - UC6 style (result in unit of first operand)
    public static QuantityLength add(QuantityLength length1, QuantityLength length2) {
        if (length1 == null || length2 == null)
            throw new IllegalArgumentException("Operands cannot be null");
        return length1.add(length2);
    }

    // Static add - UC7 style (explicit target unit)
    public static QuantityLength add(QuantityLength length1, QuantityLength length2, LengthUnit targetUnit) {
        if (length1 == null || length2 == null)
            throw new IllegalArgumentException("Operands cannot be null");
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");
        return length1.add(length2, targetUnit);
    }

    public static void demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        double result = convert(value, fromUnit, toUnit);
        System.out.printf("convert(%.4f %s -> %s) = %.4f%n", value, fromUnit, toUnit, result);
    }

    public static void demonstrateLengthConversion(QuantityLength quantity, LengthUnit toUnit) {
        System.out.printf("convert(%s -> %s) = %s%n", quantity, toUnit, quantity.convertTo(toUnit));
    }

    public static void demonstrateLengthEquality(QuantityLength q1, QuantityLength q2) {
        System.out.printf("equals(%s, %s) = %b%n", q1, q2, q1.equals(q2));
    }

    public static void main(String[] args) {
        System.out.println("--- UC8 Refactored Design ---");
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(new QuantityLength(1.0, LengthUnit.YARDS), LengthUnit.FEET);
        demonstrateLengthEquality(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES)
        );
        System.out.println(add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        ));
        System.out.println("INCHES.convertToBaseUnit(12.0) = " + LengthUnit.INCHES.convertToBaseUnit(12.0));
        System.out.println("INCHES.convertFromBaseUnit(1.0) = " + LengthUnit.INCHES.convertFromBaseUnit(1.0));
    }
}