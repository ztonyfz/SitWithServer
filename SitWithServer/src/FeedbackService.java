import java.sql.SQLException;
import java.util.Date;


public class FeedbackService {
	
	public DB db;
	
	public FeedbackService() {
		db = new DB();
	}
	
	public void giveFeedBack(String userId, String tableId, String contents) throws SQLException {
		FeedBack feedback = new FeedBack();
		feedback.userId = userId;
		feedback.tableId = tableId;
		feedback.contents = contents;
		feedback.time = new Date();
		
		db.giveFeedback(feedback);
		
		
	}

}
