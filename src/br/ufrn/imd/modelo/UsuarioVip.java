package br.ufrn.imd.modelo;

public class UsuarioVip extends Usuario {
	
	public Playlist criarPlaylist(String nome) {
		Playlist p = new Playlist(nome);
		
		return p;
	}
}
