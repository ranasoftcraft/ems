package com.ranasoftcraft.org.patient.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ranasoftcraft.org.security.entity.Users;

import lombok.Data;
/**
 * 
 * @author sandeep.rana
 *
 */

@Data
@Entity
@Table(name = "patient_info")
public class PatientInfo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 175645646546L;

	@Id
	private String patientId;
	
	private String name;
	
	
	private Long admitDate;
	
	private Integer age;
	
	@Enumerated(EnumType.STRING)
	private Geneder geneder;
	
	private String referredByDr;
	
	private String consultByDr;
	
	private String address;
	
	@Transient
	private List<PatientReports> reports;
	
	@Transient
	private Users users;
	
	enum Geneder{
		male,
		female,
		other
	}
	
	public PatientInfo() {
		this.admitDate = Instant.now().toEpochMilli();
	}
}
