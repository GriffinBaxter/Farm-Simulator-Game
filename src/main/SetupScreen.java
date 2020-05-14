package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Point;

public class SetupScreen {

	private JFrame setupScreenFrame;
	private GameEnvironment manager;
	private JTextField textFieldName;
	private final ButtonGroup buttonGroupFarmType = new ButtonGroup();
	private JTextField textFieldFarmName;
	private JLabel lblWarning;

	public SetupScreen(GameEnvironment incomingManager) {
		manager = incomingManager;
		initialize();
		setupScreenFrame.setVisible(true);
	}
	
	public void closeWindow() {
		setupScreenFrame.dispose();
	}
	
	public void finishWindow() {
		manager.closeSetupScreen(this);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetupScreen window = new SetupScreen();
					window.setupScreenFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SetupScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setupScreenFrame = new JFrame();
		setupScreenFrame.setTitle("Farm Simulator");
		setupScreenFrame.setBounds(100, 100, 1200, 650);
		setupScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupScreenFrame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome to Farm Simulator!");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWelcome.setBounds(10, 11, 1164, 33);
		setupScreenFrame.getContentPane().add(lblWelcome);
		
		JLabel lblDays = new JLabel("How many days would you like the game to last?");
		lblDays.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDays.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDays.setBounds(10, 85, 586, 45);
		setupScreenFrame.getContentPane().add(lblDays);
		
		JSlider sliderDays = new JSlider();
		sliderDays.setValue(5);
		sliderDays.setPaintLabels(true);
		sliderDays.setMajorTickSpacing(1);
		sliderDays.setSnapToTicks(true);
		sliderDays.setPaintTicks(true);
		sliderDays.setMaximum(10);
		sliderDays.setMinimum(5);
		sliderDays.setBounds(606, 85, 434, 45);
		setupScreenFrame.getContentPane().add(sliderDays);
		
		JLabel lblName = new JLabel("What is your name?");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName.setBounds(10, 157, 586, 45);
		setupScreenFrame.getContentPane().add(lblName);
		
		JLabel lblNameText = new JLabel("(must be between 3 and 15 characters and must not include numbers or special characters)");
		lblNameText.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNameText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNameText.setBounds(10, 192, 586, 27);
		setupScreenFrame.getContentPane().add(lblNameText);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldName.setBounds(617, 174, 423, 39);
		setupScreenFrame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a farm type:");
		lblPleaseSelectA.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseSelectA.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPleaseSelectA.setBounds(10, 251, 1164, 45);
		setupScreenFrame.getContentPane().add(lblPleaseSelectA);
		
		JRadioButton rdbtnNormalFarm = new JRadioButton("Normal farm: $150 starting money, average animal happiness, 10 crop spaces, the default farm.");
		rdbtnNormalFarm.setSelected(true);
		rdbtnNormalFarm.setActionCommand("Normal");
		buttonGroupFarmType.add(rdbtnNormalFarm);
		rdbtnNormalFarm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnNormalFarm.setBounds(10, 303, 744, 33);
		setupScreenFrame.getContentPane().add(rdbtnNormalFarm);
		
		JRadioButton rdbtnRichFarm = new JRadioButton("Rich farm: $200 starting money, low animal happiness, 10 crop spaces.");
		rdbtnRichFarm.setActionCommand("Rich");
		buttonGroupFarmType.add(rdbtnRichFarm);
		rdbtnRichFarm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnRichFarm.setBounds(10, 339, 744, 33);
		setupScreenFrame.getContentPane().add(rdbtnRichFarm);
		
		JRadioButton rdbtnHappyFarm = new JRadioButton("Happy farm: $100 starting money, high animal happiness, 10 crop spaces.");
		rdbtnHappyFarm.setActionCommand("Happy");
		buttonGroupFarmType.add(rdbtnHappyFarm);
		rdbtnHappyFarm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnHappyFarm.setBounds(10, 375, 744, 33);
		setupScreenFrame.getContentPane().add(rdbtnHappyFarm);
		
		JRadioButton rdbtnLargeFarm = new JRadioButton("Large farm: $100 starting money, low animal happiness, but hey, at least it has 20 crop spaces!");
		rdbtnLargeFarm.setActionCommand("Large");
		buttonGroupFarmType.add(rdbtnLargeFarm);
		rdbtnLargeFarm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnLargeFarm.setBounds(10, 411, 744, 33);
		setupScreenFrame.getContentPane().add(rdbtnLargeFarm);
		
		JLabel lblFarmName = new JLabel("What is your farm's name?");
		lblFarmName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFarmName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFarmName.setBounds(10, 473, 586, 45);
		setupScreenFrame.getContentPane().add(lblFarmName);
		
		textFieldFarmName = new JTextField();
		textFieldFarmName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldFarmName.setColumns(10);
		textFieldFarmName.setBounds(617, 476, 423, 39);
		setupScreenFrame.getContentPane().add(textFieldFarmName);
		
		lblWarning = new JLabel("");
		lblWarning.setForeground(Color.RED);
		lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
		lblWarning.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWarning.setBounds(10, 530, 1164, 27);
		setupScreenFrame.getContentPane().add(lblWarning);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.setupGame(sliderDays.getValue(), textFieldName.getText(), buttonGroupFarmType.getSelection().getActionCommand(), textFieldFarmName.getText());
			}
		});
		btnStartGame.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnStartGame.setBounds(528, 555, 128, 45);
		setupScreenFrame.getContentPane().add(btnStartGame);
	}
	
	public void setWarningText(String warningType) {
		if (warningType == "") {
			lblWarning.setText("");
		}
		else {
			lblWarning.setText("Warning: Your " + warningType + " incorrect (must be between 3 and 15 characters and must not include numbers or special characters)");
		}
	}
}
