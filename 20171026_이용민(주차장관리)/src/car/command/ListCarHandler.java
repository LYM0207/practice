package car.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import car.model.Car;
import car.service.ListCarService;

public class ListCarHandler implements CommandHandler {
	private ListCarService listCarService = new ListCarService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		// TODO Auto-generated method stub
		List<Car> list = new ArrayList<>();
		list = listCarService.getCarList();

		req.setAttribute("listCar", list);
		return "/WEB-INF/view/listCarForm.jsp";
	}

}
