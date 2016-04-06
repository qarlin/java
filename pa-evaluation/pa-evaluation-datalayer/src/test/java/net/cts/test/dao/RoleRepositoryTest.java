package net.cts.test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.cts.JPAAplication;
import net.cts.model.Role;
import net.cts.repository.RoleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JPAAplication.class)
public class RoleRepositoryTest {
	@Autowired
	private RoleRepository repository;
	
	@Test
	public void test() {
		Role role = new Role();
		role.setName("Administrador");
		role.setAdmin(true);
		
		repository.save(role);
		
		List<Role> roles = repository.findAll();
		
		assertEquals(1, roles.size());
	}

}
