package conta;

import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;

public class Menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ContaCorrente cc1 = new ContaCorrente(32, 33, 1, "Sabrina", 4000.000f, 3000.0f);
		
		ContaPoupanca cp1 = new ContaPoupanca(32, 33, 1, "Sabrina", 4000.000f, "444487323");
		
		System.out.println("Saldo inicial: " + cc1.getSaldo());
		cc1.sacar(2500);
		System.out.println("Saldo pós saque: " + cc1.getSaldo());
		
		System.out.println("------------------------------------");
		
		System.out.println(cp1.getCpf());
	}

}
