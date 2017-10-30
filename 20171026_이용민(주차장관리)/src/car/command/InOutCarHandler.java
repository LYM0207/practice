package car.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import car.service.CarInfo;
import car.service.CarRequest;
import car.service.InCarService;
import car.service.OutCarService;

public class InOutCarHandler implements CommandHandler {

	private InCarService inService = new InCarService();
	private OutCarService outService = new OutCarService();
	private CarInfo carInfo = new CarInfo();
	private static final String VIEW_FORM = "/WEB-INF/view/inOutCarForm.jsp";
	private static final String NEXT_FORM = "carInOutInfo.do";

	private InCarService inCarService = new InCarService();

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

		// 차량번호 전달 받음
		String carNo = req.getParameter("carNo");
		// 입고,출고 버튼 전달 받음
		String table = req.getParameter("table");
		CarRequest carReq = new CarRequest();
		carReq.setCarNo(carNo);
		carInfo = inCarService.searchCarInfo(carReq);

		// 주차된 차량정보가 없을때
		req.setAttribute("carInfo", carInfo);

		return VIEW_FORM;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		String table = req.getParameter("table");
		
		System.out.println("table     :  "  +table);

		if (table.equals("in")) {
			CarRequest carReq = new CarRequest();
			carReq.setCarNo(req.getParameter("carno"));
			carReq.setGrade(req.getParameter("grade"));
			carReq.setTstat("I");
			System.out.println("들어옴");

			try {
				req.setAttribute("success", inService.inputCar(carReq));

			} catch (Exception e) {
				// TODO: handle exception
				return VIEW_FORM;
			}
		}

		if (table.equals("out")) {
			CarRequest carReq = new CarRequest();
			carReq.setParkno(Integer.parseInt(req.getParameter("parkno")));
			carReq.setTstat("O");

			try {
				req.setAttribute("success", outService.outputCar(carReq));

			} catch (Exception e) {
				// TODO: handle exception
				return VIEW_FORM;
			}
		}
		return NEXT_FORM;

	}

}
