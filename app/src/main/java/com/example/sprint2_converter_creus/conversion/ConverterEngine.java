package com.example.sprint2_converter_creus.conversion;

public class ConverterEngine {
    public static double convert(Category category, double value, Unit fromUnit, Unit toUnit) {
        if (category == Category.TEMPERATURE) {
            return convertTemperature(value, fromUnit.getSymbol(), toUnit.getSymbol());
        }

        // Lógica de las nueve categorías regulares
        double baseValue = value * fromUnit.getFactorToBase();
        return baseValue / toUnit.getFactorToBase();
    }

    private static double convertTemperature(double value, String fromSymbol, String toSymbol) {
        if (fromSymbol.equals(toSymbol)) return value;

        double celsius;
        switch (fromSymbol) {
            case "°F":
                celsius = (value - 32.0) * (5.0 / 9.0);
                break;
            case "K":
                celsius = value - 273.15;
                break;
            case "°C":
            default:
                celsius = value;
                break;
        }

        switch (toSymbol) {
            case "°F":
                return (celsius * (9.0 / 5.0)) + 32.0;
            case "K":
                return celsius + 273.15;
            case "°C":
            default:
                return celsius;
        }
    }
}