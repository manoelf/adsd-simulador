package simula_restaurante.cliente;

import eduni.simjava.*;
import eduni.simjava.distributions.Sim_negexp_obj;


public class Cliente extends Sim_entity{
	
	private Sim_port saida1, saida2, saida3, saida4, saida5;
	private Sim_negexp_obj delay;
	private double delay_sim;
	
	
	public Cliente(String nome, double mean) {
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
		
		delay = new Sim_negexp_obj("Delay", mean);
		add_generator(delay);
		
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
		      sim_process(delay_sim);              // Process the event
		      sim_completed(e);                // The event has completed service
		      
			if (i % 4 == 0) {
				//métodos de planejamento de eventos sim_schedule ()
				sim_schedule(saida1, 0.0, 1);
				
				// todo o traço produzido é impresso em um arquivo (sim_trace)
				sim_trace(1, "Mesa para casal 01 escolhida.");
				
			} else if (i % 3 == 0) {
				sim_schedule(saida2, 0.0, 1);
				sim_trace(1, "Mesa para dois casais 01 escolhida.");
			} else if (i % 2 == 0) {
				sim_schedule(saida3, 0.0, 1);
				sim_trace(1, "Mesa para familia 01 escolhida");
			} else if (i % 1 == 0) {
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
