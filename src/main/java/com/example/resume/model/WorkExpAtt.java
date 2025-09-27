package com.example.resume.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class WorkExpAtt {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID;
	

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private int RefID;
	private String companyname;
	private String duration;
	private String jobrole;
	private String skillsgained;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getRefID() {
		return RefID;
	}
	public void setRefID(int refID) {
		RefID = refID;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getJobrole() {
		return jobrole;
	}
	public void setJobrole(String jobrole) {
		this.jobrole = jobrole;
	}
	public String getSkillsgained() {
		return skillsgained;
	}
	public void setSkillsgained(String skillsgained) {
		this.skillsgained = skillsgained;
	}
	public WorkExpAtt() {
	}
	public WorkExpAtt(User user, String companyname, String duration, String jobrole, String skillsgained) {
	    this.user = user;
	    this.companyname = companyname;
	    this.duration = duration;
	    this.jobrole = jobrole;
	    this.skillsgained = skillsgained;
	}
	@Override
	public String toString() {
		return "WorkExpAtt [RefID=" + RefID + ", companyname=" + companyname + ", duration=" + duration + ", jobrole="
				+ jobrole + ", skillsgained=" + skillsgained + "]";
	}
	
	
}

