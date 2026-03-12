package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // -------------------- SAME UNIT EQUALITY --------------------

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

    // -------------------- DIFFERENT VALUE INEQUALITY --------------------

    @Test
    public void givenTwoFeetValues_WhenValuesAreDifferent_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertNotEquals(q1, q2, "1.0 feet should not equal 2.0 feet");
    }

    @Test
    public void givenTwoInchValues_WhenValuesAreDifferent_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.INCHES);
        assertNotEquals(q1, q2, "1.0 inch should not equal 2.0 inch");
    }

    // -------------------- CROSS UNIT EQUALITY --------------------

    @Test
    public void givenOneFoot_WhenComparedWithTwelveInches_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);
        assertEquals(q1, q2, "1.0 feet should equal 12.0 inches");
    }

    @Test
    public void givenTwelveInches_WhenComparedWithOneFoot_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(q1, q2, "12.0 inches should equal 1.0 feet (symmetry)");
    }

    // -------------------- NULL & TYPE SAFETY --------------------

    @Test
    public void givenQuantityLength_WhenComparedWithNull_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertNotEquals(q1, null, "QuantityLength should not equal null");
    }

    @Test
    public void givenQuantityLength_WhenComparedWithDifferentType_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        String other = "1.0";
        assertNotEquals(q1, other, "QuantityLength should not equal a String type");
    }

    // -------------------- REFLEXIVE PROPERTY --------------------

    @Test
    public void givenQuantityLength_WhenComparedWithItself_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(q1, q1, "QuantityLength object should equal itself");
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