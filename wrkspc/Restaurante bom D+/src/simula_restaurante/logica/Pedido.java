package simula_restaurante.logica;

import eduni.simjava.*;

public class Pedido extends Sim_entity{
	
	private Sim_port saida1;
	private double delay;

	public Pedido(String nome, double delay) {
		super(nome);
		
		this.delay = delay;
		
		saida1 = new Sim_port("Pedido da mesa");
		add_port(saida1);
	}
	
	public void body () {
		while (Sim_system.running()) {
            Sim_event e = new Sim_event();
            // Get the next event
            sim_get_next(e);
            // Process the event
            sim_process(delay);
            // The event has completed service
            sim_completed(e);
            sim_trace(1, "Pedido da mesa feito");
          }
	}
	
	

}
