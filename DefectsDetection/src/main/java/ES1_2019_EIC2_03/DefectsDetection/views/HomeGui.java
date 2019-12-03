package ES1_2019_EIC2_03.DefectsDetection.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.TitledBorder;

import ES1_2019_EIC2_03.DefectsDetection.code.ExcelExporter;

import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.AbstractListModel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Component;
import javax.swing.border.LineBorder;

public class HomeGui extends JFrame {

	private JPanel contentPane;
	private JMenuItem mntmImportExcelFile;
	//private boolean excelExporterOpen;
	private JTextField txtFTrueIsLong;
	private JTextField txtFIsFeatureEnvy;
	private JTextField txtFIPlasmaIsLong;
	private JTextField txtFPMDIsLong;
	private ExcelExporter excelExporter;
	private ExcelReaderGui excelReader;
	private JButton btnGetEvaluation;

	
	public HomeGui(ExcelExporter excelExporter) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		setTitle("DefectsDetection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 687);
		 
		
		this.excelReader= new ExcelReaderGui(excelExporter);
		this.excelExporter = excelExporter;
		//this.excelExporterOpen = false;
		
		addComponents();
		creatEvents();
	}
	
	private void addComponents() {
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmSave = new JMenuItem("Save ");
		mnNewMenu.add(mntmSave);
		
		mntmImportExcelFile = new JMenuItem("Import and View Excel File");

		mnNewMenu.add(mntmImportExcelFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnNewMenu.add(mntmExit);
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmCreatNewRule = new JMenuItem("Creat new rule");
		mnNewMenu_1.add(mntmCreatNewRule);
		
		JMenuItem mntmEditExistingRule = new JMenuItem("Edit existing rule");
		mnNewMenu_1.add(mntmEditExistingRule);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		JPanel pnliPlamaPMD = new JPanel();
		pnliPlamaPMD.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel pnlMethodID = new JPanel();
		pnlMethodID.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel pnlCostumTools = new JPanel();
		pnlCostumTools.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblCostumTools = new JLabel("Costum Tools");
		lblCostumTools.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout gl_pnlCostumTools = new GroupLayout(pnlCostumTools);
		gl_pnlCostumTools.setHorizontalGroup(
			gl_pnlCostumTools.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCostumTools.createSequentialGroup()
					.addGap(183)
					.addComponent(lblCostumTools, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
		);
		gl_pnlCostumTools.setVerticalGroup(
			gl_pnlCostumTools.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCostumTools.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCostumTools, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(424, Short.MAX_VALUE))
		);
		pnlCostumTools.setLayout(gl_pnlCostumTools);
		
		JPanel pnlTrueEvaluation = new JPanel();
		pnlTrueEvaluation.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(pnlTrueEvaluation, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnliPlamaPMD, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnlCostumTools, GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(378)
							.addComponent(pnlMethodID, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
							.addGap(437)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlMethodID, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlCostumTools, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnlTrueEvaluation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pnliPlamaPMD, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblTrueEvaluation = new JLabel("True Evalutaion");
		lblTrueEvaluation.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblisLongMethod = new JLabel("is_long_method");
		lblisLongMethod.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtFTrueIsLong = new JTextField();
		txtFTrueIsLong.setEditable(false);
		txtFTrueIsLong.setColumns(10);
		
		JLabel lblisFeatureEnvy = new JLabel("is_feature_envy");
		lblisFeatureEnvy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtFIsFeatureEnvy = new JTextField();
		txtFIsFeatureEnvy.setEditable(false);
		txtFIsFeatureEnvy.setColumns(10);
		GroupLayout gl_pnlTrueEvaluation = new GroupLayout(pnlTrueEvaluation);
		gl_pnlTrueEvaluation.setHorizontalGroup(
			gl_pnlTrueEvaluation.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlTrueEvaluation.createSequentialGroup()
					.addGap(55)
					.addComponent(lblTrueEvaluation, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(69, Short.MAX_VALUE))
				.addGroup(gl_pnlTrueEvaluation.createSequentialGroup()
					.addContainerGap(35, Short.MAX_VALUE)
					.addGroup(gl_pnlTrueEvaluation.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlTrueEvaluation.createSequentialGroup()
							.addComponent(lblisFeatureEnvy, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(txtFIsFeatureEnvy, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTrueEvaluation.createSequentialGroup()
							.addComponent(lblisLongMethod, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(txtFTrueIsLong, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
					.addGap(29))
		);
		gl_pnlTrueEvaluation.setVerticalGroup(
			gl_pnlTrueEvaluation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlTrueEvaluation.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTrueEvaluation, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(90)
					.addGroup(gl_pnlTrueEvaluation.createParallelGroup(Alignment.LEADING)
						.addComponent(lblisLongMethod, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlTrueEvaluation.createSequentialGroup()
							.addGap(9)
							.addComponent(txtFTrueIsLong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(119)
					.addGroup(gl_pnlTrueEvaluation.createParallelGroup(Alignment.LEADING)
						.addComponent(lblisFeatureEnvy, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlTrueEvaluation.createSequentialGroup()
							.addGap(5)
							.addComponent(txtFIsFeatureEnvy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(153, Short.MAX_VALUE))
		);
		pnlTrueEvaluation.setLayout(gl_pnlTrueEvaluation);
		
		JLabel lblMethodId = new JLabel("Method ID");
		lblMethodId.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JSpinner spnrMethodID = new JSpinner();
		spnrMethodID.setModel(new SpinnerNumberModel(1, 1, 420, 1));
		
		btnGetEvaluation = new JButton("Get Evaluation");

		btnGetEvaluation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_pnlMethodID = new GroupLayout(pnlMethodID);
		gl_pnlMethodID.setHorizontalGroup(
			gl_pnlMethodID.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlMethodID.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_pnlMethodID.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlMethodID.createSequentialGroup()
							.addGap(10)
							.addComponent(spnrMethodID, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblMethodId))
					.addGap(18)
					.addComponent(btnGetEvaluation, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_pnlMethodID.setVerticalGroup(
			gl_pnlMethodID.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlMethodID.createSequentialGroup()
					.addGroup(gl_pnlMethodID.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlMethodID.createSequentialGroup()
							.addGap(22)
							.addComponent(lblMethodId, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spnrMethodID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlMethodID.createSequentialGroup()
							.addGap(30)
							.addComponent(btnGetEvaluation, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		pnlMethodID.setLayout(gl_pnlMethodID);
		
		JLabel lblIPlasma = new JLabel("iPlasma");
		lblIPlasma.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblPmd = new JLabel("PMD");
		lblPmd.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblisLongMethod2 = new JLabel("is_long_method");
		lblisLongMethod2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtFIPlasmaIsLong = new JTextField();
		txtFIPlasmaIsLong.setEditable(false);
		txtFIPlasmaIsLong.setColumns(10);
		
		JLabel lblisFeatureEnvy2 = new JLabel("is_long_method");
		lblisFeatureEnvy2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtFPMDIsLong = new JTextField();
		txtFPMDIsLong.setEditable(false);
		txtFPMDIsLong.setColumns(10);
		
		JButton btnToolQIPlasma = new JButton("Tool Quality");
		btnToolQIPlasma.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnToolQPMD = new JButton("Tool Quality");
		btnToolQPMD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnCompareTools = new JButton("Compare both tools");
		btnCompareTools.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_pnliPlamaPMD = new GroupLayout(pnliPlamaPMD);
		gl_pnliPlamaPMD.setHorizontalGroup(
			gl_pnliPlamaPMD.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnliPlamaPMD.createSequentialGroup()
					.addGap(64)
					.addGroup(gl_pnliPlamaPMD.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnliPlamaPMD.createSequentialGroup()
							.addComponent(lblIPlasma, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnToolQIPlasma))
						.addGroup(gl_pnliPlamaPMD.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_pnliPlamaPMD.createSequentialGroup()
								.addComponent(lblisFeatureEnvy2, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(txtFPMDIsLong, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_pnliPlamaPMD.createSequentialGroup()
								.addComponent(lblisLongMethod2, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(txtFIPlasmaIsLong, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(67, Short.MAX_VALUE))
				.addGroup(gl_pnliPlamaPMD.createSequentialGroup()
					.addContainerGap(92, Short.MAX_VALUE)
					.addComponent(lblPmd, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnToolQPMD, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addGap(80))
				.addGroup(Alignment.LEADING, gl_pnliPlamaPMD.createSequentialGroup()
					.addGap(79)
					.addComponent(btnCompareTools, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(82, Short.MAX_VALUE))
		);
		gl_pnliPlamaPMD.setVerticalGroup(
			gl_pnliPlamaPMD.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnliPlamaPMD.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnliPlamaPMD.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIPlasma, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnToolQIPlasma))
					.addGap(18)
					.addGroup(gl_pnliPlamaPMD.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblisLongMethod2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFIPlasmaIsLong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(82)
					.addGroup(gl_pnliPlamaPMD.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnToolQPMD, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPmd, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_pnliPlamaPMD.createParallelGroup(Alignment.LEADING)
						.addComponent(lblisFeatureEnvy2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnliPlamaPMD.createSequentialGroup()
							.addGap(9)
							.addComponent(txtFPMDIsLong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
					.addComponent(btnCompareTools, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(56))
		);
		pnliPlamaPMD.setLayout(gl_pnliPlamaPMD);
		contentPane.setLayout(gl_contentPane);
		
	}
	
	private void creatEvents() {
		
		mntmImportExcelFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					excelReader.setModal(true);
					excelReader.open();
			}
		});
		
		btnGetEvaluation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.gc();
			}
		});
		
	}
	
	public void open() {
		setVisible(true);
	}
}

