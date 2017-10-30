package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

public class ControllerUsingURI extends HttpServlet {

	// 명령어와 명령어 처리 클래스를 쌍으로 저장
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();

	/*
	 * 명령어와 처리클래스가 매핑되어 있는 properties 파일을 읽어서 Map객체인 commandHandlerMap에 저장 명령어와
	 * 처리클래스가 매핑되어 있는 properties파일이 있어야함
	 */

	public void init() throws ServletException {
		String configFile = getInitParameter("configFile");
		System.out.println("configFile : " + configFile);

		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath(configFile);
		System.out.println("configFilePath : " + configFilePath);

		try (FileReader fis = new FileReader(configFilePath)) {
			prop.load(fis);
		} catch (IOException e) {
			throw new ServletException(e);
		}

		// Iterator객체는 Enumeration객체를 확장시킨 개념의 객체
		Iterator keyIter = prop.keySet().iterator();

		// 객체를 하나씩 꺼내서 그 객체명으로 Properties객체에 저장된 객체에 접근
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String handlerClassName = prop.getProperty(command);
			try {
				// 해당 문자열을 클래스로 만든다.
				Class<?> handlerClass = Class.forName(handlerClassName);
				// 해당 클래스의 객체를 생성
				CommandHandler handlerInstance = (CommandHandler) handlerClass
						.newInstance();
				// Map객체인 commandMap에 객체 저장
				commandHandlerMap.put(command, handlerInstance);
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String tapName =null;
		String command = req.getRequestURI();

		System.out.println("처음 command : " + command);

		if (command.indexOf(req.getContextPath()) == 0) {
			command = command.substring(req.getContextPath().length());
		}

		System.out.println("변형 command : " + command);
		CommandHandler handler = commandHandlerMap.get(command);
		if (handler == null) {
			handler = new NullHandler();
		}
		String viewPage = null;
		try {
			viewPage = handler.process(req, resp);
		} catch (Throwable e) {
			throw new ServletException(e);
		}
		if (viewPage != null) {
			System.out.println("viewPage : " + viewPage);
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, resp);
		}
	}
}
