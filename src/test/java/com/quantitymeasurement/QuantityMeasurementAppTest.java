package com.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-4;

    // ==================== BACKWARD COMPATIBILITY (UC12) ====================

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
    public void testBackward_Addition_FeetPlusInches() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.FEET)
                .add(new Quantity<>(12.0, LengthUnit.INCHES));
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testBackward_Subtraction_FeetMinusInches() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(6.0, LengthUnit.INCHES));
        assertEquals(9.5, result.getValue(), EPSILON);
    }

    @Test
    public void testBackward_Division_FeetDividedByFeet() {
        double result = new Quantity<>(10.0, LengthUnit.FEET)
                .divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(5.0, result, EPSILON);
    }

    // ==================== VALIDATION CONSISTENCY ACROSS OPERATIONS ====================

    @Test
    public void testValidation_NullOperand_Add_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FEET).add(null));
    }

    @Test
    public void testValidation_NullOperand_Subtract_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FEET).subtract(null));
    }

    @Test
    public void testValidation_NullOperand_Divide_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FEET).divide(null));
    }

    @Test
    public void testValidation_CrossCategory_Add_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Quantity length = new Quantity<>(10.0, LengthUnit.FEET);
            Quantity weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);
            length.add(weight);
        });
    }

    @Test
    public void testValidation_CrossCategory_Subtract_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Quantity length = new Quantity<>(10.0, LengthUnit.FEET);
            Quantity weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);
            length.subtract(weight);
        });
    }

    @Test
    public void testValidation_CrossCategory_Divide_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Quantity length = new Quantity<>(10.0, LengthUnit.FEET);
            Quantity weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);
            length.divide(weight);
        });
    }

    @Test
    public void testValidation_NullTargetUnit_Add_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FEET)
                        .add(new Quantity<>(5.0, LengthUnit.FEET), null));
    }

    @Test
    public void testValidation_NullTargetUnit_Subtract_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET), null));
    }

    // ==================== ADDITION BEHAVIOR PRESERVED ====================

    @Test
    public void testAdd_SameUnit_FeetPlusFeet() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.FEET)
                .add(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(3.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAdd_CrossUnit_KilogramPlusGram() {
        Quantity<WeightUnit> result = new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .add(new Quantity<>(1000.0, WeightUnit.GRAM));
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAdd_ExplicitTargetUnit_Gram() {
        Quantity<WeightUnit> result = new Quantity<>(10.0, WeightUnit.KILOGRAM)
                .add(new Quantity<>(5000.0, WeightUnit.GRAM), WeightUnit.GRAM);
        assertEquals(15000.0, result.getValue(), EPSILON);
    }

    // ==================== SUBTRACTION BEHAVIOR PRESERVED ====================

    @Test
    public void testSubtract_SameUnit_LitreMinusLitre() {
        Quantity<VolumeUnit> result = new Quantity<>(10.0, VolumeUnit.LITRE)
                .subtract(new Quantity<>(3.0, VolumeUnit.LITRE));
        assertEquals(7.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtract_CrossUnit_KilogramMinusGram() {
        Quantity<WeightUnit> result = new Quantity<>(10.0, WeightUnit.KILOGRAM)
                .subtract(new Quantity<>(5000.0, WeightUnit.GRAM));
        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtract_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> result = new Quantity<>(5.0, VolumeUnit.LITRE)
                .subtract(new Quantity<>(2.0, VolumeUnit.LITRE), VolumeUnit.MILLILITRE);
        assertEquals(3000.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtract_ResultingInNegative() {
        Quantity<LengthUnit> result = new Quantity<>(5.0, LengthUnit.FEET)
                .subtract(new Quantity<>(10.0, LengthUnit.FEET));
        assertEquals(-5.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtract_ResultingInZero() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(120.0, LengthUnit.INCHES));
        assertEquals(0.0, result.getValue(), EPSILON);
    }

    @Test
    public void testSubtract_NonCommutative() {
        Quantity<LengthUnit> result1 = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(5.0, LengthUnit.FEET));
        Quantity<LengthUnit> result2 = new Quantity<>(5.0, LengthUnit.FEET)
                .subtract(new Quantity<>(10.0, LengthUnit.FEET));
        assertEquals(5.0, result1.getValue(), EPSILON);
        assertEquals(-5.0, result2.getValue(), EPSILON);
    }

    // ==================== DIVISION BEHAVIOR PRESERVED ====================

    @Test
    public void testDivide_SameUnit_FeetByFeet() {
        double result = new Quantity<>(10.0, LengthUnit.FEET)
                .divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(5.0, result, EPSILON);
    }

    @Test
    public void testDivide_CrossUnit_InchesByFeet() {
        double result = new Quantity<>(24.0, LengthUnit.INCHES)
                .divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    public void testDivide_ByZero_ThrowsException() {
        assertThrows(ArithmeticException.class, () ->
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(0.0, LengthUnit.FEET)));
    }

    @Test
    public void testDivide_NonCommutative() {
        double result1 = new Quantity<>(10.0, LengthUnit.FEET)
                .divide(new Quantity<>(5.0, LengthUnit.FEET));
        double result2 = new Quantity<>(5.0, LengthUnit.FEET)
                .divide(new Quantity<>(10.0, LengthUnit.FEET));
        assertEquals(2.0, result1, EPSILON);
        assertEquals(0.5, result2, EPSILON);
    }

    // ==================== ROUNDING CONSISTENCY ====================

    @Test
    public void testRounding_AddResult_TwoDecimalPlaces() {
        Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.FEET)
                .add(new Quantity<>(6.0, LengthUnit.INCHES));
        assertEquals(1.5, result.getValue(), EPSILON);
    }

    @Test
    public void testRounding_SubtractResult_TwoDecimalPlaces() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(6.0, LengthUnit.INCHES));
        assertEquals(9.5, result.getValue(), EPSILON);
    }

    // ==================== IMMUTABILITY ====================

    @Test
    public void testImmutability_AfterAdd() {
        Quantity<LengthUnit> original = new Quantity<>(10.0, LengthUnit.FEET);
        original.add(new Quantity<>(5.0, LengthUnit.FEET));
        assertEquals(10.0, original.getValue(), EPSILON);
    }

    @Test
    public void testImmutability_AfterSubtract() {
        Quantity<LengthUnit> original = new Quantity<>(10.0, LengthUnit.FEET);
        original.subtract(new Quantity<>(5.0, LengthUnit.FEET));
        assertEquals(10.0, original.getValue(), EPSILON);
    }

    @Test
    public void testImmutability_AfterDivide() {
        Quantity<LengthUnit> original = new Quantity<>(10.0, LengthUnit.FEET);
        original.divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(10.0, original.getValue(), EPSILON);
    }

    // ==================== ALL CATEGORIES ====================

    @Test
    public void testAllOperations_AllCategories_Addition() {
        assertEquals(3.0, new Quantity<>(1.0, LengthUnit.FEET)
                .add(new Quantity<>(2.0, LengthUnit.FEET)).getValue(), EPSILON);
        assertEquals(3.0, new Quantity<>(1.0, WeightUnit.KILOGRAM)
                .add(new Quantity<>(2.0, WeightUnit.KILOGRAM)).getValue(), EPSILON);
        assertEquals(3.0, new Quantity<>(1.0, VolumeUnit.LITRE)
                .add(new Quantity<>(2.0, VolumeUnit.LITRE)).getValue(), EPSILON);
    }

    @Test
    public void testAllOperations_AllCategories_Subtraction() {
        assertEquals(5.0, new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(5.0, LengthUnit.FEET)).getValue(), EPSILON);
        assertEquals(5.0, new Quantity<>(10.0, WeightUnit.KILOGRAM)
                .subtract(new Quantity<>(5.0, WeightUnit.KILOGRAM)).getValue(), EPSILON);
        assertEquals(5.0, new Quantity<>(10.0, VolumeUnit.LITRE)
                .subtract(new Quantity<>(5.0, VolumeUnit.LITRE)).getValue(), EPSILON);
    }

    @Test
    public void testAllOperations_AllCategories_Division() {
        assertEquals(2.0, new Quantity<>(10.0, LengthUnit.FEET)
                .divide(new Quantity<>(5.0, LengthUnit.FEET)), EPSILON);
        assertEquals(2.0, new Quantity<>(10.0, WeightUnit.KILOGRAM)
                .divide(new Quantity<>(5.0, WeightUnit.KILOGRAM)), EPSILON);
        assertEquals(2.0, new Quantity<>(10.0, VolumeUnit.LITRE)
                .divide(new Quantity<>(5.0, VolumeUnit.LITRE)), EPSILON);
    }

    // ==================== CHAIN OPERATIONS ====================

    @Test
    public void testChain_AddThenSubtract() {
        Quantity<LengthUnit> result = new Quantity<>(10.0, LengthUnit.FEET)
                .add(new Quantity<>(5.0, LengthUnit.FEET))
                .subtract(new Quantity<>(3.0, LengthUnit.FEET));
        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    public void testChain_SubtractThenDivide() {
        double result = new Quantity<>(10.0, LengthUnit.FEET)
                .subtract(new Quantity<>(4.0, LengthUnit.FEET))
                .divide(new Quantity<>(2.0, LengthUnit.FEET));
        assertEquals(3.0, result, EPSILON);
    }

    @Test
    public void testChain_AddThenSubtract_InverseRelationship() {
        Quantity<LengthUnit> original = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = original
                .add(new Quantity<>(3.0, LengthUnit.FEET))
                .subtract(new Quantity<>(3.0, LengthUnit.FEET));
        assertEquals(original.getValue(), result.getValue(), EPSILON);
    }
}