package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.TouchEvent;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import swing2swt.layout.FlowLayout;


public class Principal {
	protected Shell shell;
	Spinner spinnerResponseTime;
	Spinner spinnerAvailability;
	Spinner spinnerThroughoput;
	Spinner spinnerSuccess;
	Spinner spinnerReliability;
	Spinner spinnerCompliance;
	Spinner spinnerBest;
	Spinner spinnerLatency;


	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//Creo la BD
			Principal window = new Principal();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(1169, 650);
		shell.setText("gA - Diseño");
		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
			}
		});
		shell.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Menu menuBar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menuBar);
		
		MenuItem menuItemFile = new MenuItem(menuBar, SWT.NONE);
		menuItemFile.setText("File");
		
		Label lblResponseTime = new Label(shell, SWT.NONE);
		lblResponseTime.setText("Response Time");
		
		spinnerResponseTime = new Spinner(shell, SWT.BORDER);
		spinnerResponseTime.setMaximum(10);
		
		Label lblAvailabilty = new Label(shell, SWT.NONE);
		lblAvailabilty.setText("Availabilty");
		
		spinnerAvailability = new Spinner(shell, SWT.BORDER);
		spinnerAvailability.setMaximum(10);
		
		Label lblThroughoput = new Label(shell, SWT.NONE);
		lblThroughoput.setText("Throughoput");
		
		spinnerThroughoput = new Spinner(shell, SWT.BORDER);
		spinnerThroughoput.setMaximum(10);
		
		Label lblSuccess = new Label(shell, SWT.NONE);
		lblSuccess.setText("Successability");
		
		spinnerSuccess = new Spinner(shell, SWT.BORDER);
		spinnerSuccess.setMaximum(10);
		
		Label lblReliability = new Label(shell, SWT.NONE);
		lblReliability.setText("Reliability");
		
		spinnerReliability = new Spinner(shell, SWT.BORDER);
		spinnerReliability.setMaximum(10);
		
		Label lblCompliance = new Label(shell, SWT.NONE);
		lblCompliance.setText("Compliance");
		
		spinnerCompliance = new Spinner(shell, SWT.BORDER);
		spinnerCompliance.setMaximum(10);
		
		Label lblBest = new Label(shell, SWT.NONE);
		lblBest.setText("Best Practice");
		
		spinnerBest = new Spinner(shell, SWT.BORDER);
		spinnerBest.setMaximum(10);
		
		Label lblLatency = new Label(shell, SWT.NONE);
		lblLatency.setText("Latency");
		
		spinnerLatency = new Spinner(shell, SWT.BORDER);
		spinnerLatency.setMaximum(10);
		
		Button btnRestartValues = new Button(shell, SWT.NONE);
		btnRestartValues.addTouchListener(new TouchListener() {
			public void touch(TouchEvent arg0) {
				restartValues();
			}
		});
		btnRestartValues.setText("Restart Values");
		
		Button btnGenerate = new Button(shell, SWT.NONE);
		btnGenerate.addTouchListener(new TouchListener() {
			public void touch(TouchEvent arg0) {
				updateValues();
			}
		});
		btnGenerate.setText("Generate");
		
		System.out.println("ARRANCO?");
	
	}
	
	public void restartValues()
	{
		System.out.println("Restart");
		
		spinnerAvailability.setSelection(0);
		spinnerLatency.setSelection(0);
		spinnerResponseTime.setSelection(0);
		spinnerThroughoput.setSelection(0);
		spinnerSuccess.setSelection(0);
		spinnerReliability.setSelection(0);
		spinnerCompliance.setSelection(0);
		spinnerBest.setSelection(0);
	}
	
	public void updateValues(){
		
	}

}
