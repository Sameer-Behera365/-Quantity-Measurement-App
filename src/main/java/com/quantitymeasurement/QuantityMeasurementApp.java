package com.quantitymeasurement;

public class QuantityMeasurementApp {

    // LengthUnit Enum - defines all supported units with conversion factors to base unit (feet)
    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // Single Generic QuantityLength class - replaces separate Feet and Inches classes
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (feet) for comparison
        private double toBaseUnit() {
            return this.value * this.unit.getConversionFactor();
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
    }

    public static void main(String[] args) {
        QuantityLength oneFoot = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength twelveInches = new QuantityLength(12.0, LengthUnit.INCHES);
        System.out.println("1.0 feet == 12.0 inches: " + oneFoot.equals(twelveInches));

        QuantityLength oneInch1 = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength oneInch2 = new QuantityLength(1.0, LengthUnit.INCHES);
        System.out.println("1.0 inch == 1.0 inch: " + oneInch1.equals(oneInch2));
    }
}