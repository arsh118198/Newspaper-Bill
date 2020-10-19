package CustomerManager;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CustMngrController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtArea;

    @FXML
    private TextArea txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private DatePicker dpStart;

    @FXML
    private ListView<String> listPapers;

    @FXML
    private ListView<String> listRate;

    @FXML
    private Button btnSel;

    @FXML
    private ListView<String> listSelPapers;

    @FXML
    private ListView<String> listSelRate;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnStop;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnClose;
    
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
    void doFetch(ActionEvent event) throws SQLException {
    	stmt= con.prepareStatement("select * from customers where mobile=?");
    	stmt.setString(1, txtMobile.getText());
    	
    	ResultSet rs=stmt.executeQuery();
    	while(rs.next())
    	{
    		String name=rs.getString("name");
    		txtName.setText(""+name);
    		String address=rs.getString("address");
    		txtAddress.setText(""+address);
    		String area=rs.getString("area");
    		txtArea.setText(""+area);
    		String city=rs.getString("city");
    		txtCity.setText(""+city);
    		java.sql.Date date=rs.getDate("dos");
    		dpStart.setValue(date.toLocalDate());
    		
    		String selPapers=rs.getString("spapers");
    		String[] aryPapers=selPapers.split(",");
    		String selRate=rs.getString("sprice");
    		String[] aryRate=selRate.split(",");
    		listSelPapers.getItems().addAll(aryPapers);
    		listSelRate.getItems().addAll(aryRate);
    		
    	}
    	
    }

    @FXML
    void doClear(ActionEvent event) {
    	txtMobile.setText("");
    	txtName.setText("");
    	txtAddress.setText("");
    	txtCity.setText("");
    	txtArea.setText("");
    	dpStart.setValue(null);
    	listPapers.getSelectionModel().clearSelection();
    	listRate.getSelectionModel().clearSelection();
    	listSelPapers.getItems().clear();
    	listSelRate.getItems().clear();
    }
    
    @FXML
    void doStop(ActionEvent event) {
    	String mobile=txtMobile.getText();
    	try
    	{
    	stmt=con.prepareStatement("delete from customers where mobile=?");
    	stmt.setString(1,mobile);
    	stmt.executeUpdate();
    	stmt.close();
    	System.out.println("Record Deleted");
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	
    }

    @FXML
    void doSave(ActionEvent event) {
    	String mobile=txtMobile.getText();
    	String name=txtName.getText();
    	String address=txtAddress.getText();
    	String area=txtArea.getText();
    	String city=txtCity.getText();
    	
    	LocalDate date=dpStart.getValue();
    	Date ds=Date.valueOf(date);
    	
    	try
    	{
	    	stmt=con.prepareStatement("insert into customers(mobile, name, address, area, city, dos, spapers, sprice) values(?,?,?,?,?,?,?,?)");
	    	stmt.setString(1,mobile);
	    	stmt.setString(2,name);
	    	stmt.setString(3,address);
	    	stmt.setString(4,area);
	    	stmt.setString(5,city);
	    	stmt.setDate(6,ds);
	    	String sp=listPapers.getSelectionModel().getSelectedItems().toString();
	    	sp=sp.substring(1,sp.length()-1);
	    	String sr=listRate.getSelectionModel().getSelectedItems().toString();
	    	sr=sr.substring(1,sr.length()-1);
	    	stmt.setString(7,sp);
	    	stmt.setString(8,sr);
	    	stmt.executeUpdate();
	    	stmt.close();
	    	System.out.println("Record Saved");
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    }

    @FXML
    void doSel(ActionEvent event) 
    {
    	listSelPapers.getItems().clear();
    	listSelRate.getItems().clear();
    	ObservableList<String> slktd=listPapers.getSelectionModel().getSelectedItems();
    	listSelPapers.getItems().addAll(slktd);
    	
    	ObservableList<Integer>inxs= listPapers.getSelectionModel().getSelectedIndices();
   
   
    	for (Integer i : inxs) 
    		{
    			listRate.getSelectionModel().select(i);
    		}
   
   ObservableList<String> slktd11=listRate.getSelectionModel().getSelectedItems();
	listSelRate.getItems().addAll(slktd11);
   
   }

    @FXML
    void doUpdate(ActionEvent event) {
    	
    	String mobile=txtMobile.getText();
    	String name=txtName.getText();
    	String address=txtAddress.getText();
    	String area=txtArea.getText();
    	String city=txtCity.getText();
    	LocalDate date=dpStart.getValue();
    	
    	
    	try
    	{
    	stmt=con.prepareStatement("update customers set name=?, address=?, area=?, city=?, dos=? ,spapers=?, sprice=? where mobile=?");
    	
    	stmt.setString(1,name);
    	stmt.setString(2,address);
    	stmt.setString(3,area);
    	stmt.setString(4,city);
    	java.sql.Date dat=java.sql.Date.valueOf(date);
    	stmt.setDate(5,dat);
    	
    	String sp=listPapers.getSelectionModel().getSelectedItems().toString();
    	sp=sp.substring(1,sp.length()-1);
    	String sr=listRate.getSelectionModel().getSelectedItems().toString();
    	sr=sr.substring(1,sr.length()-1);
    	stmt.setString(6,sp);
    	stmt.setString(7,sr);
    	stmt.setString(8, mobile);
    	stmt.executeUpdate();
    	
    	stmt.close();
    	System.out.println("Updated Successfully");
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    }

    @FXML
    void initialize() {
    	doConnect();
    	listPapers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	listRate.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	listPapers.getItems().add("Dainik Bhaskar");
    	listRate.getItems().add("3");
    	listPapers.getItems().add("Dainik Jagran");
    	listRate.getItems().add("4");
    	listPapers.getItems().add("The Tribune");
    	listRate.getItems().add("6");
    	listPapers.getItems().add("The Hindu");
    	listRate.getItems().add("7");
    	listPapers.getItems().add("Hindustan Times");
    	listRate.getItems().add("8");
        assert txtMobile != null : "fx:id=\"txtMobile\" was not injected: check your FXML file 'CustMngr.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'CustMngr.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'CustMngr.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'CustMngr.fxml'.";
        assert txtCity != null : "fx:id=\"txtCity\" was not injected: check your FXML file 'CustMngr.fxml'.";
        assert dpStart != null : "fx:id=\"dpStart\" was not injected: check your FXML file 'CustMngr.fxml'.";
        assert listPapers != null : "fx:id=\"listPapers\" was not injected: check your FXML file 'CustMngr.fxml'.";
        assert listRate != null : "fx:id=\"listRate\" was not injected: check your FXML file 'CustMngr.fxml'.";
        assert btnSel != null : "fx:id=\"btnSel\" was not injected: check your FXML file 'CustMngr.fxml'.";
        assert listSelPapers != null : "fx:id=\"listSelPapers\" was not injected: check your FXML file 'CustMngr.fxml'.";
        assert listSelRate != null : "fx:id=\"listSelRate\" was not injected: check your FXML file 'CustMngr.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'CustMngr.fxml'.";
        assert btnStop != null : "fx:id=\"btnStop\" was not injected: check your FXML file 'CustMngr.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'CustMngr.fxml'.";
        assert btnClose != null : "fx:id=\"btnClose\" was not injected: check your FXML file 'CustMngr.fxml'.";

    }
}
