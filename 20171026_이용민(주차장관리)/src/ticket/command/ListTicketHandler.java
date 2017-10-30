package ticket.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import ticket.service.ListTicketService;
import ticket.service.TicketList;

public class ListTicketHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		// TODO Auto-generated method stub
		ListTicketService ltService = new ListTicketService();
		
		req.setAttribute("ticketList", ltService.getTicketList());
		return "/WEB-INF/view/listTicketForm.jsp";
	}

}
