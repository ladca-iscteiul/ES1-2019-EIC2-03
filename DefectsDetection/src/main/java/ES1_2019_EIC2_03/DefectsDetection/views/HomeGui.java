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
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import ES1_2019_EIC2_03.DefectsDetection.code.CostumRule;
import ES1_2019_EIC2_03.DefectsDetection.code.DefectDetectionProgram;
import ES1_2019_EIC2_03.DefectsDetection.code.Defects;
import ES1_2019_EIC2_03.DefectsDetection.code.ExcelExporter;
import bsh.EvalError;
import bsh.Interpreter;

import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
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
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;

import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class HomeGui extends JFrame {
	
	//Colums in Excel
	private static final int LOC = 4;
	private static final int CYCLO = 5;
	private static final int ATFD = 6;
	private static final int LAA = 7;
	private static final int IS_LONG_METHOD = 8;
	private static final int IPLASMA = 9;
	private static final int PMD = 10;
	private static final int IS_FEATURE_ENVY = 11;
	
	//Atributes
	private ExcelExporter excelExporter;
	private DefectDetectionProgram iplasma;
	private DefectDetectionProgram pmd;
	private ExcelReaderGui excelReader;
	private CreatRuleGUI newRuleViewer;
	private DefaultListModel<CostumRule> customLM = new DefaultListModel<CostumRule>();	
	private DefaultListModel<CostumRule> customFE = new DefaultListModel<CostumRule>();	

	//Gui Componets
	private JPanel contentPane;
	private JMenuItem mntmImportExcelFile;
	private JTextField txtFTrueIsLong;
	private JTextField txtFTrueIsFeature;
	private JTextField txtFIPlasmaIsLong;
	private JTextField txtFPMDIsLong;
	private JButton btnGetEvaluation;
	private JButton btnToolQPMD;
	private JButton btnToolQCLongMethod;
	private JButton btnToolQCFeatureEnvy;
	private JTextField txtFCostumIsLong;
	private JTextField txtFCostumIsFeature;
	private JMenuItem mntmCreatNewLMRule;
	private JMenuItem mntmCreatNewFERule;
	private JSpinner spnrMethodID;
	private JButton btnToolQIPlasma;
	private JList listCLongMethod;
	private JList listCFeatureEnvy;
	private JMenuItem mntmExit;
	private JMenuItem mntmEditExistingLMR;
	private JMenuItem mntmEditExistingFER;

	
	public HomeGui() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		setTitle("DefectsDetection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 687);

		this.excelExporter = ExcelExporter.getInstance();
		
		this.excelReader= new ExcelReaderGui(excelExporter.dataToMatrix());
		this.newRuleViewer = new CreatRuleGUI();
		
		
		
		getProgramStats();
		addComponents();
		creatEvents();
	}
	
	//Scanna o ficheiro excel e recolhe informações sobre a qualidade dos programas (iPlasma e PMD)
	private void getProgramStats() {
		int iplasma_dci=0;
		int iplasma_dii=0;
		int iplasma_adci=0;
		int iplasma_adii=0;
		int pmd_dci=0;
		int pmd_dii=0;
		int pmd_adci=0;
		int pmd_adii=0;

		for (int id = 1; id <= excelExporter.NumRows(); id++) {

			String data[] = excelExporter.getLine(id).split("<-->");

			if (data[IS_LONG_METHOD].equals("true") && data[IPLASMA].equals("true")) {
				iplasma_dci++;
			}else if (data[IS_LONG_METHOD].equals("false") && data[IPLASMA].equals("true")) {
				iplasma_dii++;
			}else if (data[IS_LONG_METHOD].equals("false") && data[IPLASMA].equals("false")) {
				iplasma_adci++;
			}else if (data[IS_LONG_METHOD].equals("true") && data[IPLASMA].equals("false")) {
				iplasma_adii++;
			}

			if (data[IS_LONG_METHOD].equals("true") && data[PMD].equals("true")) {
				pmd_dci++;
			}else if (data[IS_LONG_METHOD].equals("false") && data[PMD].equals("true")) {
				pmd_dii++;
			}else if (data[IS_LONG_METHOD].equals("false") && data[PMD].equals("false")) {
				pmd_adci++;
			}else if (data[IS_LONG_METHOD].equals("true") && data[PMD].equals("false")) {
				pmd_adii++;
			}
		}
		iplasma = new DefectDetectionProgram(iplasma_dci, iplasma_dii, iplasma_adci, iplasma_adii);
		pmd = new DefectDetectionProgram(pmd_dci, pmd_dii, pmd_adci, pmd_adii);
	}
	
	public void open() {
		setVisible(true);
	}
	
	private void getCustomRuleStats(Defects defects) {
		
		Interpreter interpreter = new Interpreter();
		int index;
		int dci=0;
		int dii=0;
		int adci=0;
		int adii=0;
		
		if(defects.equals(Defects.LONG_METHOD)) {
			
			CostumRule rule = (CostumRule) listCLongMethod.getSelectedValue();
			index = customLM.indexOf(rule);

			for (int id = 1; id <= excelExporter.NumRows(); id++) {

				String data[] = excelExporter.getLine(id).split("<-->");
				String code = translateRuleToJavaCode(id, rule.getRule());

				try {
					if (data[IS_LONG_METHOD].equals("true") && ((Boolean) interpreter.eval(code)).equals(true)) {
						dci++;
					}else if (data[IS_LONG_METHOD].equals("false") && ((Boolean) interpreter.eval(code)).equals(true)) {
						dii++;
					}else if (data[IS_LONG_METHOD].equals("false") && ((Boolean) interpreter.eval(code)).equals(false)) {
						adci++;
					}else if (data[IS_LONG_METHOD].equals("true") && ((Boolean) interpreter.eval(code)).equals(false)) {
						adii++;
					}
				} catch (EvalError e) {
					e.printStackTrace();
				}
			}
			customLM.get(index).setStats(dci, dii, adci, adii);
		}
		
		if(defects.equals(Defects.FEATURE_ENVY)) {
			CostumRule rule = (CostumRule) listCFeatureEnvy.getSelectedValue();
			index = customFE.indexOf(rule);

			for (int id = 1; id <= excelExporter.NumRows(); id++) {

				String data[] = excelExporter.getLine(id).split("<-->");
				String code = translateRuleToJavaCode(id, rule.getRule());

				try {
					if (data[IS_FEATURE_ENVY].equals("true") && ((Boolean) interpreter.eval(code)).equals(true)) {
						dci++;
					}else if (data[IS_FEATURE_ENVY].equals("false") && ((Boolean) interpreter.eval(code)).equals(true)) {
						dii++;
					}else if (data[IS_FEATURE_ENVY].equals("false") && ((Boolean) interpreter.eval(code)).equals(false)) {
						adci++;
					}else if (data[IS_FEATURE_ENVY].equals("true") && ((Boolean) interpreter.eval(code)).equals(false)) {
						adii++;
					}
				} catch (EvalError e) {
					e.printStackTrace();
				}
			}
			customFE.get(index).setStats(dci, dii, adci, adii);
			
		}
		
	}
	
	public String translateRuleToJavaCode(int id, String rule) {
		
		Scanner s = new Scanner(rule);
		String code = "";
		while(s.hasNext()) {
			String word = s.next();
			
			if(word.equals("LOC")) {
				code += String.valueOf(excelExporter.getElementAt(id, LOC));
				continue;
			}
			if(word.equals("CYCLO")) {
				code += excelExporter.getElementAt(id, CYCLO);
				continue;
			}
			
			if(word.equals("ATFD")) {
				code += excelExporter.getElementAt(id, ATFD);
				continue;
			}
			
			if(word.equals("LAA")) {
				code += excelExporter.getElementAt(id, LAA);
				continue;
			}
			
			if(word.equals("AND")) {
				code += "&&";
				continue;
			}
			
			if(word.contentEquals("OR")) {
				code += "||";
				continue;
			}
			
			else
				code += word;
		}
		
		s.close();
		return code;
		
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
		
		mntmExit = new JMenuItem("Exit");

		mnNewMenu.add(mntmExit);
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		mntmCreatNewLMRule = new JMenuItem("Creat new long_method() rule");

		mnNewMenu_1.add(mntmCreatNewLMRule);
		
		mntmCreatNewFERule = new JMenuItem("Creat new feature_envy() rule");

		mnNewMenu_1.add(mntmCreatNewFERule);
		
		mntmEditExistingLMR = new JMenuItem("Edit existing long_method() rule");

		mnNewMenu_1.add(mntmEditExistingLMR);
		
		mntmEditExistingFER = new JMenuItem("Edit existing feature_envy() rule");

		mnNewMenu_1.add(mntmEditExistingFER);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		JPanel pnliPlamaPMD = new JPanel();
		pnliPlamaPMD.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel pnlCostumTools = new JPanel();
		pnlCostumTools.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblCostumLongmethod = new JLabel("Costum long_method");
		lblCostumLongmethod.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JScrollPane scrlPCLongMethod = new JScrollPane();
		
		JLabel lblCostumFeatureEnvy = new JLabel("Costum feature_envy()");
		lblCostumFeatureEnvy.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JScrollPane scrlPCFeatureEnvy = new JScrollPane();
		
		btnToolQCLongMethod = new JButton("Tool Quality");
		btnToolQCLongMethod.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		btnToolQCFeatureEnvy = new JButton("Tool Quality");
		btnToolQCFeatureEnvy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblisFeatureEnvy2 = new JLabel("is_feature_envy");
		lblisFeatureEnvy2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblisLongMethod4 = new JLabel("is_long_method");
		lblisLongMethod4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtFCostumIsLong = new JTextField();
		txtFCostumIsLong.setEditable(false);
		txtFCostumIsLong.setColumns(10);
		
		txtFCostumIsFeature = new JTextField();
		txtFCostumIsFeature.setEditable(false);
		txtFCostumIsFeature.setColumns(10);
		GroupLayout gl_pnlCostumTools = new GroupLayout(pnlCostumTools);
		gl_pnlCostumTools.setHorizontalGroup(
			gl_pnlCostumTools.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlCostumTools.createSequentialGroup()
					.addGroup(gl_pnlCostumTools.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlCostumTools.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrlPCLongMethod, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
						.addGroup(gl_pnlCostumTools.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrlPCFeatureEnvy, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
						.addGroup(gl_pnlCostumTools.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblisLongMethod4, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFCostumIsLong, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
							.addComponent(btnToolQCLongMethod, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlCostumTools.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblisFeatureEnvy2, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtFCostumIsFeature, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
							.addComponent(btnToolQCFeatureEnvy, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_pnlCostumTools.createSequentialGroup()
					.addGap(156)
					.addComponent(lblCostumLongmethod, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(162))
				.addGroup(gl_pnlCostumTools.createSequentialGroup()
					.addGap(157)
					.addComponent(lblCostumFeatureEnvy, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
					.addGap(137))
		);
		gl_pnlCostumTools.setVerticalGroup(
			gl_pnlCostumTools.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCostumTools.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCostumLongmethod, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrlPCLongMethod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_pnlCostumTools.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnToolQCLongMethod, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblisLongMethod4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtFCostumIsLong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblCostumFeatureEnvy, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(scrlPCFeatureEnvy, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_pnlCostumTools.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnToolQCFeatureEnvy, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblisFeatureEnvy2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtFCostumIsFeature, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		
		listCFeatureEnvy = new JList(customFE);
		listCFeatureEnvy.setSelectionModel(new DefaultListSelectionModel() {
		    private static final long serialVersionUID = 1L;

		    boolean gestureStarted = false;

		    @Override
		    public void setSelectionInterval(int index0, int index1) {
		        if(!gestureStarted){
		            if (isSelectedIndex(index0)) {
		                super.removeSelectionInterval(index0, index1);
		            } 
		            else {
		                super.setSelectionInterval(index0, index1);
		            }
		        }
		        gestureStarted = true;
		    }

		    @Override
		    public void setValueIsAdjusting(boolean isAdjusting) {
		        if (isAdjusting == false) {
		            gestureStarted = false;
		        }
		    }

		});
		scrlPCFeatureEnvy.setViewportView(listCFeatureEnvy);
		
		listCLongMethod = new JList(customLM);
		listCLongMethod.setSelectionModel(new DefaultListSelectionModel() {
		    private static final long serialVersionUID = 1L;

		    boolean gestureStarted = false;

		    @Override
		    public void setSelectionInterval(int index0, int index1) {
		        if(!gestureStarted){
		            if (isSelectedIndex(index0)) {
		                super.removeSelectionInterval(index0, index1);
		            } 
		            else {
		                super.setSelectionInterval(index0, index1);
		            }
		        }
		        gestureStarted = true;
		    }

		    @Override
		    public void setValueIsAdjusting(boolean isAdjusting) {
		        if (isAdjusting == false) {
		            gestureStarted = false;
		        }
		    }

		});

		scrlPCLongMethod.setViewportView(listCLongMethod);
		pnlCostumTools.setLayout(gl_pnlCostumTools);
		
		JPanel pnlTrueEvaluation = new JPanel();
		pnlTrueEvaluation.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel pnlMethodID = new JPanel();
		pnlMethodID.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JLabel lblMethodId = new JLabel("Method ID");
		lblMethodId.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		spnrMethodID = new JSpinner();
		spnrMethodID.setModel(new SpinnerNumberModel(1, 1, 420, 1));
		
		btnGetEvaluation = new JButton("Get Evaluation");
		btnGetEvaluation.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
				btnGetEvaluation.setFont(new Font("Tahoma", Font.PLAIN, 13));
				GroupLayout gl_pnlMethodID = new GroupLayout(pnlMethodID);
				gl_pnlMethodID.setHorizontalGroup(
					gl_pnlMethodID.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlMethodID.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_pnlMethodID.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_pnlMethodID.createSequentialGroup()
									.addGap(10)
									.addComponent(spnrMethodID, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblMethodId))
							.addGap(18)
							.addComponent(btnGetEvaluation, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
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
									.addGap(31)
									.addComponent(btnGetEvaluation, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(24, Short.MAX_VALUE))
				);
				pnlMethodID.setLayout(gl_pnlMethodID);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(pnlTrueEvaluation, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pnliPlamaPMD, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(pnlCostumTools, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
							.addGap(7))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(pnlMethodID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(815, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlCostumTools, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(pnlMethodID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(pnliPlamaPMD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(pnlTrueEvaluation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
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
		
		txtFTrueIsFeature = new JTextField();
		txtFTrueIsFeature.setEditable(false);
		txtFTrueIsFeature.setColumns(10);
		GroupLayout gl_pnlTrueEvaluation = new GroupLayout(pnlTrueEvaluation);
		gl_pnlTrueEvaluation.setHorizontalGroup(
			gl_pnlTrueEvaluation.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlTrueEvaluation.createSequentialGroup()
					.addGap(55)
					.addComponent(lblTrueEvaluation, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
					.addGap(63))
				.addGroup(gl_pnlTrueEvaluation.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_pnlTrueEvaluation.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlTrueEvaluation.createSequentialGroup()
							.addComponent(lblisFeatureEnvy, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(txtFTrueIsFeature, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlTrueEvaluation.createSequentialGroup()
							.addComponent(lblisLongMethod, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(txtFTrueIsLong, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(29, Short.MAX_VALUE))
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
							.addComponent(txtFTrueIsFeature, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(153, Short.MAX_VALUE))
		);
		pnlTrueEvaluation.setLayout(gl_pnlTrueEvaluation);
		
		JLabel lblIPlasma = new JLabel("iPlasma");
		lblIPlasma.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblPmd = new JLabel("PMD");
		lblPmd.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblisLongMethod2 = new JLabel("is_long_method");
		lblisLongMethod2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtFIPlasmaIsLong = new JTextField();
		txtFIPlasmaIsLong.setEditable(false);
		txtFIPlasmaIsLong.setColumns(10);
		
		JLabel lblisLongMethod3 = new JLabel("is_long_method");
		lblisLongMethod3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		txtFPMDIsLong = new JTextField();
		txtFPMDIsLong.setEditable(false);
		txtFPMDIsLong.setColumns(10);
		
		btnToolQIPlasma = new JButton("Tool Quality");

		btnToolQIPlasma.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		btnToolQPMD = new JButton("Tool Quality");
		btnToolQPMD.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnToolQPMD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnCompareTools = new JButton("Compare both tools");
		btnCompareTools.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_pnliPlamaPMD = new GroupLayout(pnliPlamaPMD);
		gl_pnliPlamaPMD.setHorizontalGroup(
			gl_pnliPlamaPMD.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnliPlamaPMD.createSequentialGroup()
					.addGap(64)
					.addGroup(gl_pnliPlamaPMD.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnliPlamaPMD.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
							.addComponent(lblIPlasma, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnToolQIPlasma, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnliPlamaPMD.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_pnliPlamaPMD.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnliPlamaPMD.createSequentialGroup()
									.addComponent(lblisLongMethod3, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(txtFPMDIsLong, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pnliPlamaPMD.createSequentialGroup()
									.addComponent(lblisLongMethod2, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(txtFIPlasmaIsLong, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))))
					.addGap(63))
				.addGroup(gl_pnliPlamaPMD.createSequentialGroup()
					.addGap(88)
					.addComponent(lblPmd, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnToolQPMD, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(80, Short.MAX_VALUE))
				.addGroup(gl_pnliPlamaPMD.createSequentialGroup()
					.addGap(79)
					.addComponent(btnCompareTools, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
					.addGap(81))
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
						.addComponent(lblPmd, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnToolQPMD, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_pnliPlamaPMD.createParallelGroup(Alignment.LEADING)
						.addComponent(lblisLongMethod3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
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
		
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(HomeGui.this, "Deseja mesmo sair?");
				System.exit(0);
			}
		});
		
		mntmImportExcelFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(!excelReader.isVisible())
				excelReader.open();
			else { 
				JOptionPane.showMessageDialog(HomeGui.this, "Exportador de Excel já está aberto");
			}
			}
		});
		
		mntmCreatNewLMRule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SwingWorker<String, Void>() {

					@Override
					protected String doInBackground() throws Exception {
						newRuleViewer.addComponentsCreate(Defects.LONG_METHOD);
						newRuleViewer.setList(customLM);
						newRuleViewer.setModal(true);
						newRuleViewer.open();
						return newRuleViewer.getTxtFieldContent();
					}
					
					@Override
					protected void done() {
						try {
							String rule = get();
							if (!rule.equals("")) {
							customLM.addElement(new CostumRule(Defects.LONG_METHOD, rule));
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
					}
				}.execute();
			}
		});
		
		mntmCreatNewFERule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SwingWorker<String, Void>() {

					@Override
					protected String doInBackground()  {
						newRuleViewer.addComponentsCreate(Defects.FEATURE_ENVY);
						newRuleViewer.setList(customFE);
						newRuleViewer.setModal(true);
						newRuleViewer.open();
						return newRuleViewer.getTxtFieldContent();
					}

					@Override
					protected void done() {
						try {
							String rule = get();
							if (!rule.equals("")) {
								customFE.addElement(new CostumRule(Defects.FEATURE_ENVY, rule));
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
					}
				}.execute();
			}
		});
		
		mntmEditExistingLMR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(customLM.isEmpty()) {
					JOptionPane.showMessageDialog(HomeGui.this, "Não tem regras long_method() para editar");
				} 
				else {
					new SwingWorker<DefaultListModel<CostumRule>, Void>() {
						@Override
						protected DefaultListModel<CostumRule> doInBackground()  {
							newRuleViewer.addComponentsEdit(Defects.LONG_METHOD);
							newRuleViewer.setList(customLM);
							newRuleViewer.setModal(true);
							newRuleViewer.open();
							return newRuleViewer.getNewList();
						}

						@Override
						protected void done() {
							try {
								customLM = get();
							} catch (InterruptedException e) {
								e.printStackTrace();
							} catch (ExecutionException e) {
								e.printStackTrace();
							}
						}
					}.execute();
				}
			}
		});
		
		mntmEditExistingFER.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(customFE.isEmpty()) {
					JOptionPane.showMessageDialog(HomeGui.this, "Não tem regras feature_envy() para editar");
				}
				else {
					new SwingWorker<DefaultListModel<CostumRule>, Void>() {
						@Override
						protected DefaultListModel<CostumRule> doInBackground()  {
							newRuleViewer.addComponentsEdit(Defects.FEATURE_ENVY);
							newRuleViewer.setList(customFE);
							newRuleViewer.setModal(true);
							newRuleViewer.open();
							return newRuleViewer.getNewList();
						}
						@Override
						protected void done() {
							try {
								customLM = get();
							} catch (InterruptedException e) {
								e.printStackTrace();
							} catch (ExecutionException e) {
								e.printStackTrace();

							}
						}
					}.execute();
				}
			}
		});
		
		btnGetEvaluation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SwingWorker<String[][], Void>() {
					
					//1->txtFTrueIsLong
					//2->txtFTrueIsFeature
					//3->txtFIPlasmaIsLong
					//4->txtFPMDIsLong
					//5->txtFCostumIsLong
					//6->txtFCostumIsFeature
					
					@Override
					protected String[][] doInBackground() {
						
						int methodId = (Integer) spnrMethodID.getValue();
						String[][] data = new String[6][2];
						
						data[0][0] = "1";
						data[1][0] = "2";
						data[2][0] = "3";
						data[3][0] = "4";
						
						data[0][1] = String.valueOf(excelExporter.getElementAt(methodId, IS_LONG_METHOD));
						data[1][1] = String.valueOf(excelExporter.getElementAt(methodId, IS_FEATURE_ENVY));
						data[2][1] = String.valueOf(excelExporter.getElementAt(methodId, IPLASMA));
						data[3][1] = String.valueOf(excelExporter.getElementAt(methodId, PMD));
						
						Interpreter interpreter = new Interpreter();
						
						if(listCLongMethod.getSelectedValue() == null) {
							data[4][0] = "";
							txtFCostumIsLong.setText("");
						}else {
							data[4][0] = "5";
							try {
								data[4][1] = String.valueOf((Boolean) interpreter.eval(translateRuleToJavaCode(methodId, listCLongMethod.getSelectedValue().toString())));
							} catch (EvalError e) {
								e.printStackTrace();
							}
						}
						
						if(listCFeatureEnvy.getSelectedValue() == null) {
							data[5][0] = "";
							txtFCostumIsFeature.setText("");
						}else {
							data[5][0] = "6";
							try {
								data[5][1] = String.valueOf((Boolean) interpreter.eval(translateRuleToJavaCode(methodId, listCFeatureEnvy.getSelectedValue().toString())));
							} catch (EvalError e) {
								e.printStackTrace();
							}
						}
						
						return data;
						
					}
					
					@Override 
					protected void done () {
						
						String[][] txtFValues = null;
						
						try {
							txtFValues = get();
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
						
						for(int x = 0; x < txtFValues.length; x++) {
							
							if(txtFValues[x][0].equals("")) {
								break;
							}
							
							if (txtFValues[x][0].equals("1")){
								txtFTrueIsLong.setText(txtFValues[x][1]);
								continue;
							}
							
							if (txtFValues[x][0].equals("2")){
								txtFTrueIsFeature.setText(txtFValues[x][1]);
								continue;
							}
							
							if (txtFValues[x][0].equals("3")){
								txtFIPlasmaIsLong.setText(txtFValues[x][1]);
								continue;
							}
							
							if (txtFValues[x][0].equals("4")){
								txtFPMDIsLong.setText(txtFValues[x][1]);
								continue;
							}
							
							if (txtFValues[x][0].equals("5")){
								txtFCostumIsLong.setText(txtFValues[x][1]);
								continue;
							}
							
							if (txtFValues[x][0].equals("6")){
								txtFCostumIsFeature.setText(txtFValues[x][1]);
								continue;
							}
						}
					}
				}.execute();
				
			}
		});
		
		btnToolQIPlasma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(HomeGui.this, "Total de métodos avaliados: " + iplasma.getTotalMehtodsEvaluated() + "\nTotal de avaliações certas: " + iplasma.getCorrectEvaluations()
				+"\nTotal de avaliações erradas: " + iplasma.getIncorrectEvaluations()	+"\nDefeitos Corretamente Identificados: " + iplasma.getDci() +
				"\nDefeitos Incorretamente Identificados: " + iplasma.getDii() +"\nAusências de Defeitos Corretamente Identificadas: " + iplasma.getAdci() + 
				"\nAusências de Defeitos Incorretamente Identificadas: " + iplasma.getAdii(), "iPlasma", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		btnToolQPMD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(HomeGui.this, "Total de métodos avaliados: " + pmd.getTotalMehtodsEvaluated() + "\nTotal de avaliações certas: " + pmd.getCorrectEvaluations()
				+"\nTotal de avaliações erradas: " + pmd.getIncorrectEvaluations()	+"\nDefeitos Corretamente Identificados: " + pmd.getDci() + "\nDefeitos Incorretamente Identificados: " 
				+ pmd.getDii() + "\nAusências de Defeitos Corretamente Identificadas: " + pmd.getAdci() + "\nAusências de Defeitos Incorretamente Identificadas: " + pmd.getAdii(), "PMD", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		btnToolQCLongMethod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listCLongMethod.getSelectedValue() == null)
					JOptionPane.showMessageDialog(HomeGui.this, "Nenhuma regra selecionada");	
				if(((CostumRule) listCLongMethod.getSelectedValue()).getTotalMehtodsEvaluated() < 0)
					getCustomRuleStats(Defects.LONG_METHOD);
				CostumRule rule = (CostumRule) listCLongMethod.getSelectedValue();
				System.out.println(rule.getTotalMehtodsEvaluated());
				JOptionPane.showMessageDialog(HomeGui.this, "Total de métodos avaliados: " + rule.getTotalMehtodsEvaluated() + "\nTotal de avaliações certas: " + rule.getCorrectEvaluations()
				+"\nTotal de avaliações erradas: " + rule.getIncorrectEvaluations()	+"\nDefeitos Corretamente Identificados: " + rule.getDci() + "\nDefeitos Incorretamente Identificados: " 
				+ rule.getDii() + "\nAusências de Defeitos Corretamente Identificadas: " + rule.getAdci() + "\nAusências de Defeitos Incorretamente Identificadas: " + rule.getAdii(), 
				"Costum rule for long_method() Status", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		btnToolQCFeatureEnvy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listCFeatureEnvy.getSelectedValue() == null)
					JOptionPane.showMessageDialog(HomeGui.this, "Nenhuma regra selecionada");
				if(((CostumRule) listCFeatureEnvy.getSelectedValue()).getTotalMehtodsEvaluated() < 0)
					getCustomRuleStats(Defects.FEATURE_ENVY);
				CostumRule rule = (CostumRule) listCFeatureEnvy.getSelectedValue();
				JOptionPane.showMessageDialog(HomeGui.this, "Total de métodos avaliados: " + rule.getTotalMehtodsEvaluated() + "\nTotal de avaliações certas: " + rule.getCorrectEvaluations()
				+"\nTotal de avaliações erradas: " + rule.getIncorrectEvaluations()	+"\nDefeitos Corretamente Identificados: " + rule.getDci() + "\nDefeitos Incorretamente Identificados: " 
				+ rule.getDii() + "\nAusências de Defeitos Corretamente Identificadas: " + rule.getAdci() + "\nAusências de Defeitos Incorretamente Identificadas: " + rule.getAdii(), 
				"Costum rule for feature_envy() Status", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
	}
}

