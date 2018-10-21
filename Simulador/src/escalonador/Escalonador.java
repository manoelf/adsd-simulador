package escalonador;
import java.io.PrintWriter;
import java.util.List;


public class Escalonador extends Thread {

	private GeradorNumerosAleatorios gerador;
	private List<Integer> numerosAleatorios1;
	private List<Integer> numerosAleatorios2;
	private int tempo;
	private int chegada1;
	private int chegada2;
	private int saida1;
	private int saida2;
	private int filaChegada1;
	private int filaChegada2;
	private int a;
	private int mod1;
	private int mod2;
	private int indice1;
	private int indice2;
	private boolean servico;
	private PrintWriter writer;

	/**
	 * Construtor
	 * @param tempo de execucao
	 */
	public Escalonador(int tempo) {
		this.tempo = tempo;
		this.chegada1 = 0;
		this.chegada2 = 0;
		this.saida1 = 0;
		this.saida2 = 0;
		this.filaChegada1 = 0;
		this.filaChegada2 = 0;
		this.servico = false;
		this.gerador = new GeradorNumerosAleatorios(7);
		this.a = 5;
		this.mod1 = 10;
		this.mod2 = 5; 
		this.numerosAleatorios1 = gerador.metodoMultiplicativo(a, mod1);
		this.numerosAleatorios2 = gerador.metodoMultiplicativo(a, mod2);
		this.indice1 = 0;
		this.indice2 = 0;
		this.escalonaChegada1(0);
		//this.escalonaChegada2(0);

		try {
			writer = new PrintWriter("saida/escalonador", "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		writer.println("-*-");
		writer.println("Tempo de escalonamento" + this.tempo + "'");
		writer.println(" Chegada de fregues 1 em " + this.chegada1 + "'");
		writer.println(" Chegada de fregues 2 em " + this.chegada2 + "'");
		writer.println("-*-");
		writer.println();
		
	}

	@Override
	public void run() {
		int segundos = 0;
		while (segundos < this.tempo) {
			try {
				this.sleep(1);
				segundos++;
				checaEventosCriados1(segundos);
				//checaEventosCriados2(segundos);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		writer.close();
	}
	
	
	
	public void checaEventosCriados1(int segundos) {
		boolean houveAlteracao = false;
		if (segundos == chegada1) {
			houveAlteracao = true;
			writer.println("< Fila 1 > Evento 1 > Chegada de fregues na Fila 1 aos " + segundos + "'");
			if (!this.servico) {
				escalonaSaida1(segundos);
				this.servico = true;
				writer.println("< Fila 1 > Evento 2 > atendimento do Sistema iniciado em "	+ segundos + "'");
				writer.println("< Fila 1 > atendimento do Sistema encerrado em " + (this.saida1 - segundos)	+ "'");
			} else {
				this.filaChegada1++;
				writer.println("< Fila 1 > Evento 4 > esperando atendimento");
				writer.println("< Fila 1 > Tamanho da fila 1 em " + segundos + "'" + this.filaChegada1);
			}
			escalonaChegada1(segundos);
			writer.println("< Fila 1 > Proximo fregues em " + (this.chegada1 - segundos) + "'");
		} else if (segundos == chegada2) {
			houveAlteracao = true;
			writer.println("< Fila 2 > Evento 1 > Chegada de fregues na Fila 2 aos " + segundos + "'");
			if (!this.servico && this.filaChegada1 == 0) {
				escalonaSaida2(segundos);
				this.servico = true;
				writer.println("< Fila 2 > Evento 2 > atendimento do Sistema iniciado em "	+ segundos + "'");
				writer.println("< Fila 2 > atendimento do Sistema encerrado em " + (this.saida2 - segundos)	+ "'");
			} else {
				this.filaChegada2++;
				writer.println("< Fila 2 > Evento 4 > esperando atendimento");
				writer.println("< Fila 2 > Tamanho da fila 2 em " + segundos + "'" + this.filaChegada2);
			}
			escalonaChegada2(segundos);
			writer.println("< Fila 2 > Proximo fregues em " + (this.chegada2 - segundos) + "'");
		
		}
		

		if (segundos == this.saida1) {
			houveAlteracao = true;
			writer.println("< Fila 1 > Evento 3 > atendimento do Sistema encerrado em " + segundos + "'");
			if (this.chegada1 != 0) {
				this.chegada1--;
				this.escalonaSaida1(segundos);
				this.servico = true;
				writer.println("< Fila 1 > Evento 2 > atendimento do Sistema iniciado em "	+ segundos + "'");
				writer.println("< Fila 1 > atendimento do Sistema encerrado em " + (saida1 - segundos)	+ "'");
			} else {
				this.servico = false;
			}
		} else if (segundos == this.saida2) {
			houveAlteracao = true;
			writer.println("< Fila 2 > Evento 3 > atendimento do Sistema encerrado em " + segundos + "'");
			if (this.filaChegada2 != 0 && this.filaChegada1 == 0) {
				this.filaChegada2--;
				this.escalonaSaida2(segundos);
				this.servico = true;
				writer.println("< Fila 2 > Evento 2 > atendimento do Sistema iniciado em "	+ segundos + "'");
				writer.println("< Fila 2 > atendimento do Sistema  encerrado em " + (saida2 - segundos)	+ "'");
			} else {
				this.servico = false;
				
			}
		}
		
		
		if (houveAlteracao) {
			if (this.servico) {
				writer.println("< Fila 1 > Sistema ocupada em " + segundos + "'");
			} else {
				writer.println("< Fila 1 > Sistema livre em " + segundos + "'");
			}
			writer.println("< Fila 1 > Tamanho da fila 1 em " + segundos + "'" + this.filaChegada1);
			writer.println("-*-");
			
			if (this.servico && this.filaChegada2 != 0 && filaChegada1 != 0) {
				writer.println("< Fila 2 > Sistema ocupada em " + segundos + "'");
			} else {
				writer.println("< Fila 2 > Sistema livre em " + segundos + "'");
			}
			writer.println("< Fila 2 > Tamanho da fila 2 em " + segundos + "'" + this.filaChegada2);
			writer.println("-*-");
		}
		if (this.servico && this.filaChegada2 != 0 && filaChegada1 != 0) {
			writer.println("< Fila 2 > Sistema ocupada em " + segundos + "'");
		} else {
			writer.println("< Fila 2 > Sistema livre em " + segundos + "'");
		}
		writer.println("< Fila 2 > Tamanho da fila 2 em " + segundos + "'" + this.filaChegada2);
		writer.println("-*-");
	}
		
	
	

	public void geraNumerosAleatorios1(){
		if (indice1 == (numerosAleatorios1.size()-1)){
			this.a++;
			this.mod1++;
			this.numerosAleatorios1 = gerador.metodoMultiplicativo(a, mod1);
			this.indice1 = 0;
		}
	}
	
	public void geraNumerosAleatorios2(){
		if (indice2 == (numerosAleatorios2.size()-1)){
			this.a++;
			this.mod2++;
			this.numerosAleatorios2 =  gerador.metodoMultiplicativo(a, mod2);
			this.indice2 = 0;
		}
	}
	
	
	public void escalonaChegada1(int segundos) {
		this.geraNumerosAleatorios1();
		this.chegada1 = segundos + numerosAleatorios1.get(indice1);
		this.indice1++;
	}

	public void escalonaChegada2(int segundos) {
		this.geraNumerosAleatorios2();
		this.chegada2 = segundos + numerosAleatorios2.get(indice2);
		this.indice2++;
	}
	
	public void escalonaSaida1(int segundos) {
		this.geraNumerosAleatorios1();
		this.saida1 = segundos + numerosAleatorios1.get(indice1);
		this.indice1++;
	}
	
	public void escalonaSaida2(int segundos) {
		this.geraNumerosAleatorios2();
		this.saida2 = segundos + numerosAleatorios1.get(indice1);
		this.indice1++;
	}

	public static void main(String[] args) {
		int tempoDeEscalonamento = 300; //em segundos
		Escalonador barbearia = new Escalonador(tempoDeEscalonamento);

		//inicia a thread
		barbearia.start();
	}
}
