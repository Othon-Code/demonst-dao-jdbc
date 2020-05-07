package application;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		VendedorDao vendDao = DaoFactory.criaVendedorDao();
		
		Vendedor vend = vendDao.findById(3);
			
		System.out.println(vend);
	}

}
