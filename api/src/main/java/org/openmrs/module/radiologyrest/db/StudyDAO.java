package org.openmrs.module.radiologyrest.db;

import org.openmrs.module.radiologyrest.Study;


/**
 * radiologyResponse-related database functions
 * 
 * @author Cortex
 * @version 1.0
 */
public interface StudyDAO {

	public Study getStudy(Integer id);

	public Study saveStudy(Study study);
	
	/**
	 * @param id
	 * @return the study with orderId=id, or new Study() if there is no such study
	 */
	public Study getStudyByOrderId(Integer id);
	
}
