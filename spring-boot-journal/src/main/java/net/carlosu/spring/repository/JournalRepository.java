package net.carlosu.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.carlosu.spring.domain.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long> {

}
