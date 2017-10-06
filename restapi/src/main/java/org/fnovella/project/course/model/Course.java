package org.fnovella.project.course.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.fnovella.project.utility.APIUtility;
import org.hibernate.validator.constraints.Length;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Length(max = 50)
	private String name;
	private Integer location;
	@Length(max = 140)
	private String description;
	private boolean openCourse;
	private Integer grade;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isOpenCourse() {
		return openCourse;
	}
	public void setOpenCourse(boolean openCourse) {
		this.openCourse = openCourse;
	}
	public Course(String name, Integer location, String description, boolean openCourse, Integer grade) {
		super();
		this.name = name;
		this.location = location;
		this.description = description;
		this.openCourse = openCourse;
		this.grade = grade;
	}
	public Course() {
		super();
	}
	public ArrayList<String> validate() {
		ArrayList<String> errors = new ArrayList<String>();
		if (!APIUtility.isNotNullOrEmpty(this.name)) errors.add("Name is required");
		if (!APIUtility.isNotNullOrEmpty(this.description)) errors.add("Description is required");
		if (this.location <= 0) errors.add("Location is required");
		if (this.grade <= 0) errors.add("Grade is required");
		return errors;
	}
	public void setUpdateFields(Course course) {
		if (APIUtility.isNotNullOrEmpty(course.name)) this.name = course.name;
		if (APIUtility.isNotNullOrEmpty(course.description)) this.description = course.description;
		if (course.location > 0) this.location = course.location;
		if (course.grade > 0) this.grade = course.grade;
		this.openCourse = course.openCourse;
	}
}