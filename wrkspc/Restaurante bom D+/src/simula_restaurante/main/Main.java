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
		
		
		/*exemplos de main
		 * https://github.com/nicolasgts/Simulator-ADSD/blob/master/src/System.java
		 * https://github.com/silvarthur/adsd_simulation_project/blob/master/src/Simulation.java WENDLEY
		 * 
		 */
		
		Restaurante retaurante = new Restaurante("Restaurante", 150.4);
		
		Cliente cliente1 = new Cliente("Cliente1", 105.1, 90.5);
		Mesa mesa1 = new Mesa("Mesa1", 250.4, 69.0);
		Pedido pedido1 = new Pedido("Pedido1", 335.2, 95.9);
		Garcom garcom1 = new Garcom("Garcom1", 10);

		Cliente cliente2 = new Cliente("Cliente2", 150.1, 70.5);
		Mesa mesa2 = new Mesa("Mesa2", 200.4, 50.0);
		Pedido pedido2 = new Pedido("Pedido2", 435.2, 50.9);
		Garcom garcom2 = new Garcom("Garcom2", 20);
		
		//cliente1
		Sim_system.link_ports("Restaurante", "saidaToCliente1", "Cliente1", "entradaFromRestaurante");
		Sim_system.link_ports("Cliente1", "saidaToMesa", "Mesa1", "entradaFromCliente");
		Sim_system.link_ports("Mesa1", "saidaToPedido", "Pedido1", "entradaFromMesa");
		Sim_system.link_ports("Pedido1", "saidaToGarcom", "Garcom1", "entradaFromPedido");
		
		
		//cliente2
		Sim_system.link_ports("Restaurante", "saidaToCliente2", "Cliente2", "entradaFromRestaurante");
		Sim_system.link_ports("Cliente2", "saidaToMesa", "Mesa2", "entradaFromCliente");
		Sim_system.link_ports("Mesa2", "saidaToPedido", "Pedido2", "entradaFromMesa");
		Sim_system.link_ports("Pedido2", "saidaToGarcom", "Garcom2", "entradaFromPedido");
		
		
		
		Sim_system.set_report_detail(true, false);//gera report
		Sim_system.set_trace_detail(false, true, false);//gera traces
		Sim_system.run();

	}

}
