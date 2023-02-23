/**
 * 
 */
package com.ranasoftcraft.org.security.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author sandeep.rana
 *
 */
@Data
@Entity(name = "roles")
@Table(name ="roles")
public class Roles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 123646324L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String roleId;
	
	@NotNull(message = "Role description can't be null or empty")
	private String roleDesc;
	
	private String name;


}
