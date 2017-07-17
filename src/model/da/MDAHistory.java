package model.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	public int getTotalAnswers(int deckId) throws Exception {
		int result = 0;
		Connection conn = mainDataAccess.getConnection();
		
		String query =  "SELECT count(*) as num FROM tangocho.history where card_id in (select card_id from cards where deck_id = ?)";
		
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setInt(1, deckId);
				
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			result = rs.getInt("num");
		}
		rs.close();
		pstm.close();
		
		return result;
	}

	public int getCorrectAnswers(int deckId) throws Exception {
		int result = 0;
		Connection conn = mainDataAccess.getConnection();
		
		String query =  "SELECT count(*) as num FROM tangocho.history where next_time > 0 and card_id in (select card_id from cards where deck_id = ?)";
		
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setInt(1, deckId);
				
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			result = rs.getInt("num");
		}
		rs.close();
		pstm.close();
		
		return result;
	}
}
