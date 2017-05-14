package model.da;



import java.sql.Connection;
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

	public List<Cards> LoadCardsByDecdId(int deckId) throws Exception {
		Connection conn = mainDataAccess.getConnection();
		
		List<Cards> result = new ArrayList<Cards>(); 
		String query = "SELECT id,deck_id,question,answer,last_time,next_time,reviewed FROM tangocho.cards where deck_id = ?";
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
}
