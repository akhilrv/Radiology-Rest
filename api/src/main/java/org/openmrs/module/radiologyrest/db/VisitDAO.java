package org.openmrs.module.radiologyrest.db;

import org.openmrs.module.radiologyrest.Visit;

public interface VisitDAO {
	
	public Visit getVisit(Integer id);

	public Visit saveVisit(Visit v);

}
