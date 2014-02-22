import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public RestaurantService restaurantService;
	
	public RequestService requestService;
	
	public FeedbackService feedbackService;
	
	public void init() {
		restaurantService = new RestaurantService();
		requestService = new RequestService();
		feedbackService = new FeedbackService();
	}
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uri = request.getRequestURI();
		String type = uri.substring(uri.lastIndexOf("/") + 1);
		response.setContentType("text/html");
		System.out.println(type);
		
		/*Element restaurant = new Element("restaurant");
		Element name = new Element("name");
		name.setText("Union Grill");
		restaurant.addContent(name);
		
		Element address = new Element("address");
		address.setText("413 S Craig St, Pittsburgh, PA 15213");
		restaurant.addContent(address);
		
		Element phone = new Element("phone_number");
		phone.setText("(412) 681-8620");
		restaurant.addContent(phone);
		
		Document document = new Document(restaurant);
		
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		xmlOutput.output(document, response.getOutputStream());*/
		
		try {
			String action = request.getParameter("action");
			Document document = null;
			if (action.equals("getRestaurants")) {
				document = restaurantService.getRestaurants();
			} else if (action.equals("getRestaurant")) {
				document = restaurantService.getRestaurant(request.getParameter("restaurantId"));
			} else if (action.equals("getAvailableTables")) {
				document = restaurantService.getAvailableTables(request.getParameter("restaurantId"));
			} else if (action.equals("makeRequest")) {
				requestService.makeRequest(request.getParameter("userId"), request.getParameter("restaurant_id"), request.getParameter("tableId"));
			} else if (action.equals("getRequests")) {
				document = requestService.getRequests(request.getParameter("userId"));
			} else if (action.equals("getTable")) {
				document = requestService.getTable(request.getParameter("tableId"));
			} else if (action.equals("giveFeedback")) {
				feedbackService.giveFeedBack(request.getParameter("userId"), request.getParameter("tableId"), request.getParameter("contents"));
			}
			
			if (document != null) {
				XMLOutputter xmlOutput = new XMLOutputter();
				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(document, response.getOutputStream());
			}
			
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	

}
