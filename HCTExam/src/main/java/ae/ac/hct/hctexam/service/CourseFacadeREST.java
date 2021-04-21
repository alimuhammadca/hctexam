/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.ac.hct.hctexam.service;

import ae.ac.hct.hctexam.Course;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author amuhammad1
 */
@Stateless
@Path("ae.ac.hct.hctexam.course")
public class CourseFacadeREST extends AbstractFacade<Course> {

    @PersistenceContext(unitName = "ae.ac.hct_HCTExam_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public CourseFacadeREST() {
        super(Course.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Course entity) {
        super.create(entity);
    }

    @PUT
    @Path("{courseCode}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String courseCode, Course entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{courseCode}")
    public void remove(@PathParam("courseCode") String courseCode) {
        super.remove(super.find(courseCode));
    }

    @GET
    @Path("{courseCode}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Course find(@PathParam("courseCode") String courseCode) {
        return super.find(courseCode);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Course> findAll() {
        return super.findAll();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
