package dominio;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.csvreader.CsvReader;
import com.mgiamberardino.jnetic.population.Evolution;


public class ReadData {
	ArrayList<Wsdl>  service = new ArrayList<>();
	Chromosome crom = new Chromosome();
	private static Map<String, List<Wsdl>> serviceInterface = new LinkedHashMap<>(0, (float)0.75, false);
	
	public void ReadDataCsv (String pathFile){
		try{
			CsvReader wsdl_import = new CsvReader (pathFile);
			 
	            while (wsdl_import.readRecord()) {
	            	if (wsdl_import.get(0).startsWith("##")){
	            		continue;
	            	}
	                Double response_time = Double.valueOf(wsdl_import.get(0));
	                Double availability = Double.valueOf(wsdl_import.get(1));
	                Double throughput = Double.valueOf(wsdl_import.get(2));
	                Double successability = Double.valueOf(wsdl_import.get(3));
	                Double reliability = Double.valueOf(wsdl_import.get(4));             	
	            	Double compliance = Double.valueOf(wsdl_import.get(5));
	            	Double best_practice = Double.valueOf(wsdl_import.get(6));
	            	Double latency = Double.valueOf(wsdl_import.get(7));
	            	String documentation = wsdl_import.get(8);
	            	String service_name = wsdl_import.get(9);
	            	String wsdl_address = wsdl_import.get(10);
	            	String interfaz = wsdl_import.get(11);
	            	Wsdl w = new Wsdl(response_time, availability, throughput, successability, reliability,
	            			compliance, best_practice, latency, documentation, service_name, wsdl_address, interfaz );
	            	service.add(w);
	            	listWsdl(interfaz, w);
	            }
	            	            	           
	             wsdl_import.close();
			
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void listWsdl(String interfaz, Wsdl w ){
		if (serviceInterface.containsKey(interfaz)){
			//System.out.println("Existia la interfaz: " + interfaz);
            	List<Wsdl> interfazWdsls = serviceInterface.get(interfaz);
            	interfazWdsls.add(w);
		}
		else{
			ArrayList<Wsdl> interfazWdsls = new ArrayList<>();
			//System.out.println("NO existia la interfaz: " + interfaz + " por eso la creo.");
            	interfazWdsls.add(w);
            	serviceInterface.put(interfaz, interfazWdsls);
		}
	}
	
	
	public static Map<String, List<Wsdl>> getWsdlInterface (){
		return serviceInterface;
	}
	
	public Chromosome getCromosoma(){
		return this.crom;
	}
	
	public ArrayList<Wsdl> getAllWsdlInterface(){
		return this.service;
	}
	
	public static Chromosome generateCromosoma (){
		Chromosome ch = new Chromosome();
		ArrayList<Wsdl> wsdls;
		Iterator it = serviceInterface.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, ArrayList<Wsdl>> m = (Map.Entry<String, ArrayList<Wsdl>>)it.next();
			wsdls =m.getValue();
			int valor = (int) (Math.random()*wsdls.size());
			Wsdl w = wsdls.get(valor);
			ch.addGen(w);
		}
		return ch;		
	}
	

	public  void writeFichero (String object, Chromosome c, int evol){
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter(object));
			bf.write("Workflow óptimo: " + "\n");
			for (int i = 0; i < c.length(); i++) {
				bf.write("Interfaz: " + c.getGen(i).getInterfaz() + " - " + "Name Service: " + c.getGen(i).getService_name() + "\n");
				
			}
			bf.write("Aptitud del Cromosoma: " + c.getAptitude() + "\n");
			bf.write("Generaciones: " + evol + "\n");
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
