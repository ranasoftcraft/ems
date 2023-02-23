/**
 * 
 */
package com.ranasoftcraft.org.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ranasoftcraft.org.admin.entity.FormEntity;

/**
 * @author sandeep.rana
 *
 */
public interface FormRepository extends PagingAndSortingRepository<FormEntity, String> {

	Page<FormEntity> findByReportModuleId(String reportModuleId, Pageable page);
	
	List<FormEntity> findByReportModuleIdAndIsActive(String reportModuleId, Boolean isActive);
}
