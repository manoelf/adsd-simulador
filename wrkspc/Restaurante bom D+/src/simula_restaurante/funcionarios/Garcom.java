package simula_restaurante.funcionarios;

import eduni.simjava.*;

public class Garcom extends Sim_entity{
	
	private Sim_port entrada1, saida1;
	private double delay;
	
	public Garcom(String nome, double delay) {
		super(nome);
		
		this.delay = delay;
		entrada1 = new Sim_port("Pedido 01.");
		saida1 = new Sim_port("Pedido 01 Pronto.");
		
		add_port(entrada1);
		add_port(saida1);
	}

	public void body () {
		while (Sim_system.running()) {
            Sim_event e = new Sim_event();
            // Get the next event
            sim_get_next(e);
            // Process the event
            sim_trace(1, "Pedido 01 recebido.");
            sim_process(delay);
            // The event has completed service
            sim_completed(e);
            sim_trace(1, "Pedido 01 pronto.");
          }
	}
	
}
