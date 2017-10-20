package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
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

				if ((int) misesion.getAttribute("admin") == 1)
					hm = (List<Horariosmateria>) em.createNamedQuery("Horariosmateria.findAll").getResultList();
				else {
					Usuario user = (Usuario) em.createNamedQuery("Usuario.findbyid")
							.setParameter("id", misesion.getAttribute("usuario")).getSingleResult();
					hm = (List<Horariosmateria>) em.createNamedQuery("Horariosmateria.findAllusuario")
							.setParameter("usuario", user).getResultList();
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error al intentar listar los horarios materias: " + e.getMessage());

			} finally {
				// System.out.println("JPA CLOSING CONNEXIONS!");
				em.close();
				emf.close();
			}

			misesion.setAttribute("horarios", hm);
			request.setAttribute("tipo", "horarios");
			request.setAttribute("pagina", "1");
			request.getRequestDispatcher("Muestra.jsp").forward(request, response);

		} else if (request.getParameter("operacion").equals("eliminar")) {

		} else if (request.getParameter("operacion").equals("agregar")) {

			Integer horario = Integer.parseInt(request.getParameter("horario"));
			Integer profesor = Integer.parseInt(request.getParameter("profesor"));
			Integer materia = Integer.parseInt(request.getParameter("materia"));
			Integer grupo = Integer.parseInt(request.getParameter("grupo"));

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ESCUELA_MAESTROS");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tran = em.getTransaction();
			// Abrir su try /cash / finally

			try {
				tran.begin();
				Horariosmateria hm = new Horariosmateria();
				// Grupo gp=new Grupo();

				hm.setGrupo((Grupo) em.createNamedQuery("Grupo.findbyid").setParameter("id", grupo).getSingleResult());
				hm.setHorario((Horario) em.createNamedQuery("Horario.findbyid").setParameter("id", horario)
						.getSingleResult());
				hm.setMateria((Materia) em.createNamedQuery("Materia.findbyid").setParameter("id", materia)
						.getSingleResult());
				hm.setUsuario((Usuario) em.createNamedQuery("Usuario.findbyid").setParameter("id", profesor)
						.getSingleResult());
				em.persist(hm);
				tran.commit();

			} catch (Exception e) {
				// TODO: handle exception
				tran.rollback();
				System.out.println("Error al intentar insertar horarios materias: " + e.getMessage());

				ArrayList<String> salida = new ArrayList<String>();
				salida.add("Error al insertar el insertar en horarios materias: ");
				salida.add("en la base de datos");
				request.setAttribute("error", salida);
				request.getRequestDispatcher("error.jsp").forward(request, response);

			} finally {
				// System.out.println("JPA CLOSING CONNEXIONS!");
				em.close();
				emf.close();
			}

			ArrayList<String> salida = new ArrayList<String>();
			salida.add("Horario Insertado");
			salida.add("en la base de datos");
			request.setAttribute("info", salida);
			request.getRequestDispatcher("info.jsp").forward(request, response);

		} else if (request.getParameter("operacion").equals("agregarhorario")) {

			String horarios = request.getParameter("horarios").toUpperCase();
			String aula = request.getParameter("aula").toUpperCase();

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ESCUELA_MAESTROS");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tran = em.getTransaction();
			// Abrir su try /cash / finally

			try {
				tran.begin();

				Horario hora = new Horario();

				hora.setAula(aula);
				hora.setHorarios(horarios);

				em.persist(hora);
				tran.commit();

			} catch (Exception e) {
				// TODO: handle exception
				tran.rollback();
				System.out.println("Error al intentar insertar horarios materias: " + e.getMessage());

				ArrayList<String> salida = new ArrayList<String>();
				salida.add("Error al insertar el insertar en horarios: ");
				salida.add("en la base de datos");
				request.setAttribute("error", salida);
				request.getRequestDispatcher("error.jsp").forward(request, response);

			} finally {
				// System.out.println("JPA CLOSING CONNEXIONS!");
				em.close();
				emf.close();
			}

			ArrayList<String> salida = new ArrayList<String>();
			salida.add("Horario Insertado");
			salida.add("en la base de datos");
			request.setAttribute("info", salida);
			request.getRequestDispatcher("info.jsp").forward(request, response);
		} else if (request.getParameter("operacion").equals("exportar")) {
			// jala de la session los horarios que mostraba en la pantalla
			//
			List<Horariosmateria> lista = (List<Horariosmateria>) misesion.getAttribute("horarios");

			try {
				response.setContentType("text/xml");
				response.setHeader("Content-Disposition", "attachment;filename=archivo.xml");

				OutputStream outputStream = response.getOutputStream();

				EntityManagerFactory emf = Persistence.createEntityManagerFactory("ESCUELA_MAESTROS");
				EntityManager em = emf.createEntityManager();
				// Abrir su try /cash / finally

				String outputResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "";
				outputResult += "<Listado>\n";
				for (Horariosmateria hm : lista) {

					outputResult += "\t<ID id=\"" + hm.getHorariosmateriasId() + "\">\n";
					outputResult += "\t\t<Grupo>" + hm.getGrupo().get_descripcion() + "</Grupo>\n";
					outputResult += "\t\t<Descripcion>" + hm.getGrupo().get_descripcion() + "</Descripcion>\n";
					outputResult += "\t\t<Aula>" + hm.getHorario().getAula() + "</Aula>\n";
					outputResult += "\t\t<Horario>" + hm.getHorario().getHorarios() + "</horario>\n";
					outputResult += "\t\t<Profesor>" + hm.getUsuario().getDato().getApaterno() + " "
							+ hm.getUsuario().getDato().getNombre() + "</Profesor>\n";
					outputResult += "\t</ID>\n";

				}
				outputResult += "</Listado>\n";
		
				outputStream.write(outputResult.getBytes());
				outputStream.flush();
				outputStream.close();

			} catch (Exception e) {
				System.out.println("Error al intentar descargar el archivo G: " + e.getMessage());
				System.out.println(e.toString());
				// e.printStackTrace();
			}

		} else {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
