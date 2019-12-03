package ES1_2019_EIC2_03.DefectsDetection.code;

import javax.swing.SwingUtilities;

import ES1_2019_EIC2_03.DefectsDetection.views.HomeGui;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater( new Runnable() {
			
			public void run() {
				HomeGui frame = new HomeGui(new ExcelExporter());
				frame.open();
			}
		});
	}
}
