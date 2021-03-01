/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.ac.hct.hctexam.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author amuhammad1
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ae.ac.hct.hctexam.service.AppuserFacadeREST.class);
        resources.add(ae.ac.hct.hctexam.service.ExamFacadeREST.class);
        resources.add(ae.ac.hct.hctexam.service.IncidenceFacadeREST.class);
        resources.add(ae.ac.hct.hctexam.service.IrrFormFacadeREST.class);
        resources.add(ae.ac.hct.hctexam.service.RoleFacadeREST.class);
    }
    
}
