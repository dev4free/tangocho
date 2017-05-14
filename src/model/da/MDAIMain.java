package model.da;

import java.util.List;

import model.entities.Cards;

public interface MDAIMain {
	
	public void init() throws Exception;
	public Cards getCardById(int id) throws Exception;
	public List<Cards> LoadCardsByDecdId(int deckId) throws Exception;
}
