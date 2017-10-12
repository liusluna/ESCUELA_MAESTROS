package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;

import security.SHAHashing;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String usuario = request.getParameter("usuario");
		String contra = request.getParameter("contra");
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("ESCUELA_MAESTROS"); //		-> NOMBRE DEL PROYECTO
		EntityManager em=emf.createEntityManager();
		boolean operacion = true;
		Usuario usu =null;
		try {
			
			
			try {
				System.out.println("Intento de acceso: "+usuario +" "+SHAHashing.sha256(usuario)+" pass:"+contra+" "+SHAHashing.sha256(contra));
				usu = (Usuario)em.createNamedQuery("Usuario.findValid").setParameter("usuario", SHAHashing.sha256(usuario)).setParameter("password", SHAHashing.sha256(contra)).getSingleResult();
			}catch(Exception e){
				System.out.println("Error al intentar hacer login con el usuario: "+usuario+e.getMessage());

			}
			
			if(usu == null) {
				System.out.println("Intento de acceso: "+usuario);
				operacion=false;	

			}
			
			System.out.println("JPA OK!");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("JAP Error: "+e.getMessage());

	
		}finally{
			System.out.println("JPA CLOSING CONNEXIONS!");
			em.close();
			emf.close();
		}
		
		if (operacion==false){
			ArrayList<String> salida = new ArrayList<String>();
			salida.add("Usuario o contraseña no validos");
			request.setAttribute("error", salida);
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		else{
		//System.out.println(request.getParameterNames());
		HttpSession misesion = request.getSession();
		misesion.setAttribute("usuario", usu.getUsuariosId());
		misesion.setAttribute("maestro", usu.getEsDocente() );
		misesion.setAttribute("admin", usu.getEsAdmin() );
		response.sendRedirect("app.jsp");
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
