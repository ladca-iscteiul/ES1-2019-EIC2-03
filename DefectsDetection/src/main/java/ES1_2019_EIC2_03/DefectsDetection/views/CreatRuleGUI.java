package ES1_2019_EIC2_03.DefectsDetection.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ES1_2019_EIC2_03.DefectsDetection.code.CostumRule;
import ES1_2019_EIC2_03.DefectsDetection.code.Defects;

import javax.swing.UIManager;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class CreatRuleGUI extends JDialog {

	private JTextField txtFRule;
	private Object lastEdited = null;
	private DefaultListModel<CostumRule> list = new DefaultListModel<CostumRule>();

	public CreatRuleGUI() {
		setBounds(100, 100, 890, 400);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	
		//addComponentsEdit();	
		}
	
	public void open() {
		setVisible(true);
	}
	
	public void setList( DefaultListModel<CostumRule> list) {
		this.list = list;
	}
	
	public String getTxtFieldContent() {
		return txtFRule.getText();
	}
	
	public DefaultListModel<CostumRule> getNewList(){
		return list;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	/////////////////////UI para criar uma nova regra
	/////////////////////////////////////////////////////////////////////////////////////
	public void addComponentsCreate(final Defects defect){
		
		//Adicionar componentes à UI que cria uma nova regra
		
		setTitle("Creat new " + defect.toString() + " rule");
		
		getContentPane().removeAll();
		getContentPane().setLayout(new BorderLayout());
		JPanel creatRulePanel = new JPanel();
		creatRulePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(creatRulePanel, BorderLayout.CENTER);

		JPanel bttnsPane = new JPanel();
		bttnsPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(bttnsPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");

		okButton.setActionCommand("OK");
		bttnsPane.add(okButton);
		getRootPane().setDefaultButton(okButton);


		JButton cancelButton = new JButton("Cancel");

		cancelButton.setActionCommand("Cancel");
		bttnsPane.add(cancelButton);
		
		JLabel lblNewRule = new JLabel("New " + defect.toString() + " rule:");
		lblNewRule.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtFRule = new JTextField("");
		txtFRule.setEditable(false);
		txtFRule.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		
		GroupLayout gl_creatRulePanel = new GroupLayout(creatRulePanel);
		gl_creatRulePanel.setHorizontalGroup(
			gl_creatRulePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_creatRulePanel.createSequentialGroup()
					.addComponent(lblNewRule)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtFRule, GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE))
				.addGroup(gl_creatRulePanel.createSequentialGroup()
					.addContainerGap(283, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
					.addGap(263))
		);
		gl_creatRulePanel.setVerticalGroup(
			gl_creatRulePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_creatRulePanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_creatRulePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtFRule, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewRule, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
	
		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		
		JButton btnLoc = new JButton("LOC");
		
		JButton btnCyclo = new JButton("CYCLO");
	
		JButton btnAtfd = new JButton("ATFD");
		
		JButton btnLaa = new JButton("LAA");

		JButton btnLess = new JButton("<");

		JButton btnMore = new JButton(">");
		
		JButton btnLessOrEqual = new JButton("<=");
		
		JButton btnMoreOrEqual = new JButton(">=");
		
		JButton btnEqual = new JButton("==");
		
		JButton btnAnd = new JButton("AND");
		
		JButton btnOr = new JButton("OR");
		
		JButton btnAdd = new JButton("Add");
	
		JButton btnDelete = new JButton("Delete");

		JButton btnDeleteAll = new JButton("Delete all");

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnLoc, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnLess, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEqual, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnCyclo, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnMore, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAnd, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
								.addComponent(btnLaa, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnMoreOrEqual, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 177, Short.MAX_VALUE))
							.addGroup(Alignment.LEADING, gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnDeleteAll, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnAtfd, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnLessOrEqual, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnOr, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(13, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(72)
					.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(btnAdd)
					.addContainerGap(83, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoc)
						.addComponent(btnLess)
						.addComponent(btnEqual))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCyclo)
						.addComponent(btnMore)
						.addComponent(btnAnd))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAtfd)
						.addComponent(btnLessOrEqual)
						.addComponent(btnOr))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLaa)
						.addComponent(btnMoreOrEqual))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd))
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDeleteAll)
						.addComponent(btnDelete))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		creatRulePanel.setLayout(gl_creatRulePanel);
		
		
		
		

	//Adicionar eventos à UI que cria uma nova regra
		btnLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFRule.setText(txtFRule.getText() + "LOC ");
			}
		});
		
		btnCyclo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFRule.setText(txtFRule.getText() + "CYCLO ");
			}
		});
		
		btnAtfd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFRule.setText(txtFRule.getText() + "ATFD ");
			}
		});
		
		btnLaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFRule.setText(txtFRule.getText() + "LAA ");
			}
		});
		
		btnLess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFRule.setText(txtFRule.getText() + "< ");
			}
		});
		
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] words = txtFRule.getText().split(" ");
				words[words.length - 1] = "";
				txtFRule.setText(String.join(" ", words));
			}
		});
		
		btnMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFRule.setText(txtFRule.getText() + "> ");
			}
		});
		
		btnLessOrEqual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFRule.setText(txtFRule.getText() + "<= ");
			}
		});
		
		btnMoreOrEqual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFRule.setText(txtFRule.getText() + ">= ");
			}
		});
		
		btnEqual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFRule.setText(txtFRule.getText() + "== ");
			}
		});
		
		btnAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFRule.setText(txtFRule.getText() + "AND ");
			}
		});
		
		btnOr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFRule.setText(txtFRule.getText() + "OR ");
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFRule.setText(txtFRule.getText() + spinner.getValue() + " ");
			}
		});
		
		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFRule.setText("");
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CostumRule.isValidRule(txtFRule.getText()))
					if(list.isEmpty())
						setVisible(false);
					else if(!list.contains(new CostumRule(defect, txtFRule.getText()))) {
						setVisible(false);
					}else { } // dosomething
					
				else {}
					//do something
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFRule.setText("");
				setVisible(false);
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				txtFRule.setText("");
			}
		});
	}