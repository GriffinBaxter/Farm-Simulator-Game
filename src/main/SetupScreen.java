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

public class SetupScreen 
{

	/**
	 * The setup screen Frame, all elements of the frame are in here.
	 */
	private JFrame setupScreenFrame;
	
	/**
	 * The manager which is the GameEnviroment Class.
	 */
	private GameEnvironment manager;
	
	/**
	 * The text field where the user enters their name into.
	 * Global variable to check whether the user entered a correct name.
	 */
	private JTextField nameTextField;
	
	/**
	 * The button group for the farm types.
	 */
	private final ButtonGroup farmTypeButtonGroup = new ButtonGroup();
	
	/**
	 * The text field where the user enters their farm name into.
	 * Global variable to check whether the user entered a correct farm name.
	 */
	private JTextField farmNameTextField;
	
	/**
	 * Warning label that shows a warning message if a user has entered an incorrect farmer name or farm name.
	 */
	private JLabel warningLabel;

	/**
	 * Constructor for the setup screen. This constructor takes an incoming manager and makes it the manager of the screen.
	 * Then, the constructor calls initialise to initialise the screen and then makes the frame visible.
	 * @param incomingManager The manager for the screen.
	 */
	public SetupScreen(GameEnvironment incomingManager) 
	{
		manager = incomingManager;
		initialize();
		setupScreenFrame.setVisible(true);
	}
	
	/**
	 * A function to close the setup screen.
	 */
	public void closeWindow() 
	{
		setupScreenFrame.dispose();
	}
	
	/**
	 * A function that calls closeWindow to close the setup screen. call this method if you want to close the screen.
	 */
	public void finishWindow() 
	{
		manager.closeSetupScreen(this);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					SetupScreen window = new SetupScreen();
					window.setupScreenFrame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SetupScreen() 
	{
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() 
	{
		setupScreenFrame = new JFrame();
		setupScreenFrame.setTitle("Farm Simulator");
		setupScreenFrame.setBounds(100, 100, 1200, 650);
		setupScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupScreenFrame.getContentPane().setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome to Farm Simulator!");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		welcomeLabel.setBounds(10, 11, 1164, 33);
		setupScreenFrame.getContentPane().add(welcomeLabel);
		
		JLabel daysLabel = new JLabel("How many days would you like the game to last?");
		daysLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		daysLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		daysLabel.setBounds(10, 68, 586, 45);
		setupScreenFrame.getContentPane().add(daysLabel);
		
		JSlider daysSlider = new JSlider();
		daysSlider.setValue(5);
		daysSlider.setPaintLabels(true);
		daysSlider.setMajorTickSpacing(1);
		daysSlider.setSnapToTicks(true);
		daysSlider.setPaintTicks(true);
		daysSlider.setMaximum(10);
		daysSlider.setMinimum(5);
		daysSlider.setBounds(617, 68, 515, 45);
		setupScreenFrame.getContentPane().add(daysSlider);
		
		JLabel nameLabel = new JLabel("What is your name?");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		nameLabel.setBounds(10, 124, 586, 45);
		setupScreenFrame.getContentPane().add(nameLabel);
		
		JLabel nameWarningLabel = new JLabel("(must be between 3 and 15 characters and must not include numbers or special characters)");
		nameWarningLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameWarningLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameWarningLabel.setBounds(10, 153, 586, 27);
		setupScreenFrame.getContentPane().add(nameWarningLabel);
		
		nameTextField = new JTextField();
		nameTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameTextField.setBounds(617, 137, 515, 39);
		setupScreenFrame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel selectFarmTypeLabel = new JLabel("Please select a farm type:");
		selectFarmTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectFarmTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		selectFarmTypeLabel.setBounds(10, 275, 1164, 45);
		setupScreenFrame.getContentPane().add(selectFarmTypeLabel);
		
		JRadioButton normalFarmRadioButton = new JRadioButton("Normal farm: $150 starting money, average animal happiness, 10 crop spaces, the default farm.");
		normalFarmRadioButton.setSelected(true);
		normalFarmRadioButton.setActionCommand("Normal");
		farmTypeButtonGroup.add(normalFarmRadioButton);
		normalFarmRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		normalFarmRadioButton.setBounds(220, 316, 744, 33);
		setupScreenFrame.getContentPane().add(normalFarmRadioButton);
		
		JRadioButton richFarmRadioButton = new JRadioButton("Rich farm: $200 starting money, low animal happiness, 10 crop spaces.");
		richFarmRadioButton.setActionCommand("Rich");
		farmTypeButtonGroup.add(richFarmRadioButton);
		richFarmRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		richFarmRadioButton.setBounds(220, 352, 744, 33);
		setupScreenFrame.getContentPane().add(richFarmRadioButton);
		
		JRadioButton happyFarmRadioButton = new JRadioButton("Happy farm: $100 starting money, high animal happiness, 10 crop spaces.");
		happyFarmRadioButton.setActionCommand("Happy");
		farmTypeButtonGroup.add(happyFarmRadioButton);
		happyFarmRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		happyFarmRadioButton.setBounds(220, 388, 744, 33);
		setupScreenFrame.getContentPane().add(happyFarmRadioButton);
		
		JRadioButton largeFarmRadioButton = new JRadioButton("Large farm: $100 starting money, low animal happiness, but hey, at least it has 20 crop spaces!");
		largeFarmRadioButton.setActionCommand("Large");
		farmTypeButtonGroup.add(largeFarmRadioButton);
		largeFarmRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		largeFarmRadioButton.setBounds(220, 424, 744, 33);
		setupScreenFrame.getContentPane().add(largeFarmRadioButton);
		
		JLabel farmNameLabel = new JLabel("What is your farm's name?");
		farmNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		farmNameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		farmNameLabel.setBounds(10, 487, 586, 45);
		setupScreenFrame.getContentPane().add(farmNameLabel);
		
		farmNameTextField = new JTextField();
		farmNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		farmNameTextField.setColumns(10);
		farmNameTextField.setBounds(617, 490, 515, 39);
		setupScreenFrame.getContentPane().add(farmNameTextField);
		
		warningLabel = new JLabel("");
		warningLabel.setForeground(Color.RED);
		warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
		warningLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		warningLabel.setBounds(10, 530, 1164, 27);
		setupScreenFrame.getContentPane().add(warningLabel);
		
		JLabel farmerAgeLabel = new JLabel("What is your farmer's age?");
		farmerAgeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		farmerAgeLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		farmerAgeLabel.setBounds(10, 197, 586, 45);
		setupScreenFrame.getContentPane().add(farmerAgeLabel);
		
		JSlider farmerAgeSlider = new JSlider();
		farmerAgeSlider.setMajorTickSpacing(5);
		farmerAgeSlider.setSnapToTicks(true);
		farmerAgeSlider.setPaintTicks(true);
		farmerAgeSlider.setPaintLabels(true);
		farmerAgeSlider.setValue(1);
		farmerAgeSlider.setMinimum(15);
		farmerAgeSlider.setBounds(617, 205, 515, 45);
		setupScreenFrame.getContentPane().add(farmerAgeSlider);
		
		JLabel farmerAgeInfoLabel = new JLabel("(there are 18 available presets for farmer age)");
		farmerAgeInfoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		farmerAgeInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		farmerAgeInfoLabel.setBounds(10, 226, 586, 27);
		setupScreenFrame.getContentPane().add(farmerAgeInfoLabel);
		
		JButton startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				manager.setupGame(daysSlider.getValue(), nameTextField.getText(), farmerAgeSlider.getValue(), farmTypeButtonGroup.getSelection().getActionCommand(), farmNameTextField.getText());
			}
		});
		startGameButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		startGameButton.setBounds(528, 555, 128, 45);
		setupScreenFrame.getContentPane().add(startGameButton);
	}
	
	/**
	 * Sets the text of the warningLabel. This is used when the user needs to be notified of an error in their name or farm name.
	 * @param warningType The type of warning the user needs to know about.
	 */
	public void setWarningText(String warningType) 
	{
		if (warningType == "") 
		{
			warningLabel.setText("");
		}
		else 
		{
			warningLabel.setText("Warning: Your " + warningType + " incorrect (must be between 3 and 15 characters and must not include numbers or special characters)");
		}
	}
}
