package model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class Tarefas implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String usuario;
	private String data;
	private String horario;
	private String descricao;
	private Lugar lugar;

	
	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar l) {
		this.lugar = l;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
