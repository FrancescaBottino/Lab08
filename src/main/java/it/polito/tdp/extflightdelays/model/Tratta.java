package it.polito.tdp.extflightdelays.model;

public class Tratta {
	
	private Airport a1;
	private Airport a2;
	private double distance;
	
	public Tratta(Airport a1, Airport a2, double distance) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.distance = distance;
	}

	public Airport getA1() {
		return a1;
	}

	public void setA1(Airport a1) {
		this.a1 = a1;
	}

	public Airport getA2() {
		return a2;
	}

	public void setA2(Airport a2) {
		this.a2 = a2;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	
}
	
	
	