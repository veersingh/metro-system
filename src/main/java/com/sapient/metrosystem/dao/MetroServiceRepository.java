package com.sapient.metrosystem.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.sapient.metrosystem.model.CardTraxDetails;
import com.sapient.metrosystem.model.SmartCard;

public class MetroServiceRepository {

	private ConcurrentHashMap<SmartCard, CardTraxDetails> transaction = new ConcurrentHashMap<SmartCard, CardTraxDetails>();
	private ConcurrentHashMap<SmartCard, List<CardTraxDetails>> completeTransaction = new ConcurrentHashMap<SmartCard, List<CardTraxDetails>>();

	public void addTransaction(SmartCard card, CardTraxDetails cardTraxDetails) {

		transaction.put(card, cardTraxDetails);
	}

	public CardTraxDetails getTransaction(SmartCard card) {

		return transaction.get(card);
	}

	public void addCompletetTransaction(SmartCard card, CardTraxDetails cardTraxDetails) {

		completeTransaction.putIfAbsent(card, new ArrayList<CardTraxDetails>());
		completeTransaction.get(card).add(cardTraxDetails);
	}

	public List<CardTraxDetails> getCompleteTransaction(SmartCard card) {
		return completeTransaction.get(card);
	}
}
