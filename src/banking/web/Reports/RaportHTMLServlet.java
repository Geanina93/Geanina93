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
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import banking.db.Database;
 
import com.mysql.jdbc.Connection;
 
public class RaportHTMLServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
    public RaportHTMLServlet() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
                Map<String, Object> parameters = new HashMap<String, Object>();
               
                               
                parameters.put("idCont", request.getSession().getAttribute("idCont"));
                parameters.put("startDate", request.getSession().getAttribute("startDate"));
                parameters.put("endDate", request.getSession().getAttribute("endDate"));
               
 System.out.println("ceas: " +request.getSession().getAttribute("idCont") + request.getSession().getAttribute("startDate") + request.getSession().getAttribute("endDate"));
        response.setHeader("Content-Disposition","attachment; filename=raportHTML.html");
        response.setContentType("application/text; charset=UTF-8");
       
        ServletOutputStream out = response.getOutputStream();
       
        Connection connection=null;
        try{
                connection=(Connection) Database.getConnection();
                JasperReport jasperReport = JasperCompileManager.compileReport("D:///BankingDemo//WebContent//META-INF//RaportPdf.jrxml");
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
               
             
                HtmlExporter exporter = new HtmlExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();
           
         
               
        } catch (SQLException sqle) {
                throw new RuntimeException(sqle);
        } catch (JRException jre) {
                throw new RuntimeException(jre);
        } finally {
                Database.closeConnection(connection);
               
        }
 
        }
}