package model.bl;

import java.util.List;

import model.da.MDAIMain;
import model.da.MDAMain;
import model.entities.Cards;

public class MBLMain implements MBLIMain{
	private List<Cards> currentCardList = null;
	private MDAIMain mainDataAccess = null;
	private Cards currentCard = null;
	private int currentCardIndex;
	public void init() throws Exception {
		if (mainDataAccess == null) {
			mainDataAccess = new MDAMain();
			mainDataAccess.init();
			currentCardIndex = -1;
		}
	}
	public Cards getCardById(int id) throws Exception {
		return mainDataAccess.getCardById(id);
	}

	public void loadCardsList(int deckId) throws Exception {
		currentCardList = mainDataAccess.LoadCardsByDecdId(deckId);
	}
	public List<Cards> getCardList() {
		return currentCardList;
	}
	
	public Cards getNextCard() {
		currentCardIndex++;
		if (currentCardIndex < currentCardList.size()){
			currentCard = currentCardList.get(currentCardIndex);
		}
		return currentCard;
	}
}
