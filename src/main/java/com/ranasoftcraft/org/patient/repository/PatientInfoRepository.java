/**
 * 
 */
package com.ranasoftcraft.org.patient.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ranasoftcraft.org.patient.entity.PatientInfo;

/**
 * @author sandeep.rana
 *
 */
@Repository
public interface PatientInfoRepository extends PagingAndSortingRepository<PatientInfo, String>{

	
}
