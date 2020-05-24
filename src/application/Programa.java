package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Programa {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		VendedorDao vendDao = DaoFactory.criaVendedorDao();
		System.out.println("======= TESTE 1: Vendedor findById do Vendedor =======");
		Vendedor vend = vendDao.findById(3);
		System.out.println(vend);

		System.out.println("\n======= TESTE 2: Vendedor findByDepartment =======");
		Departamento depart = new Departamento(2, null);
		List<Vendedor> list = vendDao.findByDepartamento(depart);
		for (Vendedor obj : list) {
			System.out.println(obj);
		}

		System.out.println("\n======= TESTE 3: Vendedor findByAll =======");
		List<Vendedor> list2 = vendDao.findAll();
		for (Vendedor obj : list2) {
			System.out.println(obj);
		}

		System.out.println("\n======= TESTE 4: Vendedor insert =======");
		Vendedor novVend = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.0, depart);
		vendDao.inserir(novVend);
		System.out.println("Inserido!!Novo id = " + novVend.getId());

		System.out.println("\n======= TESTE 5: Vendedor update =======");
		vend = vendDao.findById(1);
		vend.setNome("Martha Wayne");
		vendDao.updade(vend);
		System.out.println("Atualização completa !");

		System.out.println("\n======= TESTE 6: Vendedor delete =======");
		System.out.println("Entre com id para deleção: ");
		int id = sc.nextInt();

		vendDao.deletePorId(id);
		System.out.println("Deleção completa !");

		sc.close();
	}

}
