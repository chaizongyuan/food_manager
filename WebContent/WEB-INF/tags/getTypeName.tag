<%@tag import="com.res.model.FoodTypeUtils"%>
<%@ tag language="java" pageEncoding="UTF-8"%>

<%@attribute name="typeID" required="true" type="java.lang.String" %>
<%
	String typeName = FoodTypeUtils.getTypeNameById(typeID);
%>
<%=typeName%>