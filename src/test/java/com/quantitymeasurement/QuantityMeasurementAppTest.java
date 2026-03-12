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
        assertEquals(q1, q2);
    }

    @Test
    public void givenOneFoot_WhenConvertedToInches_ThenShouldReturnTwelve() {
        double result = QuantityMeasurementApp.convert(1.0, QuantityMeasurementApp.LengthUnit.FEET, QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(12.0, result, EPSILON);
    }

    // -------------------- SAME UNIT ADDITION --------------------

    @Test
    public void givenOnePlusTwoFeet_WhenAdded_ThenShouldReturnThreeFeet() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET)
        );
        assertEquals(3.0, result.getValue(), EPSILON, "1.0 + 2.0 feet should equal 3.0 feet");
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void givenSixPlusSixInches_WhenAdded_ThenShouldReturnTwelveInches() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(6.0, QuantityMeasurementApp.LengthUnit.INCHES),
                new QuantityMeasurementApp.QuantityLength(6.0, QuantityMeasurementApp.LengthUnit.INCHES)
        );
        assertEquals(12.0, result.getValue(), EPSILON, "6.0 + 6.0 inches should equal 12.0 inches");
        assertEquals(QuantityMeasurementApp.LengthUnit.INCHES, result.getUnit());
    }

    // -------------------- CROSS UNIT ADDITION --------------------

    @Test
    public void givenOneFootPlusTwelveInches_WhenAdded_ThenShouldReturnTwoFeet() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES)
        );
        assertEquals(2.0, result.getValue(), EPSILON, "1.0 foot + 12.0 inches should equal 2.0 feet");
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void givenTwelveInchesPlusOneFoot_WhenAdded_ThenShouldReturnTwentyFourInches() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET)
        );
        assertEquals(24.0, result.getValue(), EPSILON, "12.0 inches + 1.0 foot should equal 24.0 inches");
        assertEquals(QuantityMeasurementApp.LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void givenOneYardPlusThreeFeet_WhenAdded_ThenShouldReturnTwoYards() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS),
                new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET)
        );
        assertEquals(2.0, result.getValue(), EPSILON, "1.0 yard + 3.0 feet should equal 2.0 yards");
        assertEquals(QuantityMeasurementApp.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void givenTwoPointFiveFourCmPlusOneInch_WhenAdded_ThenShouldReturnFivePointZeroEightCm() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(2.54, QuantityMeasurementApp.LengthUnit.CENTIMETERS),
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCHES)
        );
        assertEquals(5.08, result.getValue(), 0.001, "2.54 cm + 1.0 inch should equal ~5.08 cm");
        assertEquals(QuantityMeasurementApp.LengthUnit.CENTIMETERS, result.getUnit());
    }

    // -------------------- COMMUTATIVITY --------------------

    @Test
    public void givenTwoLengths_WhenAddedInBothOrders_ThenResultsShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength feet = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength inches = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.QuantityLength result1 = QuantityMeasurementApp.add(feet, inches);
        QuantityMeasurementApp.QuantityLength result2 = QuantityMeasurementApp.add(inches, feet);
        assertEquals(result1, result2, "Addition should be commutative");
    }

    // -------------------- IDENTITY (ZERO) --------------------

    @Test
    public void givenFiveFeetPlusZeroInches_WhenAdded_ThenShouldReturnFiveFeet() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(0.0, QuantityMeasurementApp.LengthUnit.INCHES)
        );
        assertEquals(5.0, result.getValue(), EPSILON, "5.0 feet + 0.0 inches should equal 5.0 feet");
    }

    // -------------------- NEGATIVE VALUES --------------------

    @Test
    public void givenFiveFeetPlusNegativeTwoFeet_WhenAdded_ThenShouldReturnThreeFeet() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(-2.0, QuantityMeasurementApp.LengthUnit.FEET)
        );
        assertEquals(3.0, result.getValue(), EPSILON, "5.0 feet + (-2.0) feet should equal 3.0 feet");
    }

    // -------------------- LARGE & SMALL VALUES --------------------

    @Test
    public void givenLargeValues_WhenAdded_ThenShouldReturnCorrectSum() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1e6, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(1e6, QuantityMeasurementApp.LengthUnit.FEET)
        );
        assertEquals(2e6, result.getValue(), EPSILON, "1e6 + 1e6 feet should equal 2e6 feet");
    }

    @Test
    public void givenSmallValues_WhenAdded_ThenShouldReturnCorrectSum() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(0.001, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(0.002, QuantityMeasurementApp.LengthUnit.FEET)
        );
        assertEquals(0.003, result.getValue(), EPSILON, "0.001 + 0.002 feet should equal 0.003 feet");
    }

    // -------------------- NULL HANDLING --------------------

    @Test
    public void givenNullSecondOperand_WhenAdded_ThenShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.add(
                        new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                        null
                ), "Null operand should throw IllegalArgumentException"
        );
    }
}