package simula_restaurante.logica;

import eduni.simjava.*;
import eduni.simjava.distributions.Sim_negexp_obj;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;

public class Mesa extends Sim_entity {
	
	private Sim_port entrada1, saida1, saida2, saida3;
	private Sim_negexp_obj negexp;
	private Sim_stat stat;
    private Sim_normal_obj delay;
    private Sim_random_obj prob;
    private double mean;
    
	public Mesa(String nome, double mean, double var) {
		super(nome);
		
		entrada1 = new Sim_port("Cliente");
		saida1 = new Sim_port("Prato 01");
		saida2 = new Sim_port("Prato 02");
		saida3 = new Sim_port("Prato 03");
		
		this.mean = mean;
		
		add_port(entrada1);
		add_port(saida1);
		add_port(saida2);
		add_port(saida3);
		
		negexp = new Sim_negexp_obj("Delay", mean);
		add_generator(negexp);
		
		stat = new Sim_stat();
		stat.add_measure(Sim_stat.THROUGHPUT);
        stat.add_measure(Sim_stat.RESIDENCE_TIME);
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
			
            if (i % 2 == 0) {
				sim_schedule(saida1, 0.0, 1);
				sim_trace(1, "Prato 01 ecolhido.");
			} else if (i % 1 == 0) {
				sim_schedule(saida2, 0.0, 1);
				sim_trace(1, "Prato 02 ecolhido.");
			} else {
				sim_schedule(saida3, 0.0, 1);
				sim_trace(1, "Prato 03 ecolhido.");
			}
            i++;
		}
	}
}
