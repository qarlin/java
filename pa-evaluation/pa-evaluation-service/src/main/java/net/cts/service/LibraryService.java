package net.cts.service;

import java.util.List;

import net.cts.model.Library;

public interface LibraryService {
	public List<Library> findAll();
	public Library findOne(Long id);
	public void save(Library library);
	public void delete(Library library);
	public List<Library> findByName(String name);
}
