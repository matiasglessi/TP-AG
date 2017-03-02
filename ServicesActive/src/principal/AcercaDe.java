package principal;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class AcercaDe {
	private Text txtLosAlgoritmosGenticos;
	private Shell acercaDe;

	public AcercaDe(Shell parent, Display d){
		acercaDe = new Shell(parent);
		acercaDe.setSize(468, 300);
		Rectangle splashRect = acercaDe.getBounds();
        Rectangle displayRect = d.getBounds();
        int x = (displayRect.width - splashRect.width) / 2;
        int y = (displayRect.height - splashRect.height) / 2;
        acercaDe.setLocation(x, y);
		
		Button btnOk = new Button(acercaDe, SWT.NONE);
		btnOk.setBounds(364, 240, 94, 28);
		btnOk.setText("OK");
		btnOk.addSelectionListener(new SelectionAdapter() {
		    @Override
		    public void widgetSelected(SelectionEvent e) {
		    	okButtonTapped();
		    }
		});
		
		Label lblNewLabel = new Label(acercaDe, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Verdana", 18, SWT.BOLD));
		lblNewLabel.setBounds(10, 10, 611, 28);
		lblNewLabel.setText("Composición Automática de Servicios Web");
		Label label = new Label(acercaDe, SWT.NONE);
		label.setText("Trabajo Final - Diseño de Sistemas de Software I");
		label.setFont(SWTResourceManager.getFont("Verdana", 13, SWT.NORMAL));
		label.setBounds(10, 72, 512, 28);
		
		Label label_1 = new Label(acercaDe, SWT.NONE);
		label_1.setText("utilizando Algoritmos Genéticos");
		label_1.setFont(SWTResourceManager.getFont("Verdana", 18, SWT.BOLD));
		label_1.setBounds(10, 38, 611, 28);
		
		txtLosAlgoritmosGenticos = new Text(acercaDe,SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);

		txtLosAlgoritmosGenticos.setEditable(false);
		txtLosAlgoritmosGenticos.setLayoutData(new GridData(GridData.FILL_BOTH));
		txtLosAlgoritmosGenticos.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.NORMAL));
		txtLosAlgoritmosGenticos.setText("	Los algoritmos genéticos, o algoritmos evolutivos, son una técnica de búsqueda estocástica guiada, inspirada por los mecanismos de la selección natural, la genética y la evolución.");
		txtLosAlgoritmosGenticos.setBounds(10, 110, 448, 64);
		Device device = Display.getCurrent();
		txtLosAlgoritmosGenticos.setBackground(new Color(device, 233, 233, 233));
		
		Label lblAutores = new Label(acercaDe, SWT.NONE);
		lblAutores.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.BOLD | SWT.ITALIC));
		lblAutores.setBounds(10, 195, 59, 14);
		lblAutores.setText("Autores:");
		
		Label lblDelRioValeria = new Label(acercaDe, SWT.NONE);
		lblDelRioValeria.setBounds(10, 223, 110, 14);
		lblDelRioValeria.setText("del Rio, Valeria");
		
		Label lblValeriadlriogmailcom = new Label(acercaDe, SWT.NONE);
		lblValeriadlriogmailcom.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.ITALIC));
		lblValeriadlriogmailcom.setText("valeriadlrio@gmail.com");
		lblValeriadlriogmailcom.setBounds(168, 223, 158, 17);
		
		Label lblGlessiMatiasAlejandro = new Label(acercaDe, SWT.NONE);
		lblGlessiMatiasAlejandro.setText("Glessi, Matias Alejandro");
		lblGlessiMatiasAlejandro.setBounds(10, 243, 130, 14);
		
		Label lblMatiasglessigmailcom = new Label(acercaDe, SWT.NONE);
		lblMatiasglessigmailcom.setText("matiasglessi@gmail.com");
		lblMatiasglessigmailcom.setFont(SWTResourceManager.getFont(".SF NS Text", 11, SWT.ITALIC));
		lblMatiasglessigmailcom.setBounds(168, 240, 158, 23);
		acercaDe.open();
	}
	
	
	private void okButtonTapped(){
		acercaDe.close();
	}
}
