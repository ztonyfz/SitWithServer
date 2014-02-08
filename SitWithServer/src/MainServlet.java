import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
	
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uri = request.getRequestURI();
		String type = uri.substring(uri.lastIndexOf("/") + 1);
		response.setContentType("text/html");
		System.out.println(type);
		
		Element restaurant = new Element("restaurant");
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
		xmlOutput.output(document, response.getOutputStream());
		
        
	}

}
