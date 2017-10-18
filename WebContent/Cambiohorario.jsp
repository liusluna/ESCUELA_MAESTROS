<%@page import="java.util.*"%>
<%@page import="javax.persistence.*"%>
<%@page import="model.Dato"%>
<%@page import="model.Materia"%>
<%@page import="model.Horario"%>
<%@page import="model.Usuario"%>

<%
	String titulo="";
	String tipo="";
	if(request.getAttribute("tipo")!=null){
		tipo=(String) request.getAttribute("tipo");
	}else if (request.getParameter("tipo")!=null){
		tipo=(String) request.getParameter("tipo");
	}else{
		Enumeration params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = (String)params.nextElement();
		 System.out.println("Parameter Name:["+paramName+"], Value:["+request.getParameter(paramName)+"]");
		}
		ArrayList<String> salida = new ArrayList<String>();
		salida.add("No ha seleccionado ningun tipo a mostrar");
		salida.add("Usar el menu para motrar uno");
		request.setAttribute("error", salida);
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}
	
	if(tipo.equals("materias") ){
		titulo="Materias";
	}else if(tipo.equals("horarios")){
		titulo="Horarios";
		tipo="horarios";
	}else if(tipo.equals("alumnos")){
		titulo="Alumnos";
		tipo="alumnos";
	}else
		titulo="Sin datos";
	
	int pagina=1;
	if (request.getAttribute("pagina") != null)
		pagina=Integer.parseInt((String) request.getAttribute("pagina"));
	else if (request.getParameter("pagina") != null)
		pagina=Integer.parseInt((String) request.getParameter("pagina"));
	int maxxpag=5;
	int numeropag=1;
	
%>  
<%!
	public int numeroPaginas(int total, int max){
		int paginas=total/max;
		if (total%max != 0)
			paginas++;
		return paginas;
	}
	
	public int registrosuperior(int total, int actual, int max){
		if(actual*max<=total)
			return actual*max;
		return total;
	}
	
	public int registroInferior(int max, int sup){
		if(sup%max==0)
			return sup-max;
		return sup-sup%max;
	}

%>



<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:directive.include file="Encabezado.jsp" />

<div class="jumbotron">
        <h4><%= titulo %></h4>
	<table class="table table-striped table-hover">
		<%
        if(tipo.equals("materias")){
        	
        	List<Materia> arra = (List<Materia>)misesion.getAttribute("materias");
        	%>

		<thead>
			<tr>
				<th></th>
				<th>ID</th>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Direccion</th>
				<th>Horario</th>
			</tr>
		</thead>
		<tbody>

			<%
				if (arra.size() >0 ){
				numeropag=(int) numeroPaginas(arra.size() , maxxpag );
				for (int i=registroInferior(maxxpag, registrosuperior(arra.size(), pagina, maxxpag));i< registrosuperior(arra.size(), pagina, maxxpag) ; i++ ){
					Materia materia =(Materia) arra.get(i);
		        	  	//System.out.println("|"+alumno.getAlumnosId()+" | "+alumno.getNombre()+" | "+alumno.getAppaterno()+" | "+alumno.getApmaterno()+" | "+alumno.getDireccion()+" | "+alumno.getColonia()+" | "+alumno.getMunicipio()+" | "+alumno.getEstado()+"|");
		        	  
		        %>
			<tr>
				<td class="actions-row"><a class="btn text-danger" data-toggle="confirmation" data-title="Desea Realmemte Eliminar" 
				href="Materias?operacion=eliminar&materia=<%=materia.getMateriasId()%>"
				data-placement="top" title="Eliminar materia">
						<span class="glyphicon glyphicon-remove"></span>
				</a></td>
				<td><%= materia.getMateriasId()%></td>
				<td><%= materia.get_nombre() %></td>
				
				<td><%//= alumno.getHorario().getMateria().getNombre() %> en <%//= alumno.getHorario().getAula() %>
					a las <%//= alumno.getHorario().getHora() %></td>
			</tr>
			<% 	  	
		     }
				}

			}else if( tipo.equals("horarios")){
			        	List<Horario> arra = (List<Horario>)misesion.getAttribute("horarios");
			%>
		
		<thead>
			<tr>
				<th></th>
				<th>ID</th>
				<th>Hora</th>
				<th>Aula</th>
				<th>Materia</th>
			</tr>
		</thead>
		<tbody>

			<%
				numeropag = (int) numeroPaginas(arra.size(), maxxpag);
				for (int i = registroInferior(maxxpag,
							registrosuperior(arra.size(), pagina, maxxpag)); i < registrosuperior(arra.size(), pagina,
									maxxpag); i++) {
						Horario horario = (Horario) arra.get(i);
						//System.out.println("|"+alumno.getAlumnosId()+" | "+alumno.getNombre()+" | "+alumno.getAppaterno()+" | "+alumno.getApmaterno()+" | "+alumno.getDireccion()+" | "+alumno.getColonia()+" | "+alumno.getMunicipio()+" | "+alumno.getEstado()+"|");
			%>
			<tr>
				<td class="actions-row"><a class="text-danger"
					href="Horarios?operacion=eliminar&horario=<%=horario.getHorariosId()%>"
					data-toggle="tooltip" data-placement="top" title="Eliminar horario">
						<span class="glyphicon glyphicon-remove"></span>
				</a></td>
				<td><%=horario.getHorariosId()%></td>
				<td><%=horario.getHorarios()%></td>
				<td><%=horario.getAula()%></td>
				<td><%//=horario.getMateria().getNombre()%></td>
			</tr>
			<%
				}
			%>

			<%
				} else if (tipo.equals("materias")) {
					List<Materia> arra = (List<Materia>) misesion.getAttribute("materias");
			%>
		
		<thead>
			<tr>
				<th></th>
				<th>ID</th>
				<th>Materia</th>
			</tr>
		</thead>
		<tbody>

			<%
				numeropag = (int) numeroPaginas(arra.size(), maxxpag);
					for (int i = registroInferior(maxxpag,
							registrosuperior(arra.size(), pagina, maxxpag)); i < registrosuperior(arra.size(), pagina,
									maxxpag); i++) {
						Materia materia = (Materia) arra.get(i);
						//System.out.println("|"+alumno.getAlumnosId()+" | "+alumno.getNombre()+" | "+alumno.getAppaterno()+" | "+alumno.getApmaterno()+" | "+alumno.getDireccion()+" | "+alumno.getColonia()+" | "+alumno.getMunicipio()+" | "+alumno.getEstado()+"|");
			%>
			<tr>
				<td class="actions-row"><a class="text-danger"
					href="Materias?operacion=eliminar&materia=<%= materia.getMateriasId() %>"
					data-toggle="tooltip" data-placement="top" title="Eliminar materia">
						<span class="glyphicon glyphicon-remove"></span>
				</a></td>
				<td><%=materia.getMateriasId()%></td>
				<td><%=materia.get_nombre()%></td>
			</tr>
			<%
				}
			%>

			<%
		}
	%>
		
		</tbody>
	</table>



 <script type="text/javascript">
 //http://bootstrap-confirmation.js.org/
		
	  $('[data-toggle=confirmation]').confirmation({
		    rootSelector: '[data-toggle=confirmation]',
		    container: 'body'
		  });
		
</script>



	<!-- Paginación -->

    <nav aria-label="Page navigation">
  	<ul class="pagination">
    <%	
       	for(int i=1;i<=numeropag;i++){
       		if(i==pagina){
    		%> 
    		<li class="active"> 
    		<%
    		}else{
    		%> 
    		<li> 
    		<%
    		}
    		
    			
    			if (i!=pagina)
    				out.print("<a href=\"Muestra.jsp?pagina="+i+"&tipo="+tipo+"&\">");
    			else
    				out.print("<a href=\"#\">");
			
    			out.print(i);
   			
    				%>
    		</a>
    		</li>
    <%
    	}
    %>
	  </ul>
	</nav>
	<!-- Termina paginacion -->
  </div>
  

<jsp:directive.include file='Pie.jsp'/>