package Grid.Bills;

public class BeanBills {

	int status,bill;
	String mobile, doe, dos, papers, price;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getBill() {
		return bill;
	}
	public void setBill(int bill) {
		this.bill = bill;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDoe() {
		return doe;
	}
	public void setDoe(String doe) {
		this.doe = doe;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
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
	public void setPrices(String price) {
		this.price = price;
		
	}
	public BeanBills(  String mobile, String dos, String doe, String papers, String price,int bill, int status) {
		super();
		this.status = status;
		this.bill = bill;
		this.mobile = mobile;
		this.doe = doe;
		this.dos = dos;
		this.papers = papers;
		this.price = price;
	}
	public BeanBills()
	{
		
	}
	
}