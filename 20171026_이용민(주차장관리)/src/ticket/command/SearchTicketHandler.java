package ticket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import ticket.service.SearchRequest;
import ticket.service.SearchTicketService;
import ticket.service.TicketList;

public class SearchTicketHandler implements CommandHandler {

	private static final String VIEW_FORM = "ticketRegister.do";
	private SearchTicketService searchTicketService = new SearchTicketService();
	private SearchRequest searchReq = new SearchRequest();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		// TODO Auto-generated method stub
		String carNo = req.getParameter("carno");

		searchReq.setCarNo(carNo);

		TicketList ticket = searchTicketService.search(searchReq);

		req.setAttribute("ticketInfo", ticket);
		return VIEW_FORM;
	}

}
