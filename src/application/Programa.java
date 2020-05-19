package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		VendedorDao vendDao = DaoFactory.criaVendedorDao();
		System.out.println("======= TESTE 1: Vendedor findById do Vendedor =======");
		Vendedor vend = vendDao.findById(3);
		System.out.println(vend);
		
				
		System.out.println("\n======= TESTE 2: Vendedor findByDepartment =======");
		Departamento depart = new Departamento(2, null);
		List<Vendedor> list  = vendDao.findByDepartamento(depart);
			for(Vendedor obj: list) {
				System.out.println(obj);
			}
			
			
			System.out.println("\n======= TESTE 3: Vendedor findByAll =======");
			List<Vendedor> list2  = vendDao.findAll();
				for(Vendedor obj: list2) {
					System.out.println(obj);
				}
			
			
			
			
			
			
			
			
			
		
	}

}
