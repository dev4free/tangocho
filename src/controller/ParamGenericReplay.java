package controller;

import model.entities.Cards;

public class ParamGenericReplay {
	public String command;
	public String errorMessage;
	public final static String NO_VALID_COMMAND = "showCard";
	public final static String SHOW_ERROR = "noMoreCards";
	
	public ParamGenericReplay(){
		super();
	}

	public ParamGenericReplay(String command, String errorMessage) {
		this.command = command;
		this.errorMessage = errorMessage;
	}
}
