package br.ufrn.imd.dao;

import java.util.ArrayList;
import br.ufrn.imd.modelo.Usuario;

public class UsuarioDao {
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	public void addVeiculo(Usuario u) {
		usuarios.add(u);
	}
	
	public void removeVeiculo(Usuario u) {
		usuarios.remove(u);
	}
}