package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-4;

    // ==================== UC10 BACKWARD COMPATIBILITY ====================

    @Test
    public void testIMeasurable_LengthUnitImplementation() {
        assertTrue(LengthUnit.FEET instanceof IMeasurable);
    }

    @Test
    public void testIMeasurable_WeightUnitImplementation() {
        assertTrue(WeightUnit.KILOGRAM instanceof IMeasurable);
    }

    @Test
    public void testGenericQuantity_Length_FeetToInches_Equal() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(q1, q2);
    }

    @Test
    public void testGenericQuantity_Weight_KilogramToGram_Equal() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    // ==================== VOLUMEUNIT ENUM TESTS ====================

    @Test
    public void testVolumeUnit_LitreConversionFactor() {
        assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor(), EPSILON);
    }

    @Test
    public void testVolumeUnit_MillilitreConversionFactor() {
        assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor(), EPSILON);
    }

    @Test
    public void testVolumeUnit_GallonConversionFactor() {
        assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), EPSILON);
    }

    @Test
    public void testVolumeUnit_ConvertToBaseUnit_MillilitreToLitre() {
        assertEquals(1.0, VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0), EPSILON);
    }

    @Test
    public void testVolumeUnit_ConvertToBaseUnit_GallonToLitre() {
        assertEquals(3.78541, VolumeUnit.GALLON.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    public void testVolumeUnit_ConvertFromBaseUnit_LitreToMillilitre() {
        assertEquals(1000.0, VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    public void testVolumeUnit_ConvertFromBaseUnit_LitreToGallon() {
        assertEquals(1.0, VolumeUnit.GALLON.convertFromBaseUnit(3.78541), EPSILON);
    }

    @Test
    public void testIMeasurable_VolumeUnitImplementation() {
        assertTrue(VolumeUnit.LITRE instanceof IMeasurable);
        assertTrue(VolumeUnit.MILLILITRE instanceof IMeasurable);
        assertTrue(VolumeUnit.GALLON instanceof IMeasurable);
    }

    @Test
    public void testVolumeUnit_GetUnitName() {
        assertEquals("LITRE", VolumeUnit.LITRE.getUnitName());
        assertEquals("MILLILITRE", VolumeUnit.MILLILITRE.getUnitName());
        assertEquals("GALLON", VolumeUnit.GALLON.getUnitName());
    }

    // ==================== VOLUME EQUALITY TESTS ====================

    @Test
    public void testEquality_LitreToLitre_SameValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(v1, v2, "1.0 L should equal 1.0 L");
    }

    @Test
    public void testEquality_LitreToLitre_DifferentValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        assertNotEquals(v1, v2, "1.0 L should not equal 2.0 L");
    }

    @Test
    public void testEquality_LitreToMillilitre_EquivalentValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(v1, v2, "1.0 L should equal 1000.0 mL");
    }

    @Test
    public void testEquality_MillilitreToLitre_EquivalentValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(v1, v2, "1000.0 mL should equal 1.0 L (symmetry)");
    }

    @Test
    public void testEquality_GallonToGallon_SameValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.GALLON);
        assertEquals(v1, v2, "1.0 gallon should equal 1.0 gallon");
    }

    @Test
    public void testEquality_LitreToGallon_EquivalentValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.GALLON);
        assertEquals(v1, v2, "3.78541 L should equal 1.0 gallon");
    }

    @Test
    public void testEquality_GallonToLitre_EquivalentValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> v2 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        assertEquals(v1, v2, "1.0 gallon should equal 3.78541 L (symmetry)");
    }

    @Test
    public void testEquality_NullComparison() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertNotEquals(v1, null);
    }

    @Test
    public void testEquality_SameReference() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertEquals(v1, v1);
    }

    @Test
    public void testEquality_ZeroValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(0.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
        assertEquals(v1, v2);
    }

    @Test
    public void testEquality_NegativeVolume() {
        Quantity<VolumeUnit> v1 = new Quantity<>(-1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(-1000.0, VolumeUnit.MILLILITRE);
        assertEquals(v1, v2);
    }

    @Test
    public void testEquality_LargeVolumeValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1000000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.LITRE);
        assertEquals(v1, v2);
    }

    @Test
    public void testEquality_SmallVolumeValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(0.001, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.MILLILITRE);
        assertEquals(v1, v2);
    }

    // ==================== CROSS CATEGORY PREVENTION ====================

    @Test
    public void testEquality_VolumeVsLength_Incompatible() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        assertNotEquals(volume, length);
    }

    @Test
    public void testEquality_VolumeVsWeight_Incompatible() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(volume, weight);
    }

    // ==================== VOLUME CONVERSION TESTS ====================

    @Test
    public void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> result = new Quantity<>(1.0, VolumeUnit.LITRE)
                .convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void testConversion_MillilitreToLitre() {
        Quantity<VolumeUnit> result = new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .convertTo(VolumeUnit.LITRE);
        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> result = new Quantity<>(1.0, VolumeUnit.GALLON)
                .convertTo(VolumeUnit.LITRE);
        assertEquals(3.78541, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_LitreToGallon() {
        Quantity<VolumeUnit> result = new Quantity<>(3.78541, VolumeUnit.LITRE)
                .convertTo(VolumeUnit.GALLON);
        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_MillilitreToGallon() {
        Quantity<VolumeUnit> result = new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .convertTo(VolumeUnit.GALLON);
        assertEquals(0.264172, result.getValue(), 0.001);
    }

    @Test
    public void testConversion_SameUnit() {
        Quantity<VolumeUnit> result = new Quantity<>(5.0, VolumeUnit.LITRE)
                .convertTo(VolumeUnit.LITRE);
        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_ZeroValue() {
        Quantity<VolumeUnit> result = new Quantity<>(0.0, VolumeUnit.LITRE)
                .convertTo(VolumeUnit.MILLILITRE);
        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_NegativeValue() {
        Quantity<VolumeUnit> result = new Quantity<>(-1.0, VolumeUnit.LITRE)
                .convertTo(VolumeUnit.MILLILITRE);
        assertEquals(-1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConversion_RoundTrip() {
        Quantity<VolumeUnit> result = new Quantity<>(1.5, VolumeUnit.LITRE)
                .convertTo(VolumeUnit.MILLILITRE)
                .convertTo(VolumeUnit.LITRE);
        assertEquals(1.5, result.getValue(), EPSILON);
    }

    // ==================== VOLUME ADDITION TESTS ====================

    @Test
    public void testAddition_SameUnit_LitrePlusLitre() {
        Quantity<VolumeUnit> result = new Quantity<>(1.0, VolumeUnit.LITRE)
                .add(new Quantity<>(2.0, VolumeUnit.LITRE));
        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testAddition_SameUnit_MillilitrePlusMillilitre() {
        Quantity<VolumeUnit> result = new Quantity<>(500.0, VolumeUnit.MILLILITRE)
                .add(new Quantity<>(500.0, VolumeUnit.MILLILITRE));
        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> result = new Quantity<>(1.0, VolumeUnit.LITRE)
                .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_MillilitrePlusLitre() {
        Quantity<VolumeUnit> result = new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .add(new Quantity<>(1.0, VolumeUnit.LITRE));
        assertEquals(2000.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_GallonPlusLitre() {
        Quantity<VolumeUnit> result = new Quantity<>(1.0, VolumeUnit.GALLON)
                .add(new Quantity<>(3.78541, VolumeUnit.LITRE));
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Litre() {
        Quantity<VolumeUnit> result = new Quantity<>(1.0, VolumeUnit.LITRE)
                .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.LITRE);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> result = new Quantity<>(1.0, VolumeUnit.LITRE)
                .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.MILLILITRE);
        assertEquals(2000.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Gallon() {
        Quantity<VolumeUnit> result = new Quantity<>(3.78541, VolumeUnit.LITRE)
                .add(new Quantity<>(3.78541, VolumeUnit.LITRE), VolumeUnit.GALLON);
        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    public void testAddition_WithZero() {
        Quantity<VolumeUnit> result = new Quantity<>(5.0, VolumeUnit.LITRE)
                .add(new Quantity<>(0.0, VolumeUnit.MILLILITRE));
        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_NegativeValues() {
        Quantity<VolumeUnit> result = new Quantity<>(5.0, VolumeUnit.LITRE)
                .add(new Quantity<>(-2000.0, VolumeUnit.MILLILITRE));
        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_LargeValues() {
        Quantity<VolumeUnit> result = new Quantity<>(1e6, VolumeUnit.LITRE)
                .add(new Quantity<>(1e6, VolumeUnit.LITRE));
        assertEquals(2e6, result.getValue(), EPSILON);
    }

    @Test
    public void testAddition_Commutativity() {
        Quantity<VolumeUnit> result1 = new Quantity<>(1.0, VolumeUnit.LITRE)
                .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.LITRE);
        Quantity<VolumeUnit> result2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .add(new Quantity<>(1.0, VolumeUnit.LITRE), VolumeUnit.LITRE);
        assertEquals(result1.getValue(), result2.getValue(), EPSILON);
    }

    // ==================== NULL & INVALID INPUT ====================

    @Test
    public void testVolume_NullUnit_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(1.0, (VolumeUnit) null));
    }

    @Test
    public void testVolume_NaNValue_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(Double.NaN, VolumeUnit.LITRE));
    }

    @Test
    public void testVolume_NullOperand_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(1.0, VolumeUnit.LITRE).add(null));
    }
}