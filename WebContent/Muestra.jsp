<%@page import="java.util.*"%>

<%@page import="model.*"%>


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
	
	if(tipo.equals("usuarios") ){
		titulo="Usuarios";
	}else if(tipo.equals("horarios")){
		titulo="Horarios";
		tipo="horarios";
	}else if(tipo.equals("info")){
		titulo="Información";
		tipo="info";
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
     <%
      if(tipo.equals("info")){
    	  
		try
        {
		ArrayList<String> info = (ArrayList<String>)request.getAttribute("info");

		for (String str : info ){			
		%>
		<p>
		<%= str %>
		</p>
		<br>
		<%
	
		}

		}catch (Exception es) {
			System.err.println("Error en desconocido en la pagina de muestra\n" + es.getMessage());
		}
      }else{
	%>
	<table class="table table-striped table-hover">
		<%
        if(tipo.equals("usuarios")){
        	
        	List<Usuario> arra = (List<Usuario>)misesion.getAttribute("usuarios");
        	%>

		<thead>
			<tr>
				<th></th>
				<th>ID</th>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Direccion</th>
				<th>Correo</th>
			</tr>
		</thead>
		<tbody>

			<%  
				//if (arra == null){}
				//else 
				if (arra.size() >0 ){
				numeropag=(int) numeroPaginas(arra.size() , maxxpag );
				for (int i=registroInferior(maxxpag, registrosuperior(arra.size(), pagina, maxxpag));i< registrosuperior(arra.size(), pagina, maxxpag) ; i++ ){
					Usuario usu =(Usuario) arra.get(i);
		        	  	//System.out.println("|"+alumno.getAlumnosId()+" | "+alumno.getNombre()+" | "+alumno.getAppaterno()+" | "+alumno.getApmaterno()+" | "+alumno.getDireccion()+" | "+alumno.getColonia()+" | "+alumno.getMunicipio()+" | "+alumno.getEstado()+"|");
		        	  	System.out.println("|"+usu.getUsuariosId()+" | "+usu.getDato().getNombre() );
		        	  
		        %>
			<tr>
				<td class="actions-row"><a class="btn text-danger" data-toggle="confirmation" data-title="Desea Realmemte Eliminar" 
				href="Usuario?operacion=eliminar&usuario=<%=  usu.getUsuariosId() %>"
				data-placement="top" title="Eliminar cliente">
						<span class="glyphicon glyphicon-remove"></span>
				</a></td>
				<td><%= usu.getUsuariosId() %></td>
				<td><%= usu.getDato().getNombre() %></td>
				<td><%= usu.getDato().getApaterno() %> &nbsp; <%= usu.getDato().getAmaterno() %>
				</td>
				<td><%= usu.getDato().getDireccion() %> &nbsp; <%= usu.getDato().getColonia() %>
					&nbsp; <%= usu.getDato().getMunicipio() %> &nbsp; <%= usu.getDato().getEstado() %>
				</td>
				<td><%= usu.getCorreo() %></td>
			</tr>
			<% 	  	
		        }
				}

			}else if( tipo.equals("horarios")){
			        	List< Horariosmateria > arra = (List<Horariosmateria>)misesion.getAttribute("horarios");
			%>
		
		<thead>
			<tr>
				<th></th>
				<th>ID</th>
				<th>Grupo</th>
				<th>Aula</th>
				<th>Horario</th>
				<th>Maestro</th>
				<th>Solicitar cambio</th>
			</tr>
		</thead>
		<tbody>

			<%
				if (arra.size() >0 ){
				numeropag = (int) numeroPaginas(arra.size(), maxxpag);
				for (int i = registroInferior(maxxpag,
							registrosuperior(arra.size(), pagina, maxxpag)); i < registrosuperior(arra.size(), pagina,
									maxxpag); i++) {
					Horariosmateria hm = (Horariosmateria) arra.get(i);
						//System.out.println("|"+alumno.getAlumnosId()+" | "+alumno.getNombre()+" | "+alumno.getAppaterno()+" | "+alumno.getApmaterno()+" | "+alumno.getDireccion()+" | "+alumno.getColonia()+" | "+alumno.getMunicipio()+" | "+alumno.getEstado()+"|");
			%>
			<tr>
				<td class="actions-row"><a class="text-danger"
					href="Horariomateria?operacion=eliminar&horario=<%=hm.getHorariosmateriasId() %>"
					data-toggle="tooltip" data-placement="top" title="Eliminar Horario">
						<span class="glyphicon glyphicon-remove"></span>
				</a></td>
				<td><%=  hm.getHorariosmateriasId() %></td>
				<td><%= hm.getGrupo().get_descripcion() %></td>
				<td><%= hm.getHorario().getAula() %></td>
				<td><%= hm.getHorario().getHorarios() %></td>
				<td><%= hm.getUsuario().getDato().getNombre() %></td>
				<td><input type="button" name="Cambio" value="Cambio" onclick="location.href='EnviarCorreoServlet?operacion=<%=hm.getUsuario().getCorreo()%>&'"></td>
			</tr>
			<%
				}

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
   <%
   		//termina info
    }
   %>
	
  </div>
  

<jsp:directive.include file='Pie.jsp'/>