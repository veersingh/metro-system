package com.sapient.metrosystem.model;

public enum Station {

	A1,A2,A3,A4,A5,A6,A7,A8,A9,A10;
	
	public int getdistance(Station destination){
		
		return Math.abs(destination.ordinal()-this.ordinal());
	}
}
