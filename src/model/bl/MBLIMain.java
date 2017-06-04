package model.bl;

import java.util.List;

import model.entities.Cards;

public interface MBLIMain {
	public void init() throws Exception;
	public Cards getCardById(int id) throws Exception;
	public void loadCardsList() throws Exception;
	public List<Cards> getCardList();
	public Cards getNextCard() throws Exception;
	public void applyAnswer(Integer cardId, Boolean failed, Boolean skip, Integer nextTime) throws Exception;
}