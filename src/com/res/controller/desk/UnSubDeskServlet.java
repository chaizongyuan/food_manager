package com.res.controller.desk;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.res.model.DeskUtils;

/**
 * Servlet implementation class UnSubDeskServlet
 */
public class UnSubDeskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnSubDeskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    DeskUtils dUtils = new DeskUtils();
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String deskID = request.getParameter("deskID");
        try {
            dUtils.unSubDesk(deskID);
            request.getRequestDispatcher("/DeskServlet?deskName=").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
