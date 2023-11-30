package br.ufrn.imd.modelo;

public class Music {
	private String nome;
	private double duracao;
	private String artista;
	private String genero;
	private String album;
	private String path;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getDuracao() {
		return duracao;
	}
	
	public void setDuracao(double duracao) {
		this.duracao = duracao;
	}
	
	public String getArtista() {
		return artista;
	}
	
	public void setArtista(String artista) {
		this.artista = artista;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public boolean equals(Music m) 
	{
		if(this.getPath().equals(m.getPath())) return true;
		if(!this.getNome().equals(m.getNome())) return false;
		if(this.getDuracao() != m.getDuracao()) return false;
		if(!this.getArtista().equals(m.getArtista())) return false;
		if(!this.getGenero().equals(m.getGenero())) return false;
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome + "\t"
				+ duracao + "\t"
				+ artista + "\t"
				+ genero + "\t"
				+ album + "\t"
				+ path;
	}
}
