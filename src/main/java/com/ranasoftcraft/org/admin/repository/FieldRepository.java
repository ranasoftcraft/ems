/**
 * 
 */
package com.ranasoftcraft.org.admin.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ranasoftcraft.org.admin.entity.FieldEntiry;

/**
 * @author sandeep.rana
 *
 */
public interface FieldRepository extends PagingAndSortingRepository<FieldEntiry, String> {

}
