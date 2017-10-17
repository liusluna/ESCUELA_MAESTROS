package servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class HorariosServlet
 */
@WebServlet("/Horarios")
public class HorariosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HorariosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession misesion = request.getSession(false);

		if (misesion.getAttribute("usuario") == null) {
			request.getRequestDispatcher("timeout.html").forward(request, response);
		}

		// realiza la operacion deseada
		if (request.getParameter("operacion").equals("muestra")) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ESCUELA_MAESTROS"); 
			EntityManager em = emf.createEntityManager();
			// Abrir su try /cash / finally
			List<Horariosmateria> hm = null;
			try {
				hm = (List<Horariosmateria>) em.createNamedQuery("Horariosmateria.findAll").getResultList();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error al intentar listar los horarios materias: " + e.getMessage());

			} finally {
				// System.out.println("JPA CLOSING CONNEXIONS!");
				em.close();
				emf.close();
			}
			

				misesion.setAttribute("horarios", hm);
				request.setAttribute("tipo","horarios");
				request.setAttribute("pagina","1");
				request.getRequestDispatcher("Muestra.jsp").forward(request, response);

		} else if (request.getParameter("operacion").equals("eliminar")) {

		} else if (request.getParameter("operacion").equals("agregar")) {
			
		}else {
			// si no hay una operacion definida, imprime en el log los
			// parametros que trae
			Enumeration<?> params = request.getParameterNames();
			while (params.hasMoreElements()) {
				String paramName = (String) params.nextElement();
				System.out
						.println("Parameter Name:[" + paramName + "], Value:[" + request.getParameter(paramName) + "]");
			}

			request.getRequestDispatcher("app.jsp").forward(request, response);
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