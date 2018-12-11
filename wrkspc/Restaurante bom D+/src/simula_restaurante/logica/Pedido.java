package simula_restaurante.logica;

import eduni.simjava.*;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;

public class Pedido extends Sim_entity{
	
	private Sim_port entradaFromMesa,saidaToGarcom;
	private Sim_stat stat;
	private double delay_time;
	private Sim_normal_obj delay;
    private Sim_random_obj prob;
    
	public Pedido(String nome, double mean, double var) {
		super(nome);
		
		this.delay_time = mean;
		
		entradaFromMesa = new Sim_port("entradaFromMesa");
		saidaToGarcom = new Sim_port("saidaToGarcom");
		
		add_port(entradaFromMesa);
		add_port(saidaToGarcom);
		
		
		stat = new Sim_stat();
		this.stat.add_measure(Sim_stat.ARRIVAL_RATE);
		this.stat.add_measure(Sim_stat.QUEUE_LENGTH);
		this.stat.add_measure(Sim_stat.WAITING_TIME);
		this.stat.add_measure(Sim_stat.UTILISATION);
		this.stat.add_measure(Sim_stat.RESIDENCE_TIME);
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
