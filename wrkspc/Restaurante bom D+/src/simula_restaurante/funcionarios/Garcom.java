package simula_restaurante.funcionarios;

import eduni.simjava.*;
import eduni.simjava.distributions.Sim_negexp_obj;

public class Garcom extends Funcionario{
	
	private double delay_time;
	private Sim_port entradaFromPedido;
	private Sim_stat stat;
	private Sim_negexp_obj delay;
	
	public Garcom(String nome, double mean) {
		super(nome);
		delay_time = mean;
		delay = new Sim_negexp_obj("Delay", mean);
		
		this.stat = new Sim_stat();
		
		entradaFromPedido = new Sim_port("entradaFromPedido");
		
		add_port(entradaFromPedido);
		
		
		
		add_generator(delay);
		
		this.stat.add_measure(Sim_stat.ARRIVAL_RATE);
		this.stat.add_measure(Sim_stat.QUEUE_LENGTH);
		this.stat.add_measure(Sim_stat.WAITING_TIME);
		this.stat.add_measure(Sim_stat.UTILISATION);
		this.stat.add_measure(Sim_stat.RESIDENCE_TIME);
		
		set_stat(stat);
	}

	public void body () {
		while (Sim_system.running()) {
            Sim_event e = new Sim_event();
            // Get the next event
            sim_get_next(e);
            // Process the event
            sim_trace(1, "Pedido recebido.");
            sim_process(delay_time);
            // The event has completed service
            sim_completed(e);
            sim_trace(1, "Pedido pronto.");
          }
	}
	
}
