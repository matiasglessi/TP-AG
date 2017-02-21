package principal;

import java.util.ArrayList;
import java.util.HashMap;


import dominio.FitnessFunction;
import dominio.ReadData;
import dominio.Wsdl;

public class ImportCsv {
	
	public static void  main(String[] arg){
			HashMap<String, Integer> ponderaciones = new HashMap<>();
			ReadData datos = new ReadData();
			
			datos.ReadDataCsv();			
			
            ponderaciones.put(Wsdl.AVAILABILITY, 3);
            ponderaciones.put(Wsdl.LATENCY, 5);
            ponderaciones.put(Wsdl.COMPLIANCE, 4);
            
             System.out.println(FitnessFunction.FunctionGoalWeighted8(datos.getCromosoma(), ponderaciones));
           
             ArrayList<Wsdl> prueba = datos.getWsdlInterface().get("reservaVuelo"); 
             System.out.println(prueba.size());
             for (int i = 0; i < prueba.size(); i++) {
            	 System.out.println(prueba.get(i).getService_name());
            	 System.out.println(prueba.get(i).getInterfaz());
             }
             
//             System.out.println("Lista de Wsdl totales: ");
//             ArrayList<Wsdl> allWsdl = datos.getAllWsdlInterface(); 
//             System.out.println(allWsdl.size());
//             for (int i = 0; i < allWsdl.size(); i++) {
//            	 System.out.println(allWsdl.get(i).getService_name());
//            	 System.out.println(allWsdl.get(i).getInterfaz());
//             }

	}
	
}
