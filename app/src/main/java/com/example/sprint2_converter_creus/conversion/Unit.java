package com.example.sprint2_converter_creus.conversion;

public class Unit {
    private final String name;
    private final String symbol;
    private final double factorToBase; // Factor para convertir de esta unidad a la base

    public Unit(String name, String symbol, double factorToBase) {
        this.name = name;
        this.symbol = symbol;
        this.factorToBase = factorToBase;
    }

    public String getName() { return name; }
    public String getSymbol() { return symbol; }
    public double getFactorToBase() { return factorToBase; }

    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }
}
