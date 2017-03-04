package principal;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.mgiamberardino.jnetic.operators.Operators;
import com.mgiamberardino.jnetic.population.Evolution;
import com.mgiamberardino.jnetic.util.Conditions;
import com.mgiamberardino.jnetic.util.Selectors;

import dominio.Chromosome;
import dominio.ChromosomeFactory;
import dominio.ReadData;
import dominio.Wsdl;

public class Principal {
	
	public static final int SHELL_TRIM = SWT.CLOSE | SWT.TITLE | SWT.MIN | SWT.MAX | SWT.RESIZE;

	Shell shell;
	static Display display;
	Button btnGenerate;
	Label lblInfoGen;
	Label lblFitness;
	Spinner spinnerResponseTime;
	Spinner spinnerAvailability;
	Spinner spinnerThroughoput;
	Spinner spinnerSuccess;
	Spinner spinnerReliability;
	Spinner spinnerCompliance;
	Spinner spinnerBest;
	Spinner spinnerLatency;
	private Table tableResults;
	private String pathFile;
	private ReadData readData;
	private HashMap<String, Integer> ponderaciones;
	private static final Integer MAX_PONDERACION =10;
	private static final Integer MIN_PONDERACION =-10;
	private Chromosome c;
	private Evolution<Chromosome, Double> evol;
	


	
	public Principal(Display display){
		shell = new Shell(display, SWT.SHELL_TRIM & (~SWT.RESIZE));
		createContents();
		shell.open();
		shell.layout();
//		shell.setBackground(new Color(display, 192,192,192));
	
		shell.setText("gA - Diseño");
        shell.setSize(824, 460);Rectangle splashRect = shell.getBounds();
        Rectangle displayRect = display.getBounds();
        int x = (displayRect.width - splashRect.width) / 2;
        int y = (displayRect.height - splashRect.height) / 2;
        shell.setLocation(x, y);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}


	/**
	 * Create contents of the window.
	 */
	public void createContents() {
		
		GridLayout gridLayout = new GridLayout(12, false);
		shell.setLayout(gridLayout);
		
		Menu menuBar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menuBar);
		
		MenuItem mntmArchivo = new MenuItem(menuBar, SWT.CASCADE);
		mntmArchivo.setText("File");
	   
		Menu menu = new Menu(mntmArchivo);
		mntmArchivo.setMenu(menu);
		
		MenuItem mntmCargar = new MenuItem(menu, SWT.NONE);
		mntmCargar.setText("Load Workflow");
		mntmCargar.addSelectionListener(new cargarWorkflowSelectionListener());
		
		MenuItem mntmGuardar = new MenuItem(menu, SWT.NONE);
		mntmGuardar.setText("Save");
		mntmGuardar.addSelectionListener(new guardarWorkflowSelectionListener());
		
		
		new MenuItem(menu, SWT.SEPARATOR);
		
		MenuItem mntmSalir = new MenuItem(menu, SWT.NONE);
		mntmSalir.setText("Exit");
		mntmSalir.addSelectionListener(new salirSelectionListener());
		
		MenuItem mntmNewSubmenu = new MenuItem(menuBar, SWT.CASCADE);
		mntmNewSubmenu.setText("Help");
		
		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);
		
		MenuItem mntmAcerca = new MenuItem(menu_1, SWT.PUSH);
		mntmAcerca.setText("About ...");
		
		mntmAcerca.addSelectionListener(new SelectionListener() {
	        public void widgetSelected(SelectionEvent e) {
	          AcercaDe acercaDeWindow = new AcercaDe(shell, display);
	        }

	        public void widgetDefaultSelected(SelectionEvent e) {

	        }
	      });

		Composite compositePonderacion = new Composite(shell, SWT.BORDER);
		gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		compositePonderacion.setLayout(gridLayout);
		
		GridData gd_compositePonderacion = new GridData(SWT.LEFT, SWT.FILL, false,
				true, 1, 1);
		gd_compositePonderacion.horizontalSpan = 3;
		gd_compositePonderacion.widthHint = 200;
		gd_compositePonderacion.heightHint = 300; //236 antes
		
		compositePonderacion.setLayoutData(gd_compositePonderacion);		
		
		Label lblResponseTime = new Label(compositePonderacion, SWT.NONE);
		lblResponseTime.setText("Response Time");
		
		spinnerResponseTime = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerResponseTime.setMaximum(MAX_PONDERACION);
		spinnerResponseTime.setMinimum(MIN_PONDERACION);
		
		Label lblAvailabilty = new Label(compositePonderacion, SWT.NONE);
		lblAvailabilty.setText("Availabilty");
		
		spinnerAvailability = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerAvailability.setMaximum(MAX_PONDERACION);
		spinnerAvailability.setMinimum(MIN_PONDERACION);

				
		Label lblThroughoput = new Label(compositePonderacion, SWT.NONE);
		lblThroughoput.setText("Throughoput");
		
		spinnerThroughoput = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerThroughoput.setMaximum(MAX_PONDERACION);
		spinnerThroughoput.setMinimum(MIN_PONDERACION);
		
		Label lblSuccess = new Label(compositePonderacion, SWT.NONE);
		lblSuccess.setText("Successability");
		
		spinnerSuccess = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerSuccess.setMaximum(MAX_PONDERACION);
		spinnerSuccess.setMinimum(MIN_PONDERACION);
		
		Label lblReliability = new Label(compositePonderacion, SWT.NONE);
		lblReliability.setText("Reliability");
		
		spinnerReliability = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerReliability.setMaximum(MAX_PONDERACION);
		spinnerReliability.setMinimum(MIN_PONDERACION);
		
		Label lblCompliance = new Label(compositePonderacion, SWT.NONE);
		lblCompliance.setText("Compliance");
		
		spinnerCompliance = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerCompliance.setMaximum(MAX_PONDERACION);
		spinnerCompliance.setMinimum(MIN_PONDERACION);
		
		Label lblBest = new Label(compositePonderacion, SWT.NONE);
		lblBest.setText("Best Practice");
		
		spinnerBest = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerBest.setMaximum(MAX_PONDERACION);
		spinnerBest.setMinimum(MIN_PONDERACION);
		
		Label lblLatency = new Label(compositePonderacion, SWT.NONE);
		lblLatency.setText("Latency");
		
		spinnerLatency = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerLatency.setMaximum(MAX_PONDERACION);
		spinnerLatency.setMinimum(MIN_PONDERACION);
		
		tableResults = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_tableResults = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_tableResults.horizontalSpan = 9;
		gd_tableResults.widthHint = 431;
		gd_tableResults.heightHint = 239;
		tableResults.setLayoutData(gd_tableResults);
		tableResults.setHeaderVisible(true);
		tableResults.setLinesVisible(true);
		
		
		TableColumn colTipo = new TableColumn(tableResults, SWT.LEFT);
		colTipo.setWidth(120);
		colTipo.setText("Abstract Service");
		
		TableColumn colDNI = new TableColumn(tableResults, SWT.LEFT);
		colDNI.setWidth(126);
		colDNI.setText("Concrete Service");
		
		TableColumn colApellido = new TableColumn(tableResults, SWT.LEFT);
		colApellido.setWidth(800);
		colApellido.setText("URL");
		
		lblInfoGen = new Label(shell, SWT.NONE);
		lblInfoGen.setText("Amount of generations: -");
		GridData gd_labelFit = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_labelFit.widthHint = 200;
		lblInfoGen.setLayoutData(gd_labelFit);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button btnRestartValues = new Button(shell, SWT.PUSH);
		btnRestartValues.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false, 1, 1));
		btnRestartValues.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		    	restartValues();
		    }
		});
		btnRestartValues.setText("Restart Values");
		
		btnGenerate = new Button(shell, SWT.PUSH);
		btnGenerate.setEnabled(false);
		GridData gd_btnGenerate = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_btnGenerate.widthHint = 86;
		gd_btnGenerate.horizontalSpan = 2; 
		btnGenerate.setLayoutData(gd_btnGenerate);
		
				btnGenerate.addSelectionListener(new SelectionAdapter() {
		            @Override
		            public void widgetSelected(SelectionEvent e) {
		            	if(pathFile!=null){
		            		saveValuesPonderation();
		            		generateSolution();
		            	}
		            }
		        });
				btnGenerate.setText("Generate");
		
		lblFitness = new Label(shell, SWT.NONE);
		lblFitness.setText("Fitness number: -");
		GridData gd_labelFitn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_labelFitn.widthHint = 200;
		lblFitness.setLayoutData(gd_labelFitn);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
	
	}
	
	
	
	public void restartValues()
	{
		spinnerAvailability.setSelection(0);
		spinnerLatency.setSelection(0);
		spinnerResponseTime.setSelection(0);
		spinnerThroughoput.setSelection(0);
		spinnerSuccess.setSelection(0);
		spinnerReliability.setSelection(0);
		spinnerCompliance.setSelection(0);
		spinnerBest.setSelection(0);
	}
	
	private void saveValuesPonderation(){
		ponderaciones = new HashMap<>();
		
        ponderaciones.put(Wsdl.RESPONSE_TIME, spinnerResponseTime.getSelection());
        ponderaciones.put(Wsdl.AVAILABILITY, spinnerAvailability.getSelection());
        ponderaciones.put(Wsdl.THROUGHPUT, spinnerThroughoput.getSelection());
        ponderaciones.put(Wsdl.SUCCESSABILITY, spinnerSuccess.getSelection());
        ponderaciones.put(Wsdl.RELIABILITY, spinnerReliability.getSelection());
        ponderaciones.put(Wsdl.COMPLIANCE, spinnerCompliance.getSelection());
        ponderaciones.put(Wsdl.BEST_PRACTICE, spinnerBest.getSelection());
        ponderaciones.put(Wsdl.LATENCY, spinnerLatency.getSelection());
        
	}
	
	
	public void generateSolution(){
		
		if(areAllZeroValues()){
		    MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
		    messageBox.setMessage("At least one attribute must be ponderated.");
		    messageBox.open();
		}
		else{
			tableResults.removeAll();
			System.out.println("Cromosoma generado: ");
	        ChromosomeFactory chromosomeFactory = new ChromosomeFactory(ponderaciones, ReadData.getWsdlInterface());
	        evol = Evolution.create(chromosomeFactory, 250, 0.01)
	             	.mutator(Operators.factory(chromosomeFactory).basicMutatorBuilder(0.05).build())
	        		.selector(Selectors.binaryTournament(0.9, 0.5))
	             	.evolveUntil(Conditions.converge(0.001));
			c = evol.best();
	        for (int i = 0; i < c.length(); i++) {
		       	 System.out.println(c.getGen(i).getService_name());
		       	 TableItem item = new TableItem(tableResults, SWT.NONE);
					 item.setText(new String[] {
								c.getGen(i).getInterfaz(),
								c.getGen(i).getService_name(),
								c.getGen(i).getWsdl_address() });
			}
	        lblInfoGen.setText("Amount of generations: " + evol.currentGeneration() );
	        lblFitness.setText("Fitness number: " + c.getAptitude());
		}
	}
	
	  class salirSelectionListener implements SelectionListener {
		    public void widgetSelected(SelectionEvent event) {
		    	shell.close();
		    }

		    public void widgetDefaultSelected(SelectionEvent event) {}
	  }
	  
	  class cargarWorkflowSelectionListener implements SelectionListener {
		    public void widgetSelected(SelectionEvent event) {
				FileDialog fdialog = new FileDialog(shell);
				fdialog.setText("Load Workflow");
				fdialog.setFilterPath("sources/");
		        String[] filterExt = { "*.csv" };
		        fdialog.setFilterExtensions(filterExt);
				pathFile = fdialog.open();
				if (pathFile != null) {
					readData = new ReadData();
					readData.ReadDataCsv(pathFile);
					btnGenerate.setEnabled(true);
				}
			}		    

		    public void widgetDefaultSelected(SelectionEvent event) {}
	  }
	  

	  
	  class guardarWorkflowSelectionListener implements SelectionListener {
		    public void widgetSelected(SelectionEvent event) {
				if ((pathFile != null) && (c != null)){
					FileDialog fdialog = new FileDialog(shell, SWT.SAVE);
					fdialog.setText("Save as...");
					fdialog.setFilterNames(new String[] { "Batch Files", "All Files (*.*)" });
					fdialog.setFilterExtensions(new String[] { "*.bat", "*.*" });
					fdialog.setFilterPath("sources/"); // Windows path
					fdialog.setFileName("*.txt");
					System.out.println("Save to: " + fdialog.open());	        
					String name = fdialog.getFileName();
					if(name != null){
						readData.writeFichero(name, c, evol.currentGeneration());
					}
				}
				
		}		    

		    public void widgetDefaultSelected(SelectionEvent event) {}
	  }


	  
	  private boolean areAllZeroValues()
	  {
		  	if(spinnerResponseTime.getSelection() == 0){
		  		if (spinnerAvailability.getSelection() == 0) {
		  			if (spinnerThroughoput.getSelection() == 0) {
		  				if(spinnerSuccess.getSelection() == 0) {
		  					if (spinnerReliability.getSelection() == 0){
		  						if(spinnerCompliance.getSelection() == 0){
		  							if(spinnerBest.getSelection() == 0){
		  								if(spinnerLatency.getSelection()==0){
		  									return true;
		  								}
		  							}
		  						}
		  					}
		  				}
		  			}
		  		}
		  	}
		  return false;
	  }
	  
	  
	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//LoadLibrary libraryLoader = new LoadLibrary();
		//libraryLoader.loadSWT();
       
		display = new Display();
        new Principal(display);
        display.dispose();
	}
}
