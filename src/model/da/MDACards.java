package model.da;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.entities.Cards;

public class MDACards {
	private MDAMain mainDataAccess = null;
	
	public MDACards(MDAMain mainDataAccess) {
		this.mainDataAccess = mainDataAccess;
	}

	public List<Cards> LoadNewCards(int deckId, Integer limit) throws Exception {
		Connection conn = mainDataAccess.getConnection();
		
		String limitStr =  "";
		if (limit != null) {
			limitStr = " LIMIT " + limit; 
		}
		
		List<Cards> result = new ArrayList<Cards>(); 
		String query = "SELECT id,deck_id,question,answer,last_time,next_time,reviewed FROM tangocho.cards where deck_id = ? " +
						"and skip = FALSE and reviewed = false" + limitStr;
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setInt(1, deckId);
				
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			Cards card = new Cards();
			card.setId(rs.getInt("id"));
			card.setDeckId(rs.getInt("deck_id"));
			card.setQuestion(rs.getString("question"));
			card.setAnswer(rs.getString("answer"));
			card.setLastTime(rs.getDate("last_time"));
			card.setNextTime(rs.getInt("next_time"));
			card.setReviewed(rs.getBoolean("reviewed"));
			result.add(card);
		}
		rs.close();
		pstm.close();
		
		return result;
	}
	
	public List<Cards> LoadCardsToReview(int deckId, boolean dayLimit) throws Exception {
		Connection conn = mainDataAccess.getConnection();
		
		List<Cards> result = new ArrayList<Cards>(); 
//		String query =  "SELECT id,deck_id,question,answer,last_time,next_time,reviewed " +
//						"FROM tangocho.cards where deck_id = ? order by " +
//						"last_time + next_time * interval '1 second' ";
		
		String limitStr = " and  last_time + next_time * interval '1 second' < current_timestamp ";
		if (!dayLimit) {
			limitStr = "";
		}
		String query =  "SELECT id,deck_id,question,answer,last_time,next_time, reviewed " +
				"FROM tangocho.cards " +
				"where reviewed = true and deck_id = ? " + limitStr +
				"order by last_time + next_time * interval '1 second' ";
		
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setInt(1, deckId);
				
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			Cards card = new Cards();
			card.setId(rs.getInt("id"));
			card.setDeckId(rs.getInt("deck_id"));
			card.setQuestion(rs.getString("question"));
			card.setAnswer(rs.getString("answer"));
			card.setLastTime(rs.getDate("last_time"));
			card.setNextTime(rs.getInt("next_time"));
			card.setReviewed(rs.getBoolean("reviewed"));
			result.add(card);
		}
		rs.close();
		pstm.close();
		
		return result;
	}
	
	public Cards getCardById(int id) throws Exception{
		Connection conn = mainDataAccess.getConnection();

		String query = "SELECT id,deck_id,question,answer,last_time,next_time,reviewed FROM tangocho.cards where id = :id";
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setString(id, "id");
		ResultSet rs = pstm.executeQuery();

		Cards card = new Cards();
		if (!rs.next()) {			
			card.setId(rs.getInt("id"));
			card.setDeckId(rs.getInt("deck_id"));
			card.setQuestion(rs.getString("question"));
			card.setAnswer(rs.getString("answer"));
			card.setLastTime(rs.getDate("last_time"));
			card.setNextTime(rs.getInt("next_time"));
			card.setReviewed(rs.getBoolean("reviewed"));
		}
		rs.close();
		pstm.close();
		//conn.close();
		return card;
	}

	public void UpdateCards(Cards card) throws Exception {
		String updateTableSQL = "UPDATE tangocho.cards\n"+
								"SET deck_id = ?,\n" +
								"question = ?,\n" +
								"answer = ?,\n" +
								"last_time = ?,\n"+
								"next_time = ?,\n" +
								"reviewed = ?,\n"+
								"skip = ?\n" +
								"WHERE id = ?\n";
		
		PreparedStatement preparedStatement = mainDataAccess.getConnection().prepareStatement(updateTableSQL);

		int i=0;
		preparedStatement.setInt(1, card.getDeckId());
		preparedStatement.setString(2, card.getQuestion());
		preparedStatement.setString(3, card.getAnswer());
		preparedStatement.setTimestamp(4, new java.sql.Timestamp(card.getLastTime().getTime()));
		preparedStatement.setInt(5, card.getNextTime());
		preparedStatement.setBoolean(6, card.getReviewed());
		preparedStatement.setBoolean(7, card.getSkip());
		preparedStatement.setInt(8, card.getId());
		preparedStatement.executeUpdate();
		
		
	}
}
