package model.bl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.ParamShowCardReplay;
import model.da.MDAIMain;
import model.da.MDAMain;
import model.entities.Cards;
import model.entities.Decks;
import model.pojo.Statistics;

public class MBLMain implements MBLIMain{
	private List<Cards> currentCardList = null;
	private MDAIMain mainDataAccess = null;
	private Cards currentCard = null;
	private Decks currentDeck = null;
	private Statistics totalStatistics = new Statistics();
	private Statistics sessionStatistics = new Statistics();
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
		}
	}
	
	public Cards getCardById(int id) throws Exception {
		return mainDataAccess.getCardById(id);
	}

	private boolean isNewDay(Date deckAcessDayTime) {
		Date currenDate = new Date();
		long diff = currenDate.getTime() - deckAcessDayTime.getTime();
		long diffMinutes = diff / (60 * 1000) % 60;
		if (diffMinutes > 1440) {
			return true;
		}
		if (deckAcessDayTime.getDay() != currenDate.getDay()){
			return true;
		}
		return false;
	}
	public Statistics getTotalStatistics() {
		return totalStatistics;
	}

	public Statistics getSessionStatistics() {
		return sessionStatistics;
	}
	
	private void initTotalStatistics() throws Exception {
		mainDataAccess.loadStatistcs(totalStatistics, currentDeckId);
		totalStatistics.correctAnswersPerc = 0; 
		totalStatistics.failedAnswersPerc = 0; 
		totalStatistics.correctAnswersPerc = (float)((float)totalStatistics.correctAnswers * 100 /(float)totalStatistics.totalAnswers); 
		totalStatistics.failedAnswersPerc = 100 - totalStatistics.correctAnswersPerc; 
	}
	public void startNormalSession() throws Exception {
		currentCardIndex = -1;
		studySessionType = StudySessionType.NORMAL;
		isNewCards = true;
		currentDeckId = 1;
		currentDeck = mainDataAccess.getDeckById(currentDeckId);
		totalStatistics.clear();
		sessionStatistics.clear();
		initTotalStatistics();
		if (isNewDay(currentDeck.getLastAccess())) {
			currentCardList = mainDataAccess.LoadNewCards(currentDeckId, new Integer(10));
		} else {
			currentCardList = new ArrayList<Cards>();
		}
	}

	public List<Cards> getCardList() {
		return currentCardList;
	}
	
	public ParamShowCardReplay replayToSetAnswer() throws Exception {
		String command = ParamShowCardReplay.SHOW_CARD;
		Cards card = getNextCard();
		
		if (card == null) {
			command = ParamShowCardReplay.NO_MORE_CARDS;
		}
		ParamShowCardReplay replay = new ParamShowCardReplay(currentCard, totalStatistics, sessionStatistics, command);
		return replay; 
	}

	public Cards getNextCard() throws Exception {
		currentCardIndex++;
		currentCard = null;
		if (currentCardIndex < currentCardList.size()){
			currentCard = currentCardList.get(currentCardIndex);
		}
		if (currentCard == null && isNewCards &&  studySessionType ==  StudySessionType.NORMAL) {
				currentCardList = mainDataAccess.LoadCardsToReview(currentDeckId, false);
				if (currentCardList.size() > 0) {
					currentCardIndex=0;
					isNewCards = false;
					currentCard = currentCardList.get(currentCardIndex);
				}
		};
		return currentCard; 
	}
	@Override
	public void applyAnswer(Integer cardId, Boolean failed, Boolean skip, Integer nextTime) throws Exception {
		if (currentCard == null) {
			return;
		}
			
		if (cardId.intValue() != currentCard.getId().intValue()) {
			throw new Exception("Current card and saving card Id are not matching ("+currentCard.getId().intValue()+" - "+ cardId +")");
		}
		sessionStatistics.totalCards++;
		sessionStatistics.reviewedCards++;
		totalStatistics.reviewedCards++;
		sessionStatistics.totalAnswers++;
		totalStatistics.totalAnswers++;
		if (failed) {
			sessionStatistics.failedAnswers++;
			totalStatistics.failedAnswers++;
		} else {
			sessionStatistics.correctAnswers++;
			totalStatistics.correctAnswers++;
		}
		sessionStatistics.correctAnswersPerc = (float)((float)sessionStatistics.correctAnswers * 100 /(float)sessionStatistics.totalAnswers); 
		sessionStatistics.failedAnswersPerc = 100 - sessionStatistics.correctAnswersPerc; 
		totalStatistics.correctAnswersPerc = (float)((float)totalStatistics.correctAnswers * 100 /(float)totalStatistics.totalAnswers); 
		totalStatistics.failedAnswersPerc = 100 - totalStatistics.correctAnswersPerc; 

		currentCard.setLastTime(new Date());
		currentCard.setSkip(skip);
		currentCard.setReviewed(true);
		currentCard.setNextTime(nextTime*24*60*60);
		mainDataAccess.updateCardAndHistory(currentCard);
		if (failed) {
			int newIndex = currentCardIndex+4;
			if (newIndex > currentCardList.size()) {
				currentCardList.add(currentCardList.get(currentCardIndex));
			} else {
				currentCardList.add(newIndex,currentCardList.get(currentCardIndex));
			}
			currentCardList.remove(currentCardIndex);
			currentCardIndex--;
		} 
	}

	@Override
	public void startOnlyNewCardsSession() throws Exception {
		sessionStatistics.clear();
		currentCardIndex = -1;
		studySessionType = StudySessionType.NEWONLY;
		isNewCards = true;
		currentDeckId = 1;
		currentCardList = mainDataAccess.LoadNewCards(currentDeckId, null);
	}
	public void startOnlyOldCardsSession() throws Exception {
		sessionStatistics.clear();
		currentCardIndex = -1;
		studySessionType = StudySessionType.REVIEWONLY;
		isNewCards = true;
		currentDeckId = 1;
		currentCardList = mainDataAccess.LoadCardsToReview(currentDeckId, true);
	}
}
