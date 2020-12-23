package kr.co.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.MemberDAO;
import kr.co.domain.CommandAction;
import kr.co.domain.MemberDTO;

public class ListCommand implements Command{

	public CommandAction execute(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException,ServletException{
		//1..
		String id = request.getParameter("id");
		//2.DAO
	MemberDAO dao = new MemberDAO();
	List<MemberDTO> list = dao.list();
	
	//3.
	request.setAttribute("list", list);
	
	//4.forwardind .. new CommandA..
	
	return new CommandAction(false,"list.jsp");
	}
	
}
