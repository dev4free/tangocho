package model.da;

import java.sql.PreparedStatement;
import java.util.Date;

import model.entities.Cards;
import model.entities.History;

public class MDAHistory {
	private MDAMain mainDataAccess = null;
	
	public MDAHistory(MDAMain mainDataAccess) {
		this.mainDataAccess = mainDataAccess;
	}
	
	public void InsertHistory(History history) throws Exception {
		String insertSQL = "INSERT INTO tangocho.history (" +
						   "card_id, \n" +
						   "reviewed_time,\n" + 
						   "next_time\n" +
						   ") VALUES (?,?,?)\n";
		
		PreparedStatement preparedStatement = mainDataAccess.getConnection().prepareStatement(insertSQL);

		int i=1;
		preparedStatement.setInt(i++, history.getCardId());
		preparedStatement.setDate(i++, new java.sql.Date(history.getReviewedTime().getTime()));
		preparedStatement.setInt(i++, history.getNextTime());
		preparedStatement.executeUpdate();
	}
}
