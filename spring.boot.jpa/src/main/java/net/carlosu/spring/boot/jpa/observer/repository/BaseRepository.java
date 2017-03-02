package net.carlosu.spring.boot.jpa.observer.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import net.carlosu.spring.boot.jpa.observer.monitor.UpdateNotify;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
 
	@UpdateNotify("value")
	public void delete(T deleted);
 
    public List<T> findAll();
     
    public Optional<T> findOne(ID id);
 
    @UpdateNotify("value")
    public T save(T persisted);
}
