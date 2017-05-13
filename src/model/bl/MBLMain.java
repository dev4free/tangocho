package model.bl;

import model.da.MDAIMain;
import model.da.MDAMain;
import model.entities.Cards;

public class MBLMain implements MBLIMain{
	public int counter=0;
	private MDAIMain mainDataAccess = null;
	public void init() throws Exception {
		if (mainDataAccess == null) {
			mainDataAccess = new MDAMain();
			mainDataAccess.init();
		}
	}
	public Cards getCardById(int id) throws Exception {
		return mainDataAccess.getCardById(id);
	}
}
