package application;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		VendedorDao vendDao = DaoFactory.criaVendedorDao();
		System.out.println("======= TESTE 1: Vendedor findById do Vendedor =======");
		Vendedor vend = vendDao.findById(3);
			
		System.out.println(vend);
	}

}
