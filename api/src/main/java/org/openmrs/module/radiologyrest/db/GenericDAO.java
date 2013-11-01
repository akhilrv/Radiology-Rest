package org.openmrs.module.radiologyrest.db;

import org.hibernate.classic.Session;

public interface GenericDAO {
	public Object get(String query,boolean unique);
	public Session session();
}
