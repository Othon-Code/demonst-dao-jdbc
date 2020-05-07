package model.dao;

import java.util.List;

import model.entities.Vendedor;


public interface VendedorDao {
	
	void inserir(Vendedor obj);
	void updade(Vendedor obj);
	void deletePorId(Integer id);
	Vendedor findById(Integer id);
	List<Vendedor> findAll();
	
	

}
