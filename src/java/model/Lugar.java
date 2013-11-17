package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class Lugar implements Serializable{
	private String nome;
	private String localizacao;
	private String tipo;

	private int id_local;
	private int rate;
	private List<Comentarios> listaComentarios;
	public static final String DATABASE_TABLE = "Lugar";
	public static final String COLUNA_NOME_LUGAR = "Nome";

	public Lugar() {
		this.listaComentarios = new ArrayList<Comentarios>();
	}

	public Lugar(int idLugar) {
		this.id_local =  idLugar;
	}

	public List<Comentarios> getListaComentarios() {
		return listaComentarios;
	}

	public void setListaComentarios(List<Comentarios> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getId_local() {
		return id_local;
	}

	public void setId_local(int id_local) {
		this.id_local = id_local;
	}

}
