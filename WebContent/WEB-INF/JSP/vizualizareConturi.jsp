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

<%@include file="home.jsp"%>

</head>
<body>

	<br>
	<div class="submeniu">
		<div class="content">
			<h2>Vizualizare Conturi</h2>

			<div id="vizualizareConturi">
				<h3>Stare Conturi</h3>

				<c:choose>
					<c:when test="${!empty accountsDetails}">
						<table id="accountTable">
							<thead>
								<tr>
									<th>Numar Cont</th>
									<th>Suma</th>
									<th>Stare</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="account" items="${accountsDetails}">
									<tr>
										<td><c:out value="${account.nrCont}" /></td>
										<td><c:out value="${account.suma}" /></td>
										<td><c:out value="${account.stare}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
					Nu sunt conturi.
				</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>

	<c:out value="${message}" />
	<%@include file="/WEB-INF/JSP/include/footer.jspf"%>
</body>
</html>