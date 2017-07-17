package controller;

import model.entities.Cards;
import model.pojo.Statistics;

public class ParamShowCardReplay {
	public Cards card;
	public Statistics totalStatistics;
	public Statistics sessionStatistics;
	public String command;
	public final static String SHOW_CARD = "showCard";
	public final static String NO_MORE_CARDS = "noMoreCards";
	
	
	public ParamShowCardReplay(){
		super();
	}

	public ParamShowCardReplay(Cards card, Statistics totalStatistics, Statistics sessionStatistics, String command) {
		super();
		this.card = card;
		this.totalStatistics = totalStatistics;
		this.sessionStatistics = sessionStatistics;
		this.command = command;
	}
}
