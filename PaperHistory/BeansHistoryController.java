package PaperHistory;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BeansHistoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboPapers;

    @FXML
    private TableView<BeanCustomers> tbl;

    @FXML
    private Button btnFetch;

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
    ObservableList<BeanCustomers> list;

    @FXML
    void doFetch(ActionEvent event) {
    	
    	TableColumn<BeanCustomers, String> mobile= new TableColumn<BeanCustomers, String>("Mobile No");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	
    	TableColumn<BeanCustomers, String> name= new TableColumn<BeanCustomers, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	
    	TableColumn<BeanCustomers, String> address= new TableColumn<BeanCustomers, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	
    	TableColumn<BeanCustomers, String> area= new TableColumn<BeanCustomers, String>("Area");
    	area.setCellValueFactory(new PropertyValueFactory<>("area"));
    	
    	TableColumn<BeanCustomers, String> city= new TableColumn<BeanCustomers, String>("City");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));
    	
    	TableColumn<BeanCustomers, String> dos= new TableColumn<BeanCustomers, String>("Date of Start");
    	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
    	
    	TableColumn<BeanCustomers, String> spapers= new TableColumn<BeanCustomers, String>("Selected Papers");
    	spapers.setCellValueFactory(new PropertyValueFactory<>("spapers"));
    	
    	TableColumn<BeanCustomers, String> sprice= new TableColumn<BeanCustomers, String>("Selected Price");
    	sprice.setCellValueFactory(new PropertyValueFactory<>("sprice"));
    	
    	TableColumn<BeanCustomers, String> status= new TableColumn<BeanCustomers, String>("Status");
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(mobile, name, address, area, city, dos, spapers, sprice, status);
    	
    	list=FXCollections.observableArrayList();
    	
    	try{
    		String slktd=comboPapers.getSelectionModel().getSelectedItem();
    		
    		stmt=con.prepareStatement("select * from customers where spapers like '%"+slktd+"%'");
    		
    		ResultSet rs=stmt.executeQuery();
    		
    		while(rs.next())
    		{
    			String mobile1=rs.getString("mobile");
    			String name1=rs.getString("name");
    			String address1=rs.getString("address");
    			String area1=rs.getString("area");
    			String city1=rs.getString("city");
    			Date dos1=rs.getDate("dos");
    			String dos11=dos1.toString();
    			String spapers1=rs.getString("spapers");
    			String sprice1=rs.getString("sprice");
    			int status1=rs.getInt("status");
    			
    			BeanCustomers bean= new BeanCustomers(mobile1, name1, address1, area1, city1, dos11, spapers1, sprice1, status1);
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
    	String papers[]={"Select","Dainik Bhaskar","Dainik Jagran","The Tribune","The Hindu","Hindustan Times"};
    	comboPapers.getItems().addAll(papers);
    	comboPapers.getSelectionModel().select(0);
        assert comboPapers != null : "fx:id=\"comboPapers\" was not injected: check your FXML file 'BeansHistory.fxml'.";
        assert tbl != null : "fx:id=\"tbl\" was not injected: check your FXML file 'BeansHistory.fxml'.";
        assert btnFetch != null : "fx:id=\"btnFetch\" was not injected: check your FXML file 'BeansHistory.fxml'.";

    }
}
