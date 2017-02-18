package principal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.csvreader.CsvReader;

import dominio.Chromosome;
import dominio.FitnessFunction;
import dominio.Wsdl;

public class ImportCsv {
	
	public static void  main(String[] arg){
		try{
			Set<Wsdl>  service = new HashSet<Wsdl>();
			Chromosome cromosoma = new Chromosome();
			HashMap<String, Integer> ponderaciones = new HashMap<>();
			HashMap<String, ArrayList<Wsdl>> serviceInterface = new HashMap<>();
//			ArrayList<Wsdl> listWsdl = new ArrayList<>();


			
			CsvReader wsdl_import = new CsvReader ("sources/services.csv");
			//wsdl_import.readHeaders();
			 
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
	            	service.add(new Wsdl(response_time, availability, throughput, successability, reliability,
	            			compliance, best_practice, latency, documentation, service_name, wsdl_address, interfaz ));
	            	
	        		cromosoma.addGen(w);

        			//System.out.println("Para interfaz: " + interfaz + " y servicio: " + service_name);
        			
	        		if (serviceInterface.containsKey(interfaz)){
	        			//System.out.println("Existia la interfaz: " + interfaz);
	   	             	ArrayList<Wsdl> interfazWdsls = serviceInterface.get(interfaz);
	   	             	interfazWdsls.add(w);
	   	             	//serviceInterface.put(interfaz, interfazWdsls);
	        		}
	        		else{
	        			ArrayList<Wsdl> interfazWdsls = new ArrayList<>();
	        			//System.out.println("NO existia la interfaz: " + interfaz + " por eso la creo.");
	   	             	interfazWdsls.add(w);
	   	             	serviceInterface.put(interfaz, interfazWdsls);

	        		}
	            }
	            
	            //serviceInterface.put(interfaz, carga(interfaz, w));
	
        			
	            ponderaciones.put(Wsdl.AVAILABILITY, 3);
	            ponderaciones.put(Wsdl.LATENCY, 5);
	            ponderaciones.put(Wsdl.COMPLIANCE, 4);
	            
	             System.out.println(FitnessFunction.FunctionGoalWeighted8(cromosoma, ponderaciones));
	           
	             ArrayList<Wsdl> prueba = serviceInterface.get("iniciaSesion");
	             System.out.println(prueba.size());
	             for (int i = 0; i < prueba.size(); i++) {
	            	 System.out.println(prueba.get(i).getService_name());
	            	 System.out.println(prueba.get(i).getInterfaz());

					
				}
	             wsdl_import.close();
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		

		
	}
	
	public static ArrayList<Wsdl> carga (String interfaz, Wsdl service){
		ArrayList<Wsdl> listWsdl = new ArrayList<>();
		
		if (interfaz.equals(service.getInterfaz()))
			listWsdl.add(service);
		return listWsdl;
	}

}
