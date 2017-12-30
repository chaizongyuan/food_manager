package com.res.controller.desk;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.res.model.DeskUtils;

/**
 * Servlet implementation class DelDeskServlet
 */
public class DelDeskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelDeskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	DeskUtils deskUtils = new DeskUtils();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deskID = request.getParameter("deskID");
		try {
            deskUtils.delDesk(deskID);
            //删除完之后跳转到餐桌列表页，更新数据
            request.getRequestDispatcher("/DeskServlet?deskName=").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
