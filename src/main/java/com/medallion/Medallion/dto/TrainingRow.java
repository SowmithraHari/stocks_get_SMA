package com.medallion.Medallion.dto;

public class TrainingRow {
	
	private final double[] features;
    private final int label;

    public TrainingRow(double[] features, int label) {
        this.features = features;
        this.label = label;
    }

	public double[] getFeatures() {
		return features;
	}

	public int getLabel() {
		return label;
	}

}
