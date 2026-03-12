package com.quantitymeasurement;

public class QuantityMeasurementApp {

    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Feet feet = (Feet) obj;
            return Double.compare(this.value, feet.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Inches inches = (Inches) obj;
            return Double.compare(this.value, inches.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // Static method for Feet equality check
    public static boolean checkFeetEquality(double value1, double value2) {
        Feet feet1 = new Feet(value1);
        Feet feet2 = new Feet(value2);
        return feet1.equals(feet2);
    }

    // Static method for Inches equality check
    public static boolean checkInchesEquality(double value1, double value2) {
        Inches inches1 = new Inches(value1);
        Inches inches2 = new Inches(value2);
        return inches1.equals(inches2);
    }

    public static void main(String[] args) {
        System.out.println("Comparing 1.0 ft and 1.0 ft: " + checkFeetEquality(1.0, 1.0));
        System.out.println("Comparing 1.0 ft and 2.0 ft: " + checkFeetEquality(1.0, 2.0));
        System.out.println("Comparing 1.0 inch and 1.0 inch: " + checkInchesEquality(1.0, 1.0));
        System.out.println("Comparing 1.0 inch and 2.0 inch: " + checkInchesEquality(1.0, 2.0));
    }
}