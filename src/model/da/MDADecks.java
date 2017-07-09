package model.da;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entities.Cards;
import model.entities.Decks;

public class MDADecks {
	private MDAMain mainDataAccess = null;

	public MDADecks(MDAMain mainDataAccess) {
		this.mainDataAccess = mainDataAccess;
	}

	public Decks getDeckById(int deckId) throws Exception {
		Connection conn = mainDataAccess.getConnection();
		String query = "SELECT id, account_id, description, last_access FROM tangocho.decks where id = ?";
		
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setInt(1, deckId);
				
		ResultSet rs = pstm.executeQuery();

		Decks deck = new Decks();
		if (rs.next()) {			
			deck.setId(rs.getInt("id"));
			deck.setAccountId(rs.getInt("account_id"));
			deck.setDescription(rs.getString("description"));
			deck.setLastAccess(rs.getDate("last_access"));
		}
		rs.close();
		pstm.close();
		return deck;
	}
	
	public void updateAccessDate(int deckId) throws Exception {
		String updateTableSQL = "update decks set last_access =  current_timestamp where id= ?"; 
		PreparedStatement preparedStatement = mainDataAccess.getConnection().prepareStatement(updateTableSQL);

		preparedStatement.setInt(1, deckId);
		preparedStatement.executeUpdate();
	}
}
