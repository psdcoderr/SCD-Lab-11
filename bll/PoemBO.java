package bll;

import dal.DataLayerPoemDB;
import dal.PoemInterface;

public class PoemBO {
	PoemInterface DAO;
	public PoemBO(PoemInterface DAO)
	{
		this.DAO=DAO;	
	}
	public void addData(String filename)
	{
		DAO.ParsePoems(filename);
	}
}
