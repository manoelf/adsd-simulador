package simula_restaurante.logica;

import eduni.simjava.*;
import eduni.simjava.distributions.Sim_negexp_obj;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;

public class Mesa extends Sim_entity {
	
	private Sim_port entradaFromCliente,saidaToPedido;
	private Sim_negexp_obj negexp;
	private Sim_stat stat;
    private Sim_normal_obj delay;
    private Sim_random_obj prob;
    private double mean;
    
	public Mesa(String nome, double mean, double var) {
		super(nome);
		
		entradaFromCliente = new Sim_port("entradaFromCliente");
		saidaToPedido = new Sim_port("saidaToPedido");
		
		this.mean = mean;
		
		add_port(entradaFromCliente);
		add_port(saidaToPedido);
		
		negexp = new Sim_negexp_obj("Delay", mean);
		add_generator(negexp);
		
		stat = new Sim_stat();
		stat.add_measure(Sim_stat.THROUGHPUT);
        stat.add_measure(Sim_stat.RESIDENCE_TIME);
        stat.add_measure(Sim_stat.UTILISATION);
        set_stat(stat);
		
        
        delay = new Sim_normal_obj("Delay", mean, var);
        prob = new Sim_random_obj("Probability");
        add_generator(delay);
        add_generator(prob);
	}

	public void body() {
		int i = 0;
		while (Sim_system.running()) {
			Sim_event e = new Sim_event();
            // Get the next event
            sim_get_next(e);
            // Process the event
            sim_process(mean);
            // The event has completed service
            sim_completed(e);
				sim_schedule(saidaToPedido, 0.0, 1);
				sim_trace(1, "pedido Feito");
            
		}
	}
}
