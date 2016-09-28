package dominio;

public class Wsdl {
	
	private String response_time;
	private String availability;
	private String throughput;
	private String successability;
	private String reliability;
	private String compliance;
	private String best_practice;
	private String latency;
	private String documentation;
	private String service_name;
	private String wsdl_address;
	
	
	public Wsdl (String response_time2, String availability2, String throughput2, String successability2, String reliability2, String compliance2, String best_practice2, String latency2, String documentation2, String sn, String address){
		this.response_time = response_time2;
		this.availability = availability2;
		this.throughput = throughput2;
		this.successability = successability2;
		this.reliability = reliability2;
		this.compliance = compliance2;
		this.best_practice = best_practice2;
		this.latency = latency2;
		this.documentation = documentation2;
		this.service_name = sn;
		this.wsdl_address = address;
	}



	public String getResponse_time() {
		return response_time;
	}


	public void setResponse_time(String response_time) {
		this.response_time = response_time;
	}


	public String getAvailability() {
		return availability;
	}


	public void setAvailability(String availability) {
		this.availability = availability;
	}


	public String getThroughput() {
		return throughput;
	}


	public void setThroughput(String throughput) {
		this.throughput = throughput;
	}


	public String getSuccessability() {
		return successability;
	}


	public void setSuccessability(String successability) {
		this.successability = successability;
	}


	public String getReliability() {
		return reliability;
	}


	public void setReliability(String reliability) {
		this.reliability = reliability;
	}


	public String getCompliance() {
		return compliance;
	}


	public void setCompliance(String compliance) {
		this.compliance = compliance;
	}


	public String getBest_practice() {
		return best_practice;
	}


	public void setBest_practice(String best_practice) {
		this.best_practice = best_practice;
	}


	public String getLatency() {
		return latency;
	}


	public void setLatency(String latency) {
		this.latency = latency;
	}


	public String getDocumentation() {
		return documentation;
	}


	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}


	public String getService_name() {
		return service_name;
	}


	public void setService_name(String service_name) {
		this.service_name = service_name;
	}


	public String getWsdl_address() {
		return wsdl_address;
	}


	public void setWsdl_address(String wsdl_address) {
		this.wsdl_address = wsdl_address;
	}
	
	
		
}
