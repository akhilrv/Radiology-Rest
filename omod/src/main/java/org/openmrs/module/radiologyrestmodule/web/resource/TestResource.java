/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.radiologyrestmodule.web.resource;

/**
 *
 * @author Akhil
 */
import javax.servlet.http.HttpServletRequest;

import org.openmrs.api.context.Context;
import org.openmrs.annotation.Handler;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RequestContext;
import org.openmrs.module.webservices.rest.web.RestUtil;
import org.openmrs.module.webservices.rest.web.api.RestService;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.resource.impl.DataDelegatingCrudResource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.openmrs.module.radiologyrestmodule.web.test.Test;
import org.openmrs.module.webservices.rest.web.representation.DefaultRepresentation;
import org.openmrs.module.webservices.rest.web.representation.FullRepresentation;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;
import org.openmrs.module.webservices.rest.web.resource.impl.MetadataDelegatingCrudResource;


@Resource("test")
@Handler(supports = Test.class, order = 0)

public class TestResource extends MetadataDelegatingCrudResource {

    @Override
    public Test getByUniqueId(String string) {
        return new Test();
    }

    
    public Test newDelegate() {
       return  new Test();
    }

    public Test save(Test t) {
        throw new UnsupportedOperationException("Not supported yet. SAVETEST");
    }

    public DelegatingResourceDescription getRepresentationDescription(Representation r) {
        if (r instanceof DefaultRepresentation) {
            DelegatingResourceDescription description = new DelegatingResourceDescription();
            description.addProperty("main");
            description.addProperty("one");
            description.addProperty("two");     
            return description;
        }
        else
            if (r instanceof FullRepresentation) {
                DelegatingResourceDescription description = new DelegatingResourceDescription();
                description.addProperty("main");
                description.addProperty("one");
                description.addProperty("two");            
                return description;
            }
        return null;
    }

    
    protected void delete(Object t, String string, RequestContext rc) throws ResponseException {
        throw new UnsupportedOperationException("Not supported yet DELETE!.");
    }

    @Override
    public void purge(Object t, RequestContext rc) throws ResponseException {
        throw new UnsupportedOperationException("Not supported yet PURGE.");
    }

    public Object save(Object t) {
        throw new UnsupportedOperationException("Not supported yet SAVE.");
    }
    
}
