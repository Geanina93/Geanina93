package banking.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.model.AccountInfo;
import banking.model.InfoUser;
import banking.services.AccountService;

/**
 * Servlet implementation class VizualizareConturi
 */
public class VizualizareConturi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AccountService accountService; 
	
	public VizualizareConturi() {
	    	accountService = new AccountService();
	    }
	    
	    @Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	viewAccountDetails(request,response); 	
		}

	   
	    	public void viewAccountDetails (HttpServletRequest request,
	    			HttpServletResponse response) throws ServletException, IOException {
	    		InfoUser getInfoUser = (InfoUser) request.getSession().getAttribute("user");
	    		//System.out.println("InfoUser" + getInfoUser);
	    		Integer userId = getInfoUser.getId();
	    		
	    		List<AccountInfo> accountsInfo = accountService.viewAccountInfo(userId);
	    		request.setAttribute("accountsDetails", accountsInfo);

				request.getRequestDispatcher("/WEB-INF/JSP/vizualizareConturi.jsp").forward(request, response);
	    	}
			
	

}
