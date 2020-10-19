package Grid.Bills;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BeanTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<BeanBills> tbl;

    @FXML
    private Button btnLoadAll;

    @FXML
    void doLoadAll(ActionEvent event) {
    	TableColumn<BeanBills, String> mobile=new TableColumn<BeanBills, String>("Mobile No");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	
    	TableColumn<BeanBills, String> dos=new TableColumn<BeanBills, String>("Date of Start");
    	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
    	
    	TableColumn<BeanBills, String> doe=new TableColumn<BeanBills, String>("Date of End");
    	doe.setCellValueFactory(new PropertyValueFactory<>("doe"));
    	
    	TableColumn<BeanBills, String> papers=new TableColumn<BeanBills, String>("Papers");
    	papers.setCellValueFactory(new PropertyValueFactory<>("papers"));
    	
    	TableColumn<BeanBills, String> price=new TableColumn<BeanBills, String>("Price");
    	price.setCellValueFactory(new PropertyValueFactory<>("price"));
    	
    	TableColumn<BeanBills,Integer> bill=new TableColumn<BeanBills, Integer>("Bill");
    	bill.setCellValueFactory(new PropertyValueFactory<>("bill"));
    	
    	TableColumn<BeanBills, Integer> status=new TableColumn<BeanBills, Integer>("Status");
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(mobile, dos, doe, papers, price, bill, status);
    	
    	ObservableList<BeanBills> list=FXCollections.observableArrayList();
    	
    	try{
    		stmt=con.prepareStatement("Select * from bill");
    		ResultSet rs=stmt.executeQuery();
    		while(rs.next())
    		{
    			String mobile1=rs.getString("mobile");
    			String ds=rs.getString("dos");
    			
    			String de=rs.getString("doe");
    			
    			String papers1=rs.getString("papers");
    			String price1=rs.getString("price");
    			int bill1=rs.getInt("bill");
    			int status1=rs.getInt("status");
    			
    			
    			BeanBills bean= new BeanBills(mobile1, ds, de, papers1, price1, bill1, status1);
    			list.add(bean);
    			System.out.println(bean.getBill()+" "+bean.getMobile());
    		}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	tbl.setItems(list);
    }
    Connection con;
    PreparedStatement stmt;
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
        assert tbl != null : "fx:id=\"tbl\" was not injected: check your FXML file 'BeanTable.fxml'.";
        assert btnLoadAll != null : "fx:id=\"btnLoadAll\" was not injected: check your FXML file 'BeanTable.fxml'.";

    }
}
