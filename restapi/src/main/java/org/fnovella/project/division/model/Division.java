package org.fnovella.project.division.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.fnovella.project.utility.APIUtility;
import org.hibernate.validator.constraints.Length;

@Entity
public class Division {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Length(max=50)
	private String name;
	private Integer programa;
	private Integer location;
	private boolean createdGroup;

	public ArrayList<String> validate() {
		ArrayList<String> errors = new ArrayList<String>();
		if (!APIUtility.isNotNullOrEmpty(this.name)) errors.add("Name is required");
		if (this.programa == null || this.programa <= 0) errors.add("Program is required");
		if (this.location == null || this.location <= 0) errors.add("Location is required");
		return errors;
	}

	public void setUpdateFields(Division division) {
		if (APIUtility.isNotNullOrEmpty(division.name)) this.name = division.name;
		if (division.programa != null && division.programa > 0) this.programa = division.programa;
		if (division.location != null && division.location > 0) this.location = division.location;
		this.createdGroup = division.createdGroup;
	}

}