package com.res.controller.foodtype;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.res.model.FoodTypeUtils;
/**
 * 菜系更新
 * @author Administrator
 *
 */
public class UpdateTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public UpdateTypeServlet() {
        super();
    }

    FoodTypeUtils tUtils = new FoodTypeUtils();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    String typeId = request.getParameter("typeId");
	    String typeName = request.getParameter("typeName");
        try {
            tUtils.updateFoodType(typeId, typeName);
            request.getRequestDispatcher("/ftServlet?typeName=").forward(request, response);
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
