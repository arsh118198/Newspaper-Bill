package CustomerDetails;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import Grid.Customers.BeanCustomers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BeansDetailsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> comboArea;

    @FXML
    private Button btnFetch;
    
    @FXML
    private Button btnFetchAll;

    @FXML
    private TableView<BeansCustomers> tbl;
    
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
    ObservableList<BeansCustomers> list;

    @SuppressWarnings("unchecked")
	@FXML
    void doFetch(ActionEvent event) {
    	TableColumn<BeansCustomers, String> mobile= new TableColumn<BeansCustomers, String>("Mobile No");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	
    	TableColumn<BeansCustomers, String> name= new TableColumn<BeansCustomers, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	
    	TableColumn<BeansCustomers, String> address= new TableColumn<BeansCustomers, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	
    	TableColumn<BeansCustomers, String> city= new TableColumn<BeansCustomers, String>("City");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));
    	
    	TableColumn<BeansCustomers, String> dos= new TableColumn<BeansCustomers, String>("Date of Start");
    	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
    	
    	TableColumn<BeansCustomers, String> spapers= new TableColumn<BeansCustomers, String>("Selected Papers");
    	spapers.setCellValueFactory(new PropertyValueFactory<>("spapers"));
    	
    	TableColumn<BeansCustomers, String> sprice= new TableColumn<BeansCustomers, String>("Selected Price");
    	sprice.setCellValueFactory(new PropertyValueFactory<>("sprice"));
    	
    	TableColumn<BeansCustomers, Integer> status= new TableColumn<BeansCustomers, Integer>("Status");
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(mobile, name, address, city, dos, spapers, sprice, status);
    	
    	list=FXCollections.observableArrayList();
    	
    	try{
    		String slktd =comboArea.getSelectionModel().getSelectedItem();
    		stmt=con.prepareStatement("select * from customers where area=?");
    		stmt.setString(1, slktd);
    		ResultSet rs=stmt.executeQuery();
    		
    		while(rs.next())
    		{
    			String mobile1=rs.getString("mobile");
    			String name1=rs.getString("name");
    			String address1=rs.getString("address");
    			String city1=rs.getString("city");
    			Date dos1=rs.getDate("dos");
    			String dos11=dos1.toString();
    			String spapers1=rs.getString("spapers");
    			String sprice1=rs.getString("sprice");
    			int status1=rs.getInt("status");
    			
    			BeansCustomers bean= new BeansCustomers(mobile1, name1, address1, city1, dos11, spapers1, sprice1, status1);	
    			list.add(bean);
    		}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	tbl.setItems(list);
    }
    
    void doFetchAreas()
    {
    	comboArea.getItems().add("Select");
    	comboArea.getSelectionModel().select(0);
    	try {
			stmt=con.prepareStatement("select distinct area from customers");
			ResultSet rs=stmt.executeQuery();
	    	while(rs.next())
	    	{
	    		comboArea.getItems().add(rs.getString("area"));
	    	}
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    
    @FXML
    void doFetchAll(ActionEvent event) {
    	TableColumn<BeansCustomers, String> mobile= new TableColumn<BeansCustomers, String>("Mobile No");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	
    	TableColumn<BeansCustomers, String> name= new TableColumn<BeansCustomers, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	
    	TableColumn<BeansCustomers, String> address= new TableColumn<BeansCustomers, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	
    	TableColumn<BeansCustomers, String> city= new TableColumn<BeansCustomers, String>("City");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));
    	
    	TableColumn<BeansCustomers, String> dos= new TableColumn<BeansCustomers, String>("Date of Start");
    	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
    	
    	TableColumn<BeansCustomers, String> spapers= new TableColumn<BeansCustomers, String>("Selected Papers");
    	spapers.setCellValueFactory(new PropertyValueFactory<>("spapers"));
    	
    	TableColumn<BeansCustomers, String> sprice= new TableColumn<BeansCustomers, String>("Selected Price");
    	sprice.setCellValueFactory(new PropertyValueFactory<>("sprice"));
    	
    	TableColumn<BeansCustomers, Integer> status= new TableColumn<BeansCustomers, Integer>("Status");
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(mobile, name, address, city, dos, spapers, sprice, status);
    	
    	list=FXCollections.observableArrayList();
    	
    	try{
    		
    		stmt=con.prepareStatement("select * from customers");
    		
    		ResultSet rs=stmt.executeQuery();
    		
    		while(rs.next())
    		{
    			String mobile1=rs.getString("mobile");
    			String name1=rs.getString("name");
    			String address1=rs.getString("address");
    			String city1=rs.getString("city");
    			Date dos1=rs.getDate("dos");
    			String dos11=dos1.toString();
    			String spapers1=rs.getString("spapers");
    			String sprice1=rs.getString("sprice");
    			int status1=rs.getInt("status");
    			
    			BeansCustomers bean= new BeansCustomers(mobile1, name1, address1, city1, dos11, spapers1, sprice1, status1);	
    			list.add(bean);
    		}
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	tbl.setItems(list);
    
    }

    @FXML
    void initialize() {
    	doConnect();
    	doFetchAreas();
    	
        assert comboArea != null : "fx:id=\"comboArea\" was not injected: check your FXML file 'BeansDetails.fxml'.";
        assert btnFetch != null : "fx:id=\"btnFetch\" was not injected: check your FXML file 'BeansDetails.fxml'.";
        assert tbl != null : "fx:id=\"tbl\" was not injected: check your FXML file 'BeansDetails.fxml'.";

    }
}
