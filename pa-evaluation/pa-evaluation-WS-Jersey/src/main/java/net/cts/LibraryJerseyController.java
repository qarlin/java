package net.cts;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.cts.model.Library;
import net.cts.service.LibraryService;

@Component
@Path("/library")
public class LibraryJerseyController {
	@Autowired
	private LibraryService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Library> getLibraries() {
		return service.findAll();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Library getLibrary(@PathParam("id") long id) {
		Library library = service.findOne(id);
		return library;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Library addLibrary(Library library) {
		service.save(library);
		return library;
	}

	@DELETE
	@Path("{id}")
	public void deleteLibrary(@PathParam("id") long id) {
		Library library = service.findOne(id);
		if (library != null)
			service.delete(library);
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Library updateLibrary(Library library) {
		service.save(library);
		return library;
	}
	
	@GET
	@Path("/search/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Library> getLibrariesByName(@PathParam("name") String name) {
		List<Library> libraries = service.findByName(name);
		return libraries;
	}
}
