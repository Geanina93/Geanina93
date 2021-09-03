package banking.web.Reports;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import banking.db.Database;

import com.mysql.jdbc.Connection;

public class RaportPDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RaportPDFServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Map<String, Object> parameters = new HashMap<String, Object>();

		// ia de pe reqest sau vezi une trebuie pus pe sesiune detaliile despre

		parameters.put("idCont", request.getSession().getAttribute("idCont"));
		parameters.put("startDate",
				request.getSession().getAttribute("startDate"));
		parameters.put("endDate", request.getSession().getAttribute("endDate"));

		System.out.println("ceas: "
				+ request.getSession().getAttribute("idCont")
				+ request.getSession().getAttribute("startDate")
				+ request.getSession().getAttribute("endDate"));
		response.setHeader("Content-Disposition",
				"attachment; filename=raportPDF.pdf");
		response.setContentType("application/pdf");

		ServletOutputStream out = response.getOutputStream();

		Connection connection = null;
		try {
			connection = (Connection) Database.getConnection();
			JasperReport jasperReport = JasperCompileManager
					.compileReport("D:///BankingDemo//WebContent//META-INF//RaportPdf.jrxml");
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, connection);

			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.exportReport();

			// request.getRequestDispatcher("/listaTranzactiiCont.do").forward(request,
			// response);

		} catch (SQLException sqle) {
			throw new RuntimeException(sqle);
		} catch (JRException jre) {
			throw new RuntimeException(jre);
		} finally {
			Database.closeConnection(connection);

		}

	}
}