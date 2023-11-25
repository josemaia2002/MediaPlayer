package br.ufrn.imd.dao;

import java.util.ArrayList;
import br.ufrn.imd.modelo.Musica;

public class MusicaDao {
	private ArrayList<Musica> musicas = new ArrayList<Musica>();
	
	public void addVeiculo(Musica m) {
		musicas.add(m);
	}
	
	public void removeVeiculo(Musica m) {
		musicas.remove(m);
	}
}
