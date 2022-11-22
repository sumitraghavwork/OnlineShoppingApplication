package com.raghavEcomm.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghavEcomm.exceptions.CardException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.UserException;
import com.raghavEcomm.model.Card;
import com.raghavEcomm.model.Cart;
import com.raghavEcomm.model.CurrentUserSession;
import com.raghavEcomm.model.Customer;
import com.raghavEcomm.model.Product;
import com.raghavEcomm.repository.CardRepo;
import com.raghavEcomm.repository.CurrentUserSessionRepo;
import com.raghavEcomm.repository.CustomerRepo;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepo cardRepo;

	@Autowired
	private CustomerRepo uRepo;

	@Autowired
	private CurrentUserSessionRepo csdao;

	@Override
	public Card addCard(Card card, String customerKey) throws UserException, LoginException {

		CurrentUserSession loggedInUser = csdao.findByUuid(customerKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getCustomer() == false) {
			throw new UserException("Unauthorized Access! Only Customer can make changes");
		}

		Optional<Customer> existingUser = uRepo.findById(loggedInUser.getUserId());

		if (existingUser.isPresent()) {

			Customer customer = existingUser.get();

			Card savedcard = cardRepo.save(card);

			customer.getUserCards().add(savedcard);

			uRepo.save(customer);

			return savedcard;

		} else {
			throw new UserException("User Not Found");
		}

	}

	@Override
	public String deleteCard(Integer cardId, String customerKey) throws CardException, LoginException, UserException {

		CurrentUserSession loggedInUser = csdao.findByUuid(customerKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getCustomer() == false) {
			throw new UserException("Unauthorized Access! Only Customer can make changes");
		}

		Optional<Customer> existingUser = uRepo.findById(loggedInUser.getUserId());

		if (existingUser.isPresent()) {
			Customer customer = existingUser.get();

			List<Card> cards = customer.getUserCards();

			boolean flag = cards.removeIf((c) -> c.getCardId() == cardId);

			customer.setUserCards(cards);

			if (flag) {

				uRepo.save(customer);

				cardRepo.deleteById(cardId);

				return "Card Deleted Succesfully!";
			} else {
				throw new CardException("No card found for this user with cardId: " + cardId);
			}
		} else {
			throw new UserException("User Not Found");
		}
	}

	@Override
	public Card updateCard(Integer cardId, Card card, String customerKey)
			throws LoginException, UserException, CardException {

		CurrentUserSession loggedInUser = csdao.findByUuid(customerKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getCustomer() == false) {
			throw new UserException("Unauthorized Access! Only Customer can make changes");
		}

		Optional<Customer> existingUser = uRepo.findById(loggedInUser.getUserId());

		if (existingUser.isPresent()) {

			Customer customer = existingUser.get();

			List<Card> cards = customer.getUserCards();

			boolean flag = cards.removeIf((c) -> c.getCardId() == cardId);

			if (flag) {

				Card savedcard = cardRepo.save(card);
				cards.add(savedcard);

				customer.setUserCards(cards);

				uRepo.save(customer);

				return savedcard;
			} else {
				throw new CardException("No card found for this user with cardId: " + cardId);
			}

		} else {
			throw new UserException("User Not Found");
		}
	}

	@Override
	public Card getCardById(Integer cardId, String customerKey) throws LoginException, UserException, CardException {

		CurrentUserSession loggedInUser = csdao.findByUuid(customerKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getCustomer() == false) {
			throw new UserException("Unauthorized Access! Only Customer can make changes");
		}

		Optional<Customer> existingUser = uRepo.findById(loggedInUser.getUserId());

		if (existingUser.isPresent()) {
			Customer customer = existingUser.get();

			List<Card> cards = customer.getUserCards();

			Card card = null;
			for (Card cd : cards) {
				if (cd.getCardId() == cardId) {
					card = cd;
					break;
				}
			}
			if (card != null)
				return card;
			else
				throw new CardException("No card found for this user with cardId: " + cardId);

		} else {
			throw new UserException("User Not Found");
		}
	}

	@Override
	public List<Card> getAllCardOfCustomer(String customerKey) throws CardException, UserException, LoginException {

		CurrentUserSession loggedInUser = csdao.findByUuid(customerKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getCustomer() == false) {
			throw new UserException("Unauthorized Access! Only Customer can make changes");
		}

		Optional<Customer> existingUser = uRepo.findById(loggedInUser.getUserId());

		if (existingUser.isPresent()) {
			Customer customer = existingUser.get();

			List<Card> cards = customer.getUserCards();

			if (cards.isEmpty() == false)
				return cards;
			else
				throw new CardException("No card found for this user");

		} else {
			throw new UserException("User Not Found");
		}
	}

}
