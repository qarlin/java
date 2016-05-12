package net.cts.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.cts.model.Library;

public interface LibraryService {
	public List<Library> findAll();
	public Page<Library> findAll(Pageable pageable);
	public Library findOne(Long id);
	public void save(Library library);
	public void delete(Library library);
	public List<Library> findByName(String name);
}
