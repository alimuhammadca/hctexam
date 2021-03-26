/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.ac.hct.hctexam.service;

import ae.ac.hct.hctexam.Appuser;
import ae.ac.hct.model.AuthenticatedUser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
@Path("ae.ac.hct.hctexam.appuser")
public class AppuserFacadeREST extends AbstractFacade<Appuser> {

    private static final Map<String, String> MAP = new HashMap(); 

    @PersistenceContext(unitName = "ae.ac.hct_HCTExam_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public AppuserFacadeREST() {
        super(Appuser.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Appuser entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Appuser entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @DELETE
    public void remove() {
        List<Appuser> list = super.findAll();
        for (Appuser user : list) {
            if (user.getRoleId()==2)
                super.remove(user);
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Appuser find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Appuser> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Appuser> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
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

    @GET
    @Path("authenticate/{id}/{password}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AuthenticatedUser authenticate(@PathParam("id") String id, @PathParam("password") String password) {
        Appuser user = super.find(id);
        if (user == null) return null;
        
        if (!password.equals(user.getPassword())) {
            return null;
        }
        
        UUID uniqueKey = UUID.randomUUID();
        if (MAP.get(id)!=null) {
            MAP.replace(id, uniqueKey.toString());
        } else {
            MAP.put(id, uniqueKey.toString());
        }
        return new AuthenticatedUser(user.getId(), user.getRoleId(), user.getFirstname(), user.getLastname(),user.getEmail(), uniqueKey.toString());
    }

    @GET
    @Path("verify/{token}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public boolean verify(@PathParam("token") String token) {
        return MAP.containsValue(token);
    }
  
    @GET
    @Path("logout/{id}/{token}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public boolean logout(@PathParam("id") String id, @PathParam("token") String token) {
        if (MAP.containsValue(token)) {
            MAP.remove(id);
            return true;
        }
        return false;
    }
    
}
