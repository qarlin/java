package net.cts.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.cts.model.Library;
import net.cts.repository.LibraryRepository;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService {
	@Autowired
	private LibraryRepository repository;

	@Override
	public List<Library> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<Library> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public void save(Library library) {
		repository.save(library);
	}

	@Override
	public void delete(Library library) {
		repository.delete(library);
	}

	@Override
	public List<Library> findByName(String name) {
		return null;
	}

	@Override
	public Library findOne(Long id) {
		return repository.findOne(id);
	}
}
