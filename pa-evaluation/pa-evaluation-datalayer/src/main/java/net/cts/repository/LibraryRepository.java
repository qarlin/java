package net.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cts.model.Library;

public interface LibraryRepository extends JpaRepository<Library, Long>{

}
