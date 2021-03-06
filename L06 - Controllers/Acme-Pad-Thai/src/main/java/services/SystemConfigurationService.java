package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.SystemConfigurationRepository;
import domain.SystemConfiguration;

@Service
@Transactional
public class SystemConfigurationService {

	//managed repository---------------------
	@Autowired
	private SystemConfigurationRepository systemConfigurationRepository;
	
	//supporting services -------------------
	
	@Autowired
	private AdministratorService adminService;
	
	//Basic CRUD methods --------------------
	public SystemConfiguration create() {
		SystemConfiguration created;
		created = new SystemConfiguration();
		Collection<String> keywords = new ArrayList<String>();
		keywords.add("love");
		keywords.add("sex");
		keywords.add("cialis");
		keywords.add("viagra");
		created.setKeywords(keywords);
		return created;
	}
	
	public SystemConfiguration findOne(int systemConfigurationId) {
		adminService.checkAdministrator();
		SystemConfiguration retrieved;
		retrieved = systemConfigurationRepository.findOne(systemConfigurationId);
		return retrieved;
	}
	
	public Collection<SystemConfiguration> findAll(){
//		adminService.checkAdministrator();
		Collection<SystemConfiguration> result;
		result = systemConfigurationRepository.findAll();
		return result;
	}
	
	public SystemConfiguration save(SystemConfiguration systemConfiguration) {
		adminService.checkAdministrator();
		SystemConfiguration saved;
		saved = systemConfigurationRepository.save(systemConfiguration);
		return saved;
	}
	
	public void delete(SystemConfiguration systemConfiguration) {
		systemConfigurationRepository.delete(systemConfiguration);
	}
	
	public SystemConfiguration findMain(){
		SystemConfiguration systemConfiguration = systemConfigurationRepository.findMain();
		return systemConfiguration;
	}
	
	//Auxiliary methods ---------------------
	
	//Our other bussiness methods -----------

}
