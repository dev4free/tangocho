package model.bl;

import model.entities.Cards;

public interface MBLIMain {
	public void init() throws Exception;
	public Cards getCardById(int id) throws Exception;
}
