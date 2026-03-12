package com.quantitymeasurement;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.393701 / 12.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    /**
     * Represents a length measurement with a value and unit.
     * Immutable value object - all operations return new instances.
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
         * Converts to base unit (feet) for internal comparison and conversion.
         */
        private double toBaseUnit() {
            return this.value * this.unit.getConversionFactor();
        }

        /**
         * Converts this measurement to the target unit and returns a new QuantityLength.
         */
        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
            double baseValue = this.toBaseUnit();
            double convertedValue = baseValue / targetUnit.getConversionFactor();
            return new QuantityLength(convertedValue, targetUnit);
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

    /**
     * Static conversion API - converts value from source unit to target unit.
     */
    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        if (sourceUnit == null || targetUnit == null)
            throw new IllegalArgumentException("Source and target units cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be a finite number");
        QuantityLength quantity = new QuantityLength(value, sourceUnit);
        return quantity.convertTo(targetUnit).getValue();
    }

    /**
     * Demonstrates conversion using raw value and units.
     * Overloaded method 1.
     */
    public static void demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        double result = convert(value, fromUnit, toUnit);
        System.out.printf("convert(%.4f %s -> %s) = %.4f%n", value, fromUnit, toUnit, result);
    }

    /**
     * Demonstrates conversion using an existing QuantityLength object.
     * Overloaded method 2.
     */
    public static void demonstrateLengthConversion(QuantityLength quantity, LengthUnit toUnit) {
        QuantityLength result = quantity.convertTo(toUnit);
        System.out.printf("convert(%s -> %s) = %s%n", quantity, toUnit, result);
    }

    /**
     * Demonstrates equality check between two QuantityLength objects.
     */
    public static void demonstrateLengthEquality(QuantityLength q1, QuantityLength q2) {
        System.out.printf("equals(%s, %s) = %b%n", q1, q2, q1.equals(q2));
    }

    /**
     * Demonstrates comparison using raw values and units.
     */
    public static void demonstrateLengthComparison(double value1, LengthUnit unit1,
                                                   double value2, LengthUnit unit2) {
        QuantityLength q1 = new QuantityLength(value1, unit1);
        QuantityLength q2 = new QuantityLength(value2, unit2);
        demonstrateLengthEquality(q1, q2);
    }

    public static void main(String[] args) {
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES);

        QuantityLength lengthInYards = new QuantityLength(1.0, LengthUnit.YARDS);
        demonstrateLengthConversion(lengthInYards, LengthUnit.INCHES);

        demonstrateLengthComparison(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES);
    }
}