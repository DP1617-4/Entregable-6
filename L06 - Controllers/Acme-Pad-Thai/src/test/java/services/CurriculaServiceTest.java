package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Curriculum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class CurriculaServiceTest extends AbstractTest{
	
	//Service under test---------------
		
		@Autowired
		private CurriculumService curriculumService;
		
		@Autowired
		private NutritionistService nutritionistService;
		
		//Tests---------------
		
		
		
		@Test
		public void testCreate() {

			authenticate("nutritionist1");
			Curriculum curriculum =  curriculumService.create();
			Assert.isNull(curriculum.getEducation());
			Assert.isNull(curriculum.getExperience());
			Assert.isNull(curriculum.getHobbies());
			Assert.isNull(curriculum.getPicture());
			Assert.isTrue(curriculum.getNutritionist().equals(nutritionistService.findByPrincipal()));
			Assert.isTrue(curriculum.getDeleted() == false);
			Assert.isTrue(curriculum.getNutritionist().equals(nutritionistService.findByPrincipal()));
			unauthenticate();
		}
		
		@Test
		public void testSavePositive(){
			
			authenticate("nutritionist1");
			
			Curriculum curriculum = curriculumService.create();
			curriculum.setEducation("ni la ESO jaja");
			curriculum.setExperience("ama de casa");
			curriculum.setHobbies("ver el Sálvame");
			curriculum.setPicture("http://omfg.org");
			Curriculum saved = curriculumService.save(curriculum);
			Assert.isTrue(nutritionistService.findByPrincipal().getCurriculum().equals(saved));
			
			unauthenticate();
		}
		
		@Test
		public void testDelete(){
			
			authenticate("nutritionist1");
			Curriculum curriculum = curriculumService.findOne(107);
			curriculumService.delete(curriculum);
			nutritionistService.findByPrincipal();
//			System.out.println(nutritionist);
//			System.out.println(nutritionist.getCurricula());
			try{
				curriculum = curriculumService.findOne(107);
			}catch(Exception e){
				System.out.println("Captured nullPointer on id 107");
			}
			unauthenticate();
		}
	
}
