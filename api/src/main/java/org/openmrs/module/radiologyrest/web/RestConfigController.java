package org.openmrs.module.radiologyrest.web;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import org.openmrs.module.radiologyrest.Activator;
import org.openmrs.module.radiologyrest.Roles;
import org.openmrs.module.radiologyrest.Utils;
import org.openmrs.web.WebConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestConfigController {

	static String typeRoles="typeRoles";
	static String dummyUsers="dummyUsers";
	static String AE="AE";
	static String SCP="SCP";
	
	@RequestMapping("/module/radiologyrest/config.list")
	ModelAndView init(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/module/radiologyrest/config");
		String command = request.getParameter("command");
		command = command == null ? "" : command;
		if (command.compareToIgnoreCase(typeRoles) == 0) {
			request.getSession().setAttribute(
					WebConstants.OPENMRS_MSG_ATTR,
					Activator.typeAndRoles() ? "radiology.successConfig"
							: "radiology.failConfig");
		}
		else if (command.compareToIgnoreCase(dummyUsers) == 0) {
			request.getSession().setAttribute(
					WebConstants.OPENMRS_MSG_ATTR,
					createDummyUsers() ? "radiology.successConfig"
							: "radiology.failConfig");
		}
// # Part of the old Radiology module which is a part of Xebra initialization. No longer needed.                
//		else if (command.compareToIgnoreCase(AE) == 0){
//			request.getSession().setAttribute(
//					WebConstants.OPENMRS_MSG_ATTR,
//					Activator.createAE() ? "radiology.successConfig"
//							: "radiology.failConfig");
//		}
//		else if (command.compareToIgnoreCase(SCP) == 0){
//			request.getSession().setAttribute(
//					WebConstants.OPENMRS_MSG_ATTR,
//					Activator.createScp() ? "radiology.successConfig"
//							: "radiology.failConfig");
//		}
		
		populate(mav);

		return mav;
	}
	
	private void populate(ModelAndView mav)
   {
	   try
      {
	      mav.addObject("mwl",new File(Utils.mwlDir()).getCanonicalPath());
	      mav.addObject("mpps",new File(Utils.mppsDir()).getCanonicalPath());
	      mav.addObject("storage",new File(Utils.storageDir()).getCanonicalPath());
      }
      catch(IOException e)
      {
	      // TODO handle
      }
   }

	boolean  createDummyUsers(){
		try{
			Field[] roles=Roles.class.getDeclaredFields();
			for(Field role:roles)
         {
				Utils.createUser(Roles.value(role),"Admin123",Roles.value(role));
         }
		}catch(Throwable t){
			return false;
		}
		return true;
	}

	

	
}
