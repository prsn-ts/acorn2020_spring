<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String msg = request.getParameter("msg");
	System.out.println("ajax 전송된 msg:"+msg);
%>
{"toClient":"hello"}