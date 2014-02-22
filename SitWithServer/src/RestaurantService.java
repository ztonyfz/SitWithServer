import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import org.jdom2.Document;
import org.jdom2.Element;


public class RestaurantService {
	
	public DB db;
	
	public RestaurantService() {
		db = new DB();
	}
	
	public Document getRestaurants() throws SQLException {
		List<Restaurant> restaurants = db.getRestaurants();
		Element neiborhoodsE = new Element("neiborhoods");
		String current = "";
		for (Restaurant restaurant : restaurants) {
			if (!restaurant.neiborhood.equals(current)) {
				current = restaurant.neiborhood;
				Element neiborhoodE = new Element("neiborhood");
				
				Element nameE = new Element("name");
				nameE.setText(current);
				neiborhoodE.addContent(nameE);
				
				neiborhoodsE.addContent(neiborhoodE);
				
				Element restaurantsE = new Element("restaurants");
				neiborhoodE.addContent(restaurantsE);
			}
			Element restaurantE = new Element("restaurant");
			
			Element idE = new Element("id");
			idE.setText(restaurant.id);
			restaurantE.addContent(idE);
			
			Element nameE = new Element("name");
			nameE.setText(restaurant.name);
			restaurantE.addContent(nameE);
			
		}
		
		Document document = new Document(neiborhoodsE);
		
		return document;
	}
	
	public Document getRestaurant(String id) throws SQLException {
		Restaurant restaurant = db.getRestaurant(id);
		
		Element restaurantE = new Element("restaurant");
		Element idE = new Element("id");
		idE.setText(restaurant.id);
		restaurantE.addContent(idE);
		
		Element nameE = new Element("name");
		nameE.setText(restaurant.name);
		restaurantE.addContent(nameE);
		
		Element categoryE = new Element("category");
		nameE.setText(restaurant.category);
		categoryE.addContent(categoryE);
		
		Element addressE = new Element("address");
		addressE.setText(restaurant.address);
		categoryE.addContent(addressE);
		
		Element websiteE = new Element("website");
		addressE.setText(restaurant.website);
		categoryE.addContent(websiteE);
		
		Element phoneE = new Element("phone");
		addressE.setText(restaurant.phone);
		categoryE.addContent(phoneE);
		
		Element pictureE = new Element("picture");
		addressE.setText(restaurant.picture);
		categoryE.addContent(pictureE);
		
		Element ratingE = new Element("rating");
		addressE.setText(restaurant.rating);
		categoryE.addContent(ratingE);
		
		Document document = new Document(restaurantE);
		
		return document;
		
	}
	
	public Document getAvailableTables(String restaurantId) throws SQLException {
		List<Table> tables = db.getTables(restaurantId);
		
		Element tablesE = new Element("tables");
		
		for (Table table : tables) {
			Element tableE = new Element("table");
			
			Element idE = new Element("id");
			idE.setText(table.id);
			tableE.addContent(idE);
			
			Element timeE = new Element("time");
			idE.setText(table.time.toString());
			tableE.addContent(timeE);
			
			tablesE.addContent(tableE);
		}
		
		Document document = new Document(tablesE);
		
		return document;
	}

}
