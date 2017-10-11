<%@page import="java.util.*"%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="Encabezado.jsp" />

      <div class="jumbotron">
        <h1>Información</h1>
           <%
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
			System.err.println("Error en desconocido en la pagina de info\n" + es.getMessage());
		}

	%>
	<br>
		<br>
		<p>
		<a href="app.jsp">Regresar</a>
		</p>
       

      </div>



<jsp:directive.include file='Pie.jsp'/>