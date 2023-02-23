/**
 * 
 */
package com.ranasoftcraft.org.security.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

import com.ranasoftcraft.org.patient.entity.PatientInfo;

import lombok.Data;

/**
 * @author sandeep.rana
 *
 */
@Data
@Entity(name = "users")
@Table(name = "users")
public class Users {
	
	@Id
	@NotNull
	private String username;
	
	@NotNull
	private String name;
	
	@NotNull
	@Min(value = 10, message = "Phone can't be less then 12 ")
	@Max(value = 12, message = "Phone can't be more then 12 ")
	private String phone;
	
	@NotNull
	@javax.validation.constraints.Email
	private String email;
	
	@NotNull
	private String position;
	
	
	private Long createdAt;
	
	
	private Long updatedAt;
	
	private Boolean isEnabled;
	
	
	private String oauth2Id;
	
	public void setOauth2Id(String oauth2Id) {
		oauth2Id = StringUtils.hasText(oauth2Id) ? oauth2Id : email;
	}
	
	@NotNull
//	@JsonIgnore
	private String password; 
	

    @ManyToMany(fetch = FetchType.LAZY)
	private Set<Roles> roles;

    
    @Transient
    private PatientInfo patientInfo;

}
