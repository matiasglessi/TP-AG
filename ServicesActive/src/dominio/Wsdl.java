package dominio;


import java.util.HashMap;

import org.apache.commons.validator.routines.UrlValidator;

import com.eviware.soapui.impl.WsdlInterfaceFactory;
import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlProject;

public class Wsdl {
	public static final String RESPONSE_TIME = "response_time";
	public static final String AVAILABILITY = "availability";
	public static final String THROUGHPUT = "throughput";
	public static final String SUCCESSABILITY = "successability";
	public static final String RELIABILITY = "reliability";
	public static final String COMPLIANCE = "compliance";
	public static final String BEST_PRACTICE = "best_practice";
	public static final String LATENCY = "latency";
	
	private HashMap<String, Double> map;

	private String documentation;
	private String service_name;
	private String wsdl_address;
	private String interfaz;
	
	public Wsdl(Double response_time2, Double availability2, Double throughput2, Double successability2, Double reliability2, Double compliance2, Double best_practice2, Double latency2, String documentation2, String sn, String address, String interfaz){
		map = new HashMap<>();
		map.put(RESPONSE_TIME, response_time2);
		map.put(AVAILABILITY, availability2);
		map.put(THROUGHPUT, throughput2);
		map.put(SUCCESSABILITY, successability2);
		map.put(RELIABILITY, reliability2);
		map.put(COMPLIANCE, compliance2);
		map.put(BEST_PRACTICE, best_practice2);
		map.put(LATENCY, latency2);
		this.documentation = documentation2;
		this.service_name = sn;
		this.wsdl_address = address;
		this.interfaz = interfaz;
	}


	public Double getAtributte(String key){
		return map.get(key);	
	}
	
	public boolean equals(Object o, String key){
//		if (o instanceof Wsdl)
		return this.getAtributte(key).equals(((Wsdl) o).getWsdl_address());
//		return false;
	}

	public String getDocumentation() {
		return documentation;
	}

	public String getService_name() {
		return service_name;
	}

	public String getWsdl_address() {
		return wsdl_address;
	}
	
	public String getInterfaz() {
		return interfaz;
	}
	
	public boolean isValidWsdlUrl (String url){
		UrlValidator urlValidator = new UrlValidator();
		if(urlValidator.isValid(url)) 
	    {
	        return true;
	    }
		System.out.println("WSDL URL is not valid...");
	    //logger.info("WSDL URL is not valid...");
	    return false;
	}
	
	public boolean isWSDLAvailable(String wsdlAddr){
		try {
			WsdlProject project = new WsdlProject();
			WsdlInterface iface = WsdlInterfaceFactory.importWsdl( project, wsdlAddr, true)[0];
			return true;
			
		} catch (Exception e) {
			return false;
		}	
      
    }
	
	
	

}
