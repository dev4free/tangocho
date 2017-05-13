package model.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import model.da.MDAIMain;
import model.entities.Cards;

public class MDAMain  implements MDAIMain {
	private Connection conn = null;
	private MDACards cardsDataAcess = null;
	
	public void init() throws Exception {
		if (conn == null) {
			Class.forName("org.postgresql.Driver");   
			String url = "jdbc:postgresql://localhost/tangocho_db";
			Properties props = new Properties();
			props.setProperty("user","tangocho");
			props.setProperty("password","hogehoge");
			conn = DriverManager.getConnection(url, props);
		}
		if (cardsDataAcess == null) {
			cardsDataAcess = new MDACards(this);
		}
	}
	
	public Cards getCardById(int id) throws Exception {
		return cardsDataAcess.getCardById(id);
	}
	public Connection getConnection() {
		return conn;
	}

}
