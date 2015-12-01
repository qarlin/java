package net.carlosu.springdata.todo.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

@Entity

@NamedQueries({
	@NamedQuery(name = "Todo.findByTitleIs",
			query = "SELECT t FROM Todo t WHERE t.title = 'title'"),
	@NamedQuery(name = "Todo.findBySearchTermNamed",
	query = "SELECT t FROM Todo t WHERE " +
	        "LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
	        "LOWER(t.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))"
	)

})

@NamedNativeQueries({
	@NamedNativeQuery (name = "Todo.findByTitleIsNative", 
					query="SELECT * FROM todos t WHERE t.title = 'title'", 
					resultClass = Todo.class),
	@NamedNativeQuery(name = "Todo.findBySearchTermNamedNative",
	query="SELECT * FROM todos t WHERE " +
	        "LOWER(t.title) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR " +
	        "LOWER(t.description) LIKE LOWER(CONCAT('%',:searchTerm, '%'))",
	resultClass = Todo.class)
})
@Table(name = "todos")
public class Todo {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @Column(name = "creation_time", nullable = true)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime creationTime;
 
    @Column(name = "description", length = 500)
    private String description;
 
    @Column(name = "modification_time")
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
    private ZonedDateTime modificationTime;
 
    @Column(name = "title", nullable = false, length = 100)
    private String title;
 
    @Version
    private long version;
     
    //The constructor, builder, and other methods are omitted
}