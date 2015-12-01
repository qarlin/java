package net.carlosu.springdata.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.carlosu.springdata.repository.BaseRepository;
import net.carlosu.springdata.todo.model.Todo;

public interface TodoRepository extends BaseRepository<Todo, Long>{

    Todo findById(Long id);
    
    public List<Todo> findByTitleIs();
    
    @Query(nativeQuery = true)
    public List<Todo> findByTitleIsNative();
    
    @Query
    List<Todo> findBySearchTermNamed(@Param("searchTerm") String searchTerm);
    
    @Query(nativeQuery = true)
    List<Todo> findBySearchTermNamedNative(@Param("searchTerm") String searchTerm);
}
