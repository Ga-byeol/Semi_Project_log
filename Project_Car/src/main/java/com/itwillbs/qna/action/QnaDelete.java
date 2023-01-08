package com.itwillbs.qna.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.qna.db.QnaDAO;

public class QnaDelete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int qna_num=Integer.parseInt(request.getParameter("qna_num"));
		QnaDAO dao = new QnaDAO();
		
		String filepath = request.getSession().getServletContext().getRealPath("qna_images");
		// 차량정보 삭제 시 이미지 파일도 같이 삭제
		dao.deleteQna(qna_num, filepath);
		
		ActionForward forward=new ActionForward();
		forward.setPath("QnaList.qn");
		forward.setRedirect(false);
		return forward;
	}
	

}
