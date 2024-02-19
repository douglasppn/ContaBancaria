package conta;

import conta.model.Conta;

public class Menu {

	public static void main(String[] args) {
		
		Conta c1 = new Conta(1, 123, 1, "Sabrina", 250000.0f);
		
		c1.visualizar();
		
		if  (c1.sacar(100))
			System.out.println("Saque efetuado com sucesso. O novo saldo é de:" + c1.getSaldo());
		else
			System.out.println("O Saldo é insuficiente");
	}

}
