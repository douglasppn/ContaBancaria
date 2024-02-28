package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {
	
	private ArrayList<Conta> listaContas = new ArrayList<Conta>(); //Conta é uma classe abstrata, por isso chamamos aqui
	int numero = 0;

	@Override
	public void procurarPorNumero(int numero) {
		// TODO Auto-generated method stub
		var conta = buscarNaCollection(numero);
		
		if (conta != null)
			conta.visualizar();
		else
			System.out.println("\nA Conta número: " + numero + "não foi encontrada!");
	}

	@Override
	public void listarTodas() {
		// TODO Auto-generated method stub
		for (var conta : listaContas) {
			conta.visualizar();
		}
	}

	@Override
	public void cadastrar(Conta conta) {
		// TODO Auto-generated method stub
		listaContas.add(conta);
		System.out.println("\nA conta número: " + conta.getNumero() + " foi criada com sucesso!");
	}

	@Override
	public void atualizar(Conta conta) {
		// TODO Auto-generated method stub
		var buscaConta = buscarNaCollection(conta.getNumero());
		
		if (buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("\nA Conta numero: " + conta.getNumero() + " foi atualizada com sucesso!");
		} else
			System.out.println("\nA Conta numero: " + conta.getNumero() + " naão foi encontrada!");
	}

	@Override
	public void deletar(int numero) {
		// TODO Auto-generated method stub
	    // Busca a conta na lista
	    var conta = buscarNaCollection(numero);
	    
	    if (conta != null) {
	        // Remove a conta da lista
	        if (listaContas.remove(conta)) {
	            System.out.println("\nA Conta numero: " + numero + " foi deletada com sucesso!");
	        } else {
	            System.out.println("\nFalha ao deletar a conta numero: " + numero);
	        }
	    } else {
	        System.out.println("\nNão foi encontrada uma conta com o numero: " + numero);
	    }
	}

	@Override
	public void sacar(int numero, float valor) {
		// TODO Auto-generated method stub
	}

	@Override
	public void depositar(int numero, float valor) {
		// TODO Auto-generated method stub
	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		// TODO Auto-generated method stub
	}
	
	public int gerarNumero() {
		return ++ numero;
	}

	public Conta buscarNaCollection(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero)
				return conta;
		}
		return null;

	}

}