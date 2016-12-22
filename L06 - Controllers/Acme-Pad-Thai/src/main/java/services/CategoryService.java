package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import domain.Category;


@Service
@Transactional
public class CategoryService {

	//managed repository-------------------
			@Autowired
			private CategoryRepository categoryRepository;
			
			//supporting services-------------------
			
			//Basic CRUD methods-------------------
			
			public Category create(Category category){
				
				Category created = new Category();
					created.setFather(category);
				return created;
			}
			
			public Category findOne(int categoryId){
				
				Category retrieved;
				retrieved = categoryRepository.findOne(categoryId);
				return retrieved;
			}

			public Category save(Category category){
				
				Category saved;
				saved = categoryRepository.save(category);
				return saved;
				
			}
			
			public void delete(Category category){
				Assert.isTrue(category.getRecipes().isEmpty(),"Solo pueden borrarse categorías sin recetas.");
				Category son;
				if(!category.getSons().isEmpty()){
					for(Category c: category.getSons()){
						son = c;
						son.setFather(null);
						save(son);
					}
				}
				categoryRepository.delete(category);
				
			}
			
			//Other Bussiness Methods
			
			public Category delete2(Category category){
				Assert.isTrue(category.getRecipes().isEmpty(),"Solo pueden borrarse categorías sin recetas.");
				category.setDeleted(true);
				Category saved = this.save(category);
				return saved;
			}
			
			public Category restore(Category category){
				
				category.setDeleted(false);
				Category saved = this.save(category);
				return saved;
			}
			
			public Collection<Category> findAllNotDeleted(){
				
				Collection<Category> notDeleted = categoryRepository.findAllNotDeleted();
				
				return notDeleted;
			}
	
}
