package model.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import model.da.MDAIMain;
import model.entities.Cards;
import model.entities.Decks;
import model.entities.History;

public class MDAMain  implements MDAIMain {
	private Connection conn = null;
	private MDACards cardsDataAcess = null;
	private MDADecks decksDataAcess = null;
	private MDAHistory historyDataAcess = null;
	
	public void init() throws Exception {
		if (conn == null) {
			Class.forName("org.postgresql.Driver");   
			String url = "jdbc:postgresql://localhost/tangocho_db";
			Properties props = new Properties();
			props.setProperty("user","tangocho");
			props.setProperty("password","hogehoge");
			conn = DriverManager.getConnection(url, props);
		}
		if (cardsDataAcess == null) {
			cardsDataAcess = new MDACards(this);
		}
		if (historyDataAcess == null) {
			historyDataAcess = new MDAHistory(this);
		}
		if (decksDataAcess == null) {
			decksDataAcess = new MDADecks(this);
		}
	}
	
	public Cards getCardById(int id) throws Exception {
		return cardsDataAcess.getCardById(id);
	}
	
	public boolean isNewDay(int deckId) {
		return true;
	}
	public List<Cards> LoadCardsToReview(int deckId, boolean dayLimit) throws Exception {
		return cardsDataAcess.LoadCardsToReview(deckId, dayLimit);
	}

	public Connection getConnection() {
		return conn;
	}
	public List<Cards> LoadNewCards(int deckId, Integer limit) throws Exception {
		return cardsDataAcess.LoadNewCards(deckId, limit);
	}

	@Override
	public void UpdateCardAndHistory(Cards card) throws Exception {
		try {
			conn.setAutoCommit(false);
			cardsDataAcess.UpdateCards(card);
			History history = new History();
			history.setCardId(card.getId());
			history.setNextTime(card.getNextTime());
			history.setReviewedTime(card.getLastTime());
			decksDataAcess.updateAccessDate(card.getDeckId());
			historyDataAcess.InsertHistory(history);
		} catch (SQLException e ) {
			conn.rollback();
			throw e;
		} finally {
			conn.setAutoCommit(true);
		}
	}

	@Override
	public Decks getDeckById(int id) throws Exception {
		return decksDataAcess.getDeckById(id);
	}
}
