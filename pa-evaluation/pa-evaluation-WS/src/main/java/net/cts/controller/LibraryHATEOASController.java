package net.cts.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.cts.model.Library;
import net.cts.service.LibraryService;

@RestController
@RequestMapping(value = "/libraryHATEOAS")
public class LibraryHATEOASController {
	@Autowired
	private LibraryService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Resource<Library>> getLibraries() {
		List<Library> libraries = service.findAll();
		List<Resource<Library>> resources = new ArrayList<Resource<Library>>();
		for (Library library : libraries) {
			resources.add(getLibraryResource(library));
		}
		return resources;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Resource<Library> addLibrary(@RequestBody Library library) {
		service.save(library);
		return getLibraryResource(library);
		//return Response.ok(getLibraryResource(library)).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteLibrary(@PathVariable long id) {
		Library library = service.findOne(id);
		if (library != null)
			service.delete(library);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Resource<Library> updateLibrary(@RequestBody Library library) {
		service.save(library);
		return getLibraryResource(library);
	}
	
	@RequestMapping(value = "/{id}")
	public Resource<Library> getLibrary(@PathVariable long id) {
		Library library = service.findOne(id);
		return getLibraryResource(library);
	}
	@RequestMapping(value = "/search/name/{name}")
	public List<Library> getLibrariesByName(@PathVariable String name) {
		List<Library> libraries = service.findByName(name);
		return libraries;
	}
	
	private Resource<Library> getLibraryResource(Library library){
		Resource<Library> resource = new Resource<Library>(library);
		// Link to Library
		resource.add(linkTo(methodOn(LibraryHATEOASController.class).getLibrary(library.getId())).withSelfRel());
		return resource;
	}
}
