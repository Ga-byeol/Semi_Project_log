package com.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.qna.db.QnaDAO;
import com.qna.db.QnaDTO;

public class QnaUpdatePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
<<<<<<< HEAD
		String filepath = request.getSession().getServletContext().getRealPath("qna_images");
		
=======
		
		String filepath = request.getSession().getServletContext().getRealPath("qna_images");
		// 차 정보 수정 시 고정값이 필요해서 차량번호는 수정 못함 
>>>>>>> 12ac4a8fdb1c6fe1fd13370bedf3989c0f50b81f
		MultipartRequest multi = new MultipartRequest
				(request, filepath, 
						1024*1024*1024, "utf-8", new DefaultFileRenamePolicy());
		
		int qna_num=Integer.parseInt(multi.getParameter("qna_num"));
		String qna_sub = multi.getParameter("qna_sub");
		String qna_content = multi.getParameter("qna_content");
		String qna_image = multi.getFilesystemName("qna_image");
		String qna_secret = multi.getParameter("qna_secret");
		int qna_index=Integer.parseInt(multi.getParameter("qna_index"));
		boolean imageChange = false;
		
		if(qna_image != null) {
			imageChange = true;
		}
		if(qna_secret == null) {
			qna_secret="N";
		} 
		
		QnaDAO dao = new QnaDAO();
		QnaDTO dto=new QnaDTO();
		
<<<<<<< HEAD
=======
		
>>>>>>> 12ac4a8fdb1c6fe1fd13370bedf3989c0f50b81f
		dto.setQna_num(qna_num);
		dto.setQna_sub(qna_sub);
		dto.setQna_content(qna_content);
		dto.setQna_image(qna_image);
		dto.setQna_secret(qna_secret);
		dao.updateQna(dto, imageChange, filepath);

		ActionForward forward=new ActionForward();
		forward.setPath("/QnaContent.qn?qna_num="+String.valueOf(qna_num)+"&qna_index="+qna_index);
		forward.setRedirect(false);
		return forward;
	
	}

}
