package simula_restaurante.cliente;

import eduni.simjava.*;
import eduni.simjava.distributions.Sim_negexp_obj;


public class Cliente extends Sim_entity{
	
	private Sim_port saida1, saida2, saida3, saida4, saida5;
	private Sim_negexp_obj delay;
	
	
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

}
