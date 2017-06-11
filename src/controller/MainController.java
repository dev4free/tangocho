package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bl.*;
import model.entities.Cards;
import com.fasterxml.jackson.databind.*;

class ParamsAnswer {
	public Integer cardId;
	public Boolean failed;
	public Boolean skip;
	public Integer nextTime;
}
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
		try {
			HttpSession session = request.getSession(true);
			MBLIMain currentModel = (MBLIMain)session.getAttribute("model");
			ObjectMapper mapper = new ObjectMapper();
			if (currentModel == null) {
				currentModel = new MBLMain();
				session.setAttribute("model", currentModel);
				currentModel.init();
			}
			String command = request.getParameter("command"); 
			String params = request.getParameter("params"); 

			if (command.equals("startNormalSession")) {
				currentModel.startNormalSession();				
				Cards card = currentModel.getNextCard();
				mapper = new ObjectMapper();

				ParamShowCardReplay replay = new ParamShowCardReplay();
				replay.card = card;
				replay.command = ParamShowCardReplay.SHOW_CARD;
				//Object to arJSON in String
				String jsonInString = mapper.writeValueAsString(replay);
				
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(jsonInString);
			} else if (command.equals("loadOnlyNewCards")) {
				currentModel.startOnlyNewCardsSession();				
				Cards card = currentModel.getNextCard();
				mapper = new ObjectMapper();

				ParamShowCardReplay replay = new ParamShowCardReplay();
				replay.card = card;
				replay.command = ParamShowCardReplay.SHOW_CARD;
				String jsonInString = mapper.writeValueAsString(replay);
				
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(jsonInString);
			} else if (command.equals("loadOnlyOldCards")) {
				currentModel.startOnlyOldCardsSession();				
				Cards card = currentModel.getNextCard();
				mapper = new ObjectMapper();

				ParamShowCardReplay replay = new ParamShowCardReplay();
				replay.card = card;
				replay.command = ParamShowCardReplay.SHOW_CARD;
				String jsonInString = mapper.writeValueAsString(replay);
				
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(jsonInString);
			} else if (command.equals("setAnswer")) {
				ParamsAnswer paramsAnswer = mapper.readValue(params, ParamsAnswer.class);
				
				currentModel.applyAnswer(paramsAnswer.cardId, paramsAnswer.failed, paramsAnswer.skip, paramsAnswer.nextTime);
				
				ParamShowCardReplay replay = currentModel.replayToSetAnswer();
				mapper = new ObjectMapper();

				//Object to JSON in String
				String jsonInString = mapper.writeValueAsString(replay);
				
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(jsonInString);
			} else {
				ParamGenericReplay replay = new ParamGenericReplay(ParamGenericReplay.NO_VALID_COMMAND, ParamGenericReplay.NO_VALID_COMMAND);
				mapper = new ObjectMapper();

				//Object to JSON in String
				String jsonInString = mapper.writeValueAsString(replay);
				
				response.setContentType("text/html; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(jsonInString);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			
			ParamGenericReplay replay = new ParamGenericReplay(ParamGenericReplay.SHOW_ERROR, sw.toString());
			ObjectMapper mapper = new ObjectMapper();

			//Object to JSON in String
			String jsonInString = mapper.writeValueAsString(replay);
			
			response.setContentType("text/html; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(jsonInString);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
