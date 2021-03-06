package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CampaignRepository;
import domain.Banner;
import domain.Campaign;
import domain.Sponsor;

@Service
@Transactional
public class CampaignService {

	//managed repository---------------------
	@Autowired
	private CampaignRepository campaignRepository;
	
	//supporting services -------------------
	@Autowired
	private SponsorService sponsorService;
	
	@Autowired
	private AdministratorService adminService;
	
	//Basic CRUD methods --------------------
	public Campaign create() { 
		Campaign created;
		Sponsor sponsor = sponsorService.findByPrincipal();
		created = new Campaign();
		created.setSponsor(sponsor);
		created.setDeleted(false);
		created.setStarred(false);
		created.setBanners(new ArrayList<Banner>());
		return created;
	}
	
	public Campaign findOne(int campaignId) {
		Campaign result;
		result = campaignRepository.findOne(campaignId);
		Assert.notNull(result);
		return result;
	}
	
	public Collection<Campaign> findAllNotDeleted() {
		Collection<Campaign> notDeleted = new ArrayList<Campaign>();
		for(Campaign c: campaignRepository.findAll()){
			if(c.getDeleted()==false){
				notDeleted.add(c);
			}
		}
		return notDeleted;
	}
	
	public Campaign save(Campaign campaign) {
		Campaign result;
		checkPrincipalSponsor(campaign);
		Assert.isTrue(campaign.getStartDate().before(campaign.getEndDate()));
		result = campaignRepository.save(campaign);
		return result;
	}
	
	public Campaign saveAdmin(Campaign campaign) {
		Campaign result;
		adminService.checkAdministrator();
		result = campaignRepository.save(campaign);
		return result;
	}
	
	public void delete(Campaign campaign) {
		checkPrincipalSponsor(campaign);
		campaignRepository.delete(campaign);
	}
	
	//Auxiliary methods ---------------------
	public Collection<Campaign> allCampaigns() {
		Collection<Campaign> res = campaignRepository.findAll();
		return res;
	}
	
	public void checkPrincipalSponsor(Campaign campaign){
		Sponsor sponsor;
		sponsor = sponsorService.findByPrincipal();
		Assert.isTrue(campaign.getSponsor().equals(sponsor));
	}
	
	//Our other bussiness methods -----------
	public Collection<Campaign> findCampaignsByPrincipal(){
		Sponsor sponsor;
		Collection<Campaign> result;
		sponsor = sponsorService.findByPrincipal();
		result = campaignRepository.findAllBySponsorId(sponsor.getId());
		return result;
	}
	
	public Campaign save2(Campaign campaign) { // Requirement 33.1
		Sponsor sponsor = sponsorService.findByPrincipal();
		Assert.notNull(sponsor,"Dear user, you are not a sponsor.");
		Assert.isTrue(!campaignPassed(campaign));
		Assert.isTrue(!activeCampaign(campaign));
		Assert.isTrue(campaign.getEndDate().after(campaign.getStartDate()));
		Campaign saved = this.save(campaign);
		return saved;
	}
	
	public Campaign delete2(Campaign campaign) { // Requirement 33.1
		Sponsor sponsor = sponsorService.findByPrincipal();
		Assert.notNull(sponsor,"Dear user, you are not a sponsor.");
		Assert.isTrue(!campaignPassed(campaign));
		Assert.isTrue(!activeCampaign(campaign));
		campaign.setDeleted(true);
		Campaign saved = this.save(campaign);
		return saved;
	}
	
	public boolean activeCampaign(Campaign c) {
		boolean res = false;
		Date moment = new Date();
		if(moment.after(c.getStartDate()) && moment.before(c.getEndDate())) {
			res = true;
		}
		return res;
	}
	
	public boolean campaignPassed(Campaign c) {
		boolean res = false;
		Date moment = new Date();
		if(moment.after(c.getEndDate())) {
			res = true;
		}
		return res;
	}
	
	public void modifyStarred(Campaign c) {
		if(c.getStarred() == false) {
			c.setStarred(true);
		} else {
			c.setStarred(false);
		}
	}
}
