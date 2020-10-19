package Dashboard;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCustMngr;

    @FXML
    private Button btnBillGen;

    @FXML
    private Button btnBillColl;

    @FXML
    private Button btnBillStatus;

    @FXML
    private Button btnCustDetails;

    @FXML
    private Button btnPprHistory;

    @FXML
    void doBillColl(ActionEvent event) {
    	try{
    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("BillCollector/BillCollector.fxml"));
    	Scene scene=new Scene(root);
    	Stage stage= new Stage();
    	stage.setScene(scene);
    	stage.show();
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    }

    @FXML
    void doBillGen(ActionEvent event) {
    	try{
        	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("BillGenerator/Bill.fxml"));
        	Scene scene=new Scene(root);
        	Stage stage= new Stage();
        	stage.setScene(scene);
        	stage.show();
        	}
        	catch(IOException e)
        	{
        		e.printStackTrace();
        	}
    }

    @FXML
    void doBillStatus(ActionEvent event) {
    	try{
        	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("BillStatus/BeanStatus.fxml"));
        	Scene scene=new Scene(root);
        	Stage stage= new Stage();
        	stage.setScene(scene);
        	stage.show();
        	}
        	catch(IOException e)
        	{
        		e.printStackTrace();
        	}
    }

    @FXML
    void doCustDetails(ActionEvent event) {
    	try{
        	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("CustomerDetails/BeansDetails.fxml"));
        	Scene scene=new Scene(root);
        	Stage stage= new Stage();
        	stage.setScene(scene);
        	stage.show();
        	}
        	catch(IOException e)
        	{
        		e.printStackTrace();
        	}
    }

    @FXML
    void doCustMngr(ActionEvent event) {
    	try{
        	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("CustomerManager/CustMngr.fxml"));
        	Scene scene=new Scene(root);
        	Stage stage= new Stage();
        	stage.setScene(scene);
        	stage.show();
        	}
        	catch(IOException e)
        	{
        		e.printStackTrace();
        	}
    }

    @FXML
    void doPprHistory(ActionEvent event) {
    	try{
        	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("PaperHistory/BeansHistory.fxml"));
        	Scene scene=new Scene(root);
        	Stage stage= new Stage();
        	stage.setScene(scene);
        	stage.show();
        	}
        	catch(IOException e)
        	{
        		e.printStackTrace();
        	}
    }
    Connection con;
    
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
        assert btnCustMngr != null : "fx:id=\"btnCustMngr\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert btnBillGen != null : "fx:id=\"btnBillGen\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert btnBillColl != null : "fx:id=\"btnBillColl\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert btnBillStatus != null : "fx:id=\"btnBillStatus\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert btnCustDetails != null : "fx:id=\"btnCustDetails\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert btnPprHistory != null : "fx:id=\"btnPprHistory\" was not injected: check your FXML file 'Dashboard.fxml'.";

    }
}
