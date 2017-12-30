package com.res.controller.food;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
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
 * 添加菜品实现类
 * 
 * @author Administrator
 *
 */
public class AddFoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddFoodServlet() {
        super();
    }

    private final static String IMG_PATH = "E:/内网通文件接收/MR廖/171012点餐系统/images/";

    FoodUtils fUtils = new FoodUtils();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * 1.获取字符串参数 2.最后再判断是否有文件上传请求
         */
        request.setCharacterEncoding("UTF-8");

        String typeID = null;
        String foodName = null;
        String price = null;
        String desc = null;
        String imgName = null;

        // 用于创建解析文件上传的工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 用于从请求中解析出文件
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {

            // 获取所有提交过来的文件(包含表单内容) 这个FileItem叫做一个项，这个项有可能是文件也有可能是表单参数
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
                        }
                    } else {
                        if (!"application/octet-stream".equals(item.getContentType())) {

                            // 菜品图片先以绝对路径的方法写入images目录，数据库中存储相对路径即可在JSP中显示。
                            BufferedInputStream bis = new BufferedInputStream(item.getInputStream());
                            String fileName = item.getName();

                            // 写入images目录
                            fos = new FileOutputStream(new File(IMG_PATH + fileName));
                            int len = -1;
                            while ((len = bis.read(buf)) != -1) {
                                fos.write(buf, 0, len);
                                fos.flush();
                            }

                            imgName = fileName;
                            bis.close();
                            fos.close();
                        }
                    }
                }
            }
            fUtils.addFood(typeID, foodName, price, desc, imgName);
            request.getRequestDispatcher("/fdServlet?foodName=").forward(request, response);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
