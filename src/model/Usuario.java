package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name="USUARIOS")
//@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
@NamedQueries
({
	@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u"),
	@NamedQuery(name="Usuario.findUser", query="SELECT u FROM Usuario u where u.usuario = :usuario"),
	@NamedQuery(name="Usuario.findValid", query="SELECT u FROM Usuario u where u.usuario = :usuario and u.pass = :password"),
	@NamedQuery(name="Usuario.findMaestros", query="SELECT u FROM Usuario u where u.esDocente = 1"),
	@NamedQuery(name="Usuario.findbyid", query="SELECT u FROM Usuario u where u.usuariosId = :id")
})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USUARIOS_ID")
	private int usuariosId;

	@Column(name="CORREO")
	private String correo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREADO")
	private Date creado;

	@Column(name="ES_ACTIVO")
	private int esActivo;

	@Column(name="ES_ADMIN")
	private int esAdmin;

	@Column(name="ES_DOCENTE")
	private int esDocente;

	@Column(name="MATRICULA")
	private int matricula;

	@Column(name="PASS")
	private String pass;

	@Column(name="USUARIO")
	private String usuario;

	//uni-directional many-to-one association to Dato
	@ManyToOne
	@JoinColumn(name="DATOS_ID")
	private Dato dato;

	public Usuario() {
	}

	public int getUsuariosId() {
		return this.usuariosId;
	}

	public void setUsuariosId(int usuariosId) {
		this.usuariosId = usuariosId;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getCreado() {
		return this.creado;
	}

	public void setCreado(Date creado) {
		this.creado = creado;
	}

	public int getEsActivo() {
		return this.esActivo;
	}

	public void setEsActivo(int esActivo) {
		this.esActivo = esActivo;
	}

	public int getEsAdmin() {
		return this.esAdmin;
	}

	public void setEsAdmin(int esAdmin) {
		this.esAdmin = esAdmin;
	}

	public int getEsDocente() {
		return this.esDocente;
	}

	public void setEsDocente(int esDocente) {
		this.esDocente = esDocente;
	}

	public int getMatricula() {
		return this.matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Dato getDato() {
		return this.dato;
	}

	public void setDato(Dato dato) {
		this.dato = dato;
	}

}