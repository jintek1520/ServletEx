package kr.co.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.MemberDAO;
import kr.co.domain.CommandAction;
import kr.co.domain.MemberDTO;

public class DeleteCommand implements Command{
	
	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response
			) throws IOException,ServletException{
		
		String id = request.getParameter("id");
		
		MemberDAO dao = new MemberDAO();
		
		dao.delete(id);
		
		return new CommandAction(true,"list.do");
		
		
		
	}

	
}
