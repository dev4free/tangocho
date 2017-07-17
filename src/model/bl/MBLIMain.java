package model.bl;

import java.util.List;

import controller.ParamShowCardReplay;
import model.entities.Cards;
import model.pojo.Statistics;

public interface MBLIMain {
	public void init() throws Exception;
	public Cards getCardById(int id) throws Exception;
	public Cards getNextCard() throws Exception;
	public void startNormalSession() throws Exception;
	public List<Cards> getCardList();
	public ParamShowCardReplay replayToSetAnswer() throws Exception;
	public void applyAnswer(Integer cardId, Boolean failed, Boolean skip, Integer nextTime) throws Exception;
	public void startOnlyNewCardsSession() throws Exception;
	public void startOnlyOldCardsSession() throws Exception;
	public Statistics getTotalStatistics();
	public Statistics getSessionStatistics();
}