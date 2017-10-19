package servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DescargaServlet
 */
@WebServlet("/Descarga")
public class DescargaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DescargaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private static final int BYTES_DOWNLOAD = 1024;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if (misesion.getAttribute("usuario") == null) {
			request.getRequestDispatcher("timeout.html").forward(request, response);
		}
		
		try{
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition","attachment;filename=archivo.xml");
			ServletContext ctx = getServletContext();
			InputStream is = ctx.getClassLoader().getResourceAsStream("/prueba.xml");
				
			
			int read=0;
			byte[] bytes = new byte[BYTES_DOWNLOAD];
			OutputStream os = response.getOutputStream();

			while((read = is.read(bytes))!= -1){
				os.write(bytes, 0, read);
			}
			os.flush();
			os.close();
		}catch(	FileNotFoundException e){
			System.out.println("Error al intentar descargar el archivo FNF: " + e.getMessage());
		}catch(IOException e ){
			System.out.println("Error al intentar descargar el archivo IO: " + e.getMessage());
		}catch(	NullPointerException e){
			System.out.println("NUll poiner, archivo  en NULL: " + e.getMessage());
		}catch(Exception e){
			System.out.println("Error al intentar descargar el archivo G: " + e.getMessage());
			e.printStackTrace();
		}

	   
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
