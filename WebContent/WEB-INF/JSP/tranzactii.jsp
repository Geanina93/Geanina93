<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/home.css">
<link rel="stylesheet" type="text/css" href="css/meniu.css">
<link rel="stylesheet" type="text/css" href="css/tranzactii.css">

<script type="text/javascript" src="javascript/lib/jquery-1.11.0.js"></script>
<script type="text/javascript" src="javascript/dateAndTime.js"></script>
<script type="text/javascript" src="javascript/tranzactii.js"></script>

<title>Lista Tranzactii</title>
</head>
<body>

	<%@ include file="home.jsp"%>

	<br>
	<div class="submeniu">
		<div class="content">
			<h2>Lista tranzactii conturi</h2>
			<br>
			<form action="tranzactii.do" method="post">

				<div class="boxForm">
					Sursa<br> <select name="idCont" id="selectConturi">
						<option value="" selected disabled>-Selectati-</option>
					</select>
				</div>

				<div class="boxForm">
					Start Date<br> <input type="date" value="2015-01-01"
						name="startDate" />
				</div>

				<div class="boxForm">
					End Date<br /> <input type="date" value="2015-01-01"
						name="endDate" />
				</div>

				<br> <br> <br> <input type="submit" value="Afisare"
					id="afisareButton" />
			</form>
			<br> <br>

			<c:choose>
				<c:when test="${!empty listaTranzactii}">
					<table id="tabelConturiClient">
						<thead>
							<tr>
								<th>Data</th>
								<th>Cont Debitor/Creditor</th>
								<th>Suma debit</th>
								<th>Suma credit</th>
								<th>Sold curent</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="tranzactie" items="${listaTranzactii}">
								<tr>
									<td><c:out value="${tranzactie.date}" /></td>
									<td><c:out value="${tranzactie.getContSursa()}" /></td>

									<td><c:out value="${tranzactie.getSumaTransfer()}" /></td>
									<td></td>
									<td><c:out value="${tranzactie.getSoldFinalContSursa()}" />
									</td>
								</tr>

								<tr>
									<td><c:out value="${tranzactie.getDate()}" /></td>
									<td><c:out
											value="${tranzactie.getSoldFinalContDestinatie()}" /></td>
									<td></td>
									<td><c:out value="${tranzactie.getSumaTransfer()}" /></td>
									<td><c:out
											value="${tranzactie.getSoldFinalContDestinatie()}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<br>
					<br>

					<br>
					<br>
					<form action="raportCSVServlet" method="post">
						<input type="submit" value="Raport CSV" class="buttonRapoarte" />
					</form>
					<form action="raportHTMLServlet" method="get">
						<input type="submit" value="Raport HTML" class="buttonRapoarte" />
					</form>
					
					<form action="raportPDFServlet" method="get">
						<input type="submit" value="Raport PDF" class="buttonRapoarte" />
					</form>
					
				</c:when>
				<c:otherwise>
					Nu sunt tranzactii pentru acest cont.
				</c:otherwise>
			</c:choose>

			<br> <br>
		</div>
	</div>

	<%@include file="/WEB-INF/JSP/include/footer.jspf"%>

</body>
</html>