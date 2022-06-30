package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HospedeController;
import controller.ReservasController;
import model.HospedeModelo;
import model.ReservasModelo;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private ReservasController reservasController = new ReservasController();
	private HospedeController hospedeController = new HospedeController();
	private JTable tbReservas;
	private DefaultTableModel model;
	private DefaultTableModel modelHospede;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Buscar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagens/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(647, 85, 158, 31);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Buscar.class.getResource("/imagens/lupa2.png")));
		btnBuscar.setBounds(815, 75, 54, 41);
		contentPane.add(btnBuscar);

		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Buscar.class.getResource("/imagens/editar-texto.png")));
		btnEditar.setBackground(SystemColor.menu);
		btnEditar.setBounds(587, 416, 54, 41);
		contentPane.add(btnEditar);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});

		JLabel lblNewLabel_4 = new JLabel("Sistema de Busca");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_4.setBounds(155, 42, 212, 42);
		contentPane.add(lblNewLabel_4);

		JButton btnSair = new JButton("");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});
		btnSair.setIcon(new ImageIcon(Buscar.class.getResource("/imagens/encerrar-sessao-32-px.png")));
		btnSair.setForeground(Color.WHITE);
		btnSair.setBackground(Color.WHITE);
		btnSair.setBounds(815, 416, 54, 41);
		contentPane.add(btnSair);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBounds(10, 127, 874, 265);
		contentPane.add(panel);

		tbHospedes = new JTable();
		tbHospedes.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/imagens/pessoa.png")), tbHospedes, null);

		modelHospede = (DefaultTableModel) tbHospedes.getModel();
		modelHospede.addColumn("ID");
		modelHospede.addColumn("Nome");
		modelHospede.addColumn("Sobrenome");
		modelHospede.addColumn("Telefone");
		modelHospede.addColumn("Data de Nascimento");
		modelHospede.addColumn("Nacionalidade");
		modelHospede.addColumn("ID Reserva");
		modelHospede.addRow(
				new Object[] { "ID", "Nome", "Sobrenome", "Telefone", "D. Nascimento", "Nacionalidade", "ID Reserva" });
		mostrarHospede();

		tbReservas = new JTable();
		tbReservas.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagens/calendario.png")), tbReservas, null);

		model = (DefaultTableModel) tbReservas.getModel();
		model.addColumn("Id");
		model.addColumn("Check-in");
		model.addColumn("Check-out");
		model.addColumn("FormaPagamento");
		model.addColumn("Valor");
		model.addRow(new Object[] { "ID", "Check-in", "Check-out", "F. Pagamento", "Valor" });
		mostrarReservas();

		JButton btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(Buscar.class.getResource("/imagens/deletar.png")));
		btnDelete.setBackground(SystemColor.menu);
		btnDelete.setBounds(651, 416, 54, 41);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});

		JButton btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Buscar.class.getResource("/imagens/cancelar.png")));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(713, 416, 54, 41);
		contentPane.add(btnCancelar);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagens/Ha-100px.png")));
		lblNewLabel_2.setBounds(25, 10, 104, 107);
		contentPane.add(lblNewLabel_2);
		setResizable(false);

	}

	private void deletar() {
		Object objetoDaLinha = (Object) model.getValueAt(tbReservas.getSelectedRow(),
				tbReservas.getSelectedColumn());

		if (objetoDaLinha instanceof Integer) {
			Integer id = (Integer) objetoDaLinha;

			this.reservasController.deletar(id);
			model.removeRow(tbReservas.getSelectedRow());
			modelHospede.setRowCount(1);
			mostrarHospede();
			JOptionPane.showMessageDialog(this, "Item excluído com sucesso!");
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	private void alterar() {
		Object objetoDaLinha = (Object) model.getValueAt(tbReservas.getSelectedRow(),
				tbReservas.getSelectedColumn());

		if (objetoDaLinha instanceof Integer) {
			Integer id = (Integer) objetoDaLinha;

			String data_checkin = tbReservas.getValueAt(tbReservas.getSelectedRow(), 1).toString();
			String data_checkout = tbReservas.getValueAt(tbReservas.getSelectedRow(), 2).toString();
			String forma_pagamento = tbReservas.getValueAt(tbReservas.getSelectedRow(), 3).toString();
			String valor_hosp = tbReservas.getValueAt(tbReservas.getSelectedRow(), 4).toString();

			this.reservasController.alterar(data_checkin,
					data_checkout,
					forma_pagamento,
					Integer.parseInt(valor_hosp),
					id);
			JOptionPane.showMessageDialog(this, "Item alterado com sucesso!");
			// model.removeRow(tbReservas.getSelectedRow());
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	private void mostrarReservas() {
		ReservasController reservasController = new ReservasController();
		List<ReservasModelo> reservas = reservasController.listar();
		reservas.forEach(reserva -> {
			model.addRow(new Object[] {
					reserva.getId(),
					reserva.getDataCheckin(),
					reserva.getDataCheckout(),
					reserva.getFormaPagamento(),
					reserva.getValor() });
		});
	}

	private void mostrarHospede() {
		HospedeController hospedeController = new HospedeController();
		List<HospedeModelo> hospedes = hospedeController.listar();
		hospedes.forEach(hospede -> {
			modelHospede.addRow(new Object[] {
					hospede.getIdHospede(),
					hospede.getNome(),
					hospede.getSobrenome(),
					hospede.getDataNascimento(),
					hospede.getNacionalidade(),
					hospede.getTelefone(),
					hospede.getIdReservas() });
		});
	}

}
