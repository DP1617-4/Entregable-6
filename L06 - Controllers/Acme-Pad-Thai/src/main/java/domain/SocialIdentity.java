package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SocialIdentity extends DomainEntity {
	
	//Constructor
	
	public SocialIdentity(){
		super();
	}
	
	
	//Attributes
	
	private String nick;
	private String socialNetworkName;
	private String socialNetworkLink;
	private String picture;
	
	@NotBlank
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	@NotBlank
	
	public String getSocialNetworkName() {
		return socialNetworkName;
	}
	public void setSocialNetworkName(String socialNetworkName) {
		this.socialNetworkName = socialNetworkName;
	}
	
	@URL
	@NotBlank
	
	public String getSocialNetworkLink() {
		return socialNetworkLink;
	}
	public void setSocialNetworkLink(String socialNetworkLink) {
		this.socialNetworkLink = socialNetworkLink;
	}
	
	@URL
	@NotBlank
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	//Relationships
	
	private Actor actor;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}

}
