package dominio;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.mgiamberardino.jnetic.Individual.Factory;

public class ChromosomeFactory implements Factory<Chromosome, Wsdl> {

	private Map<String, Integer> ponderaciones;
	private Map<String, List<Wsdl>> serviceInterface;
	private final static Random RANDOM = new Random(System.currentTimeMillis());
	public ChromosomeFactory(Map<String, Integer> ponderaciones, Map<String, List<Wsdl>> serviceInterface) {
		super();
		this.ponderaciones = ponderaciones;
		this.serviceInterface = serviceInterface;
	}

	@Override
	public Chromosome build(List<Wsdl> genes) {		
		return new Chromosome(ponderaciones, genes);
	}

	@Override
	public Wsdl buildGen(Integer position) {
		List<Wsdl> wsdls =  serviceInterface.entrySet().stream().collect(Collectors.toList()).get(position).getValue();
		return wsdls.get(RANDOM.nextInt(wsdls.size()));
	}

	@Override
	public Chromosome buildRandom() {
		List<Wsdl> genes = IntStream.range(0,serviceInterface.size())
							.boxed()
							.map(this::buildGen)
							.collect(Collectors.toList());
		Chromosome c = build(genes);
		System.out.println(c);
		return c;
	}

}
