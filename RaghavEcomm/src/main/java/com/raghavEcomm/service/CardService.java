package com.raghavEcomm.service;

import java.util.List;

import com.raghavEcomm.exceptions.CardException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.UserException;
import com.raghavEcomm.model.Card;

public interface CardService {

	public Card addCard(Card card, String customerKey) throws LoginException, UserException, CardException;

	public String deleteCard(Integer cardId, String customerKey) throws LoginException, UserException, CardException;

	public Card updateCard(Integer cardId, Card card, String customerKey)
			throws LoginException, UserException, CardException;

	public Card getCardById(Integer cardId, String customerKey) throws LoginException, UserException, CardException;

	public List<Card> getAllCardOfCustomer(String customerKey) throws LoginException, UserException, CardException;

}
