/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.radiologyrestmodule.web.test;

import java.io.Serializable;
import org.openmrs.BaseOpenmrsMetadata;

/**
 *
 * @author Akhil
 */
public class Test extends BaseOpenmrsMetadata implements Serializable {
    
    String one,two;
    Integer id;
    
    public Test()
    {
        one="one 1";
        two="two 2";
        id=1;        
    }
    public String getTest()
    {
        return "this is a test string";
    }
    
    public String getOne()
    {
        return "This is one's property";
        
    }
    public String getTwo()
    {
        return "This is Two's property";
        
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer intgr) {
        this.id=intgr;
    }
       
    
    @Override
    public int hashCode ()
    {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Test other = (Test) obj;
        return true;
    }
    
}
