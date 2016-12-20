package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Endorser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class EndorserServiceTest extends AbstractTest{
	
	//Service under test---------------
		
		@Autowired
		private NutritionistService nutritionistService;
		
		@Autowired
		private EndorserService endorserService;
		
		//Tests---------------
		
		
		
		@Test
		public void testCreate() {

			authenticate("nutritionist1");
			Endorser endorser =  endorserService.create();
			Assert.isTrue(endorser.getCurriculum().equals(nutritionistService.findByPrincipal().getCurriculum()));
			
			unauthenticate();
		}
		
		@Test
		public void testSavePositive(){
			
			authenticate("nutritionist1");
			Endorser endorser = endorserService.create();
			endorser.setName("blae");
			endorser.setHomePage("http://omfg.org");
			Endorser saved = endorserService.save(endorser);
			Assert.isTrue(nutritionistService.findByPrincipal().getCurriculum().equals(saved.getCurriculum()));
			
			unauthenticate();
		}
		
		@Test
		public void testDelete(){
			
			authenticate("nutritionist1");
			Endorser endorser = endorserService.create();
			endorser.setName("blae");
			Endorser saved = endorserService.save(endorser);
			endorserService.delete(saved);
			Assert.isTrue(!nutritionistService.findByPrincipal().getCurriculum().getEndorsers().contains(saved));
			unauthenticate();
		}
	
}
