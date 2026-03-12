package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // -------------------- FEET TESTS (UC1) --------------------

    @Test
    public void givenTwoFeetValues_WhenBothAreOneFoot_ThenShouldBeEqual() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(1.0);
        assertEquals(feet1, feet2, "1.0 ft should equal 1.0 ft");
    }

    @Test
    public void givenTwoFeetValues_WhenValuesAreDifferent_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet feet2 = new QuantityMeasurementApp.Feet(2.0);
        assertNotEquals(feet1, feet2, "1.0 ft should not equal 2.0 ft");
    }

    @Test
    public void givenFeetValue_WhenComparedWithNull_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        assertNotEquals(feet1, null, "1.0 ft should not equal null");
    }

    @Test
    public void givenFeetValue_WhenComparedWithNonFeetType_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        String nonNumeric = "1.0";
        assertNotEquals(feet1, nonNumeric, "Feet should not equal a String type");
    }

    @Test
    public void givenFeetValue_WhenComparedWithItself_ThenShouldBeEqual() {
        QuantityMeasurementApp.Feet feet1 = new QuantityMeasurementApp.Feet(1.0);
        assertEquals(feet1, feet1, "Feet object should equal itself");
    }

    // -------------------- INCHES TESTS (UC2) --------------------

    @Test
    public void givenTwoInchValues_WhenBothAreOneInch_ThenShouldBeEqual() {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches inches2 = new QuantityMeasurementApp.Inches(1.0);
        assertEquals(inches1, inches2, "1.0 inch should equal 1.0 inch");
    }

    @Test
    public void givenTwoInchValues_WhenValuesAreDifferent_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches inches2 = new QuantityMeasurementApp.Inches(2.0);
        assertNotEquals(inches1, inches2, "1.0 inch should not equal 2.0 inch");
    }

    @Test
    public void givenInchValue_WhenComparedWithNull_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(1.0);
        assertNotEquals(inches1, null, "1.0 inch should not equal null");
    }

    @Test
    public void givenInchValue_WhenComparedWithNonInchType_ThenShouldNotBeEqual() {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(1.0);
        String nonNumeric = "1.0";
        assertNotEquals(inches1, nonNumeric, "Inches should not equal a String type");
    }

    @Test
    public void givenInchValue_WhenComparedWithItself_ThenShouldBeEqual() {
        QuantityMeasurementApp.Inches inches1 = new QuantityMeasurementApp.Inches(1.0);
        assertEquals(inches1, inches1, "Inches object should equal itself");
    }
}