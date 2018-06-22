package com.sapient.metrosystem.model;

import java.time.LocalDateTime;

import javax.swing.text.StyledEditorKit.StyledTextAction;

public class CardTraxDetails {

	private SmartCard card;

	private Station source;
	private Station destination;
	private int distance;
	private double fare;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private double curBalance;

	public SmartCard getCard() {
		return card;
	}

	public void setCard(SmartCard card) {
		this.card = card;
	}

	public Station getSource() {
		return source;
	}

	public void setSource(Station source) {
		this.source = source;
	}

	

	public Station getDestination() {
		return destination;
	}

	public void setDestination(Station destination) {
		this.destination = destination;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public double getCurBalance() {
		return curBalance;
	}

	public void setCurBalance(double curBalance) {
		this.curBalance = curBalance;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CardTraxDetails [card=");
		builder.append(card);
		builder.append(", source=");
		builder.append(source);
		builder.append(", destination=");
		builder.append(destination);
		builder.append(", distance=");
		builder.append(distance);
		builder.append(", fare=");
		builder.append(fare);
		builder.append(", startDateTime=");
		builder.append(startDateTime);
		builder.append(", endDateTime=");
		builder.append(endDateTime);
		builder.append(", curBalance=");
		builder.append(curBalance);
		builder.append("]");
		return builder.toString();
	}

}
