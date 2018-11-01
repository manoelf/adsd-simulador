package simula_restaurante.cliente;

import eduni.simjava.*;
import eduni.simjava.distributions.Sim_negexp_obj;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;


public class Cliente extends Sim_entity{
	
	private Sim_port saida1, saida2, saida3, saida4, saida5;
	private Sim_normal_obj delay;
	private Sim_stat stat;
    private Sim_random_obj prob;
	private double delay_sim;
	
	
	
	public Cliente(String nome, double mean, double var) {
		super(nome);
		
		saida1 = new Sim_port("Mesa para casal 01");
		saida2 = new Sim_port("Mesa para dois casais 01");
		saida3 = new Sim_port("Mesa para familia 01");
		saida4 = new Sim_port("Mesa unica 01");
		saida5 = new Sim_port("Mesa familia 02");
		
		add_port(saida1);
		add_port(saida2);
		add_port(saida3);
		add_port(saida4);
		add_port(saida5);
		
		//delay = new Sim_negexp_obj("Delay", mean);
		//add_generator(delay);
		
		stat = new Sim_stat();
		stat.add_measure(Sim_stat.UTILISATION);
        set_stat(stat);
        
        delay = new Sim_normal_obj("Delay", mean, var);
        prob = new Sim_random_obj("Probability");
        add_generator(delay);
        add_generator(prob);
	}
	

	  /**Quando um evento é recebido por seu destinatário, ele é recebido como um objeto Sim_event	
    Sim_event e = new Sim_event();
    sim_get_next(e);

    //Este método define uma entidade para ser processada por algum período de tempo
    //sim_process(delay);
    
    //é usado para sinalizar quando um evento é considerado como tendo concluído todo
    //o serviço em uma entidade
    sim_completed(e);
    
		
		
    
     O rastreamento de entidade é produzido pelo modelador, modificando 
     * as entidades para produzir saída de rastreio. Isso é obtido por 
     * meio do método sim_trace ()
     **/
	public void body() {
		int i = 0;
		while(Sim_system.running()) {
		      Sim_event e = new Sim_event();
		      sim_get_next(e);                 // Get the next event
		      sim_process(delay.sample());              // Process the event
		      sim_completed(e);                // The event has completed service
		      
		      double p = prob.sample();
			if (p > 0 && p < 0.2) {
				//métodos de planejamento de eventos sim_schedule ()
				sim_schedule(saida1, 0.0, 1);
				
				// todo o traço produzido é impresso em um arquivo (sim_trace)
				sim_trace(1, "Mesa para casal 01 escolhida.");
				
			} else if (p > 0.2 && p < 0.4) {
				sim_schedule(saida2, 0.0, 1);
				sim_trace(1, "Mesa para dois casais 01 escolhida.");
			} else if (p > 0.4 && p < 0.6) {
				sim_schedule(saida3, 0.0, 1);
				sim_trace(1, "Mesa para familia 01 escolhida");
			} else if (p > 0.6 && p < 0.8) {
				sim_schedule(saida4, 0.0, 1);
				sim_trace(1, "Mesa unica 01 escolhida");
			} else {
				sim_schedule(saida5, 0.0, 1);
				sim_trace(1, "Mesa familia 02 escolhida");
			}
		}
		i++;
	}

}
