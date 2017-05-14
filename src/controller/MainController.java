package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bl.*;
import model.entities.Cards;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			HttpSession session = request.getSession(true);
			MBLIMain currentModel = (MBLIMain)session.getAttribute("model");
			if (currentModel == null) {
				currentModel = new MBLMain();
				session.setAttribute("model", currentModel);
				currentModel.init();
				currentModel.loadCardsList(1);
			}
			String command = request.getParameter("command"); 
			if (command.equals("getNextCard")) {
				Cards card = currentModel.getNextCard();
				ObjectMapper mapper = new ObjectMapper();

				//Object to JSON in String
				String jsonInString = mapper.writeValueAsString(card);
				
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(jsonInString);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
