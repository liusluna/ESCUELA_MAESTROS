<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page import="javax.persistence.*"%>
<!--  %@page import="model.Horario"%-->

<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:directive.include file="Encabezado.jsp" />

<%
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("ESCUELA_MAESTROS");
	EntityManager em = emf.createEntityManager();
	// Abrir su try /cash / finally

	List<Usuario> listaMaestros = null;
	List<Materia> listaMaterias = null;
	List<Grupo> listaGrupos = null;
	List<Horario> listaHorarios = null;

	try {
		listaMaestros = (List<Usuario>) em.createNamedQuery("Usuario.findMaestros").getResultList();
		listaMaterias = (List<Materia>) em.createNamedQuery("Materia.findAll").getResultList();
		listaGrupos = (List<Grupo>) em.createNamedQuery("Grupo.findAll").getResultList();
		listaHorarios = (List<Horario>) em.createNamedQuery("Horario.findAll").getResultList();
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("Error al intentar listar los horarios materias: " + e.getMessage());

	} finally {
		// System.out.println("JPA CLOSING CONNEXIONS!");
		em.close();
		emf.close();
	}
%>



<div class="jumbotron">

	<form class="form-horizontal" action="Horarios" method="post">
		<fieldset>

			<!-- Form Name -->
			<legend>Asignacion de horarios</legend>

			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="profesor">Profesor</label>
				<div class="col-md-4">
					<select id="profesor" name="profesor" class="form-control">

						<%
							for (Usuario usuario : listaMaestros) {
						%>
						<option value="<%= usuario.getUsuariosId() %>">
							<%=usuario.getDato().getApaterno()  %> <%=usuario.getDato().getNombre()  %> 
						</option>
						<%
							}
						%>
					</select>
				</div>
			</div>

			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="materia">Materia</label>
				<div class="col-md-4">
					<select id="materia" name="materia" class="form-control">

						<%
							for (Materia materia : listaMaterias) {
						%>
						<option value="<%= materia.getMateriasId() %>">
							<%=materia.get_nombre()  %> 
						</option>
						<%
							}
						%>
					</select>
				</div>
			</div>

			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="grupo">Grupo</label>
				<div class="col-md-4">
					<select id="grupo" name="grupo" class="form-control">

						<%
							for (Grupo grupo : listaGrupos) {
						%>
						<option value="<%= grupo.getGruposId() %>">
							<%=grupo.getGruposId()  %> 
						</option>
						<%
							}
						%>
					</select>
				</div>
			</div>

			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="horario">Horarios</label>
				<div class="col-md-4">
					<select id="horario" name="horario" class="form-control">

						<%
							for (Horario horario : listaHorarios) {
						%>
						<option value="<%= horario.getHorariosId() %>">
							<%=horario.getHorarios()   %>  en <%=horario.getAula()   %>
						</option>
						<%
							}
						%>
					</select>
				</div>
			</div>


			<input type="hidden" name="operacion" value="agregar">

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="guardar">Guardar</label>
				<div class="col-md-4">
					<button id="guardar" name="guardar" class="btn btn-primary">OK</button>
				</div>
			</div>

		</fieldset>
	</form>



</div>

<jsp:directive.include file='Pie.jsp' />