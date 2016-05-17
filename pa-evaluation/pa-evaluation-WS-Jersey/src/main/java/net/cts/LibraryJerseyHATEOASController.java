package net.cts;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.Resource;

import net.cts.model.Library;
import net.cts.service.LibraryService;

@Component
@Path("/libraryHATEOAS")
public class LibraryJerseyHATEOASController {
	@Autowired
	private LibraryService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Resource<Library>> getLibraries() {
		List<Library> libraries = service.findAll();
		List<Resource<Library>> resources = new ArrayList<Resource<Library>>();
		for (Library library : libraries) {
			resources.add(getLibraryResource(library));
		}
		return resources;
	}

	@GET
	@Path("/page")
	@Produces(MediaType.APPLICATION_JSON)
	public PageResource<Library> findAll(@QueryParam("page") @DefaultValue("0") int page,
			@QueryParam("size") @DefaultValue("20") int size,
			@QueryParam("sort") @DefaultValue("name") List<String> sort,
			@QueryParam("direction") @DefaultValue("asc") String direction) {

		Page<Library> pageResult = service.findAll(new PageRequest(page, size, Sort.Direction.fromString(direction), sort.toArray(new String[0])));
		return new PageResource<Library>(pageResult,"page","size");
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Resource<Library> getLibrary(@PathParam("id") long id) {
		Library library = service.findOne(id);
		return getLibraryResource(library);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Resource<Library> addLibrary(Library library) {
		service.save(library);
		return getLibraryResource(library);
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
	public Resource<Library> updateLibrary(Library library) {
		service.save(library);
		return getLibraryResource(library);
	}

	@GET
	@Path("/search/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Library> getLibrariesByName(@PathParam("name") String name) {
		List<Library> libraries = service.findByName(name);
		return libraries;
	}
	
	private Resource<Library> getLibraryResource(Library library){
		Resource<Library> resource = new Resource<Library>(library);
		// Link to Library
		//resource.add(linkTo(methodOn(LibraryHATEOASController.class).getLibrary(library.getId())).withSelfRel());
		resource.add(linkTo(LibraryJerseyHATEOASController.class).slash(library.getId()).withSelfRel());
		return resource;
	}
}
