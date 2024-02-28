package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;


public class Menu {
	
	public static Scanner leia = new Scanner(System.in);

	public static void main(String[] args) {
			// TODO Auto-generated method stub
			
		ContaController contas = new ContaController();
		
		int numero, agencia, tipo, aniversario;
		String titular, opcao = "0";
		float saldo, limite;
		boolean loopMenu = true;
		
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);
		
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000f, 100.0f);
		contas.cadastrar(cc2);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana dos Santos", 4000f, 12);
		contas.cadastrar(cp1);
		
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);
		
			
		while (loopMenu) {
			
			System.out.println(Cores.TEXT_GREEN_BRIGHT + Cores.ANSI_GRAY_BACKGROUND + "*".repeat(53));
			System.out.println(" ".repeat(53));
			System.out.println(" ".repeat(21) + "BANCO VIEIRA" + " ".repeat(20));
			System.out.println(" ".repeat(53));
			System.out.println("*".repeat(53));
			System.out.println(" ".repeat(53));
			System.out.println(" ".repeat(12) + "1 - Criar Conta" + " ".repeat(26));
			System.out.println(" ".repeat(12) + "2 - Listar todas as Contas" + " ".repeat(15));
			System.out.println(" ".repeat(12) + "3 - Buscar Conta por Numero" + " ".repeat(14));
			System.out.println(" ".repeat(12) + "4 - Atualizar Dados da Conta" + " ".repeat(13));
			System.out.println(" ".repeat(12) + "5 - Apagar Conta" + " ".repeat(25));
			System.out.println(" ".repeat(12) + "6 - Sacar" + " ".repeat(32));
			System.out.println(" ".repeat(12) + "7 - Depositar" + " ".repeat(28));
			System.out.println(" ".repeat(12) + "8 - Transferir valores entre Contas" + " ".repeat(6));
			System.out.println(" ".repeat(12) + "9 - Sair" + " ".repeat(33));
			System.out.println(" ".repeat(53));
			System.out.println("*".repeat(53));
			System.out.println("Entre com a opção desejada:" + " ".repeat(26));
			System.out.println(" ".repeat(53) + Cores.TEXT_RESET);
			
			try {
				opcao = leia.nextLine();
			}catch(InputMismatchException e){
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao = "0";
			}
	
			if (opcao.equals("9")) {
				System.out.println("\nBanco Vieira - O seu futuro começa aqui!");
				sobre();
	            leia.close();
				System.exit(0);
			}
			
			switch (opcao) {
			case "1":
				System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");
				
				System.out.println("Digite o Numero da Agência: ");
				agencia = leia.nextInt();
				leia.nextLine(); // Consumir a nova linha pendente
				
				System.out.println("Digite o Nome do Titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();
				
				do {
					System.out.println("Digite o Tipo da Conta (1-CC ou 2-CP)");
					tipo = leia.nextInt();
					leia.nextLine(); // Consumir a nova linha pendente
				}while(tipo < 1 && tipo > 2);
				
				System.out.println("Digite o Saldo da Conta (R$): ");
				saldo = leia.nextFloat();
				leia.nextLine(); // Consumir a nova linha pendente
				
				switch(tipo) {
				case 1 -> {
					System.out.println("Digite o Limite de Crédito (R$): ");
					limite = leia.nextFloat();
					leia.nextLine(); // Consumir a nova linha pendente
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
					}
				case 2 -> {
					System.out.println("Digite o dia do Aniversario da Conta: ");
					aniversario = leia.nextInt();
					leia.nextLine(); // Consumir a nova linha pendente
					contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
					}
				}
				
				System.out.println("Deseja continuar? (S/N) ");
				loopMenu = continueMenu(leia.nextLine());
				
				break;
				
			case "2":
				System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");
				contas.listarTodas();
				System.out.println("Deseja continuar? (S/N) ");
				loopMenu = continueMenu(leia.nextLine());
				
				break;
				
			case "3":
				System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				leia.nextLine();
				
				contas.procurarPorNumero(numero);
				
				System.out.println("Deseja continuar? (S/N) ");
				loopMenu = continueMenu(leia.nextLine());
				
				break;
				
			case "4":
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				leia.nextLine();
				
				var buscaConta = contas.buscarNaCollection(numero);
				
				if(buscaConta != null) {
					
					tipo = buscaConta.getTipo();
					
					System.out.println("Digite o número da Agência: ");
					agencia = leia.nextInt();
					leia.nextLine();
					
					
					System.out.println("Digite o Nome do Titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();
					
					System.out.println("Digite o Saldo da Conta (R$): ");
					saldo = leia.nextFloat();
					leia.nextLine();
					
					switch (tipo) {
						case 1 -> {
							System.out.println("Digite o Limite de Crédito (R$): ");
							limite = leia.nextFloat();
							leia.nextLine();
							
							contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
							
						}
						
						case 2 -> {
							System.out.println("Digite o dia do Aniversario da Conta: ");
							aniversario = leia.nextInt();
							leia.nextLine();
							
							contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
							
						}
						default -> {
							System.out.println("Tipo de conta inválido!");
						}
					}
				} else {
					System.out.println("A Conta não foi encontrada!");
				}
				
				System.out.println("Deseja continuar? (S/N) ");
				loopMenu = continueMenu(leia.nextLine());
				
				break;
				
			case "5":
				System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				leia.nextLine();
				
				contas.deletar(numero);
				
				
				System.out.println("Deseja continuar? (S/N) ");
				loopMenu = continueMenu(leia.nextLine());
				
				break;
				
			case "6": //arrumar a lógica de erro corretamente!
				System.out.println(Cores.TEXT_WHITE + "Saque\n\n");

			    System.out.println("Deseja continuar? (S/N) ");
			    loopMenu = continueMenu(leia.nextLine());
				
				break;
				
			case "7":
				System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");

			    System.out.println("Deseja continuar? (S/N) ");
			    loopMenu = continueMenu(leia.nextLine());
				
				break;
				
			case "8":
				System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");
				
				System.out.println("Deseja continuar? (S/N) ");
				loopMenu = continueMenu(leia.nextLine());
				
				break;
				
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nPor favor, digite uma opção válida!\n" + Cores.TEXT_RESET);
				
				System.out.println("Deseja continuar? (S/N) ");
				loopMenu = continueMenu(leia.nextLine());
				
				break;
			}
			
		}
			
	}
	
		public static void sobre() {
			System.out.println("*".repeat(53));
			System.out.println("Projeto Desenvolvido por: Gabriel de Andrade Vieira");
			System.out.println("Generation Brasil - generation@generation.org");
			System.out.println("github.com/GabrielVieiraz9/contabancaria");
			System.out.println("*".repeat(53));
			
		}
		
		public static boolean continueMenu (String continuar) {

			if (continuar.equalsIgnoreCase("S")) {
				return continuar.equalsIgnoreCase("S");
			} else {
				System.out.println("Programa finalizado!");
				return false;
			}
				
				
		}

		
		public static void keyPress() {

			try {

				System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
				System.in.read();

			} catch (IOException e) {

				System.out.println("Você pressionou uma tecla diferente de enter!");

			}
		}

		
	}