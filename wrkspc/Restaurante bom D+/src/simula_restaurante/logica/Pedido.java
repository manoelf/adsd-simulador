package simula_restaurante.logica;

import eduni.simjava.*;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;

public class Pedido extends Sim_entity{
	
	private Sim_port saida1;
	private Sim_stat stat;
	private double delay_time;
	private Sim_normal_obj delay;
    private Sim_random_obj prob;
    
	public Pedido(String nome, double mean, double var) {
		super(nome);
		
		this.delay_time = mean;
		
		saida1 = new Sim_port("Pedido da mesa");
		add_port(saida1);
		
		stat = new Sim_stat();
		stat.add_measure(Sim_stat.UTILISATION);
        set_stat(stat);
        
        delay = new Sim_normal_obj("Delay", mean, var);
        prob = new Sim_random_obj("Probability");
        add_generator(delay);
        add_generator(prob);
	}
	
	public void body () {
		while (Sim_system.running()) {
            Sim_event e = new Sim_event();
            // Get the next event
            sim_get_next(e);
            // Process the event
            sim_process(delay_time);
            // The event has completed service
            sim_completed(e);
            sim_trace(1, "Pedido da mesa feito");
          }
	}
	
	

}
