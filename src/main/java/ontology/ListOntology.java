package ontology;

public class ListOntology {
	private String name;
	private String id;
	private String delay;
	private String provied;
	private String place;
	
	public ListOntology(String name, String id, String delay, String provied, String place) {
		this.name = name;
		this.id = id;
		this.delay = delay;
		this.provied = provied;
		this.place = place;
	}
	
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	public String getDelay() {
		return delay;
	}
	public String getProvied() {
		return provied;
	}
	public String getPlace() {
		return place;
	}

	public void setName(String name){
		this.name = name;
	}
	public void setId(String id){
		this.id = id;
	}
	public void setDelay(String delay){
		this.delay = delay;
	}
	public void setProvied(String provied){
		this.provied = provied;
	}
	public void setPlace(String place){
		this.place = place;
	}
}
