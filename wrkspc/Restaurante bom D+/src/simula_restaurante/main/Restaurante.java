package simula_restaurante.main;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import eduni.simjava.distributions.Sim_negexp_obj;

public class Restaurante extends Sim_entity {

	private Sim_port saida1; 
	private Sim_negexp_obj delay;
	private double mean;
	
	public Restaurante(String nome, double mean) {
		super(nome);
		this.mean = mean;
		
		saida1 = new Sim_port("Cliente");
		
		add_port(saida1);
		
		delay = new Sim_negexp_obj("Delay", mean);
        add_generator(delay);
	}

	public void body() {
		for (int i=0; i < 100; i++) {
          // Send the processor a job
          sim_schedule(saida1, 0.0, 0);
          sim_trace(1, "Cliente no restaurante.");
          // Pause
          sim_pause(delay.sample());
        }
      }	

}
