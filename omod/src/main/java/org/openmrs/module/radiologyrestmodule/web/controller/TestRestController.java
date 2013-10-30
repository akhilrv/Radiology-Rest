/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.radiologyrestmodule.web.controller;

import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.openmrs.module.radiologyrestmodule.web.test.Test;

import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RestUtil;
import org.openmrs.module.webservices.rest.web.annotation.WSDoc;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openmrs.module.radiologyrestmodule.web.resource.TestResource;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseCrudController;

@Controller
@RequestMapping(value = "/rest/v1/radiology/test")
public class TestRestController extends BaseRestController {
    
        Gson gson = new GsonBuilder().serializeNulls().create();

    
        @RequestMapping(method = RequestMethod.GET)	
	@ResponseBody()
        @WSDoc("Blaah")
	public String getRestTest(HttpServletRequest request, HttpServletResponse response) throws ResponseException {
		Test t= new Test();
               SimpleObject obj = new SimpleObject();
                obj.add("main", t.getTest());
                obj.add("one",t.getOne());
                obj.add("two",t.getTwo());
                //return gson.toJson("This is a test string");
                return gson.toJson(obj);
                
	}
        
   
    
}