package com.quantitymeasurement;

/**
 * QuantityMeasurementApp - simplified orchestration class.
 * Generic methods handle all measurement categories uniformly.
 */
public class QuantityMeasurementApp {

    public static <U extends IMeasurable> void demonstrateEquality(
            Quantity<U> q1, Quantity<U> q2) {
        System.out.printf("equals(%s, %s) = %b%n", q1, q2, q1.equals(q2));
    }

    public static <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> quantity, U targetUnit) {
        Quantity<U> result = quantity.convertTo(targetUnit);
        System.out.printf("convert(%s -> %s) = %s%n",
                quantity, targetUnit.getUnitName(), result);
    }

    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        Quantity<U> result = q1.add(q2, targetUnit);
        System.out.printf("add(%s, %s) in %s = %s%n",
                q1, q2, targetUnit.getUnitName(), result);
    }

    public static <U extends IMeasurable> void demonstrateSubtraction(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        Quantity<U> result = q1.subtract(q2, targetUnit);
        System.out.printf("subtract(%s, %s) in %s = %s%n",
                q1, q2, targetUnit.getUnitName(), result);
    }

    public static <U extends IMeasurable> void demonstrateDivision(
            Quantity<U> q1, Quantity<U> q2) {
        double result = q1.divide(q2);
        System.out.printf("divide(%s / %s) = %f%n", q1, q2, result);
    }

    public static void main(String[] args) {
        System.out.println("--- Length Operations ---");
        Quantity<LengthUnit> oneFoot = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, LengthUnit.INCHES);
        demonstrateEquality(oneFoot, twelveInches);
        demonstrateConversion(oneFoot, LengthUnit.INCHES);
        demonstrateAddition(oneFoot, twelveInches, LengthUnit.FEET);

        System.out.println("--- Subtraction (UC12) ---");
        demonstrateSubtraction(
                new Quantity<>(10.0, LengthUnit.FEET),
                new Quantity<>(6.0, LengthUnit.INCHES),
                LengthUnit.FEET);
        demonstrateSubtraction(
                new Quantity<>(10.0, WeightUnit.KILOGRAM),
                new Quantity<>(5000.0, WeightUnit.GRAM),
                WeightUnit.KILOGRAM);
        demonstrateSubtraction(
                new Quantity<>(5.0, VolumeUnit.LITRE),
                new Quantity<>(500.0, VolumeUnit.MILLILITRE),
                VolumeUnit.LITRE);

        System.out.println("--- Division (UC12) ---");
        demonstrateDivision(
                new Quantity<>(10.0, LengthUnit.FEET),
                new Quantity<>(2.0, LengthUnit.FEET));
        demonstrateDivision(
                new Quantity<>(24.0, LengthUnit.INCHES),
                new Quantity<>(2.0, LengthUnit.FEET));
        demonstrateDivision(
                new Quantity<>(10.0, WeightUnit.KILOGRAM),
                new Quantity<>(5.0, WeightUnit.KILOGRAM));
        demonstrateDivision(
                new Quantity<>(5.0, VolumeUnit.LITRE),
                new Quantity<>(10.0, VolumeUnit.LITRE));
    }
}