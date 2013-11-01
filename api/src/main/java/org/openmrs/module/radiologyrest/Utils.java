package org.openmrs.module.radiologyrest;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.OrderType;
import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.Role;
import org.openmrs.User;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.OrderService;
import org.openmrs.api.context.Context;

/**
 * @author Cortex
 * 
 */
public class Utils {

	static final Log log = LogFactory.getLog(Utils.class);
	private static AdministrationService as = Context
			.getAdministrationService();

	public static String aeTitle() {
		return as.getGlobalProperty("radiologyrest.applicationEntityTitle");
	}

	public static String mppsDir() {
		return as.getGlobalProperty("radiologyrest.mppsDirectory");
	}

	public static String mwlDir() {
		return as.getGlobalProperty("radiologyrest.mwlDirectory");
	}

	public static String mwlMppsPort() {
		return as.getGlobalProperty("radiologyrest.mwlMppsPort");
	}

	public static String storageDir() {
		return as.getGlobalProperty("radiologyrest.storageDirectory");
	}

	public static String storagePort() {
		return as.getGlobalProperty("radiologyrest.storagePort");
	}
	
	public static String storageCommitmentPort() {
		return as.getGlobalProperty("radiologyrest.storageCommitmentPort");
	}

	public static String serversAddress() {
		return as.getGlobalProperty("radiologyrest.serversAddress");
	}

	/**
	 * Prefix for DICOM objects in the application, Ex:
	 * 1.2.826.0.1.3680043.8.2186
	 */
	public static String applicationUID() {
		return as.getGlobalProperty("radiologyrest.applicationUID");
	}

	public static String studyUIDSlug() {
		return as.getGlobalProperty("radiologyrest.studyUIDSlug");
	}

	/**
	 * Example: 1.2.826.0.1.3680043.8.2186.1. (With last dot)
	 */
	public static String studyPrefix() {
		return applicationUID() + "." + studyUIDSlug() + ".";
	}

	public static String specificCharacterSet() {
		return as.getGlobalProperty("radiologyrest.specificCharacterSet");
	}

	public static String devModeP() {
		return as.getGlobalProperty("radiologyrest.devMode");
	}

	public static boolean devMode() {
		return devModeP().compareToIgnoreCase("on") == 0;
	}
        
        public static String serversPort(){
            return as.getGlobalProperty("radiologyrest.serversPort");
        }
        
        public static String oviyamLocalServerName(){
            return as.getGlobalProperty("radiologyrest.oviyamLocalServerName");
        }
                

	static Main service() {
		return Context.getService(Main.class);
	}

	/**
	 * @return List of all Order objects with OrderType == "Radiology"
	 */
	public static List<OrderType> getRadiologyOrderType() {
		List<OrderType> radiologyType = new Vector<OrderType>();
		OrderService os = Context.getOrderService();
		List<OrderType> allTypes = os.getAllOrderTypes();
		for (OrderType orderType : allTypes) {
			if (orderType.getName().equals("Radiology"))
				radiologyType.add(orderType);
		}
		return radiologyType;
	}

	/**
	 * @param d
	 *            the date to plain
	 * @return d in the format yyyymmdd as string
	 */
	@SuppressWarnings("static-access")
	static String plain(Date d) {
		if (d == null)
			d = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return "" + pad(c.get(c.YEAR), 4) + pad(c.get(c.MONTH)+1)
				+ pad(c.get(c.DAY_OF_MONTH));
	}

	/**
	 * @param c
	 * @return [field1,field2...]
	 */
	public static String[] forSelect(Class<?> c) {
		Field[] f = c.getDeclaredFields();
		String s[] = new String[f.length + 1];
		s[0] = Context.getMessageSourceService().getMessage("general.select");
		for (int i = 0; i < f.length; i++) {
			try
         {
	         s[i + 1] = (String) c.getMethod("string",Integer.class,Boolean.class).invoke(c.newInstance(),new Integer(i),new Boolean(true));
         }
         catch(Exception e)
         {
	         log.error("Not the type expected: "+c.toString()+" has to have the method public String string(Integer ordinal,Boolean localized)");
         }
		}
		return s;
	}

	/**
	 * @param d
	 *            the date to 'time'
	 * @return d in the format hhmmss as string
	 */
	@SuppressWarnings("static-access")
	static String time(Date d) {
		if (d == null)
			d = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return "" + pad(c.get(c.HOUR_OF_DAY)) + pad(c.get(c.MINUTE))
				+ pad(c.get(c.SECOND));
	}

	/**
	 * @param x
	 *            Number to pad
	 * @param min
	 *            Minimum numbers to be written
	 * @return pad(2,3) returns "002"
	 */
	static String pad(int x, int min) {
		return String.format("%0" + min + "d", x);
	}

	static String pad(int x) {
		return pad(x, 2);
	}

	public static boolean hasRadiology(OrderService os) {
		return getRadiologyOrderType().size() > 0;
	}

	public static void setRoles(User u, String... roles)
   {
	   HashSet<Role> rolesSet=new HashSet<Role>();
	   for(int j=0;j<roles.length;j++)
      {
	   	Role role=Context.getUserService().getRole(roles[j]);
	   	rolesSet.add(role);
      }
	   u.setRoles(rolesSet);
   }
	
	public static void createUser(String name, String pass, String... roles) throws Exception
   {
	   if(Context.getUserService().getUserByUsername(name)==null)
      {
	      Person p=new Person();
	      p.setGender("M");
	      p.setDead(false);
	      p.setVoided(false);
	      p.addName(new PersonName(name,"",""));
	      User u=new User(p);
	      u.setUsername(name);
	      Utils.setRoles(u,roles);
	      try
	      {
		      Context.getUserService().saveUser(u,pass);
	      }
	      catch(Exception e)
	      {
		      throw e;
	      }
      }
   }
}
