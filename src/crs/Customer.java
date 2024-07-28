package crs;

public class Customer {
	
	private String customerId;
	
	private String name;

	public Customer(String customerId, String name) {
		super();
		this.customerId = customerId;
		this.name = name;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
