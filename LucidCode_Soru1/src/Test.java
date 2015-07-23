
public class Test {

	public static void main(String[] args) {
		Resource resource = new Resource();
		resource.setId(1);
		resource.setKey(1L);
		resource.setResourceValue("Resource");
		Target target = new Target();
		System.out.println("Before call api");
		System.out.println(resource);
		System.out.println(target);
		Api.populateTarget(resource, target);
		System.out.println("After call api");
		System.out.println(resource);
		System.out.println(target);
		
	
	}

}
