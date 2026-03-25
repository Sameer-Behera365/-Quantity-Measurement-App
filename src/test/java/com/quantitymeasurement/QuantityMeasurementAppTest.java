package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-4;

    // ==================== BACKWARD COMPATIBILITY ====================

    @Test
    public void testBackward_Length_FeetToInches_Equal() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(q1, q2);
    }

    @Test
    public void testBackward_Weight_KilogramToGram_Equal() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    public void testBackward_Volume_LitreToMillilitre_Equal() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(v1, v2);
    }

    @Test
    public void testBackward_Length_Addition() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.FEET)
                .add(new Quantity<>(12.0, LengthUnit.INCHES));
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    // ==================== SUBTRACTION - SAME UNIT ====================

    @Test
    public void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(5.0, LengthUnit.FEET));
        assertEquals(5.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testSubtraction_SameUnit_LitreMinusLitre() {
        Quantity<VolumeUnit> result = new Quantity<>(10.0, VolumeUnit.LITRE)
                .subtract(new Quantity<>(3.0, VolumeUnit.LITRE));
        assertEquals(7.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testSubtraction_SameUnit_KilogramMinusKilogram() {
        Quantity<WeightUnit> result = new Quantity<>(10.0, WeightUnit.KILOGRAM)
                .subtract(new Quantity<>(3.0, WeightUnit.KILOGRAM));
        assertEquals(7.0, result.getValue(), EPSILON);
    }

    // ==================== SUBTRACTION - CROSS UNIT ====================

    @Test
    public void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(6.0, LengthUnit.INCHES));
        assertEquals(9.5, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testSubtraction_CrossUnit_InchesMinusFeet() {
        Quantity<LengthUnit> result = new Quantity<>(120.0, LengthUnit.INCHES)
                .subtract(new Quantity<>(5.0, LengthUnit.FEET));
        assertEquals(60.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testSubtraction_CrossUnit_KilogramMinusGram() {
        Quantity<WeightUnit> result = new Quantity<>(10.0, WeightUnit.KILOGRAM)
                .subtract(new Quantity<>(5000.0, WeightUnit.GRAM));
        assertEquals(5.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testSubtraction_CrossUnit_LitreMinusMillilitre() {
        Quantity<VolumeUnit> result = new Quantity<>(5.0, VolumeUnit.LITRE)
                .subtract(new Quantity<>(500.0, VolumeUnit.MILLILITRE));
        assertEquals(4.5, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    // ==================== SUBTRACTION - EXPLICIT TARGET UNIT ====================

    @Test
    public void testSubtraction_ExplicitTargetUnit_Feet() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.FEET);
        assertEquals(9.5, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_Inches() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(6.0, LengthUnit.INCHES), LengthUnit.INCHES);
        assertEquals(114.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> result = new Quantity<>(5.0, VolumeUnit.LITRE)
                .subtract(new Quantity<>(2.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE);
        assertEquals(3000.0, result.getValue(), EPSILON);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_Gram() {
        Quantity<WeightUnit> result = new Quantity<>(10.0, WeightUnit.KILOGRAM)
                .subtract(new Quantity<>(5000.0, WeightUnit.GRAM), WeightUnit.GRAM);
        assertEquals(5000.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    // ==================== SUBTRACTION - EDGE CASES ====================

    @Test
    public void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET)
                .subtract(new Quantity<>(10.0, LengthUnit.FEET));
        assertEquals(-5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(120.0, LengthUnit.INCHES));
        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_WithZeroOperand() {
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET)
                .subtract(new Quantity<>(0.0, LengthUnit.INCHES));
        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_WithNegativeValues() {
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET)
                .subtract(new Quantity<>(-2.0, LengthUnit.FEET));
        assertEquals(7.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_NonCommutative() {
        Quantity<LengthUnit> result1 = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(5.0, LengthUnit.FEET));
        Quantity<LengthUnit> result2 = new Quantity<>(5.0, LengthUnit.FEET)
                .subtract(new Quantity<>(10.0, LengthUnit.FEET));
        assertEquals(5.0, result1.getValue(), EPSILON);
        assertEquals(-5.0, result2.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_WithLargeValues() {
        Quantity<WeightUnit> result = new Quantity<>(1e6, WeightUnit.KILOGRAM)
                .subtract(new Quantity<>(5e5, WeightUnit.KILOGRAM));
        assertEquals(5e5, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_ChainedOperations() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(2.0, LengthUnit.FEET))
                .subtract(new Quantity<>(1.0, LengthUnit.FEET));
        assertEquals(7.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtraction_Immutability() {
        Quantity<LengthUnit> original = new Quantity<>(10.0, LengthUnit.FEET);
        original.subtract(new Quantity<>(5.0, LengthUnit.FEET));
        assertEquals(10.0, original.getValue(), EPSILON);
    }

    // ==================== SUBTRACTION - NULL & CROSS CATEGORY ====================

    @Test
    public void testSubtraction_NullOperand_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FEET).subtract(null));
    }

    @Test
    public void testSubtraction_NullTargetUnit_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET), null));
    }

    @Test
    public void testSubtraction_CrossCategory_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Quantity length = new Quantity<>(10.0, LengthUnit.FEET);
            Quantity weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);
            length.subtract(weight);
        });
    }

    // ==================== SUBTRACTION - INVERSE OF ADDITION ====================

    @Test
    public void testSubtraction_AdditionInverse() {
        Quantity<LengthUnit> original = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> added = original.add(new Quantity<>(3.0, LengthUnit.FEET));
        Quantity<LengthUnit> result = added.subtract(new Quantity<>(3.0, LengthUnit.FEET));
        assertEquals(original.getValue(), result.getValue(), EPSILON);
    }

    // ==================== DIVISION - SAME UNIT ====================

    @Test
    public void testDivision_SameUnit_FeetDividedByFeet() {
        double result = new Quantity<>(10.0, LengthUnit.FEET)
                .divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(5.0, result, EPSILON);
    }

    @Test
    public void testDivision_SameUnit_LitreDividedByLitre() {
        double result = new Quantity<>(10.0, VolumeUnit.LITRE)
                .divide(new Quantity<>(5.0, VolumeUnit.LITRE));
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    public void testDivision_SameUnit_KilogramDividedByKilogram() {
        double result = new Quantity<>(10.0, WeightUnit.KILOGRAM)
                .divide(new Quantity<>(5.0, WeightUnit.KILOGRAM));
        assertEquals(2.0, result, EPSILON);
    }

    // ==================== DIVISION - CROSS UNIT ====================

    @Test
    public void testDivision_CrossUnit_InchesDividedByFeet() {
        double result = new Quantity<>(24.0, LengthUnit.INCHES)
                .divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    public void testDivision_CrossUnit_KilogramDividedByGram() {
        double result = new Quantity<>(2.0, WeightUnit.KILOGRAM)
                .divide(new Quantity<>(2000.0, WeightUnit.GRAM));
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    public void testDivision_CrossUnit_LitreDividedByMillilitre() {
        double result = new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                .divide(new Quantity<>(1.0, VolumeUnit.LITRE));
        assertEquals(1.0, result, EPSILON);
    }

    // ==================== DIVISION - RATIOS ====================

    @Test
    public void testDivision_RatioGreaterThanOne() {
        double result = new Quantity<>(10.0, LengthUnit.FEET)
                .divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(5.0, result, EPSILON);
        assertTrue(result > 1.0);
    }

    @Test
    public void testDivision_RatioLessThanOne() {
        double result = new Quantity<>(5.0, LengthUnit.FEET)
                .divide(new Quantity<>(10.0, LengthUnit.FEET));
        assertEquals(0.5, result, EPSILON);
        assertTrue(result < 1.0);
    }

    @Test
    public void testDivision_RatioEqualToOne() {
        double result = new Quantity<>(10.0, LengthUnit.FEET)
                .divide(new Quantity<>(10.0, LengthUnit.FEET));
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    public void testDivision_NonCommutative() {
        double result1 = new Quantity<>(10.0, LengthUnit.FEET)
                .divide(new Quantity<>(5.0, LengthUnit.FEET));
        double result2 = new Quantity<>(5.0, LengthUnit.FEET)
                .divide(new Quantity<>(10.0, LengthUnit.FEET));
        assertEquals(2.0, result1, EPSILON);
        assertEquals(0.5, result2, EPSILON);
    }

    @Test
    public void testDivision_WithLargeRatio() {
        double result = new Quantity<>(1e6, WeightUnit.KILOGRAM)
                .divide(new Quantity<>(1.0, WeightUnit.KILOGRAM));
        assertEquals(1e6, result, EPSILON);
    }

    @Test
    public void testDivision_WithSmallRatio() {
        double result = new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .divide(new Quantity<>(1e6, WeightUnit.KILOGRAM));
        assertEquals(1e-6, result, 1e-10);
    }

    @Test
    public void testDivision_Immutability() {
        Quantity<LengthUnit> original = new Quantity<>(10.0, LengthUnit.FEET);
        original.divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(10.0, original.getValue(), EPSILON);
    }

    // ==================== DIVISION - ERRORS ====================

    @Test
    public void testDivision_ByZero_ThrowsException() {
        assertThrows(ArithmeticException.class, () ->
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(0.0, LengthUnit.FEET)));
    }

    @Test
    public void testDivision_NullOperand_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FEET).divide(null));
    }

    @Test
    public void testDivision_CrossCategory_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Quantity length = new Quantity<>(10.0, LengthUnit.FEET);
            Quantity weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);
            length.divide(weight);
        });
    }

    // ==================== INTEGRATION ====================

    @Test
    public void testIntegration_SubtractThenDivide() {
        Quantity<LengthUnit> subtracted = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(4.0, LengthUnit.FEET));
        double result = subtracted.divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(3.0, result, EPSILON);
    }

    @Test
    public void testIntegration_AllCategories_Subtraction() {
        // Length
        Quantity<LengthUnit> lenResult = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(5.0, LengthUnit.FEET));
        assertEquals(5.0, lenResult.getValue(), EPSILON);

        // Weight
        Quantity<WeightUnit> wgtResult = new Quantity<>(10.0, WeightUnit.KILOGRAM)
                .subtract(new Quantity<>(5.0, WeightUnit.KILOGRAM));
        assertEquals(5.0, wgtResult.getValue(), EPSILON);

        // Volume
        Quantity<VolumeUnit> volResult = new Quantity<>(10.0, VolumeUnit.LITRE)
                .subtract(new Quantity<>(5.0, VolumeUnit.LITRE));
        assertEquals(5.0, volResult.getValue(), EPSILON);
    }

    @Test
    public void testIntegration_AllCategories_Division() {
        assertEquals(2.0,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(5.0, LengthUnit.FEET)), EPSILON);
        assertEquals(2.0,
                new Quantity<>(10.0, WeightUnit.KILOGRAM)
                        .divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)), EPSILON);
        assertEquals(2.0,
                new Quantity<>(10.0, VolumeUnit.LITRE)
                        .divide(new Quantity<>(5.0, VolumeUnit.LITRE)), EPSILON);
    }
}