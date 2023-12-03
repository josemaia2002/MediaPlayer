package br.ufrn.imd.model;

/**
 * Class that represents a Directory DTO with its attributes.
 * 
 * @author Davi Matias
 * @author Jose Maia
 * @version 1.0
 * @since 3/12/2023
 */
public class DirectoryDTO {
	
	/**
	 * The DTO's path.
	 */
	private String path;

	/**
	 * Method that retrieves the DTO's path.
	 *
	 * @return The DTO's path.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Method that sets the DTO's path.
	 * 
	 * @param path The path to be assigned to the DTO.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Consctructs a new instance of the DirectoryDTO Class with its path.
	 * 
	 * @param path The DTO's path.
	 */
	public DirectoryDTO(String path) {
		this.path = path;
	}
	
	/**
	 * Method that returns a string representation of a DirectoryDTO object.
	 * 
	 * @return A string with a simple representation of a DirectoryDTO object.
	 */
	@Override
	public String toString() 
	{
		return path;
	}
}
