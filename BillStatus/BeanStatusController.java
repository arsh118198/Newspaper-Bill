package BillStatus;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;




import Grid.Bills.BeanBills;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class BeanStatusController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton radRec;

    @FXML
    private ToggleGroup status;

    @FXML
    private RadioButton radPend;
    
    @FXML
    private Button btnCheck;

    @FXML
    private TableView<BeansBill> tbl;
    
    Connection con;
	PreparedStatement stmt;
	void doConnect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/news", "root", "Skg123456");
			System.out.println("Connected");
		}
    	catch (ClassNotFoundException | SQLException  e) {
			e.printStackTrace();
		}
	}
    
    @FXML
    void doCheck(ActionEvent event) {
    	

	
			
		    	TableColumn<BeansBill, String> mobile=new TableColumn<BeansBill, String>("Mobile No");
		    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
		    	
		    	TableColumn<BeansBill, String> dos=new TableColumn<BeansBill, String>("Date of Start");
		    	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
		    	
		    	TableColumn<BeansBill, String> doe=new TableColumn<BeansBill, String>("Date of End");
		    	doe.setCellValueFactory(new PropertyValueFactory<>("doe"));
		    	
		    	TableColumn<BeansBill, String> papers=new TableColumn<BeansBill, String>("Papers");
		    	papers.setCellValueFactory(new PropertyValueFactory<>("papers"));
		    	
		    	TableColumn<BeansBill, String> price=new TableColumn<BeansBill, String>("Price");
		    	price.setCellValueFactory(new PropertyValueFactory<>("price"));
		    	
		    	TableColumn<BeansBill,Integer> bill=new TableColumn<BeansBill, Integer>("Bill");
		    	bill.setCellValueFactory(new PropertyValueFactory<>("bill"));
		    	
		    	
		    	tbl.getColumns().clear();
		    	tbl.getColumns().addAll(mobile, dos, doe, papers, price, bill);
		    	
		    	ObservableList<BeansBill> list=FXCollections.observableArrayList();
		    	
		if(radPend.isSelected()==true)	
		{
		    	
		    	try{
		    		stmt=con.prepareStatement("Select * from bill where status=0");
		    		ResultSet rs=stmt.executeQuery();
		    		while(rs.next())
		    		{
		    			String mobile1=rs.getString("mobile");
		    			String ds=rs.getString("dos");
		    			
		    			String de=rs.getString("doe");
		    			
		    			String papers1=rs.getString("papers");
		    			String price1=rs.getString("price");
		    			int bill1=rs.getInt("bill");
		    			
		    			
		    			
		    			BeansBill bean= new BeansBill(mobile1, ds, de, papers1, price1, bill1);
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
		if(radRec.isSelected()==true)	
		{
		    	
		    	try{
		    		stmt=con.prepareStatement("Select * from bill where status=1");
		    		ResultSet rs=stmt.executeQuery();
		    		while(rs.next())
		    		{
		    			String mobile1=rs.getString("mobile");
		    			String ds=rs.getString("dos");
		    			
		    			String de=rs.getString("doe");
		    			
		    			String papers1=rs.getString("papers");
		    			String price1=rs.getString("price");
		    			int bill1=rs.getInt("bill");
		    			
		    			
		    			
		    			BeansBill bean= new BeansBill(mobile1, ds, de, papers1, price1, bill1);
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
		
    }
    
	

    @FXML
    void initialize() {
    	doConnect();
        assert radRec != null : "fx:id=\"radRec\" was not injected: check your FXML file 'BeanStatus.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'BeanStatus.fxml'.";
        assert radPend != null : "fx:id=\"radPend\" was not injected: check your FXML file 'BeanStatus.fxml'.";
        assert tbl != null : "fx:id=\"tbl\" was not injected: check your FXML file 'BeanStatus.fxml'.";

    }
}
