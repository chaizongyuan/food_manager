package com.res.controller.foodtype;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.res.model.FoodTypeUtils;
import com.res.utils.Pager;

/**
 * Servlet implementation class FoodTypeListServlet
 */
public class FoodTypeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodTypeListServlet() {
        super();
    }
    
    FoodTypeUtils tUtils = new FoodTypeUtils();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
        try {
            String typeName = request.getParameter("typeName");
            
            typeName = typeName == null ? "" : typeName;
            
            String curPage = request.getParameter("curPage");
            Integer curPageInt = 1;
            if (curPage != null && !"".equals(curPage)) {
                curPageInt = Integer.parseInt(curPage);
            }

            // 初始化pager
            Pager pager = tUtils.getPager(typeName, curPageInt);

            // 将pager放到request作用域对象中
            request.setAttribute("pager", pager);

            // 请求转发到JSP
            request.getRequestDispatcher("/detail/cuisineList.jsp?search=" + typeName).forward(request, response);
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
