package banking.web.Reports;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.model.TransactionDetails;

/**
 * Servlet implementation class RaportCSVServlet
 */
public class RaportCSVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RaportCSVServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		generateCsvFile(request, "D:/BankingDemo/RapoarteCsv/raportCSV.csv");

		request.getRequestDispatcher("/WEB-INF/JSP/tranzactii.jsp").forward(
				request, response);
	}

	public static void generateCsvFile(HttpServletRequest request,
			String fileName) {
		try {
			FileWriter writer = new FileWriter(fileName);

			writer.append("Data");
			writer.append(", ");
			writer.append("Cont susra");
			writer.append(", ");
			writer.append("Sold final cont susra");
			writer.append(", ");
			writer.append("Cont destinatie");
			writer.append(", ");
			writer.append("Sold final cont destinatie");
			writer.append(", ");
			writer.append("Suma Transfer");
			writer.append("\n ");

			@SuppressWarnings("unchecked")
			List<TransactionDetails> tranzactii = (List<TransactionDetails>) request.getSession().getAttribute("listaTranzactii");
			for (TransactionDetails transactionDetails : tranzactii) {

				String data = String.valueOf(transactionDetails.getDate());
				String contSusra = String.valueOf(transactionDetails
						.getContSursa());
				String soldFinalContSursa = String.valueOf(transactionDetails
						.getSoldFinalContSursa());
				String contDest = String.valueOf(transactionDetails
						.getContDestinatie());
				String soldFinalContDest = String.valueOf(transactionDetails
						.getSoldFinalContDestinatie());
				String sumatransfer = String.valueOf(transactionDetails
						.getSumaTransfer());

				writer.append(data);
				writer.append(", ");
				writer.append(contSusra);
				writer.append(", ");
				writer.append(soldFinalContSursa);
				writer.append(", ");
				writer.append(contDest);
				writer.append(", ");
				writer.append(soldFinalContDest);
				writer.append(", ");
				writer.append(sumatransfer);
				writer.append(",\n ");
			}

			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
