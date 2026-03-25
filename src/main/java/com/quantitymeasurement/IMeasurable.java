package com.quantitymeasurement;

/**
 * IMeasurable interface - defines the contract for all measurement units.
 * Every unit enum (LengthUnit, WeightUnit, etc.) must implement this interface.
 * Single Responsibility: standardizes unit behavior across all categories.
 */
public interface IMeasurable {
    double getConversionFactor();
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();
}