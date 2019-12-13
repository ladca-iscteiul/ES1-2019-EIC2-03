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
import javax.swing.JOptionPane;
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
	
	private final static CreatRuleGUI instance = new CreatRuleGUI();

	private JTextField txtFRule;
	private Object lastEdited = null;
	private DefaultListModel<CostumRule> list = new DefaultListModel<CostumRule>();

	private CreatRuleGUI() {
		setBounds(100, 100, 890, 400);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static CreatRuleGUI getInstance() {
		return instance;
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
					}else { 
						JOptionPane.showMessageDialog(CreatRuleGUI.this, "Regra já existe");
					}
				else {
					JOptionPane.showMessageDialog(CreatRuleGUI.this, "Regra inválida");
				}
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







	//////////////////////////////////////////////////////////////////////////////////////
	/////////////////////UI para alterar uma regra 
	/////////////////////////////////////////////////////////////////////////////////////
	public void addComponentsEdit(final Defects defect){

		//Adicionar componentes à UI que altera uma regra

		setTitle("Edit " + defect.toString() + " rule");

		getContentPane().removeAll();
		getContentPane().setLayout(new BorderLayout());
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel label = new JLabel("New " + defect.toString() + " rule:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));

		JScrollPane scrollPane = new JScrollPane();
		final JList listRules = new JList(list);
		listRules.setSelectionModel(new DefaultListSelectionModel() {
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
		scrollPane.setViewportView(listRules);

		txtFRule = new JTextField("");
		txtFRule.setEditable(false);
		txtFRule.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

		JButton btnLoc = new JButton("LOC");

		JButton btnLess = new JButton("<");

		JButton btnEqual = new JButton("==");

		JButton btnCyclo = new JButton("CYCLO");

		JButton btnMore = new JButton(">");

		JButton btnAnd = new JButton("AND");

		JButton btnLaa = new JButton("LAA");

		JButton btnMoreOrEqual = new JButton(">=");

		JButton btnDelete = new JButton("Delete");

		JButton btnDeleteAll = new JButton("Delete all");

		JButton btnAtfd = new JButton("ATFD");

		JButton btnLessOrEqual = new JButton("<=");

		JButton btnOr = new JButton("OR");

		final JSpinner spinner = new JSpinner();

		JButton btnAdd = new JButton("Add");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 322, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel_1.createSequentialGroup()
														.addComponent(btnLoc, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnLess, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnEqual, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel_1.createSequentialGroup()
														.addComponent(btnCyclo, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnMore, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnAnd, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel_1.createSequentialGroup()
														.addComponent(btnLaa, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnMoreOrEqual, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
														.addGroup(gl_panel_1.createSequentialGroup()
																.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(btnDeleteAll, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_panel_1.createSequentialGroup()
																.addComponent(btnAtfd, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(btnLessOrEqual, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(btnOr, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))))
								.addGroup(gl_panel_1.createSequentialGroup()
										.addGap(72)
										.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(btnAdd)))
						.addContainerGap(19, Short.MAX_VALUE))
				);
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 246, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLoc)
								.addComponent(btnLess)
								.addComponent(btnEqual))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCyclo)
								.addComponent(btnMore)
								.addComponent(btnAnd))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAtfd)
								.addComponent(btnLessOrEqual)
								.addComponent(btnOr))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLaa)
								.addComponent(btnMoreOrEqual))
						.addGap(26)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAdd))
						.addGap(27)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnDeleteAll)
								.addComponent(btnDelete))
						.addContainerGap())
				);
		panel_1.setLayout(gl_panel_1);

		JPanel buttonPane = new JPanel();

		JButton btnEdit = new JButton("Editar");

		JButton btnSave = new JButton("Guardar Alterações");

		btnSave.setActionCommand("OK");
		getRootPane().setDefaultButton(btnSave);


		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");

		JButton btnSair = new JButton("Sair");

		GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
		gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnEdit)
						.addGap(37)
						.addComponent(btnSave)
						.addGap(32)
						.addComponent(cancelButton)
						.addPreferredGap(ComponentPlacement.RELATED, 441, Short.MAX_VALUE)
						.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
		gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnEdit)
								.addComponent(btnSave)
								.addComponent(cancelButton)
								.addComponent(btnSair)))
				);
		buttonPane.setLayout(gl_buttonPane);


		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addComponent(label)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(txtFRule, GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
										.addContainerGap()
										.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 509, GroupLayout.PREFERRED_SIZE))
								.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 865, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(22)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtFRule, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);


		panel.setLayout(gl_panel);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
				gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
				);
		gl_contentPanel.setVerticalGroup(
				gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
						.addContainerGap())
				);
		contentPanel.setLayout(gl_contentPanel);



		//Adicionar eventos à UI que altera regras
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

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listRules.getSelectedValue()== null) {
					JOptionPane.showMessageDialog(CreatRuleGUI.this, "Não tem nenhuma regra selecionada para guardar");
				}
				else {
					lastEdited = listRules.getSelectedValue();
					txtFRule.setText(((CostumRule) lastEdited).toString());
				}
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lastEdited == null) {
					JOptionPane.showMessageDialog(CreatRuleGUI.this, "Não tem nenhuma regra selecionada para guardar");
				} 
				else {
					if(CostumRule.isValidRule(txtFRule.getText())) {
						int index = list.indexOf(lastEdited);
						list.removeElement(lastEdited);
						if(txtFRule.getText().equals(""))
							return;
						list.add(index, new CostumRule(defect, txtFRule.getText()));
						lastEdited = null;
					}else {
						JOptionPane.showMessageDialog(CreatRuleGUI.this, "Regra inválida");
					}
				}
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFRule.setText(((CostumRule) lastEdited).toString());
			}
		});

		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
}
