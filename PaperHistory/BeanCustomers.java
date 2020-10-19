package PaperHistory;

public class BeanCustomers {

	String mobile, name, address, area, city, dos, spapers, sprice, status;

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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public BeanCustomers(String mobile, String name, String address, String area, String city, String dos,
			String spapers, String sprice, String status) {
		super();
		this.mobile = mobile;
		this.name = name;
		this.address = address;
		this.area = area;
		this.city = city;
		this.dos = dos;
		this.spapers = spapers;
		this.sprice = sprice;
		this.status = status;
	}
	public BeanCustomers()
	{}
}
