<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/home.css">
<link rel="stylesheet" type="text/css" href="css/meniu.css">

<script type="text/javascript" src="javascript/lib/jquery-1.11.0.js"></script>
<script type="text/javascript" src="javascript/dateAndTime.js"></script>
<script type="text/javascript" src="javascript/tranasferuri.js"></script>

<title>Transfer</title>
</head>
<body>

	<%@ include file="home.jsp"%>
	<br>

	<div class="submeniu">
		<div class="content">
			<div id="form">
				<h2>Transfer</h2>
				<br>
				<form action="transfer.do" method="post">

					Sursa: <select name="idContSursa" id="selectConturi">
						<option value="" selected disabled>-Selectati-</option>
					</select> &nbsp;&nbsp;&nbsp; <input type="text" id="sumaCont"
						name="sumaCont" readonly /> <br> <br> Destinatie: <select
						name="idDestinatieClient" id="selectClientDestinatie">
						<option value="" selected disabled>-Selectati-</option>
					</select> <br> <br> <select name="nrContDestinatie"
						id="selectConturiDestinatie">
					</select> <br> <br> Suma transferata: <input type="text"
						name="sumaTransfer" /> <br> <br>
					<div align=center>
						<input type="submit" value="Transfera" id="transferaButton" />
					</div>
					<br> <br>

				</form>
			</div>
		</div>
	</div>

	<c:out value="${message}" />
	<%@include file="/WEB-INF/JSP/include/footer.jspf"%>

</body>
</html>