package BillCollector;

import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class BillCollectorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtMobile;

    @FXML
    private Button btnFetch;

    @FXML
    private ListView<String> listDOS;

    @FXML
    private ListView<String> listDOE;

    @FXML
    private ListView<String> listAMOUNT;
    
    @FXML
    private Button btnCollect;
    		
    @FXML
    void doCollect(ActionEvent event) throws SQLException {
    	ObservableList<String> slktd=listDOS.getSelectionModel().getSelectedItems();
    	ObservableList<Integer>inxs= listDOS.getSelectionModel().getSelectedIndices();
   
    	for (Integer i : inxs) 
    		{
    			listDOE.getSelectionModel().select(i);
    			listAMOUNT.getSelectionModel().select(i);
    		}
    	String mob= txtMobile.getText();
    	String ados= listDOS.getSelectionModel().getSelectedItem();
    	String adoe= listDOE.getSelectionModel().getSelectedItem();
    	String aamt= listAMOUNT.getSelectionModel().getSelectedItem();
    	
    	LocalDate b=LocalDate.parse(ados);
    	java.sql.Date c=java.sql.Date.valueOf(b);
    	
    	stmt1=con.prepareStatement("update bill set status=1 where mobile=? and dos=?");
    	stmt1.setString(1, mob);
    	stmt1.setDate(2, c);
    	stmt1.executeUpdate();
    	stmt1.close();
    	
    	listDOS.getItems().remove(ados);
    	listDOE.getItems().remove(adoe);
    	listAMOUNT.getItems().remove(aamt);
    }

    @FXML
    void doFetch(ActionEvent event) throws SQLException {
    	stmt= con.prepareStatement("select * from bill where mobile=? and status=0");
    	stmt.setString(1, txtMobile.getText());
    	//stmt.setString(2, txtMobile.getText());
    	ResultSet rs=stmt.executeQuery();
    	while(rs.next())
    	{
    		java.sql.Date dats=rs.getDate("dos");
    		String dats1=dats.toString();
    		java.sql.Date date=rs.getDate("doe");
    		String date1=date.toString();
    		String amount= rs.getString("bill");
    		
    		listDOS.getItems().addAll(dats1);
    		listDOE.getItems().addAll(date1);
    		listAMOUNT.getItems().addAll(amount);
    		
    	}
    }
    
    void doSel(MouseEvent event) 
    {
    	
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
    	listDOS.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	listDOE.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	listAMOUNT.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        assert txtMobile != null : "fx:id=\"txtMobile\" was not injected: check your FXML file 'BillCollector.fxml'.";
        assert btnFetch != null : "fx:id=\"btnFetch\" was not injected: check your FXML file 'BillCollector.fxml'.";
        assert listDOS != null : "fx:id=\"listDOS\" was not injected: check your FXML file 'BillCollector.fxml'.";
        assert listDOE != null : "fx:id=\"listDOE\" was not injected: check your FXML file 'BillCollector.fxml'.";
        assert listAMOUNT != null : "fx:id=\"listAMOUNT\" was not injected: check your FXML file 'BillCollector.fxml'.";
        assert btnCollect != null : "fx:id=\"btnCollect\" was not injected: check your FXML file 'BillCollector.fxml'.";

    }
}
