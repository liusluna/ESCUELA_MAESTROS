package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HORARIOS database table.
 * 
 */
@Entity
@Table(name="HORARIOS")
@NamedQueries
({
	@NamedQuery(name="Horario.findAll", query="SELECT h FROM Horario h"),
	@NamedQuery(name="Horario.findbyid", query="SELECT h FROM Horario h where h.horariosId = :id")
})
public class Horario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="HORARIOS_ID")
	private int horariosId;

	@Column(name="AULA")
	private String aula;

	@Column(name="HORARIOS")
	private String horarios;

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

}