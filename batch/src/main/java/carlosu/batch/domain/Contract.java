package carlosu.batch.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contract {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
}
