package com.quantitymeasurement;

public class QuantityMeasurementApp {

    // ==================== LENGTH SECTION (UC1-UC8) ====================

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
            return unit.convertToBaseUnit(value);
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
            double baseValue = unit.convertToBaseUnit(value);
            double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
            return new QuantityLength(convertedValue, targetUnit);
        }

        private QuantityLength addWithTargetUnit(QuantityLength other, LengthUnit targetUnit) {
            if (other == null) throw new IllegalArgumentException("Operand cannot be null");
            if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
            double sumInBase = this.toBaseUnit() + other.toBaseUnit();
            double resultValue = targetUnit.convertFromBaseUnit(sumInBase);
            return new QuantityLength(resultValue, targetUnit);
        }

        public QuantityLength add(QuantityLength other) {
            return addWithTargetUnit(other, this.unit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            return addWithTargetUnit(other, targetUnit);
        }

        public double getValue() { return value; }
        public LengthUnit getUnit() { return unit; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public int hashCode() { return Double.hashCode(toBaseUnit()); }

        @Override
        public String toString() { return value + " " + unit.name(); }
    }

    // Static length API
    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        if (sourceUnit == null || targetUnit == null)
            throw new IllegalArgumentException("Source and target units cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be a finite number");
        return new QuantityLength(value, sourceUnit).convertTo(targetUnit).getValue();
    }

    public static QuantityLength add(QuantityLength l1, QuantityLength l2) {
        if (l1 == null || l2 == null) throw new IllegalArgumentException("Operands cannot be null");
        return l1.add(l2);
    }

    public static QuantityLength add(QuantityLength l1, QuantityLength l2, LengthUnit targetUnit) {
        if (l1 == null || l2 == null) throw new IllegalArgumentException("Operands cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        return l1.add(l2, targetUnit);
    }

    // ==================== WEIGHT SECTION (UC9) ====================

    public static double convert(double value, WeightUnit sourceUnit, WeightUnit targetUnit) {
        if (sourceUnit == null || targetUnit == null)
            throw new IllegalArgumentException("Source and target units cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be a finite number");
        return new QuantityWeight(value, sourceUnit).convertTo(targetUnit).getValue();
    }

    public static QuantityWeight add(QuantityWeight w1, QuantityWeight w2) {
        if (w1 == null || w2 == null) throw new IllegalArgumentException("Operands cannot be null");
        return w1.add(w2);
    }

    public static QuantityWeight add(QuantityWeight w1, QuantityWeight w2, WeightUnit targetUnit) {
        if (w1 == null || w2 == null) throw new IllegalArgumentException("Operands cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        return w1.add(w2, targetUnit);
    }

    public static void demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        System.out.printf("convert(%.4f %s -> %s) = %.4f%n",
                value, fromUnit, toUnit, convert(value, fromUnit, toUnit));
    }

    public static void demonstrateLengthConversion(QuantityLength quantity, LengthUnit toUnit) {
        System.out.printf("convert(%s -> %s) = %s%n", quantity, toUnit, quantity.convertTo(toUnit));
    }

    public static void demonstrateLengthEquality(QuantityLength q1, QuantityLength q2) {
        System.out.printf("equals(%s, %s) = %b%n", q1, q2, q1.equals(q2));
    }

    public static void main(String[] args) {
        System.out.println("--- Length (UC1-UC8) ---");
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);

        System.out.println("--- Weight (UC9) ---");
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight grams = new QuantityWeight(1000.0, WeightUnit.GRAM);
        System.out.println("1 kg == 1000 g: " + kg.equals(grams));
        System.out.println("1 kg convertTo GRAM: " + kg.convertTo(WeightUnit.GRAM));
        System.out.println("1 kg + 1000 g: " + add(kg, grams));
        System.out.println("1 kg + 1000 g in GRAM: " + add(kg, grams, WeightUnit.GRAM));
    }
}