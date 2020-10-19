package CustomerDetails;

public class BeansCustomers {
	int status;
	String mobile, address, name, city, dos, spapers, sprice;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getSpapers() {
		return spapers;
	}
	public void setSpapers(String spapers) {
		this.spapers = spapers;
	}
	public String getSprice() {
		return sprice;
	}
	public void setSprice(String sprice) {
		this.sprice = sprice;
	}
	public BeansCustomers( String mobile, String name, String address, String city, String dos,
			String spapers, String sprice,int status) {
		super();
		
		this.mobile = mobile;
		this.name = name;
		this.address = address;
		
		this.city = city;
		this.dos = dos;
		this.spapers = spapers;
		this.sprice = sprice;
		this.status = status;
	}
	public BeansCustomers()
	{}
}
