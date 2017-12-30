package com.res.controller.food;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.res.model.FoodUtils;
/**
 * 实现菜品删除 
 * @author Administrator
 */
public class DelFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DelFoodServlet() {
        super();
    }
    
    FoodUtils fUtils = new FoodUtils();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String foodID = request.getParameter("foodID");
		try {
            fUtils.delFood(foodID);
            request.getRequestDispatcher("/fdServlet?foodName=").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
