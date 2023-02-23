/**
 * 
 */
package com.ranasoftcraft.org.patient.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.ranasoftcraft.org.admin.entity.ReportModuleEntiry;

import lombok.Data;

/**
 * @author sandeep.rana
 *
 */

@Data
@Entity
@Table(name = "patient_report")
public class PatientReports implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1263546253675432L;

	@Id
	private String reportId;

	@NotNull
	private String patientId;

//	@Enumerated(EnumType.STRING)
//	private ReportType reportType;
	
	private String reportType;
	
	@Transient
	private ReportModuleEntiry reportModuleEntiry;

	@Enumerated(EnumType.STRING)
	private Status status;

	public enum ReportType {
		blodd_glucose, widle_test, other
	}

	public enum Status {
		init, start, inp, done
	}

}
