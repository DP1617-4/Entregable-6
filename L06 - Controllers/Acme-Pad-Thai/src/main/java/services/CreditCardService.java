package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import domain.CreditCard;
import domain.Sponsor;

@Service
@Transactional
public class CreditCardService {

	//managed repository---------------------
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	//supporting services -------------------
	@Autowired
	private SponsorService sponsorService;
	
	//Basic CRUD methods --------------------
	public CreditCard create(Sponsor sponsor) {
//		sponsor = sponsorService.findByPrincipal();
//		Assert.notNull(sponsor,"Dear user, you are not a sponsor.");
		CreditCard creditCard = new CreditCard();
//		creditCard.setSponsor(sponsor);
		return creditCard;
	}
	
	public CreditCard findOne(int creditCardId) {
		Sponsor sponsor = sponsorService.findByPrincipal();
		Assert.notNull(sponsor,"Dear user, you are not a sponsor.");
		CreditCard retrieved = creditCardRepository.findOne(creditCardId);
		return retrieved;
	}
	
	public CreditCard findOneToEdit(int creditCardId) {
		CreditCard res;
		res = creditCardRepository.findOne(creditCardId);
		Assert.notNull(res, "That creditcard does not exist");
		return res;
	}
	
	public Collection<CreditCard> findAll() {
		return creditCardRepository.findAll();
	}
	
	public CreditCard save(CreditCard creditCard) {
		CreditCard saved = creditCardRepository.save(creditCard);
		return saved;
	}
	
	public void delete(CreditCard creditCard) {
		creditCardRepository.delete(creditCard);
	}
	
	//Auxiliary methods ---------------------
	public boolean expirationDate(CreditCard creditCard) {
		boolean res = false;
		Calendar moment = new GregorianCalendar();
		int year = creditCard.getExpirationYear();
		year += 2000;
		if(year == moment.get(Calendar.YEAR)) {
			if (creditCard.getExpirationMonth() >= moment.get(Calendar.MONTH)) {
				res = true;
			}
		} else if (year > moment.get(Calendar.YEAR)) {
			res = true;
		}
		return res;
	}
	
	//Our other bussiness methods -----------
	public CreditCard findCreditCardByPrincipal() {
		Sponsor sponsor;
		CreditCard res;
		sponsor = sponsorService.findByPrincipal();
		res = sponsor.getCreditCard();
		return res;
	}
	
	public CreditCard save2(CreditCard creditCard) { // Requirement 33.2
		Assert.isTrue(expirationDate(creditCard));
		CreditCard saved = this.save(creditCard);
		return saved;
	}
	
}
