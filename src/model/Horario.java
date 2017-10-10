package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HORARIOS database table.
 * 
 */
@Entity
@Table(name="HORARIOS")
@NamedQuery(name="Horario.findAll", query="SELECT h FROM Horario h")
public class Horario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="HORARIOS_ID")
	private int horariosId;

	@Column(name="AULA")
	private String aula;

	@Column(name="HORARIOS")
	private String horarios;

	//bi-directional many-to-one association to Horariosmateria
	@OneToMany(mappedBy="horario")
	private List<Horariosmateria> horariosmaterias;

	public Horario() {
	}

	public int getHorariosId() {
		return this.horariosId;
	}

	public void setHorariosId(int horariosId) {
		this.horariosId = horariosId;
	}

	public String getAula() {
		return this.aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public String getHorarios() {
		return this.horarios;
	}

	public void setHorarios(String horarios) {
		this.horarios = horarios;
	}

	public List<Horariosmateria> getHorariosmaterias() {
		return this.horariosmaterias;
	}

	public void setHorariosmaterias(List<Horariosmateria> horariosmaterias) {
		this.horariosmaterias = horariosmaterias;
	}

	public Horariosmateria addHorariosmateria(Horariosmateria horariosmateria) {
		getHorariosmaterias().add(horariosmateria);
		horariosmateria.setHorario(this);

		return horariosmateria;
	}

	public Horariosmateria removeHorariosmateria(Horariosmateria horariosmateria) {
		getHorariosmaterias().remove(horariosmateria);
		horariosmateria.setHorario(null);

		return horariosmateria;
	}

}