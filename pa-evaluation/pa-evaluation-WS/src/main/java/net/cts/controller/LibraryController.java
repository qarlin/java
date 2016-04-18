package net.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.cts.service.LibraryService;
import net.cts.model.Library;

@RestController
@RequestMapping(value = "/library")
public class LibraryController {
	@Autowired
	private LibraryService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Library> getLibraries() {
		return service.findAll();
	}
	
	//@RequestMapping(value = "/add/{name}/{description}")
	//public Library addLibrary(@PathVariable String name, @PathVariable String description) {
	@RequestMapping(method = RequestMethod.POST)
	public Library addLibrary(@RequestBody Library library) {
		service.save(library);
		return library;
	}
	//@RequestMapping(value = "/delete/{id}")
	//public void deleteLibrary(@PathVariable long id) {
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteLibrary(@PathVariable long id) {
		Library library = service.findOne(id);
		if (library != null)
			service.delete(library);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Library updateLibrary(@RequestBody Library library) {
		service.save(library);
		return library;
	}
	
	@RequestMapping(value = "/{id}")
	public Library getLibrary(@PathVariable long id) {
		Library library = service.findOne(id);
		return library;
	}
	@RequestMapping(value = "/search/name/{name}")
	public List<Library> getLibrariesByName(@PathVariable String name) {
		List<Library> libraries = service.findByName(name);
		return libraries;
	}
}
