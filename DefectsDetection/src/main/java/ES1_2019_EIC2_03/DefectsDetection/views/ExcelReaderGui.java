package ES1_2019_EIC2_03.DefectsDetection.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ES1_2019_EIC2_03.DefectsDetection.code.ExcelExporter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class ExcelReaderGui extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableExcel;
	private String[][] data;
	
	/**
	 * Create the dialog.
	 */
	
	public ExcelReaderGui(String[][] data) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		setTitle("View Excel File");
		setBounds(100, 100, 1059, 680);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		this.data = data;
		
		addComponents();
		
	}
	
	private void addComponents() {
		
		JScrollPane scrollPaneExcel = new JScrollPane();
		
		JLabel lblExcelViewer = new JLabel("Excel Viewer");
		lblExcelViewer.setFont(new Font("Tahoma", Font.BOLD, 17));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addGap(424)
					.addComponent(lblExcelViewer, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
					.addGap(502))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneExcel, GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(29)
					.addComponent(lblExcelViewer, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
					.addGap(26)
					.addComponent(scrollPaneExcel, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
					.addGap(50))
		);
		
		//ExcelExporter excelExporter = new ExcelExporter();
		tableExcel = new JTable();

		tableExcel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableExcel.setModel(new DefaultTableModel(data,
				new String[] {"MethodID", "package", "class", "method", "LOC", "CYCLO", "ATDF", "LAA", "is_long_method", "iPlasma", "PMD", "is_feature_envy"}));
		tableExcel.getColumnModel().getColumn(1).setPreferredWidth(258);
		tableExcel.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableExcel.getColumnModel().getColumn(3).setPreferredWidth(150);
		scrollPaneExcel.setViewportView(tableExcel);
		contentPanel.setLayout(gl_contentPanel);
		
	}
	
	public void open() {
		setVisible(true);
	}
}