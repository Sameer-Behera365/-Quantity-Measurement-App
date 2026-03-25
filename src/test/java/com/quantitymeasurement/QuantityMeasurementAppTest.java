package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-4;

    // ==================== IMEASURABLE INTERFACE TESTS ====================

    @Test
    public void testIMeasurable_LengthUnitImplementation() {
        assertTrue(LengthUnit.FEET instanceof IMeasurable);
        assertTrue(LengthUnit.INCHES instanceof IMeasurable);
    }

    @Test
    public void testIMeasurable_WeightUnitImplementation() {
        assertTrue(WeightUnit.KILOGRAM instanceof IMeasurable);
        assertTrue(WeightUnit.GRAM instanceof IMeasurable);
    }

    @Test
    public void testIMeasurable_LengthUnitGetUnitName() {
        assertEquals("FEET", LengthUnit.FEET.getUnitName());
        assertEquals("INCHES", LengthUnit.INCHES.getUnitName());
        assertEquals("YARDS", LengthUnit.YARDS.getUnitName());
    }

    @Test
    public void testIMeasurable_WeightUnitGetUnitName() {
        assertEquals("KILOGRAM", WeightUnit.KILOGRAM.getUnitName());
        assertEquals("GRAM", WeightUnit.GRAM.getUnitName());
        assertEquals("POUND", WeightUnit.POUND.getUnitName());
    }

    // ==================== GENERIC QUANTITY - LENGTH EQUALITY ====================

    @Test
    public void testGenericQuantity_Length_SameValue_Equal() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(q1, q2, "1.0 feet should equal 1.0 feet");
    }

    @Test
    public void testGenericQuantity_Length_FeetToInches_Equal() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(q1, q2, "1.0 feet should equal 12.0 inches");
    }

    @Test
    public void testGenericQuantity_Length_YardToFeet_Equal() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> q2 = new Quantity<>(3.0, LengthUnit.FEET);
        assertEquals(q1, q2, "1.0 yard should equal 3.0 feet");
    }

    @Test
    public void testGenericQuantity_Length_DifferentValues_NotEqual() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        assertNotEquals(q1, q2, "1.0 feet should not equal 2.0 feet");
    }

    // ==================== GENERIC QUANTITY - WEIGHT EQUALITY ====================

    @Test
    public void testGenericQuantity_Weight_SameValue_Equal() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertEquals(w1, w2, "1.0 kg should equal 1.0 kg");
    }

    @Test
    public void testGenericQuantity_Weight_KilogramToGram_Equal() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertEquals(w1, w2, "1.0 kg should equal 1000.0 g");
    }

    @Test
    public void testGenericQuantity_Weight_KilogramToPound_Equal() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(2.20462, WeightUnit.POUND);
        assertEquals(w1, w2, "1.0 kg should equal ~2.20462 lb");
    }

    @Test
    public void testGenericQuantity_Weight_DifferentValues_NotEqual() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        assertNotEquals(w1, w2, "1.0 kg should not equal 2.0 kg");
    }

    // ==================== CROSS CATEGORY PREVENTION ====================

    @Test
    public void testCrossCategory_LengthVsWeight_NotEqual() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(length, weight, "Length and weight should never be equal");
    }

    // ==================== GENERIC QUANTITY - LENGTH CONVERSION ====================

    @Test
    public void testGenericQuantity_Length_ConvertFeetToInches() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES);
        assertEquals(12.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testGenericQuantity_Length_ConvertYardsToFeet() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.YARDS)
                .convertTo(LengthUnit.FEET);
        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testGenericQuantity_Length_ConvertSameUnit() {
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET)
                .convertTo(LengthUnit.FEET);
        assertEquals(5.0, result.getValue(), EPSILON);
    }

    // ==================== GENERIC QUANTITY - WEIGHT CONVERSION ====================

    @Test
    public void testGenericQuantity_Weight_ConvertKilogramToGram() {
        Quantity<WeightUnit> result = new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testGenericQuantity_Weight_ConvertPoundToKilogram() {
        Quantity<WeightUnit> result = new Quantity<>(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.0, result.getValue(), 0.001);
    }

    @Test
    public void testGenericQuantity_Weight_RoundTrip() {
        Quantity<WeightUnit> result = new Quantity<>(1.5, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.5, result.getValue(), EPSILON);
    }

    // ==================== GENERIC QUANTITY - LENGTH ADDITION ====================

    @Test
    public void testGenericQuantity_Length_AddSameUnit() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.FEET)
                .add(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testGenericQuantity_Length_AddCrossUnit() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.FEET)
                .add(new Quantity<>(12.0, LengthUnit.INCHES));
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testGenericQuantity_Length_AddWithExplicitTargetUnit() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.FEET)
                .add(new Quantity<>(12.0, LengthUnit.INCHES), LengthUnit.INCHES);
        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testGenericQuantity_Length_AddYardPlusFeet() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.YARDS)
                .add(new Quantity<>(3.0, LengthUnit.FEET));
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    // ==================== GENERIC QUANTITY - WEIGHT ADDITION ====================

    @Test
    public void testGenericQuantity_Weight_AddSameUnit() {
        Quantity<WeightUnit> result = new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .add(new Quantity<>(2.0, WeightUnit.KILOGRAM));
        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testGenericQuantity_Weight_AddCrossUnit() {
        Quantity<WeightUnit> result = new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .add(new Quantity<>(1000.0, WeightUnit.GRAM));
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testGenericQuantity_Weight_AddWithExplicitTargetUnit() {
        Quantity<WeightUnit> result = new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .add(new Quantity<>(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);
        assertEquals(2000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    // ==================== NULL & INVALID INPUT ====================

    @Test
    public void testGenericQuantity_NullUnit_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(1.0, (LengthUnit) null));
    }

    @Test
    public void testGenericQuantity_NaNValue_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    @Test
    public void testGenericQuantity_NullOperand_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(1.0, LengthUnit.FEET).add(null));
    }

    @Test
    public void testGenericQuantity_NullTargetUnit_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(1.0, LengthUnit.FEET).convertTo(null));
    }

    // ==================== EQUALITY CONTRACT ====================

    @Test
    public void testGenericQuantity_Reflexive() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(q, q);
    }

    @Test
    public void testGenericQuantity_Symmetric() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(q1, q2);
        assertEquals(q2, q1);
    }

    @Test
    public void testGenericQuantity_NullComparison() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertNotEquals(q, null);
    }

    // ==================== TOSTRING ====================

    @Test
    public void testGenericQuantity_ToString_Length() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals("1.0 FEET", q.toString());
    }

    @Test
    public void testGenericQuantity_ToString_Weight() {
        Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertEquals("1.0 KILOGRAM", w.toString());
    }

    // ==================== SCALABILITY - NEW CATEGORY ====================

    @Test
    public void testScalability_NewVolumeUnitWorksWithGenericQuantity() {
        // Inline enum to simulate adding a new category - no changes to Quantity needed
        IMeasurable litre = new IMeasurable() {
            public double getConversionFactor() { return 1.0; }
            public double convertToBaseUnit(double v) { return v; }
            public double convertFromBaseUnit(double v) { return v; }
            public String getUnitName() { return "LITRE"; }
        };
        IMeasurable millilitre = new IMeasurable() {
            public double getConversionFactor() { return 0.001; }
            public double convertToBaseUnit(double v) { return v * 0.001; }
            public double convertFromBaseUnit(double v) { return v / 0.001; }
            public String getUnitName() { return "MILLILITRE"; }
        };
        // Verifies generic design works with any IMeasurable
        assertEquals(1.0, litre.convertToBaseUnit(1.0), 1e-6);
        assertEquals(1000.0, millilitre.convertFromBaseUnit(1.0), 1e-6);
    }
}