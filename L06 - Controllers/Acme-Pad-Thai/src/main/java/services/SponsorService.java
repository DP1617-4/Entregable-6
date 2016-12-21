package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import repositories.SponsorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Bill;
import domain.Campaign;
import domain.Folder;
import domain.SocialIdentity;
import domain.Sponsor;

@Service
@Transactional
public class SponsorService {

	// managed repository---------------------
	@Autowired
	private SponsorRepository sponsorRepository;

	// supporting services -------------------
	@Autowired
	private FolderService folderService;

	@Autowired
	private LoginService loginService;

	// Basic CRUD methods --------------------
	public Sponsor create() {
		Sponsor sponsor = new Sponsor();
		UserAccount userAccount = new UserAccount();

		sponsor.setFolders(new ArrayList<Folder>());
		sponsor.setSocialIdentities(new ArrayList<SocialIdentity>());
		sponsor.setCampaigns(new ArrayList<Campaign>());
		sponsor.setBills(new ArrayList<Bill>());

		Authority authority = new Authority();
		authority.setAuthority(Authority.SPONSOR);
		Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		sponsor.setUserAccount(userAccount);

		return sponsor;
	}

	public Sponsor findOne(int sponsorId) {
		Sponsor retrieved;
		retrieved = sponsorRepository.findOne(sponsorId);
		return retrieved;
	}

	public Collection<Sponsor> findAll() {
		Collection<Sponsor> result;
		result = sponsorRepository.findAll();
		return result;
	}

	public Sponsor save(Sponsor sponsor) {
		if(sponsor.getUserAccount().getId()==0){
			// Creamos un codificador de hash para la password.
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			// Convertimos la pass del usuario a hash.
			String pass = encoder.encodePassword(sponsor.getUserAccount()
					.getPassword(), null);
			// Creamos una nueva cuenta y le pasamos los parametros.
			sponsor.getUserAccount().setPassword(pass);
		}
		Sponsor saved = sponsorRepository.save(sponsor);
		if (sponsor.getId() <= 0)
			folderService.initFolders(saved);
		return saved;
	}

	public void delete(Sponsor sponsor) {
		sponsorRepository.delete(sponsor);
	}

	// Auxiliary methods ---------------------
	public Sponsor findByUserAccount(UserAccount userAccount) {
		Sponsor result;
		result = sponsorRepository.findByPrincipal(userAccount.getId());
		return result;
	}

	@SuppressWarnings("static-access")
	public Sponsor findByPrincipal() {
		Sponsor result;
		UserAccount userAccount;
		userAccount = loginService.getPrincipal();
		result = findByUserAccount(userAccount);
		return result;
	}

	// Our other bussiness methods -----------
	public Double[][][] calculateMinAvgMaxFromCampaignsOfSponsors() {
		return sponsorRepository.calculateMinAvgMaxFromCampaignsOfSponsors();
	}

	public Double[][][] calculateMinAvgMaxFromCampaignsOfSponsorsByDate() {
		return sponsorRepository
				.calculateMinAvgMaxFromCampaignsOfSponsorsByDate();
	}

	public Collection<String> findCompaniesNameOfSponsors() {
		return sponsorRepository.findCompaniesNameOfSponsors();
	}

	public Collection<String> findCompaniesNameOfSponsorsByBills() {
		return sponsorRepository.findCompaniesNameOfSponsorsByBills();
	}

	public Collection<Sponsor> findInnactiveSponsorInThreeMonths() {
		return sponsorRepository.findInnactiveSponsorInThreeMonths();
	}

	public Collection<String> findCompaniesNameWhichSpentLessInCampaignsThanAvg() {
		return sponsorRepository
				.findCompaniesNameWhichSpentLessInCampaignsThanAvg();
	}

	public Collection<String> findCompaniesNameSpent90Percent() {
		return sponsorRepository.findCompaniesNameSpent90Percent();
	}
}