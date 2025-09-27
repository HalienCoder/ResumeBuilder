package com.example.resume.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ID;
	private String name;
	private String email;
	private String phone;
	private String gender;
	private String address;
	private String date;
	private String school;
	private String tenthmark;
	private String yoctenth;
	private String twelthmark;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	private String yocplustwo;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getTenthmark() {
		return tenthmark;
	}
	public void setTenthmark(String tenthmark) {
		this.tenthmark = tenthmark;
	}
	public String getYoctenth() {
		return yoctenth;
	}
	public void setYoctenth(String yoctenth) {
		this.yoctenth = yoctenth;
	}
	public String getTwelthmark() {
		return twelthmark;
	}
	public void setTwelthmark(String twelthmark) {
		this.twelthmark = twelthmark;
	}
	public String getYocplustwo() {
		return yocplustwo;
	}
	public void setYocplustwo(String yocplustwo) {
		this.yocplustwo = yocplustwo;
	}
	@OneToMany(mappedBy = "user")
	private List<WorkExpAtt> workExperiences;
	public List<WorkExpAtt> getWorkExperiences() {
		return workExperiences;
	}
	public void setWorkExperiences(List<WorkExpAtt> workExperiences) {
		this.workExperiences = workExperiences;
	}
	 public User() {
	        this.workExperiences = new ArrayList<>();
	    }
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", phone=" + phone + ", gender=" + gender + ", address="
				+ address + ", date=" + date + ", school=" + school + ", tenthmark=" + tenthmark + ", yoctenth="
				+ yoctenth + ", twelthmark=" + twelthmark + ", yocplustwo=" + yocplustwo  ;
		
	}
	
}

