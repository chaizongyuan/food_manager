package com.res.controller.desk;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.res.model.DeskUtils;
import com.res.utils.Pager;

/**
 * Servlet implementation class UserServlet
 */
public class DeskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeskServlet() {
        super();
    }

    DeskUtils dUtils = new DeskUtils();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {

            String dname = request.getParameter("deskName");
            dname = dname == null ? "" : dname;
            String curPage = request.getParameter("curPage");
            Integer curPageInt = 1;
            if (curPage != null && !"".equals(curPage)) {
                curPageInt = Integer.parseInt(curPage);
            }

            // 初始化pager
            Pager pager = dUtils.getPager(dname, curPageInt);

            // 将pager放到request作用域对象中
            request.setAttribute("pager", pager);

            // 请求转发到JSP
            request.getRequestDispatcher("/detail/boardList.jsp?search=" + dname).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
