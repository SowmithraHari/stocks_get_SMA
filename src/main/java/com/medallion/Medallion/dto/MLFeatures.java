package com.medallion.Medallion.dto;

public class MLFeatures {

	private double bullishProbability;

    private double bearishProbability;

    private double confidenceScore;

    private double spread;

    private double consistency;

    private double medianPrice;
    private double volatility;
    private double momentum;
    private double volumeFactor;
    
    public double[] toArray() {
        return new double[] {
                bullishProbability,
                bearishProbability,
                confidenceScore,
                spread,
                consistency,
                medianPrice,
                volatility,
                momentum,
                volumeFactor
        };
    }

    
	public double getVolatility() {
		return volatility;
	}


	public void setVolatility(double volatility) {
		this.volatility = volatility;
	}


	public double getMomentum() {
		return momentum;
	}


	public void setMomentum(double momentum) {
		this.momentum = momentum;
	}


	public double getVolumeFactor() {
		return volumeFactor;
	}


	public void setVolumeFactor(double volumeFactor) {
		this.volumeFactor = volumeFactor;
	}


	public double getBullishProbability() {
		return bullishProbability;
	}

	public void setBullishProbability(double bullishProbability) {
		this.bullishProbability = bullishProbability;
	}

	public double getBearishProbability() {
		return bearishProbability;
	}

	public void setBearishProbability(double bearishProbability) {
		this.bearishProbability = bearishProbability;
	}

	public double getConfidenceScore() {
		return confidenceScore;
	}

	public void setConfidenceScore(double confidenceScore) {
		this.confidenceScore = confidenceScore;
	}

	public double getSpread() {
		return spread;
	}

	public void setSpread(double spread) {
		this.spread = spread;
	}

	public double getConsistency() {
		return consistency;
	}

	public void setConsistency(double consistency) {
		this.consistency = consistency;
	}

	public double getMedianPrice() {
		return medianPrice;
	}

	public void setMedianPrice(double medianPrice) {
		this.medianPrice = medianPrice;
	}

    
}
