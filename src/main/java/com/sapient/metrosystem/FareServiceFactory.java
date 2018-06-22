package com.sapient.metrosystem;

import java.time.LocalDateTime;

public class FareServiceFactory {

	public static FareService getFareService(LocalDateTime dateTime){
		
		if(dateTime.getDayOfWeek().getValue()==6 || dateTime.getDayOfWeek().getValue()==7){
			return new WeekendFareService();
		}else{
			return new WeekdaysFareService();
		}
	}
}
