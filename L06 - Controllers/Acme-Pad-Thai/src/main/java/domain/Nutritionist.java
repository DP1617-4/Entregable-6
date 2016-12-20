package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;


@Entity
@Access(AccessType.PROPERTY)
public class Nutritionist extends SocialUser{
	// Constructors -----------------------------------------------------------

		public Nutritionist() {
			super();
		}
		
		
		//Relations
		
		private Curriculum curriculum;

		@Valid
		@OneToOne(optional = true, mappedBy= "nutritionist", cascade = CascadeType.REMOVE)
		public Curriculum getCurriculum() {
			return curriculum;
		}


		public void setCurriculum(Curriculum curriculum) {
			this.curriculum = curriculum;
		}
		
	}
