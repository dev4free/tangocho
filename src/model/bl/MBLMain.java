package model.bl;

import java.util.Date;
import java.util.List;

import model.da.MDAIMain;
import model.da.MDAMain;
import model.entities.Cards;

public class MBLMain implements MBLIMain{
	private List<Cards> currentCardList = null;
	private MDAIMain mainDataAccess = null;
	private Cards currentCard = null;
	private int currentCardIndex;
	private boolean isNewCards = false;
	private int currentDeckId;
	private enum StudySessionType {
	    NORMAL, NEWONLY, REVIEWONLY
	}
	private StudySessionType studySessionType;
	
	public void init() throws Exception {
		if (mainDataAccess == null) {
			mainDataAccess = new MDAMain();
			mainDataAccess.init();
			currentCardIndex = -1;
			studySessionType = StudySessionType.NORMAL;
			isNewCards = true;
			currentDeckId = 1;
			currentCardList = mainDataAccess.LoadNewCards(currentDeckId, new Integer(10));
		}
	}
	public Cards getCardById(int id) throws Exception {
		return mainDataAccess.getCardById(id);
	}

	public void loadCardsList() throws Exception {
		currentCardList = mainDataAccess.LoadCardsToReview(currentDeckId);
	}
	public List<Cards> getCardList() {
		return currentCardList;
	}
	
	public Cards getNextCard() throws Exception {
		currentCardIndex++;
		currentCard = null;
		if (currentCardIndex < currentCardList.size()){
			currentCard = currentCardList.get(currentCardIndex);
		}
		if (currentCard == null && isNewCards &&  studySessionType ==  StudySessionType.NORMAL) {
				currentCardList = mainDataAccess.LoadCardsToReview(currentDeckId);
				if (currentCardList.size() > 0) {
					currentCardIndex=0;
					currentCard = currentCardList.get(currentCardIndex);
				}
		};
		return currentCard; 
	}
	@Override
	public void applyAnswer(Integer cardId, Boolean failed, Boolean skip, Integer nextTime) throws Exception {
		if (cardId.intValue() != currentCard.getId().intValue()) {
			throw new Exception("Current card and saving card Id are not matching ("+currentCard.getId().intValue()+" - "+ cardId +")");
		}
		if (failed) {
			currentCardList.add(currentCardIndex+4,currentCardList.get(currentCardIndex));
			currentCardList.remove(currentCardIndex);
			currentCardIndex--;
		} else {
			currentCard.setLastTime(new Date());
			currentCard.setSkip(skip);
			currentCard.setReviewed(true);
			currentCard.setNextTime(nextTime*24*60*60);
			mainDataAccess.UpdateCardAndHistory(currentCard);
		}
	}
}
