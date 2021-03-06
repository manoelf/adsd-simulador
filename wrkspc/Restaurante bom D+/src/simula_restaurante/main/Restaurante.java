package simula_restaurante.main;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import eduni.simjava.distributions.Sim_negexp_obj;

public class Restaurante extends Sim_entity {

	private Sim_port saidaToCliente1,saidaToCliente2; 
	private Sim_negexp_obj delay;
	private double mean;
	
	public Restaurante(String nome, double mean) {
		super(nome);
		this.mean = mean;
		
		saidaToCliente1 = new Sim_port("saidaToCliente1");
		saidaToCliente2 = new Sim_port("saidaToCliente2");
		
		add_port(saidaToCliente1);
		add_port(saidaToCliente2);
		
		delay = new Sim_negexp_obj("Delay", mean);
        add_generator(delay);
	}

	public void body() {
		for (int i=0; i < 100; i++) {
			
			// Send the processor a job
			if(i%2 == 0) {
				sim_schedule(saidaToCliente1, 0.0, 1);
				sim_trace(1, "Cliente 1 no restaurante.");
			}else {
				sim_schedule(saidaToCliente2, 0.0, 1);
				sim_trace(1, "Cliente 2 no restaurante.");
			}
			
			// Pause
			sim_pause(delay.sample());
        }
      }	
}