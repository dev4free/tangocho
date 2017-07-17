package model.da;

import java.util.List;

import model.entities.Cards;
import model.entities.Decks;
import model.pojo.Statistics;

public interface MDAIMain {
	
	public void init() throws Exception;
	public Cards getCardById(int id) throws Exception;
	public Decks getDeckById(int id) throws Exception;
	public List<Cards> LoadCardsToReview(int deckId, boolean dayLimit) throws Exception;
	public List<Cards> LoadNewCards(int deckId, Integer limit) throws Exception;
	public void updateCardAndHistory(Cards card) throws Exception;
	public void loadStatistcs(Statistics statistics, int deckId) throws Exception;
}
