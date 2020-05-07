package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		Departamento obj = new Departamento(1, "Books");
		Vendedor vend = new Vendedor(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
		VendedorDao vendedor = DaoFactory.criaVendedorDao();
		
		
		System.out.println(vend);
	}

}
