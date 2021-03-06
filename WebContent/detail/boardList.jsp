﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<!-- 包含公共的JSP代码片段 -->

<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/detail/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/detail/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath}/detail/style/css/common_style_blue.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/detail/style/css/index_1.css" />
<script type="text/javascript">
	function confirmDelete(deskID){
		if (confirm("是否删除当前餐桌？")) {
			window.location="delDesk?deskID=" + deskID;
		}
	}
	
	function confirmSubDesk(deskID){
		if (confirm("是否退订当前餐桌？")) {
			window.location="UnSubDesk?deskID=" + deskID;
		}
	}
</script>
</head>
<body>
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13"
					src="style/images/title_arrow.gif" /> 餐桌列表
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>


	<!-- 过滤条件 -->
	<div id="QueryArea">
		<form action="${pageContext.request.contextPath}/DeskServlet" method="post">
			<input type="hidden" name="method" value="search"> 
			<input type="text" name="deskName" title="请输入餐桌名称" value="${param.search}"> 
			<input type="submit" value="搜索">
		</form>
	</div>


	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<table class="MainArea_Content" cellspacing="0" cellpadding="0">
			<!-- 表头-->
			<thead>
				<tr align="center" valign="middle" id="TableTitle">
					<td>编号</td>
					<td>桌名</td>
					<td>状态</td>
					<td>预定时间</td>
					<td>操作</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData">
				<c:forEach var="list" items="${requestScope.pager.data}">
					<tr class="TableDetail1">
						<td align="center">${pageScope.list.DESKID}</td>
						<td align="center">${pageScope.list.DNAME}</td>
						<td align="center">${pageScope.list.DSTATE == 0 ? '空闲' : '预定'}</td>
						<td align="center">${pageScope.list.DTIME}</td>
						<td>
							<a href="javascript:confirmSubDesk(${pageScope.list.DESKID})" class="FunctionButton">
								退桌
							</a> 
							<a href="javascript:confirmDelete(${pageScope.list.DESKID})" class="FunctionButton">
								删除
							</a>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="8" style="text-align: center;">
						<a href="${pageContext.request.contextPath}/DeskServlet?curPage=1&search=">首页</a> 
						<a href="${pageContext.request.contextPath}/DeskServlet?curPage=${requestScope.pager.prePage}&search=">上一页</a>
							<c:forEach var="i" begin="1" end="${requestScope.pager.totalPage}" step="1">
								<a href="${pageContext.request.contextPath}/DeskServlet?curPage=${pageScope.i}&search=">${pageScope.i}</a>
							</c:forEach> 
						<a href="${pageContext.request.contextPath}/DeskServlet?curPage=${requestScope.pager.nextPage}&search=">下一页</a>
						<a href="${pageContext.request.contextPath}/DeskServlet?curPage=${requestScope.pager.totalPage}&search=">尾页</a>
					</td>
				</tr>
			</tbody>
		</table>

		<!-- 其他功能超链接 -->
		<div id="TableTail" align="center">
			<div class="FunctionButton">
				<a href="${pageContext.request.contextPath}/detail/saveBoard.html">添加</a>
			</div>
		</div>
	</div>
</body>
</html>
