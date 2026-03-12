package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // -------------------- LENGTHUNIT STANDALONE ENUM TESTS --------------------

    @Test
    public void testLengthUnitEnum_FeetConversionFactor() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON,
                "FEET conversion factor should be 1.0");
    }

    @Test
    public void testLengthUnitEnum_InchesConversionFactor() {
        assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), EPSILON,
                "INCHES conversion factor should be ~0.0833");
    }

    @Test
    public void testLengthUnitEnum_YardsConversionFactor() {
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), EPSILON,
                "YARDS conversion factor should be 3.0");
    }

    @Test
    public void testLengthUnitEnum_CentimetersConversionFactor() {
        assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), EPSILON,
                "CENTIMETERS conversion factor should be ~0.0328");
    }

    // -------------------- convertToBaseUnit TESTS --------------------

    @Test
    public void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0), EPSILON,
                "5.0 feet to base unit should be 5.0");
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), EPSILON,
                "12.0 inches to base unit should be 1.0 feet");
    }

    @Test
    public void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), EPSILON,
                "1.0 yard to base unit should be 3.0 feet");
    }

    @Test
    public void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), EPSILON,
                "30.48 centimeters to base unit should be ~1.0 feet");
    }

    // -------------------- convertFromBaseUnit TESTS --------------------

    @Test
    public void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0), EPSILON,
                "2.0 feet from base should be 2.0 feet");
    }

    @Test
    public void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), EPSILON,
                "1.0 foot from base to inches should be 12.0");
    }

    @Test
    public void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), EPSILON,
                "3.0 feet from base to yards should be 1.0");
    }

    @Test
    public void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), 0.001,
                "1.0 foot from base to centimeters should be ~30.48");
    }

    // -------------------- REFACTORED QUANTITYLENGTH TESTS --------------------

    @Test
    public void testQuantityLengthRefactored_Equality() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCHES);
        assertEquals(q1, q2, "1.0 feet should equal 12.0 inches");
    }

    @Test
    public void testQuantityLengthRefactored_ConvertTo() {
        QuantityMeasurementApp.QuantityLength q = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength result = q.convertTo(LengthUnit.INCHES);
        assertEquals(12.0, result.getValue(), EPSILON, "1.0 feet convertTo inches should be 12.0");
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testQuantityLengthRefactored_Add() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCHES)
        );
        assertEquals(2.0, result.getValue(), EPSILON, "1 foot + 12 inches should be 2.0 feet");
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testQuantityLengthRefactored_AddWithTargetUnit() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS
        );
        assertEquals(0.6667, result.getValue(), 0.001, "1 foot + 12 inches in yards should be ~0.667");
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testQuantityLengthRefactored_NullUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                        new QuantityMeasurementApp.QuantityLength(1.0, null),
                "Null unit should throw IllegalArgumentException"
        );
    }

    @Test
    public void testQuantityLengthRefactored_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () ->
                        new QuantityMeasurementApp.QuantityLength(Double.NaN, LengthUnit.FEET),
                "NaN value should throw IllegalArgumentException"
        );
    }

    // -------------------- BACKWARD COMPATIBILITY --------------------

    @Test
    public void testBackwardCompatibility_UC1Equality() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(q1, q2, "UC1: 1.0 feet should equal 1.0 feet");
    }

    @Test
    public void testBackwardCompatibility_UC5Conversion() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES);
        assertEquals(36.0, result, EPSILON, "UC5: 1 yard should convert to 36 inches");
    }

    @Test
    public void testBackwardCompatibility_UC6Addition() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityMeasurementApp.QuantityLength(3.0, LengthUnit.FEET)
        );
        assertEquals(2.0, result.getValue(), EPSILON, "UC6: 1 yard + 3 feet should be 2.0 yards");
    }

    @Test
    public void testBackwardCompatibility_UC7AdditionWithTargetUnit() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(36.0, LengthUnit.INCHES),
                new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.YARDS),
                LengthUnit.FEET
        );
        assertEquals(6.0, result.getValue(), EPSILON, "UC7: 36 inches + 1 yard in FEET should be 6.0");
    }

    // -------------------- ROUND TRIP --------------------

    @Test
    public void testRoundTripConversion_RefactoredDesign() {
        double original = 5.0;
        double toInches = QuantityMeasurementApp.convert(original, LengthUnit.FEET, LengthUnit.INCHES);
        double backToFeet = QuantityMeasurementApp.convert(toInches, LengthUnit.INCHES, LengthUnit.FEET);
        assertEquals(original, backToFeet, EPSILON, "Round trip conversion should preserve original value");
    }
}