package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
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

	
	public Principal(Display display){
		shell = new Shell(display, SWT.SHELL_TRIM & (~SWT.RESIZE));
		createContents();
		shell.open();
		shell.layout();

		shell.setText("gA - Diseño");
        shell.setSize(724, 368);
        shell.setLocation(300, 300);
        
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
		
		GridLayout gridLayout = new GridLayout(11, false);
		shell.setLayout(gridLayout);
		/*
		Menu menuBar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menuBar);
		
		MenuItem mntmArchivo = new MenuItem(menuBar, SWT.CASCADE);
		mntmArchivo.setText("Archivo");
		
		Menu menu = new Menu(mntmArchivo);
		mntmArchivo.setMenu(menu);
		
		MenuItem mntmCargar = new MenuItem(menu, SWT.NONE);
		mntmCargar.setText("Cargar Workflow");
		
		new MenuItem(menu, SWT.SEPARATOR);
		
		MenuItem mntmSalir = new MenuItem(menu, SWT.NONE);
		mntmSalir.setText("Salir");
		
		MenuItem mntmAcercaDe = new MenuItem(menuBar, SWT.PUSH);
		mntmAcercaDe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println("HOLA");
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				System.out.println("HOLA");

			}
		});
		mntmAcercaDe.setText("Acerca de");
		
		mntmAcercaDe.addListener(SWT.PUSH, new Listener() {
			
			public void handleEvent(Event e) {
	          System.out.println("Select All");
	        }
	      });
		*/		
		Button btnLoadWorkflow = new Button(shell, SWT.NONE);
		btnLoadWorkflow.setText("Load Workflow");
		btnLoadWorkflow.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fdialog = new FileDialog(shell);
				fdialog.setText("Load Workflow");
				fdialog.setFilterPath("sources/");
		        String[] filterExt = { "*.csv" };
		        fdialog.setFilterExtensions(filterExt);
				pathFile = fdialog.open();
				if (pathFile != null) {
					readData = new ReadData();
					readData.ReadDataCsv(pathFile);
				}

			}
		});
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		
		Composite compositePonderacion = new Composite(shell, SWT.BORDER);
		gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		compositePonderacion.setLayout(gridLayout);
		
		GridData gd_compositePonderacion = new GridData(SWT.LEFT, SWT.FILL, false,
				true, 1, 1);
		gd_compositePonderacion.horizontalSpan = 2;
		gd_compositePonderacion.widthHint = 200;
		gd_compositePonderacion.heightHint = 236;
		
		compositePonderacion.setLayoutData(gd_compositePonderacion);		
		
		Label lblResponseTime = new Label(compositePonderacion, SWT.NONE);
		lblResponseTime.setText("Response Time");
		
		spinnerResponseTime = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerResponseTime.setMaximum(10);
		
		Label lblAvailabilty = new Label(compositePonderacion, SWT.NONE);
		lblAvailabilty.setText("Availabilty");
		
		spinnerAvailability = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerAvailability.setMaximum(10);		
				
		Label lblThroughoput = new Label(compositePonderacion, SWT.NONE);
		lblThroughoput.setText("Throughoput");
		
		spinnerThroughoput = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerThroughoput.setMaximum(10);
		
		Label lblSuccess = new Label(compositePonderacion, SWT.NONE);
		lblSuccess.setText("Successability");
		
		spinnerSuccess = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerSuccess.setMaximum(10);
		
		Label lblReliability = new Label(compositePonderacion, SWT.NONE);
		lblReliability.setText("Reliability");
		
		spinnerReliability = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerReliability.setMaximum(10);
		
		Label lblCompliance = new Label(compositePonderacion, SWT.NONE);
		lblCompliance.setText("Compliance");
		
		spinnerCompliance = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerCompliance.setMaximum(10);
		
		Label lblBest = new Label(compositePonderacion, SWT.NONE);
		lblBest.setText("Best Practice");
		
		spinnerBest = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerBest.setMaximum(10);
		
		Label lblLatency = new Label(compositePonderacion, SWT.NONE);
		lblLatency.setText("Latency");
		
		spinnerLatency = new Spinner(compositePonderacion, SWT.BORDER);
		spinnerLatency.setMaximum(10);
		
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
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		button.setText("?");
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
		
		Button btnGenerate = new Button(shell, SWT.PUSH);
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
		tableResults.removeAll();
		System.out.println("Cromosoma generado: ");
        ChromosomeFactory chromosomeFactory = new ChromosomeFactory(ponderaciones, ReadData.getWsdlInterface());
		Chromosome c = Evolution.create(chromosomeFactory, 250, 0.01)
             	.mutator(Operators.factory(chromosomeFactory).basicMutatorBuilder(0.05).build())
        		.selector(Selectors.binaryTournament(0.9, 0.5))
             	.evolveUntil(Conditions.converge(0.001, Chromosome.class).or(Conditions.after(150)))
            	.best();
        for (int i = 0; i < c.length(); i++) {
       	 System.out.println(c.getGen(i).getService_name());
       	 TableItem item = new TableItem(tableResults, SWT.NONE);
			 item.setText(new String[] {
						c.getGen(i).getInterfaz(),
						c.getGen(i).getService_name(),
						c.getGen(i).getWsdl_address() });
			}
	}

	
	
	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();
        new Principal(display);
        display.dispose();
	}
}