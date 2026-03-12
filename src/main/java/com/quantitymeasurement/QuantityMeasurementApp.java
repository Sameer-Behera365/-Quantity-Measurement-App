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
     * Represents an immutable length measurement with a value and unit.
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

        private double toBaseUnit() {
            return this.value * this.unit.getConversionFactor();
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
            double convertedValue = this.toBaseUnit() / targetUnit.getConversionFactor();
            return new QuantityLength(convertedValue, targetUnit);
        }

        /**
         * Adds another QuantityLength to this one.
         * Result is expressed in the unit of this (first) operand.
         */
        public QuantityLength add(QuantityLength other) {
            if (other == null) throw new IllegalArgumentException("Operand cannot be null");
            double sumInBase = this.toBaseUnit() + other.toBaseUnit();
            double resultValue = sumInBase / this.unit.getConversionFactor();
            return new QuantityLength(resultValue, this.unit);
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
     * Static conversion API.
     */
    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        if (sourceUnit == null || targetUnit == null)
            throw new IllegalArgumentException("Source and target units cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be a finite number");
        return new QuantityLength(value, sourceUnit).convertTo(targetUnit).getValue();
    }

    /**
     * Static addition API - overload 1: accepts two QuantityLength objects.
     * Result is in unit of first operand.
     */
    public static QuantityLength add(QuantityLength length1, QuantityLength length2) {
        if (length1 == null || length2 == null)
            throw new IllegalArgumentException("Operands cannot be null");
        return length1.add(length2);
    }

    /**
     * Static addition API - overload 2: accepts raw values and units.
     * Result is in unit of first operand.
     */
    public static QuantityLength add(double value1, LengthUnit unit1,
                                     double value2, LengthUnit unit2) {
        QuantityLength l1 = new QuantityLength(value1, unit1);
        QuantityLength l2 = new QuantityLength(value2, unit2);
        return l1.add(l2);
    }

    public static void demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        double result = convert(value, fromUnit, toUnit);
        System.out.printf("convert(%.4f %s -> %s) = %.4f%n", value, fromUnit, toUnit, result);
    }

    public static void demonstrateLengthConversion(QuantityLength quantity, LengthUnit toUnit) {
        QuantityLength result = quantity.convertTo(toUnit);
        System.out.printf("convert(%s -> %s) = %s%n", quantity, toUnit, result);
    }

    public static void demonstrateLengthEquality(QuantityLength q1, QuantityLength q2) {
        System.out.printf("equals(%s, %s) = %b%n", q1, q2, q1.equals(q2));
    }

    public static void demonstrateLengthComparison(double value1, LengthUnit unit1,
                                                   double value2, LengthUnit unit2) {
        demonstrateLengthEquality(
                new QuantityLength(value1, unit1),
                new QuantityLength(value2, unit2)
        );
    }

    public static void main(String[] args) {
        System.out.println("--- Addition Examples ---");
        System.out.println(add(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(2.0, LengthUnit.FEET)));
        System.out.println(add(new QuantityLength(1.0, LengthUnit.FEET), new QuantityLength(12.0, LengthUnit.INCHES)));
        System.out.println(add(new QuantityLength(12.0, LengthUnit.INCHES), new QuantityLength(1.0, LengthUnit.FEET)));
        System.out.println(add(new QuantityLength(1.0, LengthUnit.YARDS), new QuantityLength(3.0, LengthUnit.FEET)));
        System.out.println(add(new QuantityLength(2.54, LengthUnit.CENTIMETERS), new QuantityLength(1.0, LengthUnit.INCHES)));
    }
}