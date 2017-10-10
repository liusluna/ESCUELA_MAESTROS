package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HORARIOSMATERIAS database table.
 * 
 */
@Entity
@Table(name="HORARIOSMATERIAS")
@NamedQuery(name="Horariosmateria.findAll", query="SELECT h FROM Horariosmateria h")
public class Horariosmateria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HORARIOSMATERIAS_ID")
	private int horariosmateriasId;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="USUARIOS_ID")
	private Usuario usuario;

	//bi-directional many-to-one association to Materia
	@ManyToOne
	@JoinColumn(name="MATERIAS_ID")
	private Materia materia;

	//bi-directional many-to-one association to Horario
	@ManyToOne
	@JoinColumn(name="HORARIOS_ID")
	private Horario horario;

	//bi-directional many-to-one association to Grupo
	@ManyToOne
	@JoinColumn(name="GRUPOS_ID")
	private Grupo grupo;

	public Horariosmateria() {
	}

	public int getHorariosmateriasId() {
		return this.horariosmateriasId;
	}

	public void setHorariosmateriasId(int horariosmateriasId) {
		this.horariosmateriasId = horariosmateriasId;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Horario getHorario() {
		return this.horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}