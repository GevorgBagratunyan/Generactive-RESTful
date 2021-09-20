package com.generactive.model.enums;

public enum Resolution {
    HD(1.0),
    FHD(2.0),
    UHD(4.0);

    private final double resolutionCoefficient;

    Resolution(double resolutionCoefficient) {
        this.resolutionCoefficient = resolutionCoefficient;
    }

    public double getResolutionCoefficient() {
        return resolutionCoefficient;
    }
}
