<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="javascript/lib/jquery-1.11.0.js"></script>
<script type="text/javascript" src="javascript/dateAndTime.js"></script>

<%@include file="home.jsp"%>


</head>

<body>
	<br>
	<br>

	<div class="submeniu">
		<div class="content">
			<br> <br> Administrare Cont
			<div id="renuntaButton" align=center>
				<a href="home.do">RENUNTA</a>
			</div>
			<div id="solicitareCreare">Solicitare creare cont nou!</div>
			<br>
			<h3>LISTA CONTURI:</h3>
			<br>
			<table id="table">

				<tr>
					<th>Numar Cont:</th>
				</tr>

				<c:forEach var="account" items="${accountsList}">
					<tr>
						<td><c:out value="${account}" /></td>
					</tr>
				</c:forEach>
			</table>


			<div id="adaugaCont">
				<form action="administreazaCont.do" method="post">
					Cont nou: <input type="text" name="newAccountNr" id="newAccountNr" />
					<br> <br>
					<div align=center>
						<input type="submit" id="submitButton" value="Trimite solicitare" />
					</div>
					<c:out value="${message}" />
				</form>
				<br>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/JSP/include/footer.jspf"%>
</body>
</html>