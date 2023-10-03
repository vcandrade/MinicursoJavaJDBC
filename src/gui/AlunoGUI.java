package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import entities.Aluno;
import service.AlunoService;

public class AlunoGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRA;
	private JTextField txtNome;
	private JTextField txtCurso;
	private JTable tblAlunos;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rbMasculino;
	private JRadioButton rbFeminino;
	private JRadioButton rbNaoInformado;
	
	public AlunoGUI() {
		
		setTitle("Aluno");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1002, 491);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Arquivo");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Sair");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finalizarPrograma();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Registro Acad\u00EAmico");
		lblNewLabel.setBounds(10, 23, 164, 22);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblNewLabel);

		txtRA = new JTextField();
		txtRA.setBounds(184, 18, 150, 27);
		contentPane.add(txtRA);
		txtRA.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome Completo");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(25, 68, 145, 22);
		contentPane.add(lblNewLabel_1);

		txtNome = new JTextField();
		txtNome.setBounds(184, 63, 364, 27);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Curso");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(25, 114, 145, 22);
		contentPane.add(lblNewLabel_2);

		txtCurso = new JTextField();
		txtCurso.setBounds(184, 111, 364, 25);
		contentPane.add(txtCurso);
		txtCurso.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(582, 23, 164, 113);
		contentPane.add(panel);
		panel.setLayout(null);

		rbMasculino = new JRadioButton("Masculino");
		buttonGroup.add(rbMasculino);
		rbMasculino.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rbMasculino.setBounds(17, 26, 109, 23);
		panel.add(rbMasculino);

		rbFeminino = new JRadioButton("Feminino");
		buttonGroup.add(rbFeminino);
		rbFeminino.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rbFeminino.setBounds(17, 52, 109, 23);
		panel.add(rbFeminino);

		rbNaoInformado = new JRadioButton("N\u00E3o Informar");
		buttonGroup.add(rbNaoInformado);
		rbNaoInformado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rbNaoInformado.setBounds(17, 78, 126, 23);
		panel.add(rbNaoInformado);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Alunos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(25, 147, 721, 272);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 28, 678, 222);
		panel_1.add(scrollPane);

		tblAlunos = new JTable();
		tblAlunos.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Ra", "Nome Completo", "Sexo", "Curso" }));
		scrollPane.setViewportView(tblAlunos);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				cadastrarNovoAluno();
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCadastrar.setBounds(776, 23, 189, 45);
		contentPane.add(btnCadastrar);

		JButton btnLimparCampos = new JButton("LimparCampos");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimparCampos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLimparCampos.setBounds(776, 374, 189, 45);
		contentPane.add(btnLimparCampos);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				atualizarCadastroAluno();
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditar.setBounds(776, 79, 189, 45);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCadastroAluno();
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExcluir.setBounds(776, 135, 189, 45);
		contentPane.add(btnExcluir);
		
		this.atualizarTabela();
	}

	public void cadastrarNovoAluno() {

		
	}
	
	public void atualizarTabela() {

		
	}
	
	public void atualizarCadastroAluno() {
		
		
	}
	
	public void excluirCadastroAluno() {
		
		
	}
	
	public void limparCampos() {
		
		
	}
	
	public void finalizarPrograma() {
		
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlunoGUI frame = new AlunoGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
