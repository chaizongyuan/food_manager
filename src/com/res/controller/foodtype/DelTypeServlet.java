package com.res.controller.foodtype;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.res.model.FoodTypeUtils;

/**
 * Servlet implementation class DelTypeServlet
 */
public class DelTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DelTypeServlet() {
        super();
    }

    FoodTypeUtils tUtils = new FoodTypeUtils();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String typeId = request.getParameter("typeId");
        try {
            tUtils.delFoodType(typeId);
            //删除完之后跳转到列表页，更新数据
            request.getRequestDispatcher("/ftServlet?typeName=").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
