package carlosu.library.test.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.Ignore;
import org.junit.Test;

public class CompletableFuture8Test {
	
	@Test
	public void test(){
		UserService userService = new UserService();
		
		CompletableFuture.supplyAsync(
				userService::listUsers
	    ).thenApply(
	            this::mapUsersToUserViews
	    ).thenAccept(
	            this::updateView
	    ).exceptionally(
	            throwable -> { showErrorDialogFor(throwable); return null; }
	    );
	}
	
	@Test
	@Ignore
	public void test2(){
		UserService userService = new UserService();
		CompletableFuture<List<Person>> responseFuture = CompletableFuture.supplyAsync(userService::listUsers);
		responseFuture.thenApply(this::mapUsersToUserViews)
					  .thenAccept(this::updateView)
					  .exceptionally(throwable -> { showErrorDialogFor(throwable); return null; });
	}
	
	
	
	private void showErrorDialogFor(Throwable throwable) {
		System.out.println(throwable.getMessage());
	}

	public List<Person> mapUsersToUserViews(List<Person> people) {
		for (Person person : people) {
			System.out.println(person);
		}
		return people;
	}
	
	public void updateView(List<Person> people) {
		System.out.println("Updated View!");
	}
	
	class Person {
		String name;

		Person(String name){
			this.name = name;
		}
		
		@Override
		public String toString(){
			return name;
		}
	}
	
	class UserService {
		public List<Person> listUsers(){
			List<Person> people = new ArrayList<CompletableFuture8Test.Person>();
			for (int i = 0; i < 250; i++) {
				people.add(new Person("Nane"+i));
			}
			return people;
		}
	}
}
