package br.ufrn.imd.dao;

import java.util.ArrayList;
import br.ufrn.imd.modelo.Music;

public class MusicaDao {
	private ArrayList<Music> musicas = new ArrayList<Music>();
	
	public void addVeiculo(Music m) {
		musicas.add(m);
	}
	
	public void removeVeiculo(Music m) {
		musicas.remove(m);
	}
}
