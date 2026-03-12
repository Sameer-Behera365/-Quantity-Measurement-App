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

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

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
        QuantityLength oneYard = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength threeFeet = new QuantityLength(3.0, LengthUnit.FEET);
        System.out.println("1.0 yard == 3.0 feet: " + oneYard.equals(threeFeet));

        QuantityLength oneYard2 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength thirtySixInches = new QuantityLength(36.0, LengthUnit.INCHES);
        System.out.println("1.0 yard == 36.0 inches: " + oneYard2.equals(thirtySixInches));

        QuantityLength oneCm = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength cmInInches = new QuantityLength(0.393701, LengthUnit.INCHES);
        System.out.println("1.0 cm == 0.393701 inches: " + oneCm.equals(cmInInches));
    }
}