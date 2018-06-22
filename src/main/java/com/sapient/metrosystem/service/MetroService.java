package com.sapient.metrosystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.sapient.metrosystem.dao.MetroServiceRepository;
import com.sapient.metrosystem.exception.InsuffficentBalanceException;
import com.sapient.metrosystem.exception.MinimumBalanceException;
import com.sapient.metrosystem.model.CardTraxDetails;
import com.sapient.metrosystem.model.SmartCard;
import com.sapient.metrosystem.model.Station;
import com.sapient.metrosystem.utility.FareCalculatorUtility;

public class MetroService {

	ConcurrentHashMap<Station, AtomicInteger> stationFootFall = new ConcurrentHashMap<Station, AtomicInteger>();
	MetroServiceRepository repository = new MetroServiceRepository();

	public void swipeIn(SmartCard card, Station source, LocalDateTime dateTime) throws MinimumBalanceException {

		if (card.getBalance() < 5.5) {
			throw new MinimumBalanceException("Minimum Balance Rs 5.5 required. Please recharge card and try again");
		}
		stationFootFall.putIfAbsent(source, new AtomicInteger());
		stationFootFall.get(source).incrementAndGet();
		CardTraxDetails cardTraxDetails = new CardTraxDetails();
		cardTraxDetails.setCard(card);
		cardTraxDetails.setSource(source);
		cardTraxDetails.setStartDateTime(dateTime);
		repository.addTransaction(card, cardTraxDetails);
	}

	public void swipeOut(SmartCard card, Station destination, LocalDateTime dateTime)
			throws InsuffficentBalanceException {

		stationFootFall.putIfAbsent(destination, new AtomicInteger());
		stationFootFall.get(destination).incrementAndGet();
		CardTraxDetails cardTraxDetails = repository.getTransaction(card);
		cardTraxDetails.setDestination(destination);
		cardTraxDetails.setEndDateTime(dateTime);
		double totalFare = FareCalculatorUtility.gettotalFare(cardTraxDetails.getSource(), destination, dateTime);
		if (totalFare > card.getBalance()) {
			throw new InsuffficentBalanceException(
					"Balance is Insufficenet to swipeOut.  Please recharge card and try again");
		}
		cardTraxDetails.setFare(totalFare);
		cardTraxDetails.setDistance(cardTraxDetails.getSource().getdistance(destination));
		double curBalance = card.getBalance() - totalFare;
		card.setBalance(curBalance);
		cardTraxDetails.setCurBalance(curBalance);

		repository.addCompletetTransaction(card, cardTraxDetails);
	}

	public int calculateSwipeInSwipeOut(Station station) {
		return stationFootFall.get(station).intValue();
	}

	public List<CardTraxDetails> getCardReport(SmartCard card) {

		return repository.getCompleteTransaction(card);

	}
}
