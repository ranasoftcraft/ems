/**
 * 
 */
package com.ranasoftcraft.org.patient.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ranasoftcraft.org.admin.entity.ReportModuleEntiry;
import com.ranasoftcraft.org.admin.repository.ReportModuleRepository;
import com.ranasoftcraft.org.patient.entity.PatientInfo;
import com.ranasoftcraft.org.patient.entity.PatientReports;
import com.ranasoftcraft.org.patient.entity.PatientReports.Status;
import com.ranasoftcraft.org.patient.repository.PatientInfoRepository;
import com.ranasoftcraft.org.patient.repository.PatientReportsRepository;
import com.ranasoftcraft.org.security.entity.Roles;
import com.ranasoftcraft.org.security.entity.Users;
import com.ranasoftcraft.org.security.services.UsersService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sandeep.rana
 *
 */
@Service
@Transactional
@Slf4j
public class PatientInfoServiceImpl implements PatientInfoService {

	
	@Autowired
	private PatientInfoRepository patientInfoRepository;
	
	@Autowired
	private PatientReportsRepository patientReportsRepository;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private ReportModuleRepository reportModuleRepository;
	
	
	@Override
	public PatientInfo saveUpdate(final Users user) {
		final Roles roleModel = new Roles();
		roleModel.setRoleId("103");
		roleModel.setName("PATIENT");
		roleModel.setRoleDesc("patient");
		Set<Roles> roles = new HashSet<>(); roles.add(roleModel);
		user.setRoles(roles);
		user.setPassword(StringUtils.hasText(user.getPassword()) ? user.getPassword() : user.getUsername());
		userService.signUp(user);
		log.info("After user details saved {} ", user.getUsername());
		// save the patient report type 
		if(user.getPatientInfo().getReports() !=null && !user.getPatientInfo().getReports().isEmpty()) {
			List<PatientReports> requestReport = user.getPatientInfo().getReports();
			requestReport.forEach(r->{
				r.setStatus(PatientReports.Status.init);
				r.setPatientId(user.getUsername());
				r.setReportId((String) UUID.randomUUID().toString().subSequence(0, 24));
			});
			patientReportsRepository.saveAll(requestReport);
//			log.info("After all report type saved {} ", requestReport.stream().map(m-> m.getReportType()).collect(Collectors.toList()));
		}
		
		return patientInfoRepository.save(user.getPatientInfo());
	}
	
	@Override
	public PatientReports saveUpdateRepory(final PatientReports patientReports) {
		return patientReportsRepository.save(patientReports);
	}
	
	@Override
	public Page<PatientInfo> getPatientList(final int _page, final int _size) {
		final Pageable pageable =  PageRequest.of(_page, _size);
		final Page<PatientInfo> lst =  patientInfoRepository.findAll(pageable);
		lst.getContent().forEach(fr->{
			fr.setUsers(userService.findByUserId(fr.getPatientId()).get());
			List<PatientReports> lP = patientReportsRepository.findByPatientId(fr.getPatientId());
			lP.forEach(l->{
				Optional<ReportModuleEntiry> ml = reportModuleRepository.findById(l.getReportType());
				l.setReportModuleEntiry(ml.isPresent() ? ml.get(): null);
			});
			fr.setReports(lP);
		});
		return lst;
	}
	
	@Override
	public void updateStatus(Status status, String reportId ) {
		Optional<PatientReports> pReportsOps =  patientReportsRepository.findById(reportId);
		if(pReportsOps.isPresent()) {
			PatientReports pr =  pReportsOps.get();
			pr.setStatus(status);
			saveUpdateRepory(pr);
		}
	}
	
}
