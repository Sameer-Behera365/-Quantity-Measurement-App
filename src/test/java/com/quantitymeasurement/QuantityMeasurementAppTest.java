package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // -------------------- BACKWARD COMPATIBILITY (UC6) --------------------

    @Test
    public void givenOnePlusTwoFeet_WhenAdded_ThenShouldReturnThreeFeet() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET)
        );
        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void givenOneFootPlusTwelveInches_WhenAdded_ThenShouldReturnTwoFeet() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES)
        );
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    // -------------------- UC7: EXPLICIT TARGET UNIT --------------------

    @Test
    public void givenOneFootPlusTwelveInches_WhenTargetFeet_ThenShouldReturnTwoFeet() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.FEET
        );
        assertEquals(2.0, result.getValue(), EPSILON, "1 foot + 12 inches in FEET should be 2.0");
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void givenOneFootPlusTwelveInches_WhenTargetInches_ThenShouldReturnTwentyFourInches() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.INCHES
        );
        assertEquals(24.0, result.getValue(), EPSILON, "1 foot + 12 inches in INCHES should be 24.0");
        assertEquals(QuantityMeasurementApp.LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void givenOneFootPlusTwelveInches_WhenTargetYards_ThenShouldReturnPointSixSeven() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.YARDS
        );
        assertEquals(0.6667, result.getValue(), 0.001, "1 foot + 12 inches in YARDS should be ~0.667");
        assertEquals(QuantityMeasurementApp.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void givenOneInchPlusOneInch_WhenTargetCentimeters_ThenShouldReturnFivePointZeroEight() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCHES),
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.CENTIMETERS
        );
        assertEquals(5.08, result.getValue(), 0.001, "1 inch + 1 inch in CENTIMETERS should be ~5.08");
        assertEquals(QuantityMeasurementApp.LengthUnit.CENTIMETERS, result.getUnit());
    }

    @Test
    public void givenTwoYardsPlusThreeFeet_WhenTargetYards_ThenShouldReturnThreeYards() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.YARDS),
                new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.YARDS
        );
        assertEquals(3.0, result.getValue(), EPSILON, "2 yards + 3 feet in YARDS should be 3.0");
        assertEquals(QuantityMeasurementApp.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void givenTwoYardsPlusThreeFeet_WhenTargetFeet_ThenShouldReturnNineFeet() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.YARDS),
                new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.FEET
        );
        assertEquals(9.0, result.getValue(), EPSILON, "2 yards + 3 feet in FEET should be 9.0");
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    // -------------------- COMMUTATIVITY WITH TARGET UNIT --------------------

    @Test
    public void givenTwoLengths_WhenAddedInBothOrdersWithTargetUnit_ThenResultsShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength result1 = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.YARDS
        );
        QuantityMeasurementApp.QuantityLength result2 = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.YARDS
        );
        assertEquals(result1.getValue(), result2.getValue(), EPSILON, "Addition should be commutative with explicit target unit");
    }

    // -------------------- ZERO & NEGATIVE WITH TARGET UNIT --------------------

    @Test
    public void givenFiveFeetPlusZeroInches_WhenTargetYards_ThenShouldReturnCorrectYards() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(0.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.YARDS
        );
        assertEquals(5.0 / 3.0, result.getValue(), 0.001, "5 feet + 0 inches in YARDS should be ~1.667");
        assertEquals(QuantityMeasurementApp.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void givenFiveFeetPlusNegativeTwoFeet_WhenTargetInches_ThenShouldReturnThirtySixInches() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(-2.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.INCHES
        );
        assertEquals(36.0, result.getValue(), EPSILON, "5 feet + (-2 feet) in INCHES should be 36.0");
        assertEquals(QuantityMeasurementApp.LengthUnit.INCHES, result.getUnit());
    }

    // -------------------- LARGE & SMALL SCALE --------------------

    @Test
    public void givenLargeValues_WhenTargetInches_ThenShouldReturnCorrectResult() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(1000.0, QuantityMeasurementApp.LengthUnit.FEET),
                new QuantityMeasurementApp.QuantityLength(500.0, QuantityMeasurementApp.LengthUnit.FEET),
                QuantityMeasurementApp.LengthUnit.INCHES
        );
        assertEquals(18000.0, result.getValue(), EPSILON, "1000 + 500 feet in INCHES should be 18000.0");
    }

    @Test
    public void givenTwelveInchesPlusTwelveInches_WhenTargetYards_ThenShouldReturnPointSixSeven() {
        QuantityMeasurementApp.QuantityLength result = QuantityMeasurementApp.add(
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                QuantityMeasurementApp.LengthUnit.YARDS
        );
        assertEquals(0.6667, result.getValue(), 0.001, "12 + 12 inches in YARDS should be ~0.667");
    }

    // -------------------- NULL HANDLING --------------------

    @Test
    public void givenNullTargetUnit_WhenAdded_ThenShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.add(
                        new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                        new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES),
                        null
                ), "Null target unit should throw IllegalArgumentException"
        );
    }

    @Test
    public void givenNullOperand_WhenAdded_ThenShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.add(
                        new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET),
                        null,
                        QuantityMeasurementApp.LengthUnit.FEET
                ), "Null operand should throw IllegalArgumentException"
        );
    }
}