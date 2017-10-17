package servlet;

import model.Usuario;
import security.SHAHashing;
import model.Dato;

import java.io.IOException;
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

/**
 * Servlet implementation class UsuariosServlet
 */
@WebServlet("/Usuarios")
public class UsuariosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuariosServlet() {
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

		// verifica que la sesion este activa
		HttpSession misesion = request.getSession(false);

		if (misesion.getAttribute("usuario") == null) {
			request.getRequestDispatcher("timeout.html").forward(request, response);
		}

		// realiza la operacion deseada
		if (request.getParameter("operacion").equals("muestra")) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ESCUELA_MAESTROS"); 
			EntityManager em = emf.createEntityManager();
			// Abrir su try /cash / finally
			List<Usuario> listaUsuarios = null;
			try {
				listaUsuarios = (List<Usuario>) em.createNamedQuery("Usuario.findAll").getResultList();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error al intentar listar los usuarios: " + e.getMessage());

			} finally {
				// System.out.println("JPA CLOSING CONNEXIONS!");
				em.close();
				emf.close();
			}
			

				misesion.setAttribute("usuarios", listaUsuarios);
				request.setAttribute("tipo","usuarios");
				request.setAttribute("pagina","1");
				request.getRequestDispatcher("Muestra.jsp").forward(request, response);

		} else if (request.getParameter("operacion").equals("eliminar")) {

		} else if (request.getParameter("operacion").equals("agregar")) {

			//// ---------------------------------------------
			String usuario = request.getParameter("usuario");
			String contra = request.getParameter("contra");
			String correo = request.getParameter("correo");
			String nombre = request.getParameter("nombre").toUpperCase();
			String appaterno = request.getParameter("appaterno").toUpperCase();
			String apmaterno = request.getParameter("apmaterno").toUpperCase();
			String direccion = request.getParameter("direccion").toUpperCase();
			String colonia = request.getParameter("colonia").toUpperCase();
			String estado = request.getParameter("estado").toUpperCase();
			String municipio = request.getParameter("municipio").toUpperCase();
			String docente=null ; if (request.getParameter("docente") != null) docente = request.getParameter("docente").toUpperCase();
			String admin =null; if (request.getParameter("admin") != null) admin = request.getParameter("admin").toUpperCase();
			Integer matricula = Integer.parseInt(request.getParameter("matricula"));

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ESCUELA_MAESTROS");
			EntityManager em = emf.createEntityManager();
			EntityTransaction tran = em.getTransaction();

			// Abrir su try /cash / finally
			

			try {
				Usuario usu = null;
							
				
						
				
				try {
					usu = (Usuario) em.createNamedQuery("Usuario.findUser")
							.setParameter("usuario", SHAHashing.sha256(usuario)).getSingleResult();
				} catch (Exception e) {
					System.out.println("Problemas al agregar: " + nombre + " " + appaterno + " " + apmaterno + e.getMessage());
				}
				
				if (usu == null) {
					tran.begin();
					System.out.println("Create usuario");
					Dato datos = new Dato();
					usu = new Usuario();
					
					datos.setApaterno(appaterno);
					datos.setAmaterno(apmaterno);
					datos.setColonia(colonia);
					datos.setDireccion(direccion);
					datos.setEstado(estado);
					datos.setMunicipio(municipio);
					datos.setNombre(nombre);
					em.persist(datos);
					em.flush();
					//em.refresh(datos);
					System.out.println(datos.getDatosId());
					
					usu.setDato(datos);
					//usu.setCreado(creado);
					usu.setEsActivo(1);
					usu.setMatricula(matricula);
					usu.setCorreo(correo);
					usu.setEsActivo(1);
					if( docente == null )   usu.setEsDocente(0); else usu.setEsDocente(1) ;
					if( admin == null) { usu.setEsAdmin(0); } else {usu.setEsAdmin(1);}
					usu.setUsuario(SHAHashing.sha256(usuario));
					usu.setPass(SHAHashing.sha256(contra));
					

					System.out.println("Persist usu");
					em.persist(usu);
					em.flush();
					System.out.println(usu.getUsuariosId());
					
					tran.commit();
					

					ArrayList<String> salida = new ArrayList<String>();
					salida.add("Usuario : ["+usuario+"] con datos:" + nombre + " " + appaterno + " " + apmaterno +" Insertado");
					salida.add("en la base de datos");
					request.setAttribute("info", salida);
					request.getRequestDispatcher("info.jsp").forward(request, response);
					

					
				} else {
					ArrayList<String> salida = new ArrayList<String>();
					salida.add("Usuario : ["+usuario+"] con datos:" + nombre + " " + appaterno + " " + apmaterno + " ya existe");
					salida.add("en la base de datos");
					request.setAttribute("info", salida);
					request.getRequestDispatcher("info.jsp").forward(request, response);
				}

				
			} catch (Exception e) {
				tran.rollback();
				// TODO: handle exception
				System.out.println("Error al insertar usu: " + e.getMessage());

				ArrayList<String> salida = new ArrayList<String>();
				salida.add("Error al insertar el usuario: " + nombre + " " + appaterno + " " + apmaterno);
				salida.add("en la base de datos");
				request.setAttribute("error", salida);
				request.getRequestDispatcher("error.jsp").forward(request, response);

			} finally {
				// System.out.println("JPA CLOSING CONNEXIONS!");
				em.close();
				emf.close();
			}
			// ------------

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
