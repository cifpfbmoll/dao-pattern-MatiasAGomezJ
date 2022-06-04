package edu.poniperro.daop.resource;

import edu.poniperro.daop.domain.Developer;
import edu.poniperro.daop.domain.DeveloperRepository;
import io.smallrye.common.constraint.NotNull;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/dev")
public class DeveloperResource {

    @Inject
    DeveloperRepository developerRepository;

    @GET
    @Path("id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Developer findById(@PathParam("id") Long id) {
        return developerRepository.findDevById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Developer> getAllDevs() {
        return developerRepository.findAllDevs();
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Developer findByName(@PathParam("name") String name) {
        return developerRepository.findDevByName(name);
    }

    @GET
    @Path("name/{name}/{age}")
    @Produces(MediaType.APPLICATION_JSON)
    public Developer findByNameAndAge(@PathParam("name") String name, @PathParam("age") Integer age) {
        return developerRepository.find("name = ?1 and age =?2", name, age).firstResult();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDev(Developer dev) {
        developerRepository.createDeveloper(dev);
        return Response.created(URI.create("/dev/" + dev.getId())).build();
    }
}