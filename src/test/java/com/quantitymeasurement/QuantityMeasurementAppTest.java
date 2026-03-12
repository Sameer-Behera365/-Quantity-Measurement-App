package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // -------------------- BACKWARD COMPATIBILITY --------------------

    @Test
    public void givenOneFoot_WhenComparedWithTwelveInches_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(q1, q2, "1.0 feet should equal 12.0 inches");
    }

    @Test
    public void givenOneYard_WhenComparedWithThreeFeet_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(q1, q2, "1.0 yard should equal 3.0 feet");
    }

    // -------------------- BASIC CONVERSIONS --------------------

    @Test
    public void givenOneFoot_WhenConvertedToInches_ThenShouldReturnTwelve() {
        double result = QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(12.0, result, EPSILON, "1.0 feet should convert to 12.0 inches");
    }

    @Test
    public void givenTwentyFourInches_WhenConvertedToFeet_ThenShouldReturnTwo() {
        double result = QuantityMeasurementApp.convert(24.0, QuantityMeasurementApp.LengthUnit.INCHES, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(2.0, result, EPSILON, "24.0 inches should convert to 2.0 feet");
    }

    @Test
    public void givenOneYard_WhenConvertedToInches_ThenShouldReturnThirtySix() {
        double result = QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.YARDS, QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(36.0, result, EPSILON, "1.0 yard should convert to 36.0 inches");
    }

    @Test
    public void givenSeventyTwoInches_WhenConvertedToYards_ThenShouldReturnTwo() {
        double result = QuantityMeasurementApp.convert(72.0, QuantityMeasurementApp.LengthUnit.INCHES, QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(2.0, result, EPSILON, "72.0 inches should convert to 2.0 yards");
    }

    @Test
    public void givenSixFeet_WhenConvertedToYards_ThenShouldReturnTwo() {
        double result = QuantityMeasurementApp.convert(6.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(2.0, result, EPSILON, "6.0 feet should convert to 2.0 yards");
    }

    @Test
    public void givenTwoPointFiveFourCm_WhenConvertedToInches_ThenShouldReturnOne() {
        double result = QuantityMeasurementApp.convert(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS, QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(1.0, result, 0.001, "2.54 cm should convert to ~1.0 inch");
    }

    // -------------------- SAME UNIT CONVERSION --------------------

    @Test
    public void givenFiveFeet_WhenConvertedToFeet_ThenShouldReturnFive() {
        double result = QuantityMeasurementApp.convert(5.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(5.0, result, EPSILON, "5.0 feet to feet should return 5.0");
    }

    // -------------------- ZERO VALUE --------------------

    @Test
    public void givenZeroFeet_WhenConvertedToInches_ThenShouldReturnZero() {
        double result = QuantityMeasurementApp.convert(0.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(0.0, result, EPSILON, "0.0 feet should convert to 0.0 inches");
    }

    // -------------------- NEGATIVE VALUE --------------------

    @Test
    public void givenNegativeOneFoot_WhenConvertedToInches_ThenShouldReturnNegativeTwelve() {
        double result = QuantityMeasurementApp.convert(-1.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(-12.0, result, EPSILON, "-1.0 feet should convert to -12.0 inches");
    }

    // -------------------- ROUND TRIP --------------------

    @Test
    public void givenFeetToInchesAndBack_WhenRoundTrip_ThenShouldPreserveValue() {
        double original = 5.0;
        double toInches = QuantityMeasurementApp.convert(original, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES);
        double backToFeet = QuantityMeasurementApp.convert(toInches, QuantityMeasurementApp.LengthUnit.INCHES, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(original, backToFeet, EPSILON, "Round trip conversion should preserve original value");
    }

    // -------------------- INSTANCE METHOD --------------------

    @Test
    public void givenQuantityLength_WhenConvertToUsed_ThenShouldReturnCorrectValue() {
        QuantityMeasurementApp.QuantityLength q = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.QuantityLength result = q.convertTo(QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(36.0, result.getValue(), EPSILON, "1.0 yard convertTo inches should return 36.0");
    }

    // -------------------- INVALID INPUT HANDLING --------------------

    @Test
    public void givenNullSourceUnit_WhenConvert_ThenShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                        QuantityMeasurementApp.convert(1.0, null, QuantityMeasurementApp.LengthUnit.INCHES),
                "Null source unit should throw IllegalArgumentException"
        );
    }

    @Test
    public void givenNullTargetUnit_WhenConvert_ThenShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                        QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.FEET, null),
                "Null target unit should throw IllegalArgumentException"
        );
    }

    @Test
    public void givenNaNValue_WhenConvert_ThenShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                        QuantityMeasurementApp.convert(Double.NaN, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES),
                "NaN value should throw IllegalArgumentException"
        );
    }

    @Test
    public void givenInfiniteValue_WhenConvert_ThenShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                        QuantityMeasurementApp.convert(Double.POSITIVE_INFINITY, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES),
                "Infinite value should throw IllegalArgumentException"
        );
    }

    // -------------------- TOSTRING --------------------

    @Test
    public void givenQuantityLength_WhenToStringCalled_ThenShouldReturnReadableFormat() {
        QuantityMeasurementApp.QuantityLength q = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals("1.0 FEET", q.toString(), "toString should return '1.0 FEET'");
    }
}