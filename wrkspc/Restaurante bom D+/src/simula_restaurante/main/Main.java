package simula_restaurante.main;

import eduni.simjava.*;
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
		Sim_system.run();

	}

}
