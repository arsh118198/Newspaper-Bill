package BillStatus;

public class BeansBill {
	String mobile, dos, doe, papers, price;
	int status, bill;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
	public String getDoe() {
		return doe;
	}
	public void setDoe(String doe) {
		this.doe = doe;
	}
	public String getPapers() {
		return papers;
	}
	public void setPapers(String papers) {
		this.papers = papers;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getBill() {
		return bill;
	}
	public void setBill(int bill) {
		this.bill = bill;
	}
	public BeansBill(String mobile, String dos, String doe, String papers, String price, int bill) {
		super();
		this.mobile = mobile;
		this.dos = dos;
		this.doe = doe;
		this.papers = papers;
		this.price = price;
		this.bill = bill;
	}
	public BeansBill()
	{}
}
