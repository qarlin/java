package net.carlosu.spring.boot.jpa.observer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.carlosu.spring.boot.jpa.observer.domain.Book;

public interface BookRepository extends JpaRepository<Book,Long>{

}
