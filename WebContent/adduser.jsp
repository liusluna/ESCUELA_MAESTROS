<%@page import="java.util.*"%>
<%@page import="javax.persistence.*"%>
<!--  %@page import="model.Horario"%-->

<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:directive.include file="Encabezado.jsp" />

<div class="jumbotron">

	<form class="form-horizontal" action="Usuarios" method="post">
		<fieldset>

			<!-- Form Name -->
			<legend>Alta de Usuarios</legend>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nombre">Usuario</label>
				<div class="col-md-4">
					<input id="usuario" name="usuario" placeholder="Usuario"
						class="form-control input-md" type="text" required
						pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{3,50}$" maxlength="50"> <span
						class="help-block">Escribe el nombre de usuario</span>
				</div>
			</div>

			<!-- Secret input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="password">Contraseña</label>
				<div class="col-md-4">
					<input id="contra" name="contra" placeholder="Contraseña"
						class="form-control input-md" type="password" required
						pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
						maxlength="50"> <span class="help-block">Escribe la
						contrasela, minimo de 8 caracteres, al menos una mayuscula, un
						numero y un caracter especial</span>
				</div>
			</div>

			<!-- Checkbox input-->
			<div class="checkbox">
				<label class="checkbox-inline"><input type="checkbox" name="docente" value="docente">Es Docente</label>
				<label class="checkbox-inline"><input type="checkbox" name="admin" value="admin">Es Administrador</label>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="correo">Correo</label>
				<div class="col-md-4">
					<input id="correo" name="correo" placeholder="Correo"
						class="form-control input-md" type="email" required maxlength="45">
					<span class="help-block">Escribe el correo electronico</span>
				</div>
			</div>
			
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nombre">Matricula</label>
				<div class="col-md-4">
					<input id="matricula" name="matricula" placeholder="Matricula"
						class="form-control input-md" type="text" required
						pattern="[0-9]{10,16}" maxlength="50"> <span
						class="help-block">Escribe la matricula</span>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nombre">Nombre</label>
				<div class="col-md-4">
					<input id="nombre" name="nombre" placeholder="Nombre"
						class="form-control input-md" type="text" required
						pattern="[A-Za-z\s]{2,50}" maxlength="50"> <span
						class="help-block">Escribe el nombre</span>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="appaterno">Apellido
					Paterno</label>
				<div class="col-md-4">
					<input id="nombre" name="appaterno" placeholder="Apellido Paterno"
						class="form-control input-md" type="text" required
						pattern="[A-Za-z\s]{2,45}" maxlength="45"> <span
						class="help-block">Escribe el Apellido Paterno</span>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="apmaterno">Apellido
					Materno</label>
				<div class="col-md-4">
					<input id="nombre" name="apmaterno" placeholder="Apellido Paterno"
						class="form-control input-md" type="text" required
						pattern="[A-Za-z\s]{2,45}" maxlength="45"> <span
						class="help-block">Escribe el Apellido Materno</span>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="direccion">Direcci&oacute;n</label>
				<div class="col-md-4">
					<input id="nombre" name="direccion" placeholder="Calle y numero"
						class="form-control input-md" type="text" required
						pattern="[A-Za-z0-9#.\s]{3,45}" maxlength="45"> <span
						class="help-block">Escribe la Calle y numero</span>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="colonia">Colonia</label>
				<div class="col-md-4">
					<input id="nombre" name="colonia"
						placeholder="Colonia o Poblaci&oacute;n"
						class="form-control input-md" type="text" required
						pattern="[A-Za-z0-9#.\s]{3,45}" maxlength="45"> <span
						class="help-block">Escribe la Colonia o Poblaci&oacute;n</span>
				</div>
			</div>

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="municipio">Municipio</label>
				<div class="col-md-4">
					<input id="nombre" name="municipio" placeholder="Municipio"
						class="form-control input-md" type="text" required
						pattern="[A-Za-z0-9#.\s]{3,50}" maxlength="50"> <span
						class="help-block">Escribe el Municipio</span>
				</div>
			</div>


			<!-- Select Basic -->
			<div class="form-group">
				<label class="col-md-4 control-label" for="estado">Estado</label>
				<div class="col-md-4">
					<select id="estado" name="estado" class="form-control">
						<option value="Aguascalientes">Aguascalientes</option>
						<option value="Baja California">Baja California</option>
						<option value="Baja California Sur">Baja California Sur</option>
						<option value="Campeche">Campeche</option>
						<option value="Chiapas">Chiapas</option>
						<option value="Chihuahua">Chihuahua</option>
						<option value="Coahuila">Coahuila</option>
						<option value="Colima">Colima</option>
						<option value="Ciudad de Mexico">Ciudad de México</option>
						<option value="Durango">Durango</option>
						<option value="Estado de Mexico">Estado de México</option>
						<option value="Guanajuato">Guanajuato</option>
						<option value="Guerrero">Guerrero</option>
						<option value="Hidalgo">Hidalgo</option>
						<option value="Jalisco">Jalisco</option>
						<option value="Michoacan">Michoacán</option>
						<option value="Morelos">Morelos</option>
						<option value="Nayarit">Nayarit</option>
						<option value="Nuevo Leon">Nuevo León</option>
						<option value="Oaxaca">Oaxaca</option>
						<option value="Puebla">Puebla</option>
						<option value="Queretaro">Querétaro</option>
						<option value="Quintana Roo">Quintana Roo</option>
						<option value="San Luis Potosí">San Luis Potosí</option>
						<option value="Sinaloa">Sinaloa</option>
						<option value="Sonora">Sonora</option>
						<option value="Tabasco">Tabasco</option>
						<option value="Tamaulipas">Tamaulipas</option>
						<option value="Tlaxcala">Tlaxcala</option>
						<option value="Veracruz">Veracruz</option>
						<option value="Yucatan">Yucatán</option>
						<option value="Zacatecas">Zacatecas</option>
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