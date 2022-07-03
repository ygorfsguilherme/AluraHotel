package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import controller.ReservasController;
import model.ReservasModelo;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Reservas extends JFrame {

	private JPanel contentPane;
	private static JTextField Valor = new JTextField();

	static JDateChooser FechaE = new JDateChooser();
	static JDateChooser FechaS = new JDateChooser();
	private JComboBox<Object> FormaPago = new JComboBox<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservas frame = new Reservas();
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
	public Reservas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/imagens/calendario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 540);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(0, 0, 900, 502);
		contentPane.add(panel);
		panel.setLayout(null);

		FechaE.setBounds(88, 166, 235, 33);
		panel.add(FechaE);
		FechaE.getDateEditor().addPropertyChangeListener(
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						CalcularHospedagem();
					}
				});

		JLabel lblNewLabel_1 = new JLabel("Data de Check In");
		lblNewLabel_1.setBounds(88, 142, 133, 14);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Data de Check Out");
		lblNewLabel_1_1.setBounds(88, 210, 133, 14);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1);

		FechaS.setBounds(88, 234, 235, 33);
		FechaS.getCalendarButton().setBackground(Color.WHITE);
		panel.add(FechaS);
		FechaS.getDateEditor().addPropertyChangeListener(
				new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent e) {
						CalcularHospedagem();
					}
				});

		// Valor JTextField
		Valor.setBounds(88, 303, 235, 33);
		Valor.setEnabled(false);
		panel.add(Valor);
		Valor.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("Valor da Reserva");
		lblNewLabel_1_1_1.setBounds(88, 278, 133, 14);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1);

		FormaPago.setBounds(88, 373, 235, 33);
		FormaPago.setFont(new Font("Arial", Font.PLAIN, 14));
		FormaPago.setModel(new DefaultComboBoxModel<Object>(new Object[] {
				"Selecione",
				"Dinheiro",
				"Cartão de Crédito",
				"Cartão de Débito" }));
		panel.add(FormaPago);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Forma de pagamento");
		lblNewLabel_1_1_1_1.setBounds(88, 347, 151, 24);
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_4 = new JLabel("Sistema de Reservas");
		lblNewLabel_4.setBounds(108, 93, 199, 42);
		lblNewLabel_4.setForeground(new Color(65, 105, 225));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblNewLabel_4);

		JButton btnReservar = new JButton("Continuar");
		btnReservar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				salvaReserva();
			}
		});
		btnReservar.setForeground(Color.WHITE);
		btnReservar.setBounds(190, 436, 133, 33);
		btnReservar.setIcon(new ImageIcon(Reservas.class.getResource("/imagens/calendario.png")));
		btnReservar.setBackground(new Color(65, 105, 225));
		btnReservar.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(btnReservar);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(399, 0, 491, 502);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, -16, 500, 539);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(Reservas.class.getResource("/imagens/reservas-img-2.png")));

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Reservas.class.getResource("/imagens/Ha-100px.png")));
		lblNewLabel_2.setBounds(15, 6, 104, 107);
		panel.add(lblNewLabel_2);
	}

	private static void CalcularHospedagem() {
		try {
			LocalDateTime dataInicial = LocalDateTime.ofInstant(FechaE.getDate().toInstant(),
					ZoneId.systemDefault());
			LocalDateTime dataFinal = LocalDateTime.ofInstant(FechaS.getDate().toInstant(),
					ZoneId.systemDefault());

			long dias = ChronoUnit.DAYS.between(dataInicial, dataFinal);
			int totalDias = (int) dias;
			int valorAPagar = totalDias * 20;

			if (totalDias < 1) {
				FechaS.setDate(null);
				FechaE.setDate(null);
				Valor.setText("");
				JOptionPane.showMessageDialog(null, "Data de Check In não pode ser maior que Data de Check Out");
			} else {
				Valor.setText(Integer.toString(valorAPagar));
			}

		} catch (Exception e) {
		}
	}

	private void salvaReserva() {
		if (FormaPago.getSelectedItem().equals("Selecione")) {
			JOptionPane.showMessageDialog(null, "Selecione uma forma de pagamento!");
		} else if (FechaE.getDate() == null || FechaS.getDate() == null || Valor.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
		} else {
			String dCheckin = new SimpleDateFormat("yyyy-MM-dd").format(FechaE.getDate());
			String dCheckout = new SimpleDateFormat("yyyy-MM-dd").format(FechaS.getDate());
			String formaPagamento = FormaPago.getSelectedItem().toString();
			int valor = Integer.parseInt(Valor.getText());

			ReservasModelo reservasModelo = new ReservasModelo(dCheckin,
					dCheckout,
					formaPagamento,
					valor);
			ReservasController reservasController = new ReservasController();
			reservasController.salvar(reservasModelo);

			FechaE.setDate(null);
			FechaS.setDate(null);
			Valor.setText("");
			FormaPago.setSelectedIndex(0);

			RegistroHospede registroHospede = new RegistroHospede();
			registroHospede.setVisible(true);
			dispose();
		}
	}
}
