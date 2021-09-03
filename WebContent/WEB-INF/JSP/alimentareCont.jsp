<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/home.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="javascript/lib/jquery-1.11.0.js"></script>
<script type="text/javascript" src="javascript/dateAndTime.js"></script>
<script type="text/javascript" src="javascript/alimentareCont.js"></script>

<%@include file="home.jsp"%>


</head>
<body>

	<br>
	<div class="submeniu">
		<div class="content">
			<div id="form">
				<h2>Alimentare Cont</h2>
				<br>
				<form action="alimentareCont.do" method="post">

					Sursa: <select name="idCont" id="selectConturi">
						<option value="" selected disabled>-Selectati-</option>
					</select> &nbsp;&nbsp;&nbsp;&nbsp; <input type="text" id="sumaCont" readonly />
					<br> <br> <br> <br> Suma alimentare: <input
						type="text" name="sumaAdaugata" /> <br> <br> <br>
					<br>
					<div align=center>
						<input type="submit" value="Transfera" id="transferaButton" />
					</div>

				</form>
			</div>
		</div>
	</div>
	<br>
	<br>

	<c:out value="${message}" />
	<%@include file="/WEB-INF/JSP/include/footer.jspf"%>
</body>
</html>