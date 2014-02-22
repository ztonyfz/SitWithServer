import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DB {
	
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	public static final String DB_URL = "jdbc:mysql://54.197.7.33/testlog";
	
	public static final String USER = "root";
	public static final String PASS = "15619";
	
	public Connection conn;
	
	public DB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Restaurant getRestaurant(String id) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM Restaurant WHERE id = " + id;
	    ResultSet rs = stmt.executeQuery(sql);
	    
	    Restaurant restaurant = new Restaurant();
	    while (rs.next()){
	    	restaurant.address = rs.getString("address");
	    	restaurant.category = rs.getString("category");
	    	restaurant.id = String.valueOf(rs.getInt("id"));
	    	restaurant.name = rs.getString("name");
	    	restaurant.phone = rs.getString("phone");
	    	restaurant.picture = rs.getString("picture");
	    	restaurant.rating = rs.getString("rating");
	    	restaurant.website = rs.getString("website");
	    }
	    
	    return restaurant;
	}

	public List<Table> getTables(String restaurantId) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM Table WHERE restaurant_id = " + restaurantId;
	    ResultSet rs = stmt.executeQuery(sql);
	    
	    List<Table> tables = new ArrayList<Table>();
	    while (rs.next()){
	    	Table table = new Table();
	    	table.id = String.valueOf(rs.getInt("id"));
	    	table.time = rs.getDate("time");
	    	
	    	tables.add(table);
	    }
	    
	    return tables;
	}

	public void saveRequest(Request request) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "INSERT INTO Request (user_id, table_id, restaurant_id, status, time) values (" + request.userId + ", " + 
				request.tableId + ", " + request.restaruantId + ", '" + request.status + "', '" + request.time.toString() + "'";
		
		stmt.executeQuery(sql);
		
	}

	public void updateTable(String tableId) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM Table WHERE id = " + tableId;
	    ResultSet rs = stmt.executeQuery(sql);
	    
	    rs.next();
	    int count = rs.getInt("count");
	    count--;
	    
	    sql = "UPDATE Table SET count = " + count + " WHERE id = " + tableId;
	    stmt.executeUpdate(sql);
		
	}

	public List<Request> getRequests(String userId) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM Request WHERE user_id = " + userId;
	    ResultSet rs = stmt.executeQuery(sql);
	    
	    List<Request> requests = new ArrayList<Request>();
	    while (rs.next()){
	    	Request request = new Request();
	    	request.id = rs.getString("id");
	    	request.status = rs.getString("status");
	    	request.time = rs.getDate("time");
	    	request.tableId = rs.getString("table_id");
	    	
	    	requests.add(request);
	    }
	    
	    return requests;
	}

	public void giveFeedback(FeedBack feedback) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "INSERT INTO Feedback (user_id, table_id, contents, time) values (" + feedback.userId + ", " + 
				feedback.tableId + ", '" + feedback.contents + "', '" + feedback.time.toString() + "'";
		
		stmt.executeQuery(sql);
		
	}

	public List<User> getUsers(String tableId) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM Request WHERE table_id = " + tableId;
	    ResultSet rs = stmt.executeQuery(sql);
	    
	    List<User> users = new ArrayList<User>();
	    while (rs.next()){
	    	String sql2 = "SELECT * FROM User WHERE user_id = " + rs.getInt("user_id");
	    	ResultSet rs2 = stmt.executeQuery(sql2);
	    	
	    	rs2.next();
	    	User user = new User();
	    	user.fullname = rs2.getString("fullname");
	    	user.gender = rs2.getString("gender");
	    	user.age = rs2.getString("age");
	    	
	    	users.add(user);
	    }
	    
	    return users;
	}

	public Table getTable(String tableId) throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM Table WHERE table_id = " + tableId;
	    ResultSet rs = stmt.executeQuery(sql);
	    
	    rs.next();
	    Table table = new Table();
	    table.time = rs.getDate("time");
	    
	    return table;
	}

	public List<Restaurant> getRestaurants() throws SQLException {
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM Restaurant ORDER BY neiborhood";
	    ResultSet rs = stmt.executeQuery(sql);
	    
	    List<Restaurant> restaurants = new ArrayList<Restaurant>();
	    while (rs.next()){
	    	Restaurant restaurant = new Restaurant();
	    	restaurant.id = rs.getString("id");
	    	restaurant.neiborhood = rs.getString("neiborhood");
	    	restaurant.name = rs.getString("name");
	    	
	    	restaurants.add(restaurant);
	    }
	    
	    return restaurants;
	}

}
