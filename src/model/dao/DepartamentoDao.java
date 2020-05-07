package model.dao;

import java.util.List;

import model.entities.Departamento;

public interface DepartamentoDao {
	
	void inserir(Departamento obj);
	void updade(Departamento obj);
	void deletePorId(Integer id);
	Departamento findById(Integer id);
	List<Departamento> findAll();
	
	
	

}
