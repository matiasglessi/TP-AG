package dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mgiamberardino.jnetic.Individual;

public class Chromosome implements Individual<Wsdl>{
	private List<Wsdl> genes;
	private Map<String, Integer> ponderaciones;
	
	public Chromosome (Map<String, Integer> ponderaciones){
		this(ponderaciones, new ArrayList<Wsdl>());
 	}
	
	public Chromosome (){
		this(new HashMap<>());
	}
	
	public Chromosome(Map<String, Integer> ponderaciones, List<Wsdl> genes){
		this.genes=genes;
		this.ponderaciones=ponderaciones;
	
	}
	
	public Map<String, Integer> getPonderaciones() {
		return ponderaciones;
	}

	public void setPonderaciones(Map<String, Integer> ponderaciones) {
		this.ponderaciones = ponderaciones;
	}

	public void addGen(Wsdl w){
		genes.add(w);
	}
	
	public void addGenPosition (Wsdl w, Integer i){
		genes.add(i, w);
	}
	
	public boolean equals(Object o){
		return this.equals(o);
	}
	
	public int length(){
		return genes.size();
	}
	
	public Wsdl getGen(int i){
		return genes.get(i);
	}

	
	private  double sumaPorAtributo(String atributo){
		return gens().stream().mapToDouble(values->values.getAtributte(atributo)).sum();		
	}
	

	private  Integer sumaPonderacion(Map<String, Integer> ponderacion) {
		return ponderacion.values().stream().map(Math::abs).reduce(0, Integer::sum);
	}
	
	@Override
	public List<Wsdl> gens() {
		return new ArrayList<>(genes);
	}

	@Override
	public Double getAptitude() {
		double suma = ponderaciones.entrySet().stream().mapToDouble(
				entry -> entry.getValue()*sumaPorAtributo(entry.getKey())).sum();
		Integer ponderacion = sumaPonderacion(ponderaciones);
		return null == ponderacion ? 0 : suma/ponderacion;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "Chromosome [getAptitude()=" + getAptitude() + "]";
	}
	
}
