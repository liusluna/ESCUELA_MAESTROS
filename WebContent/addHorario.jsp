<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@page import="javax.persistence.*"%>
<!--  %@page import="model.Horario"%-->

<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:directive.include file="Encabezado.jsp" />


<div class="jumbotron">

	<form class="form-horizontal" action="Horarios" method="post">
		<fieldset>

			<!-- Form Name -->
			<legend>Agregar horarios</legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="horarios">Horarios</label>
				<div class="col-md-4">
					<input id="horarios" name="horarios" placeholder="horario en formato 10:00-12:00"
						class="form-control input-md" type="text" required
						pattern="{3,50}" maxlength="50"> <span
						class="help-block">Escribe el horario </span>
				</div>
			</div>
						<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="aula">Aula</label>
				<div class="col-md-4">
					<input id="aula" name="aula" placeholder="Numero o descripcion del aula"
						class="form-control input-md" type="text" required
						pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{3,50}$" maxlength="50"> <span
						class="help-block">Escribe la aula</span>
				</div>
			</div>

					


			<input type="hidden" name="operacion" value="agregarhorario">

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