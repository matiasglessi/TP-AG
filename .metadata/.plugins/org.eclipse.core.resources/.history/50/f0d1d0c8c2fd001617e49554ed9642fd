package dominio;

import java.util.HashMap;

public class FitnessFunction {
	
	public static double FunctionGoalWeighted8(Chromosome c, HashMap<String, Integer> ponderacion) {
		double suma = ponderacion.entrySet().stream().mapToDouble(
				entry -> entry.getValue()*sumaPorAtributo(c, entry.getKey())).sum();
		Integer ponderaciones = sumaPonderacion(ponderacion);
		return null == ponderaciones ? 0 : suma/ponderaciones;
	}

	
	public static double sumaPorAtributo(Chromosome c, String atributo){
		return c.getServices().stream().mapToDouble(values->values.getAtributte(atributo)).sum();		
	}
	

	public static Integer sumaPonderacion(HashMap<String, Integer> ponderacion) {
		return ponderacion.values().stream().reduce(0, Integer::sum);
	}

}
