package BillGenerator;

import java.net.URL;
import java.security.spec.DSAGenParameterSpec;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class BillController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtMobile;

    @FXML
    private Button btnFetch;

    @FXML
    private ListView<String> listPapers;

    @FXML
    private ListView<String> listRate;

    @FXML
    private DatePicker dpStart;

    @FXML
    private DatePicker dpEnd;

    @FXML
    private Button btnBill;

    @FXML
    private TextField txtAmount;

    @FXML
    private Button btnSave;

    @FXML
    void doBill(ActionEvent event) throws SQLException {
    	
    	
    	float sum=0;
    	ObservableList<String> allp=listRate.getItems();
    	for(String stn:allp)
    	{
    		sum=sum+Float.parseFloat(stn);
    	}
    	LocalDate start= dpStart.getValue();
    	LocalDate end= dpEnd.getValue();
    	
    	DaysBetweenDates(start, end, sum);
    	
    	LocalDate dateE=dpEnd.getValue();
    	Date de=Date.valueOf(dateE);
    	String mobile=txtMobile.getText();
    	
    	stmt1=con.prepareStatement("update customers set dos=? where mobile=?");
    	stmt1.setDate(1, de);
    	stmt1.setString(2, mobile);
    	stmt1.executeUpdate();
    	stmt1.close();
    }
    void DaysBetweenDates(LocalDate s,LocalDate e, float sum)
    {
    	int startDay = s.getDayOfYear();
    	int endDay=e.getDayOfYear();
    	int sYear=s.getYear();
    	int eYear=e.getYear();
    	
    	int diff= (eYear*365+endDay)-(sYear*365+startDay)+1;
    	txtAmount.setText(Float.toString(diff*sum));
    }
    String selPapers,selRate;
    @FXML
    void doFetch(ActionEvent event) throws SQLException {
    	listPapers.getItems().clear();
		listRate.getItems().clear();;
    	
    	stmt= con.prepareStatement("select * from customers where mobile=?");
    	stmt.setString(1, txtMobile.getText());
    	ResultSet rs=stmt.executeQuery();
    	while(rs.next())
    	{
    		java.sql.Date dat=rs.getDate("dos");
    		selPapers=rs.getString("spapers");
    		String[] aryPapers=selPapers.split(",");
    		selRate=rs.getString("sprice");
    		String[] aryRate=selRate.split(",");
    		listPapers.getItems().addAll(aryPapers);
    		listRate.getItems().addAll(aryRate);
    		dpStart.setValue(dat.toLocalDate());
    	}
    }

    @FXML
    void doSave(ActionEvent event) throws SQLException {
    	String mobile=txtMobile.getText();
    	String sp=listPapers.getSelectionModel().getSelectedItems().toString();
    	sp=sp.substring(1,sp.length()-1);
    	String sr=listRate.getSelectionModel().getSelectedItems().toString();
    	sr=sr.substring(1,sr.length()-1);
    	LocalDate dateE=dpEnd.getValue();
    	Date de=Date.valueOf(dateE);
    	LocalDate dateS=dpStart.getValue();
    	Date ds=Date.valueOf(dateS);
    	
    	String bill=txtAmount.getText();
    	int status=0;
    	stmt=con.prepareStatement("insert into bill(mobile,dos,doe,papers,price,bill,status) values(?,?,?,?,?,?,?)");
    	stmt.setString(1,mobile);
    	stmt.setDate(2,ds);
    	stmt.setDate(3,de);
    	stmt.setString(4,selPapers);
    	stmt.setString(5,selRate);
    	stmt.setString(6,bill);
    	stmt.setInt(7, status);
    	stmt.executeUpdate();
    	stmt.close();
    
    }
    Connection con;
    PreparedStatement stmt,stmt1;
    void doConnect()
    {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/news", "root", "Skg123456");
			System.out.println("Connected");
		}
    	catch (ClassNotFoundException | SQLException  e) {
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
    	doConnect();
        assert txtMobile != null : "fx:id=\"txtMobile\" was not injected: check your FXML file 'Bill.fxml'.";
        assert btnFetch != null : "fx:id=\"btnFetch\" was not injected: check your FXML file 'Bill.fxml'.";
        assert listPapers != null : "fx:id=\"listPapers\" was not injected: check your FXML file 'Bill.fxml'.";
        assert listRate != null : "fx:id=\"listRate\" was not injected: check your FXML file 'Bill.fxml'.";
        assert dpStart != null : "fx:id=\"txtDos\" was not injected: check your FXML file 'Bill.fxml'.";
        assert dpEnd != null : "fx:id=\"dpEnd\" was not injected: check your FXML file 'Bill.fxml'.";
        assert btnBill != null : "fx:id=\"btnBill\" was not injected: check your FXML file 'Bill.fxml'.";
        assert txtAmount != null : "fx:id=\"txtAmount\" was not injected: check your FXML file 'Bill.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'Bill.fxml'.";

    }
}
