package simula_restaurante.cliente;

import eduni.simjava.*;
import eduni.simjava.distributions.Sim_negexp_obj;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;


public class Cliente extends Sim_entity{
	
	private Sim_port entradaFromRestaurante,saidaToMesa;
	
	private Sim_negexp_obj negexp;
	private Sim_normal_obj delay;
	private Sim_stat stat;
    private Sim_random_obj prob;
	private double delay_sim;
	
	
	
	public Cliente(String nome, double mean, double var) {
		super(nome);
		
		entradaFromRestaurante = new Sim_port("entradaFromRestaurante");
		saidaToMesa = new Sim_port("saidaToMesa");
		
		add_port(entradaFromRestaurante);
		add_port(saidaToMesa);
		
		negexp = new Sim_negexp_obj("Delay", mean);
		add_generator(negexp);
		
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
		      sim_schedule(saidaToMesa, 0.0, 1);
		      double p = prob.sample();
		      sim_trace(1,i +  " - Mesa para casal escolhida.");
		      i ++;
		}
	}

}
