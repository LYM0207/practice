package ticket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import ticket.service.JoinRequest;
import ticket.service.JoinTicketService;

public class JoinTicketHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/joinTicketForm.jsp";

	JoinTicketService joinService = new JoinTicketService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		// TODO Auto-generated method stub
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		String date = joinService.getStartDate();
		req.setAttribute("date", date);
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		JoinRequest joinReq = new JoinRequest();
		joinReq.setCarNo(req.getParameter("carno"));
		joinReq.setPhone(req.getParameter("phone"));
		joinReq.setGrade(req.getParameter("grade"));
		joinReq.setTstat(req.getParameter("tstat"));
		joinReq.setStartDate(req.getParameter("startdate"));
		joinReq.setEndDate(req.getParameter("enddate"));

		try {
			req.setAttribute("insertSuccess", joinService.join(joinReq));
			return "ticketInfo.do";
		} catch (Exception e) {
			// TODO: handle exception
			return FORM_VIEW;
		}
	}

}
