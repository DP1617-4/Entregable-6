package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Endorser extends DomainEntity{

	// Constructors -----------------------------------------------------------

		public Endorser() {
			super();
		}

		// Attributes -------------------------------------------------------------

		private String name;
		private String homePage;
		
		@NotBlank
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		@NotBlank
		
		@URL
		public String getHomePage() {
			return homePage;
		}
		public void setHomePage(String homePage) {
			this.homePage = homePage;
		}
		
		//Relationships
		
		private Curriculum curriculum;

		@Valid
		@ManyToOne(optional = false)
		public Curriculum getCurriculum() {
			return curriculum;
		}
		public void setCurriculum(Curriculum curriculum) {
			this.curriculum = curriculum;
		}

}
