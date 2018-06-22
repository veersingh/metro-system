package com.sapient.metrosystem.utility;

import java.time.LocalDateTime;

import com.sapient.metrosystem.FareService;
import com.sapient.metrosystem.FareServiceFactory;
import com.sapient.metrosystem.model.Station;

public class FareCalculatorUtility {

	public static double gettotalFare(Station source, Station destination, LocalDateTime dateTime) {

		FareService fareService = FareServiceFactory.getFareService(dateTime);
		int distance = source.getdistance(destination);
		double totalfare = fareService.getfarePerStation() * distance;
		return totalfare;
	}

}
