package model.da;

import java.util.List;

import model.entities.Cards;

public interface MDAIMain {
	
	public void init() throws Exception;
	public Cards getCardById(int id) throws Exception;
	public List<Cards> LoadCardsToReview(int deckId, boolean dayLimit) throws Exception;
	public List<Cards> LoadNewCards(int deckId, Integer limit) throws Exception;
	public void UpdateCardAndHistory(Cards card) throws Exception;
	
}
