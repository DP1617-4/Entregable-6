package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ContestRepository;
import domain.Contest;
import domain.Recipe;

@Service
@Transactional
public class ContestService {

	//managed repository-------------------
			@Autowired
			private ContestRepository contestRepository;
			
			//supporting services-------------------
			@Autowired
			private RecipeService recipeService;
			//Basic CRUD methods-------------------
			
			public Contest create(){
				
				Contest created;
				created = new Contest();
				return created;
			}
			
			public Contest findOne(int contestId){
				
				Contest retrieved;
				retrieved = contestRepository.findOne(contestId);
				return retrieved;
			}

			public Contest save(Contest contest){
				Contest saved;
				Contest previous;
				Date cTime = new Date();
				if(contest.getId() != 0){
					previous = findOne(contest.getId());
					if(previous.getOpeningTime() != contest.getOpeningTime())
						Assert.isTrue(cTime.before(contest.getOpeningTime()) && cTime.before(previous.getOpeningTime()));
					if(previous.getClosingTime() != contest.getClosingTime())
						Assert.isTrue(cTime.before(contest.getClosingTime()) && cTime.before(previous.getClosingTime()));
					if(previous.getTitle() != contest.getTitle())
						Assert.isTrue(contest.getQualified().isEmpty());
				}
				saved = contestRepository.save(contest);
				return saved;
				
			}
			
			public void delete(Contest contest){
				Assert.isTrue(contest.getQualified().isEmpty());
				contestRepository.delete(contest);
				
			}
			
			//Other Bussiness Methods
			
			public void setQualified(Contest contest, Recipe recipe){
				recipe.setContest(contest);
				recipe = recipeService.save(recipe);
				contest.getQualified().add(recipe);
				contest = this.save(contest);
			}
			
			public Collection<Recipe> getContestWinners(Contest contest){
				Collection<Recipe> winners = new ArrayList<Recipe>();
				Collection<Recipe> todas = contest.getQualified();
				List<Recipe> aux = (List<Recipe>) contestRepository.findBestRecipes(contest.getId());
				for(int i=0;i<3;i++){
					if(aux.iterator().hasNext()&&!(winners.contains(aux.iterator().next()))){
						winners.add(aux.iterator().next());
					} else {
						break;	
					}
				}
				for(Recipe r: todas){
					if(winners.size()<3 && !(winners.contains(r))){
						winners.add(r);
					}
				}
				return winners;
			}
			public void setWon(Contest contest){
				Collection<Recipe> winners = new ArrayList<Recipe>();
				winners = this.getContestWinners(contest);
				for(Recipe r : winners){
					recipeService.winContest(contest,r);
				}
				contest.setWinners(winners);
				save(contest);
			}
			
			public Double[] getMinAvgMaxRecipesQualifiedForContest(){
				Double[] result = contestRepository.getMinAvgMaxRecipesQualifiedForContest();
				return result;
			}
			
			public Contest getContestWithMoreRecipesQualified(){
				Contest result = contestRepository.getContestWithMoreRecipesQualified();
				return result;
			}
			
			public Contest delete2(Contest contest){
				
				contest.setDeleted(true);
				Contest saved = this.save(contest);
				return saved;
			}
			
			public Contest restore(Contest contest){
				
				contest.setDeleted(false);
				Contest saved = this.save(contest);
				return saved;
			}
	
			public Collection<Contest> findAllNotDeleted(){
				
				Collection<Contest> notDeleted = new ArrayList<Contest>();
				for(Contest c: contestRepository.findAll()){
					
					if(c.getDeleted()==false){
						
						notDeleted.add(c);
					}
				}
				
				return notDeleted;
			}
			
}
