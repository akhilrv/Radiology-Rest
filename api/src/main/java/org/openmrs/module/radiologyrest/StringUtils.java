package org.openmrs.module.radiologyrest;

public class StringUtils
{

	public static String path(String dir,String file)
   {
   	if(dir.endsWith("/"))return dir+file;
   	else return dir+"/"+file;
   }

}
