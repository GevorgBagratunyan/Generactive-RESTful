package com.generactive.model.enums;

public enum Complexity {
    ONE(1.0),
    TWO(2.0);

    private final double complexity;

    Complexity(double complexity) {
        this.complexity = complexity;
    }

    public double getValue() {
        return complexity;
    }
}
