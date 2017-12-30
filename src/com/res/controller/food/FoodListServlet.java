package com.res.controller.food;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.res.model.FoodUtils;
import com.res.utils.Pager;

/**
 * 菜品列表controller
 * @author Administrator
 *
 */
public class FoodListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	FoodUtils fUtils = new FoodUtils();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
        try {
            String foodName = request.getParameter("foodName");
            
            foodName = foodName == null ? "" : foodName;
            String curPage = request.getParameter("curPage");
            Integer curPageInt = 1;
            if (curPage != null && !"".equals(curPage)) {
                curPageInt = Integer.parseInt(curPage);
            }

            // 初始化pager
            Pager pager = fUtils.getPager(foodName, curPageInt);

            // 将pager放到request作用域对象中
            request.setAttribute("pager", pager);

            // 请求转发到JSP
            request.getRequestDispatcher("/detail/foodList.jsp?search=" + foodName).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	    
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
