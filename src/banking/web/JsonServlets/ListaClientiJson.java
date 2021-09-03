package banking.web.JsonServlets;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.model.InfoUser;
import banking.services.UserInfoService;

public class ListaClientiJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
private UserInfoService usersService;
	
    public ListaClientiJson() {
    	usersService = new UserInfoService();
    }

    @Override
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     	clientiToJson(request,response);    	
 	}
 	
 	private void clientiToJson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		
 		List<InfoUser> listaClienti = usersService.getInfoUser();
 		
 		String clientiDest = "[";
 		for(int i = 0; i < listaClienti.size(); i++){
			clientiDest += "{\"Id\": " + listaClienti.get(i).getId() + " , ";
 			clientiDest += "\"Nume\": " + "\"" + listaClienti.get(i).getNume() + "\"" + " , ";
 			clientiDest += "\"Prenume\": " + "\"" + listaClienti.get(i).getPrenume() + "\"" + " }";
 			if(i < listaClienti.size() - 1){
 				clientiDest += ", ";
 			} else {
 				clientiDest += "]";
 			}
 		}

 		response.setContentType("application/json");      
 		PrintWriter out = response.getWriter(); 
 		out.print(clientiDest);
 		out.flush();
 	}
}
