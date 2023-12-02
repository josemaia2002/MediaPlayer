package br.ufrn.imd.model;

public class DirectoryDTO {
	
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public DirectoryDTO(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() 
	{
		return path;
	}
}
