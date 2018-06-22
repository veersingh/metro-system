package com.sapient.metrosystem.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sapient.metrosystem.exception.InsuffficentBalanceException;
import com.sapient.metrosystem.exception.MinimumBalanceException;
import com.sapient.metrosystem.model.CardTraxDetails;
import com.sapient.metrosystem.model.SmartCard;
import com.sapient.metrosystem.model.Station;
import com.sapient.metrosystem.model.User;

import junit.framework.TestCase;

public class MetroServiceTest extends TestCase {

	private User user;
	private SmartCard card;
	private MetroService metroService;

	@Before
	protected void setUp() throws Exception {
		user = new User(1, "Ramesh");
		card = new SmartCard(1, 200, user);
		metroService = new MetroService();
	}

	@Test
	public void testMinimumBalanceAtSwipeIn() {
		card.setBalance(1);
		try {
			metroService.swipeIn(card, Station.A1, LocalDateTime.of(2016, Month.APRIL, 8, 18, 25));
		} catch (MinimumBalanceException e) {
			assertEquals("", e.getMessage(), "Minimum Balance Rs 5.5 required. Please recharge card and try again");
		}

	}

	@Test(expected = InsuffficentBalanceException.class)
	public void testSufficientBalanceAtSwipeOut() throws MinimumBalanceException {
		
		    card.setBalance(10);
		try{
			metroService.swipeIn(card, Station.A1, LocalDateTime.of(2016, Month.APRIL, 8, 18, 25));
			metroService.swipeOut(card, Station.A6, LocalDateTime.of(2016, Month.APRIL, 8, 18, 35));
		}
			catch (InsuffficentBalanceException e) {
				assertEquals("", e.getMessage(),"Balance is Insufficenet to swipeOut.  Please recharge card and try again");
			}
		
	}

	@Test
	public void testCalculateSwipeInSwipeOut() throws MinimumBalanceException, InsuffficentBalanceException {
		metroService.swipeIn(card, Station.A1, LocalDateTime.of(2018, 06, 03, 15, 07));
		metroService.swipeOut(card, Station.A6, LocalDateTime.of(2018, 06, 03, 15, 30));
		metroService.swipeIn(card, Station.A6, LocalDateTime.of(2018, 06, 03, 18, 07));
		metroService.swipeOut(card, Station.A10, LocalDateTime.of(2018, 06, 03, 18, 40));

		int totalFootfall = metroService.calculateSwipeInSwipeOut(Station.A6);

		Assert.assertEquals("Total swipe in and swipe out for station A6", 2, totalFootfall);

		assertEquals("FootFall for station A6 should be 2", metroService.calculateSwipeInSwipeOut(Station.A6), 2);
		assertEquals("FootFall for station A1 should be 1", metroService.calculateSwipeInSwipeOut(Station.A1), 1);
		assertEquals("FootFall for station A10 should be 1", metroService.calculateSwipeInSwipeOut(Station.A10), 1);
	}

	@Test
	public void testGetCardReport() throws MinimumBalanceException, InsuffficentBalanceException {
		metroService.swipeIn(card, Station.A2, LocalDateTime.of(2018, Month.JUNE, 03, 15, 07));
		metroService.swipeOut(card, Station.A6, LocalDateTime.of(2018, Month.JUNE, 03, 18, 35));
		metroService.swipeIn(card, Station.A6, LocalDateTime.of(2018, Month.JUNE, 04, 18, 07));
		metroService.swipeOut(card, Station.A10, LocalDateTime.of(2018, Month.JUNE, 04, 18, 50));
		final List<CardTraxDetails> trxs = metroService.getCardReport(card);
		assertEquals("There should be 2 trxs for this card", trxs.size(), 2);
		assertEquals("first One of the Trx should be charged 22", Double.valueOf(trxs.get(0).getFare()),
				Double.valueOf(22.0));

		assertEquals("Other Trx should be charged 28", Double.valueOf(trxs.get(1).getFare()), Double.valueOf(28.0));

	}

	
	@Test
	public void testCalculateSwipeInSwipeOut2() throws MinimumBalanceException, InsuffficentBalanceException {
		metroService.swipeIn(card, Station.A1, LocalDateTime.of(2018, 06, 03, 15, 07));
		metroService.swipeOut(card, Station.A6, LocalDateTime.of(2018, 06, 03, 15, 30));
		metroService.swipeIn(card, Station.A6, LocalDateTime.of(2018, 06, 03, 18, 07));
		metroService.swipeOut(card, Station.A10, LocalDateTime.of(2018, 06, 03, 18, 40));

		int totalFootfall = metroService.calculateSwipeInSwipeOut(Station.A6);

		Assert.assertEquals("Total swipe in and swipe out for station A6", 2, totalFootfall);

		assertEquals("FootFall for station A6 should be 2", metroService.calculateSwipeInSwipeOut(Station.A6), 3);
		assertEquals("FootFall for station A1 should be 1", metroService.calculateSwipeInSwipeOut(Station.A1), 2);
		assertEquals("FootFall for station A10 should be 1", metroService.calculateSwipeInSwipeOut(Station.A10), 1);
	}

	@Test
	public void testGetCardReport2() throws MinimumBalanceException, InsuffficentBalanceException {
		metroService.swipeIn(card, Station.A2, LocalDateTime.of(2018, Month.JUNE, 03, 15, 07));
		metroService.swipeOut(card, Station.A6, LocalDateTime.of(2018, Month.JUNE, 03, 18, 35));
		metroService.swipeIn(card, Station.A6, LocalDateTime.of(2018, Month.JUNE, 04, 18, 07));
		metroService.swipeOut(card, Station.A10, LocalDateTime.of(2018, Month.JUNE, 04, 18, 50));
		final List<CardTraxDetails> trxs = metroService.getCardReport(card);
		assertEquals("There should be 2 trxs for this card", trxs.size(), 2);
		assertEquals("first One of the Trx should be charged 22", Double.valueOf(trxs.get(0).getFare()),
				Double.valueOf(22.0));

		assertEquals("Other Trx should be charged 28", Double.valueOf(trxs.get(1).getFare()), Double.valueOf(27));

	}
}
