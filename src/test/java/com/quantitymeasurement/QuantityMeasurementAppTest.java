package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // ==================== LENGTH BACKWARD COMPATIBILITY ====================

    @Test
    public void givenOneFoot_WhenComparedWithTwelveInches_ThenShouldBeEqual() {
        QuantityMeasurementApp.QuantityLength q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        QuantityMeasurementApp.QuantityLength q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCHES);
        assertEquals(q1, q2);
    }

    @Test
    public void givenOneYard_WhenConvertedToInches_ThenShouldReturnThirtySix() {
        double result = QuantityMeasurementApp.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES);
        assertEquals(36.0, result, EPSILON);
    }

    // ==================== WEIGHTUNIT ENUM TESTS ====================

    @Test
    public void testWeightUnit_KilogramConversionFactor() {
        assertEquals(1.0, WeightUnit.KILOGRAM.getConversionFactor(), EPSILON);
    }

    @Test
    public void testWeightUnit_GramConversionFactor() {
        assertEquals(0.001, WeightUnit.GRAM.getConversionFactor(), EPSILON);
    }

    @Test
    public void testWeightUnit_PoundConversionFactor() {
        assertEquals(0.453592, WeightUnit.POUND.getConversionFactor(), EPSILON);
    }

    @Test
    public void testWeightUnit_ConvertToBaseUnit_GramToKilogram() {
        assertEquals(1.0, WeightUnit.GRAM.convertToBaseUnit(1000.0), EPSILON);
    }

    @Test
    public void testWeightUnit_ConvertToBaseUnit_PoundToKilogram() {
        assertEquals(0.453592, WeightUnit.POUND.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    public void testWeightUnit_ConvertFromBaseUnit_KilogramToGram() {
        assertEquals(1000.0, WeightUnit.GRAM.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    public void testWeightUnit_ConvertFromBaseUnit_KilogramToPound() {
        assertEquals(2.20462, WeightUnit.POUND.convertFromBaseUnit(1.0), 0.001);
    }

    // ==================== EQUALITY TESTS ====================

    @Test
    public void testEquality_KilogramToKilogram_SameValue() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_KilogramToKilogram_DifferentValue() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);
        assertNotEquals(w1, w2);
    }

    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_GramToKilogram_EquivalentValue() {
        QuantityWeight w1 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight w2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_GramToGram_SameValue() {
        QuantityWeight w1 = new QuantityWeight(500.0, WeightUnit.GRAM);
        QuantityWeight w2 = new QuantityWeight(500.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_PoundToPound_SameValue() {
        QuantityWeight w1 = new QuantityWeight(2.0, WeightUnit.POUND);
        QuantityWeight w2 = new QuantityWeight(2.0, WeightUnit.POUND);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_KilogramToPound_EquivalentValue() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(2.20462, WeightUnit.POUND);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_GramToPound_EquivalentValue() {
        QuantityWeight w1 = new QuantityWeight(453.592, WeightUnit.GRAM);
        QuantityWeight w2 = new QuantityWeight(1.0, WeightUnit.POUND);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_NullComparison() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(w1, null);
    }

    @Test
    public void testEquality_SameReference() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertEquals(w1, w1);
    }

    @Test
    public void testEquality_WeightVsLength_Incompatible() {
        QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityMeasurementApp.QuantityLength l = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        assertNotEquals(w, l);
    }

    @Test
    public void testEquality_ZeroValue() {
        QuantityWeight w1 = new QuantityWeight(0.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(0.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_SmallWeightValue() {
        QuantityWeight w1 = new QuantityWeight(0.001, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testEquality_LargeWeightValue() {
        QuantityWeight w1 = new QuantityWeight(1000000.0, WeightUnit.GRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.KILOGRAM);
        assertEquals(w1, w2);
    }

    // ==================== CONVERSION TESTS ====================

    @Test
    public void testConversion_KilogramToGram() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testConversion_PoundToKilogram() {
        QuantityWeight result = new QuantityWeight(2.20462, WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.0, result.getValue(), 0.001);
    }

    @Test
    public void testConversion_KilogramToPound() {
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.POUND);
        assertEquals(2.20462, result.getValue(), 0.001);
    }

    @Test
    public void testConversion_SameUnit() {
        QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.KILOGRAM);
        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_ZeroValue() {
        QuantityWeight result = new QuantityWeight(0.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);
        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_NegativeValue() {
        QuantityWeight result = new QuantityWeight(-1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);
        assertEquals(-1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_RoundTrip() {
        QuantityWeight original = new QuantityWeight(1.5, WeightUnit.KILOGRAM);
        QuantityWeight result = original.convertTo(WeightUnit.GRAM).convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.5, result.getValue(), EPSILON);
    }

    // ==================== ADDITION TESTS ====================

    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {
        QuantityWeight result = QuantityMeasurementApp.add(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(2.0, WeightUnit.KILOGRAM)
        );
        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusGram() {
        QuantityWeight result = QuantityMeasurementApp.add(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM)
        );
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_GramPlusKilogram() {
        QuantityWeight result = QuantityMeasurementApp.add(
                new QuantityWeight(500.0, WeightUnit.GRAM),
                new QuantityWeight(0.5, WeightUnit.KILOGRAM)
        );
        assertEquals(1000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_PoundPlusKilogram() {
        QuantityWeight result = QuantityMeasurementApp.add(
                new QuantityWeight(2.20462, WeightUnit.POUND),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
        );
        assertEquals(4.40924, result.getValue(), 0.001);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Gram() {
        QuantityWeight result = QuantityMeasurementApp.add(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                WeightUnit.GRAM
        );
        assertEquals(2000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testAddition_WithZero() {
        QuantityWeight result = QuantityMeasurementApp.add(
                new QuantityWeight(5.0, WeightUnit.KILOGRAM),
                new QuantityWeight(0.0, WeightUnit.GRAM)
        );
        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_NegativeValues() {
        QuantityWeight result = QuantityMeasurementApp.add(
                new QuantityWeight(5.0, WeightUnit.KILOGRAM),
                new QuantityWeight(-2000.0, WeightUnit.GRAM)
        );
        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_LargeValues() {
        QuantityWeight result = QuantityMeasurementApp.add(
                new QuantityWeight(1e6, WeightUnit.KILOGRAM),
                new QuantityWeight(1e6, WeightUnit.KILOGRAM)
        );
        assertEquals(2e6, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_Commutativity() {
        QuantityWeight result1 = QuantityMeasurementApp.add(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                WeightUnit.KILOGRAM
        );
        QuantityWeight result2 = QuantityMeasurementApp.add(
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                WeightUnit.KILOGRAM
        );
        assertEquals(result1.getValue(), result2.getValue(), EPSILON);
    }

    // ==================== NULL & INVALID INPUT ====================

    @Test
    public void testWeightNull_Unit() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityWeight(1.0, null));
    }

    @Test
    public void testWeightInvalid_NaNValue() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM));
    }

    @Test
    public void testWeightAddition_NullOperand() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.add(new QuantityWeight(1.0, WeightUnit.KILOGRAM), null));
    }
}