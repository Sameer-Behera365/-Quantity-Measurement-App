package com.quantitymeasurement;

/**
 * QuantityMeasurementApp - simplified orchestration class.
 * Generic methods handle all measurement categories uniformly.
 * No category-specific duplication.
 */
public class QuantityMeasurementApp {

    /**
     * Generic equality demonstration - works for any IMeasurable unit.
     */
    public static <U extends IMeasurable> void demonstrateEquality(
            Quantity<U> q1, Quantity<U> q2) {
        System.out.printf("equals(%s, %s) = %b%n", q1, q2, q1.equals(q2));
    }

    /**
     * Generic conversion demonstration - works for any IMeasurable unit.
     */
    public static <U extends IMeasurable> void demonstrateConversion(
            Quantity<U> quantity, U targetUnit) {
        Quantity<U> result = quantity.convertTo(targetUnit);
        System.out.printf("convert(%s -> %s) = %s%n",
                quantity, targetUnit.getUnitName(), result);
    }

    /**
     * Generic addition demonstration - works for any IMeasurable unit.
     */
    public static <U extends IMeasurable> void demonstrateAddition(
            Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        Quantity<U> result = q1.add(q2, targetUnit);
        System.out.printf("add(%s, %s) in %s = %s%n",
                q1, q2, targetUnit.getUnitName(), result);
    }

    public static void main(String[] args) {
        System.out.println("--- Length Operations (UC1-UC8) ---");
        Quantity<LengthUnit> oneFoot = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, LengthUnit.INCHES);
        demonstrateEquality(oneFoot, twelveInches);
        demonstrateConversion(oneFoot, LengthUnit.INCHES);
        demonstrateAddition(oneFoot, twelveInches, LengthUnit.FEET);

        System.out.println("--- Weight Operations (UC9) ---");
        Quantity<WeightUnit> oneKg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> thousandGrams = new Quantity<>(1000.0, WeightUnit.GRAM);
        demonstrateEquality(oneKg, thousandGrams);
        demonstrateConversion(oneKg, WeightUnit.GRAM);
        demonstrateAddition(oneKg, thousandGrams, WeightUnit.KILOGRAM);

        System.out.println("--- Cross-Category Prevention ---");
        Quantity<LengthUnit> lengthQ = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weightQ = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        System.out.println("1 foot == 1 kg: " + lengthQ.equals(weightQ));
    }
}