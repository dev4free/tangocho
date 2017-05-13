package model.da;

import model.entities.Cards;

public interface MDAIMain {
	
	public void init() throws Exception;
	public Cards getCardById(int id) throws Exception;
}
