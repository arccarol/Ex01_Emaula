//capacidade para 100 pessoas
// 300 pessoas, no primeiro instante acessam o sistema de compra
//podem comprar de 1 a 4 ingressos por compra, sendo que isso é uma condição aleatória
// 50 ms a 2 s, sendo que, se o tempo passar de 1s, mensagem de timeout
//Processo que pode demorar de 1 s a 3 s, sendo que, se o tempo passar de 2,5s, mensagem de timeout

package controller;

import java.util.concurrent.Semaphore;

public class Exercicio01 extends Thread{
	
	public static int quantTotIngressos = 100; 
	private int idpessoa;
	private int templogin;
	private int tempcompra;
	private int quantingressos;
	private Semaphore semaforo;
	
	
	public Exercicio01(int idpessoa, Semaphore semaforo) {
		this.idpessoa = idpessoa;
		this.templogin = 0;
		this.tempcompra = 0;
		this.quantingressos = 0;
		this.semaforo = semaforo;
		
	}
	
	public void run() {
		if(Login()) {
			if(Compra()) {
			try {
				semaforo.acquire();
				ValidaCompra();
			}catch (InterruptedException e) {
			 e.printStackTrace();
			}finally {
				semaforo.release();
			}
		}
		
		}
	}
	
	public boolean Login() {
		this.templogin = numeroalearorio(500, 2001);
		
		try {
			sleep(this.templogin);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(this.templogin >= 1000) {
			System.out.println("O usuario " +idpessoa + " recebeu timeout ao tentar logar após " + this.templogin +" ms");
			return false;
		}else {
			System.out.println("O usuario " +idpessoa + " conseguiu fazer o logar após " + this.templogin +" ms");
			this.quantingressos = numeroalearorio(1, 5);
			return true;
		}
	}
	
	public boolean Compra() {
		this.tempcompra = numeroalearorio(1000, 3001);
		
		try {
			sleep(this.tempcompra);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(this.templogin >= 2500) {
			System.out.println("O usuario " +idpessoa + " teve a sessão finalizada após " + this.tempcompra +" ms");
			return false;
		}
		
		return true;
	
	}
	
	public boolean ValidaCompra() {
		if(quantTotIngressos >= this.quantingressos) {
			quantTotIngressos -= this.quantingressos; 
			System.out.println("O usuario " +idpessoa + " conseguiu efetuar a compra de " +this.quantingressos + " ingressos");
			return true; 
		}else {
			System.out.println("O usuario " +idpessoa + " não conseguiu efetuar a compra de " +this.quantingressos + " ingressos");
			return false;
			
			
		}
	}
	
	public int numeroalearorio(int min, int max) {
		Integer numeroaleatorio = min + (int)(Math.random() * (max - min));
		return numeroaleatorio;
		
	}
	
	
	

}
