package br.ufrn.imd.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import br.ufrn.imd.modelo.Music;
import br.ufrn.imd.modelo.User;
import br.ufrn.imd.modelo.UserVip;

public class MusicaDao {
	private ArrayList<Music> songs = new ArrayList<Music>();
	
	public void addVeiculo(Music m) {
		songs.add(m);
	}
	
	public void removeVeiculo(Music m) {
		songs.remove(m);
	}
	
	public ArrayList<Music> listSongs() {
		return songs;
	}
	
	public void loadSongs() {
		BufferedReader buffRead;
		try {
			buffRead = new BufferedReader(new FileReader(getClass().getResource("/resources/data/musicas.txt").getFile()));
		
			String line = buffRead.readLine();
			while (true) {
				if (line != null) {
					String[] info = line.split(" ");					
							
					Music m = new Music();
					m.setNome(info[0]);
					m.setPath(info[1]);
					
					songs.add(m);
					line = buffRead.readLine();
				}
				else break;
			}
			buffRead.close();
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
}
