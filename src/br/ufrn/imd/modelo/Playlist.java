package br.ufrn.imd.modelo;

import java.util.List;

public class Playlist {
	private String nome;
	private List<Musica> musicas;
	
	public Playlist(String nome) {
		super();
		this.nome = nome;
	}
	
	public void addSong(Musica m) {
		musicas.add(m);
	}
	
	public void removeSong(Musica m) {
		musicas.remove(m);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}