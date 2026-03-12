package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // -------------------- UC1 & UC2 BACKWARD COMPATIBILITY --------------------

    @Test
    public void givenTwoFeetValues_WhenBothAreOneFoot_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(q1, q2, "1.0 feet should equal 1.0 feet");
    }

    @Test
    public void givenTwoInchValues_WhenBothAreOneInch_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(q1, q2, "1.0 inch should equal 1.0 inch");
    }

    @Test
    public void givenOneFoot_WhenComparedWithTwelveInches_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(q1, q2, "1.0 feet should equal 12.0 inches");
    }

    // -------------------- YARDS TESTS --------------------

    @Test
    public void givenTwoYardValues_WhenBothAreOneYard_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(q1, q2, "1.0 yard should equal 1.0 yard");
    }

    @Test
    public void givenTwoYardValues_WhenValuesAreDifferent_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertNotEquals(q1, q2, "1.0 yard should not equal 2.0 yards");
    }

    @Test
    public void givenOneYard_WhenComparedWithThreeFeet_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(q1, q2, "1.0 yard should equal 3.0 feet");
    }

    @Test
    public void givenThreeFeet_WhenComparedWithOneYard_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(q1, q2, "3.0 feet should equal 1.0 yard");
    }

    @Test
    public void givenOneYard_WhenComparedWithThirtySixInches_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(36.0, QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(q1, q2, "1.0 yard should equal 36.0 inches");
    }

    @Test
    public void givenThirtySixInches_WhenComparedWithOneYard_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(36.0, QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(q1, q2, "36.0 inches should equal 1.0 yard");
    }

    @Test
    public void givenOneYard_WhenComparedWithTwoFeet_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertNotEquals(q1, q2, "1.0 yard should not equal 2.0 feet");
    }

    // -------------------- CENTIMETERS TESTS --------------------

    @Test
    public void givenTwoCmValues_WhenBothAreOneCm_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertEquals(q1, q2, "2.0 cm should equal 2.0 cm");
    }

    @Test
    public void givenOneCm_WhenComparedWithEquivalentInches_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(0.393701, QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(q1, q2, "1.0 cm should equal 0.393701 inches");
    }

    @Test
    public void givenOneCm_WhenComparedWithOneFoot_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertNotEquals(q1, q2, "1.0 cm should not equal 1.0 feet");
    }

    // -------------------- NULL & REFLEXIVE --------------------

    @Test
    public void givenYardObject_WhenComparedWithItself_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertEquals(q1, q1, "Yard object should equal itself");
    }

    @Test
    public void givenYardObject_WhenComparedWithNull_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        assertNotEquals(q1, null, "Yard object should not equal null");
    }

    @Test
    public void givenCmObject_WhenComparedWithItself_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertEquals(q1, q1, "Centimeters object should equal itself");
    }

    @Test
    public void givenCmObject_WhenComparedWithNull_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
        assertNotEquals(q1, null, "Centimeters object should not equal null");
    }

    // -------------------- TRANSITIVE PROPERTY --------------------

    @Test
    public void givenYardFeetInches_WhenTransitiveCheck_ThenAllShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength yards = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        QuantityMeasurementApp.QuantityLength feet = new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength inches = new QuantityMeasurementApp.QuantityLength(36.0, QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(yards, feet, "1 yard should equal 3 feet");
        assertEquals(feet, inches, "3 feet should equal 36 inches");
        assertEquals(yards, inches, "1 yard should equal 36 inches (transitive)");
    }

    // -------------------- NULL UNIT SAFETY --------------------

    @Test
    public void givenQuantityLength_WhenUnitIsNull_ThenShouldThrowException() {
        assertThrows(NullPointerException.class, () -> {
            QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, null);
            q1.equals(new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET));
        }, "Null unit should throw NullPointerException");
    }
}