package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Placeholder;

import repositories.PlaceholderRepository;

@Service
@Transactional
public class PlaceholderService {

	
	// Managed Repository
	
	@Autowired
	private PlaceholderRepository placeholderRepository;
	
	//Auxiliar Services
	

	// CRUD
	
	public Placeholder create(){
		Placeholder result;
		result = new Placeholder();
		result.setCadenas(new ArrayList<String>());
		return result;
	}
	
	public Placeholder findOne(int id){
		Placeholder result;
		result = placeholderRepository.findOne(id);
		return result;
	}
	
	public Collection<Placeholder> findAll(){
		Collection<Placeholder> result;
		result = placeholderRepository.findAll();
		return result;
	}
	
	public Placeholder save(Placeholder placeholder){
		Placeholder result;
		result = placeholderRepository.save(placeholder);
		return result;
	}
	
	public void delete(Placeholder placeholder){
		placeholderRepository.delete(placeholder);
	}
	
	//Business methods
	
	public Collection<Placeholder> findAllByContest(int contestId){
		Collection<Placeholder> result;
		result = placeholderRepository.findAllByContest(contestId);
		return result;
	}
	
	//Auxiliar methods
	
	
	
}
