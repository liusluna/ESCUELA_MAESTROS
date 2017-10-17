package model;

import java.io.Serializable;
import javax.persistence.*;


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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MATERIAS_ID")
	private int materiasId;

	@Column(name="` NOMBRE`")
	private String _nombre;

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

}