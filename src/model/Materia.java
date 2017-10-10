package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MATERIAS database table.
 * 
 */
@Entity
@Table(name="MATERIAS")
@NamedQuery(name="Materia.findAll", query="SELECT m FROM Materia m")
public class Materia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MATERIAS_ID")
	private int materiasId;

	@Column(name="` NOMBRE`")
	private String _nombre;

	//bi-directional many-to-one association to Horariosmateria
	@OneToMany(mappedBy="materia")
	private List<Horariosmateria> horariosmaterias;

	public Materia() {
	}

	public int getMateriasId() {
		return this.materiasId;
	}

	public void setMateriasId(int materiasId) {
		this.materiasId = materiasId;
	}

	public String get_nombre() {
		return this._nombre;
	}

	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}

	public List<Horariosmateria> getHorariosmaterias() {
		return this.horariosmaterias;
	}

	public void setHorariosmaterias(List<Horariosmateria> horariosmaterias) {
		this.horariosmaterias = horariosmaterias;
	}

	public Horariosmateria addHorariosmateria(Horariosmateria horariosmateria) {
		getHorariosmaterias().add(horariosmateria);
		horariosmateria.setMateria(this);

		return horariosmateria;
	}

	public Horariosmateria removeHorariosmateria(Horariosmateria horariosmateria) {
		getHorariosmaterias().remove(horariosmateria);
		horariosmateria.setMateria(null);

		return horariosmateria;
	}

}