package carlosu.library.test.certification;

public class Constructor {

	
	/* Constructors
	 * Accept: empty, public, private, protected
	 */
	protected Constructor(){
	}
	
	/*
	 * Order  on Fields and Initialization blocks
	 */
	//{ System.out.println(name); }  // DOES NOT COMPILE
	private String name = "Fluffy";
	{ System.out.println(name); }  //COMPILE
		
}
