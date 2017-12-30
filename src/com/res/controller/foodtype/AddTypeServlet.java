package com.res.controller.foodtype;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.res.model.FoodTypeUtils;

/**
 * Servlet implementation class AddTypeServlet
 * 实现添加菜系的Servlet
 */
public class AddTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTypeServlet() {
        super();
    }
    
    FoodTypeUtils tUtils = new FoodTypeUtils();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    String typeName = request.getParameter("typeName");
	    try {
            tUtils.addFoodType(typeName);
            //添加完成后跳转到菜系列表
            request.getRequestDispatcher("/ftServlet?typeName=").forward(request, response);
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
