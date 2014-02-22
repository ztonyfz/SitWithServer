import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.jdom2.Document;
import org.jdom2.Element;


public class RequestService {
	
	public DB db;
	
	public RequestService() {
		db = new DB();
	}
	
	public void makeRequest(String userId, String restaurantId, String tableId) throws SQLException {
		Request request = new Request();
		request.userId = userId;
		request.tableId = tableId;
		request.restaruantId = restaurantId;
		request.time = new Date();
		
		db.saveRequest(request);
		db.updateTable(tableId);
	}
	
	public Document getRequests(String userId) throws SQLException {
		List<Request> requests = db.getRequests(userId);
		
		Element requestsE = new Element("requests");
		
		for (Request request : requests) {
			Element requestE = new Element("request");
			
			Element idE = new Element("id");
			idE.setText(request.id);
			requestE.addContent(idE);
			
			Element timeE = new Element("time");
			idE.setText(request.time.toString());
			requestE.addContent(timeE);
			
			Element statusE = new Element("status");
			statusE.setText(request.status);
			requestE.addContent(statusE);
			
			Element tableE = new Element("table");
			tableE.setText(request.tableId);
			requestE.addContent(tableE);
			
			requestsE.addContent(requestE);
		}
		
		Document document = new Document(requestsE);
		
		return document;
	}
	
	public Document getTable(String tableId) throws SQLException {
		Table table = db.getTable(tableId);
		
		Element tableE = new Element("table");
		
		Element timeE = new Element("time");
		timeE.setText(table.time.toString());
		tableE.addContent(timeE);
		
		Element usersE = new Element("users");
		List<User> users = db.getUsers(tableId);
		for (User user : users) {
			Element userE = new Element("user");
			
			Element nameE = new Element("name");
			nameE.setText(user.fullname);
			userE.addContent(nameE);
			
			Element genderE = new Element("gender");
			genderE.setText(user.gender);
			userE.addContent(genderE);
			
			Element ageE = new Element("age");
			ageE.setText(user.age);
			userE.addContent(ageE);
			
			usersE.addContent(userE);
		}
		
		Document document = new Document(tableE);
		
		return document;
	}

}
