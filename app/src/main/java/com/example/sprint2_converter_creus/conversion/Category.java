package com.example.sprint2_converter_creus.conversion;

import java.util.Arrays;
import java.util.List;

public enum Category {
    DISTANCE("Distance", Arrays.asList(
            new Unit("Meter", "m", 1.0),
            new Unit("Kilometer", "km", 1000.0),
            new Unit("Centimeter", "cm", 0.01),
            new Unit("Millimeter", "mm", 0.001),
            new Unit("Mile", "mi", 1609.344),
            new Unit("Yard", "yd", 0.9144),
            new Unit("Foot", "ft", 0.3048),
            new Unit("Inch", "in", 0.0254)
    )),

    TEMPERATURE("Temperature", Arrays.asList(
            new Unit("Celsius", "°C", 1.0),
            new Unit("Fahrenheit", "°F", 1.0),
            new Unit("Kelvin", "K", 1.0)
    )),

    VOLUME("Volume", Arrays.asList(
            new Unit("Liter", "L", 1.0),
            new Unit("Milliliter", "mL", 0.001),
            new Unit("Cubic Meter", "m³", 1000.0),
            new Unit("US Gallon", "gal", 3.78541),
            new Unit("Fluid Ounce", "fl oz", 0.0295735)
    )),

    MASS("Mass & Weight", Arrays.asList(
            new Unit("Gram", "g", 1.0),
            new Unit("Kilogram", "kg", 1000.0),
            new Unit("Milligram", "mg", 0.001),
            new Unit("Pound", "lb", 453.59237),
            new Unit("Ounce", "oz", 28.34952)
    )),

    TIME("Time", Arrays.asList(
            new Unit("Second", "s", 1.0),
            new Unit("Minute", "min", 60.0),
            new Unit("Hour", "h", 3600.0),
            new Unit("Day", "d", 86400.0),
            new Unit("Week", "wk", 604800.0)
    )),

    DIGITAL_INFO("Digital Information", Arrays.asList(
            new Unit("Byte", "B", 1.0),
            new Unit("Kilobyte", "KB", 1024.0),
            new Unit("Megabyte", "MB", 1048576.0),
            new Unit("Gigabyte", "GB", 1073741824.0),
            new Unit("Bit", "b", 0.125)
    )),

    CURRENCY("Currency", Arrays.asList(
            new Unit("Euro", "EUR", 1.0),
            new Unit("US Dollar", "USD", 0.92),        // 1 USD = 0.92 EUR
            new Unit("British Pound", "GBP", 1.17),    // 1 GBP = 1.17 EUR
            new Unit("Japanese Yen", "JPY", 0.0061)    // 1 JPY = 0.0061 EUR
    )),

    AREA("Area", Arrays.asList(
            new Unit("Square Meter", "m²", 1.0),
            new Unit("Square Kilometer", "km²", 1000000.0),
            new Unit("Hectare", "ha", 10000.0),
            new Unit("Acre", "ac", 4046.856),
            new Unit("Square Foot", "ft²", 0.092903)
    )),

    SPEED("Speed", Arrays.asList(
            new Unit("Meters per Second", "m/s", 1.0),
            new Unit("Kilometers per Hour", "km/h", 0.277778), // (1 / 3.6)
            new Unit("Miles per Hour", "mph", 0.44704),
            new Unit("Knot", "kn", 0.514444)
    )),

    PRESSURE("Pressure", Arrays.asList(
            new Unit("Pascal", "Pa", 1.0),
            new Unit("Bar", "bar", 100000.0),
            new Unit("Atmosphere", "atm", 101325.0),
            new Unit("PSI", "psi", 6894.76)
    ));

    private final String displayName;
    private final List<Unit> units;

    Category(String displayName, List<Unit> units) {
        this.displayName = displayName;
        this.units = units;
    }

    public String getDisplayName() { return displayName; }
    public List<Unit> getUnits() { return units; }

    @Override
    public String toString() { return displayName; }
}
