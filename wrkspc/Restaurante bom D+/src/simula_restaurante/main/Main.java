package simula_restaurante.main;

import eduni.simjava.*;
import simula_restaurante.cliente.Cliente;
import simula_restaurante.funcionarios.Garcom;
import simula_restaurante.logica.Caixa;
import simula_restaurante.logica.Mesa;
import simula_restaurante.logica.Pedido;
public class Main {

	public static void main(String[] args) {
		Sim_system.initialise();
		Sim_system.set_report_detail(true, false);//gera report
		Sim_system.set_trace_detail(false, true, false);//gera traces
		/*exemplos de main
		 * https://github.com/nicolasgts/Simulator-ADSD/blob/master/src/System.java
		 * https://github.com/silvarthur/adsd_simulation_project/blob/master/src/Simulation.java WENDLEY
		 * 
		 */
		
		Restaurante retaurante = new Restaurante("Restaurante", 150.4);
		Cliente cliente1 = new Cliente("Cliente1", 105.1, 90.5);
		/*Cliente cliente2 = new Cliente("Cliente2", 145.0, 70.5);
		Cliente cliente3 = new Cliente("Cliente3", 135.2, 40.5);
		Cliente cliente4 = new Cliente("Cliente4", 100.7, 99.5);
		Cliente cliente5 = new Cliente("Cliente5", 159.3, 24.5); 
		
		Mesa mesa1 = new Mesa("Mesa1", 250.4, 69.0);
		Mesa mesa2 = new Mesa("Mesa2", 120.7, 100.0);
		Mesa mesa3 = new Mesa("Mesa3", 450.6, 59.0);
		Mesa mesa4 = new Mesa("Mesa4", 200.4, 29.0);
		Mesa mesa5 = new Mesa("Mesa5", 210.5, 69.0);
		
		Pedido pedido1 = new Pedido("Pedido1", 335.2, 95.9);
		Pedido pedido2 = new Pedido("Pedido2", 125.2, 75.0);
		Pedido pedido3 = new Pedido("Pedido3", 115.2, 85.9);
		
		Garcom garcom = new Garcom("Garcom", 100);
		*/
		Sim_system.link_ports("Restaurante", "Cliente", "Cliente1", "Mesa para casal 01");
		/*Sim_system.link_ports("Restaurante", "Out", "Cliente2", "In");
		Sim_system.link_ports("Restaurante", "Out", "Cliente3", "In");
		Sim_system.link_ports("Restaurante", "Out", "Cliente4", "In");
		Sim_system.link_ports("Restaurante", "Out", "Cliente5", "In");
		
		
		Sim_system.link_ports("Cliente1", "In", "Mesa1", "Out");
		Sim_system.link_ports("Cliente2", "In", "Mesa2", "Out");
		Sim_system.link_ports("Cliente3", "In", "Mesa3", "Out");
		Sim_system.link_ports("Cliente4", "In", "Mesa4", "Out");
		Sim_system.link_ports("Cliente5", "In", "Mesa5", "Out");
		
		Sim_system.link_ports("Mesa1", "In", "Pedido1", "Out");
		Sim_system.link_ports("Mesa2", "In", "Pedido1", "Out");
		Sim_system.link_ports("Mesa3", "In", "Pedido2", "Out");
		Sim_system.link_ports("Mesa4", "In", "Pedido2", "Out");
		Sim_system.link_ports("Mesa5", "In", "Pedido3", "Out");
		
		Sim_system.link_ports("Pedido1", "In", "Garcom", "Out");
		Sim_system.link_ports("Pedido2", "In", "Garcom", "Out");
		Sim_system.link_ports("Pedido3", "In", "Garcom", "Out");*/
		
		Sim_system.run();

	}

}
