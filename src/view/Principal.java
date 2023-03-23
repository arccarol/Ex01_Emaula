package view;

import java.util.concurrent.Semaphore;

import controller.Exercicio01;

public class Principal {
	
	public static void main(String args []) {
		
		Semaphore semaforo = new Semaphore(1);
		for (int i = 0; i < 300; i++) {
			Exercicio01 show = new Exercicio01(i, semaforo);
			show.start();
			
					}
		}

}
