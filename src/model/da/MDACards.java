package model.da;



import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import model.entities.Cards;

public class MDACards {
	private MDAMain mainDataAccess = null;
	
	public MDACards(MDAMain mainDataAccess) {
		this.mainDataAccess = mainDataAccess;
	}

	public void LoadNewCards(List<Cards> cardList) {
		
	}
	
	public Cards getCardById(int id) throws Exception{
		Connection conn = mainDataAccess.getConnection();
		Statement stmt = null;

		stmt = conn.createStatement();
		String sql;
		sql = "SELECT id,deck_id,question,answer,last_time,next_time,reviewed FROM tangocho.cards where id = 2";
		ResultSet rs = stmt.executeQuery(sql);

		Cards card = new Cards();
		// STEP 5: Extract data from result set
		while (rs.next()) {
			// Retrieve by column name
			card.setId(rs.getInt("id"));
			card.setDeckId(rs.getInt("deck_id"));
			card.setQuestion(rs.getString("question"));
			card.setAnswer(rs.getString("answer"));
			card.setLastTime(rs.getDate("last_time"));
			card.setNextTime(rs.getInt("next_time"));
			card.setReviewed(rs.getBoolean("reviewed"));
		}
		// STEP 6: Clean-up environment
		rs.close();
		stmt.close();
		//conn.close();
		return card;
	}
}
