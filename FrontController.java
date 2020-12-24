package kr.co.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.command.Command;

import kr.co.dao.MemberDAO;
import kr.co.domain.CommandAction;

@WebServlet("*.do")
public class FrontController extends HttpServlet {

	

	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		// 1.
		String uri = req.getRequestURI();
		String cPath = req.getContextPath();
		String sp = uri.substring(cPath.length());

		Command com = null;
// 다중if문 대신 써주는 리플렉션 = 클래스와 패키지를 같이 사용하는 것.
		MemberDAO dao = new MemberDAO();
		Map<String, String> menus = dao.menuMap();

		String fullname = menus.get(sp.toLowerCase());

		if (fullname != null) {

			try {
				Class<?> testClass = Class.forName(fullname);
				Constructor<?> cons = testClass.getConstructor();
				com = (Command) cons.newInstance();

				CommandAction action = com.execute(req, resp);

				if (action.isRedirect()) {
					resp.sendRedirect(action.getWhere());
				} else {
					RequestDispatcher dis = req.getRequestDispatcher(action.getWhere());
					dis.forward(req, resp);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			PrintWriter out = resp.getWriter();
			out.print("<html>");
			out.print("<body>");
			out.print("해당 서비스는 제공하지 않습니다.");
			out.print("</body>");
			out.print("</html>");
		}

		// 2

		// 3

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
