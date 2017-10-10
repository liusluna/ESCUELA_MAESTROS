package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the GRUPOS database table.
 * 
 */
@Entity
@Table(name="GRUPOS")
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="GRUPOS_ID")
	private int gruposId;

	@Column(name="` DESCRIPCION`")
	private String _descripcion;

	//bi-directional many-to-one association to Horariosmateria
	@OneToMany(mappedBy="grupo")
	private List<Horariosmateria> horariosmaterias;

	public Grupo() {
	}

	public int getGruposId() {
		return this.gruposId;
	}

	public void setGruposId(int gruposId) {
		this.gruposId = gruposId;
	}

	public String get_descripcion() {
		return this._descripcion;
	}

	public void set_descripcion(String _descripcion) {
		this._descripcion = _descripcion;
	}

	public List<Horariosmateria> getHorariosmaterias() {
		return this.horariosmaterias;
	}

	public void setHorariosmaterias(List<Horariosmateria> horariosmaterias) {
		this.horariosmaterias = horariosmaterias;
	}

	public Horariosmateria addHorariosmateria(Horariosmateria horariosmateria) {
		getHorariosmaterias().add(horariosmateria);
		horariosmateria.setGrupo(this);

		return horariosmateria;
	}

	public Horariosmateria removeHorariosmateria(Horariosmateria horariosmateria) {
		getHorariosmaterias().remove(horariosmateria);
		horariosmateria.setGrupo(null);

		return horariosmateria;
	}

}