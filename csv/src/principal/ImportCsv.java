package principal;

import java.util.List;

import dominio.Wsdl;
import com.csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ImportCsv {
	
	public static void  main(String[] arg){
		try{
			List<Wsdl>  service = new ArrayList<Wsdl>();
			
			CsvReader wsdl_import = new CsvReader ("/home/valeria/Diseño/csv/src/QWS_Dataset_v2.csv");
			wsdl_import.readHeaders();
			 
	            while (wsdl_import.readRecord()) {
	                String response_time = wsdl_import.get(0);
	                String availability = wsdl_import.get(1);
	                String throughput = wsdl_import.get(2);
	                String successability = wsdl_import.get(3);
	                String reliability = wsdl_import.get(4);             	
	            	String compliance = wsdl_import.get(5);
	            	String best_practice = wsdl_import.get(6);
	            	String latency = wsdl_import.get(7);
	            	String documentation = wsdl_import.get(8);
	            	String service_name = wsdl_import.get(9);
	            	String wsdl_address = wsdl_import.get(10);
	                
	            	service.add(new Wsdl(response_time, availability, throughput, successability, reliability,
	            			compliance, best_practice, latency, documentation, service_name, wsdl_address ));             
	            }
	             
	            wsdl_import.close();
	             
	            for(Wsdl ws : service){
	             
	                System.out.println(ws.getResponse_time() + " - " + ws.getAvailability() + " - "
	                    + ws.getThroughput() + " - " + ws.getSuccessability());
	            }
			
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
