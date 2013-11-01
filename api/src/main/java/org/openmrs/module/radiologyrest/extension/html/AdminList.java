package org.openmrs.module.radiologyrest.extension.html;

import java.util.HashMap;
import java.util.Map;

import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.AdministrationSectionExt;

public class AdminList extends AdministrationSectionExt {
	
	public Extension.MEDIA_TYPE getMediaType() {
		return Extension.MEDIA_TYPE.html;
	}
	
	public String getTitle() {
		return "radiologyrest.title";
	}
	
	public Map<String, String> getLinks() {
		
		Map<String, String> map = new HashMap<String, String>();
		
	//	map.put("module/radiologyrest/radiologyOrder.list", "radiologyrest.manageOrders");
		map.put("module/radiologyrest/config.list", "radiologyrest.config");
		
		
		return map;
	}
	
}