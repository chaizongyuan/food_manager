package com.res.controller.food;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.res.model.FoodUtils;

/**
 * 实现菜品信息更新
 * @author Administrator
 *
 */
public class UpdateFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateFoodServlet() {
        super();
    }

    private final static String IMG_PATH = "E:/内网通文件接收/MR廖/171012点餐系统/images/";
    
    FoodUtils fUtils = new FoodUtils();
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    
	    String foodID = request.getParameter("foodID");
	    String typeID = null;
        String foodName = null;
        String price = null;
        String desc = null;
        String imgPath = null;
        
        // 用于创建解析文件上传的工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 用于从请求中解析出文件
        ServletFileUpload upload = new ServletFileUpload(factory);
        
        try {

            /*
             *  获取所有提交过来的文件(包含表单内容) 这个FileItem叫做一个项，
             *  这个项有可能是文件也有可能是表单参数，所以需要在下方作判断。
             */
            List<FileItem> items = upload.parseRequest(request);

            // 定义一个输出流
            FileOutputStream fos = null;

            // 缓冲区
            byte[] buf = new byte[8192];

            if (items != null && items.size() > 0) { // 不为空且长度大于0则遍历之
                for (FileItem item : items) {
                    if (item.isFormField()) { // isFormField = true 文本框
                        if ("typeID".equals(item.getFieldName())) {
                            typeID = item.getString();
                        } else if ("foodName".equals(item.getFieldName())) {
                            foodName = item.getString("UTF-8");
                        } else if ("price".equals(item.getFieldName())) {
                            price = item.getString();
                        } else if ("desc".equals(item.getFieldName())) {
                            desc = item.getString("UTF-8");
                        } else {
                            imgPath = item.getString("UTF-8");
                        }   
                    } else {
                        
                        if (!"application/octet-stream".equals(item.getContentType())) {
                            
                            String imgName = item.getName();    // 获取新的图片文件名
                            
                            BufferedInputStream bis = new BufferedInputStream(item.getInputStream());
                            
                            fos = new FileOutputStream(new File(IMG_PATH + imgName));
                            int len = -1;
                            while ((len = bis.read(buf)) != -1) {
                                fos.write(buf, 0, len);
                                fos.flush();
                            }
                            
                            // 更新图片路径
                            imgPath = item.getName();
                            bis.close();
                            fos.close();
                        }
                    }
                }
            }
            fUtils.updateFood(typeID, foodName, price, desc, imgPath, foodID);
            request.getRequestDispatcher("/fdServlet?foodName=").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
