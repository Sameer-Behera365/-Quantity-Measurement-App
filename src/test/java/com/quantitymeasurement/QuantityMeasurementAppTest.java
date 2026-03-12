
package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // Test 1: Same value - should be equal
    @Test
    public void givenTwoFeetValues_WhenBothAreOneFoot_ThenShouldBeEqual() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(1.0);
        assertEquals(feet1, feet2, "1.0 ft should equal 1.0 ft");
    }

    // Test 2: Different values - should not be equal
    @Test
    public void givenTwoFeetValues_WhenValuesAreDifferent_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(2.0);
        assertNotEquals(feet1, feet2, "1.0 ft should not equal 2.0 ft");
    }

    // Test 3: Null comparison - should return false
    @Test
    public void givenFeetValue_WhenComparedWithNull_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        assertNotEquals(feet1, null, "1.0 ft should not equal null");
    }

    // Test 4: Different type comparison - should return false
    @Test
    public void givenFeetValue_WhenComparedWithNonFeetType_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        String nonNumeric = "1.0";
        assertNotEquals(feet1, nonNumeric, "Feet should not equal a String type");
    }

    // Test 5: Same reference - reflexive property
    @Test
    public void givenFeetValue_WhenComparedWithItself_ThenShouldBeEqual() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        assertEquals(feet1, feet1, "Feet object should equal itself");
    }
}
