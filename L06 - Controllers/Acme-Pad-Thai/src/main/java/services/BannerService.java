package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BannerRepository;
import domain.Banner;
import domain.Campaign;
import domain.Sponsor;

@Service
@Transactional
public class BannerService {

	//managed repository---------------------
	@Autowired
	private BannerRepository bannerRepository;
	
	//supporting services -------------------
	@Autowired
	private SponsorService sponsorService;
	
	//Basic CRUD methods --------------------
	public Banner create(Campaign campaign) {
		Sponsor sponsor = sponsorService.findByPrincipal();
		Assert.notNull(sponsor,"Dear user, you are not a sponsor.");
		Banner created;
		created = new Banner();
		created.setCampaign(campaign);
		return created;
	}
	
	public Banner findOne(int bannerId) {
		Banner retrieved;
		retrieved = bannerRepository.findOne(bannerId);
		return retrieved;
	}
	
	public Banner findOneToEdit(int id){
		Banner result;
		result = bannerRepository.findOne(id);
		Assert.notNull(result);
		checkPrincipal(result);
		return result;
	}
	
	public Collection<Banner> findAll(){
		Collection<Banner> banners;
		banners = bannerRepository.findAll();
		return banners;	
	}
	
	public Banner save(Banner banner) {
		Banner saved;
		saved = bannerRepository.save(banner);
		return saved;
	}
	
	public void delete(Banner banner) {
		bannerRepository.delete(banner);
	}
	

	public Banner findRandomStarBanner(){
		Banner result;
		Collection<Banner> banners;
		banners = bannerRepository.findRandomStarBanner();
		if(banners.isEmpty())
			result = null;
		else
			result = banners.iterator().next();
		return result;
	}
	
	//Auxiliary methods ---------------------
	
	//Our other bussiness methods -----------
	public void checkPrincipal(Banner b){
		sponsorService.findByPrincipal();
	}
	
	public Collection<Banner> paidBanners(int id) {
		return bannerRepository.numberOfBanners(id);
	}
	
	public Collection<Banner> findAllByCampaign(int campaignId) {
		Collection<Banner> banner = new ArrayList<Banner>();
		banner = bannerRepository.findAllByCampaign(campaignId); 
		return banner;
	}
}
