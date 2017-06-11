package controller;

import model.entities.Cards;

public class ParamShowCardReplay {
	public Cards card;
	public String command;
	public final static String SHOW_CARD = "showCard";
	public final static String NO_MORE_CARDS = "noMoreCards";
	
	public ParamShowCardReplay(){
		super();
	}

	public ParamShowCardReplay(Cards card, String command) {
		super();
		this.card = card;
		this.command = command;
	}
}
