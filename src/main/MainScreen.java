package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

/**
 * Main screen class.
 * In this screen the user plays the game with the help of a digital interface.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class MainScreen 
{
	
	/**
	 * The main screen Frame, all elements of the frame are in here.
	 */
	private JFrame mainScreenFrame;
	
	/**
	 * The manager which is the GameEnviroment Class.
	 */
	private GameEnvironment manager;
	
	/**
	 * Constructor for the main screen. This constructor takes an incoming manager and makes it the manager of the screen.
	 * Then, the constructor calls initialise to initialise the screen and then makes the frame visible.
	 * @param incomingManager The manager for the screen.
	 */
	public MainScreen(GameEnvironment incomingManager) 
	{
		manager = incomingManager;
		initialize();
		mainScreenFrame.setVisible(true);
	}
	
	/**
	 * A function to close the main screen.
	 */
	public void closeWindow() 
	{
		mainScreenFrame.dispose();
	}
	
	/**
	 * A function that calls closeWindow to close the main screen. call this method if you want to close the screen.
	 */
	public void finishWindow() 
	{
		manager.closeMainScreen(this);
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
					MainScreen window = new MainScreen();
					window.mainScreenFrame.setVisible(true);
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
	public MainScreen() 
	{
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() 
	{
		mainScreenFrame = new JFrame();
		mainScreenFrame.setTitle("Farm Simulator - Main Screen");
		mainScreenFrame.setBounds(100, 100, 1200, 650);
		mainScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainScreenFrame.getContentPane().setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(10, 11, 1164, 78);
		headerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainScreenFrame.getContentPane().add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel farmStatusLabel = new JLabel("Farm Status");
		farmStatusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		farmStatusLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		farmStatusLabel.setBounds(10, 11, 151, 24);
		headerPanel.add(farmStatusLabel);
		
		JLabel currentMoneyLabel = new JLabel("Current Money: $" + manager.returnMoneyString());
		currentMoneyLabel.setHorizontalAlignment(SwingConstants.LEFT);
		currentMoneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		currentMoneyLabel.setBounds(10, 43, 347, 24);
		headerPanel.add(currentMoneyLabel);
		
		JLabel freeCropSpaceLabel = new JLabel("Free crop space: " + manager.returnFreeCropSpace());
		freeCropSpaceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		freeCropSpaceLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		freeCropSpaceLabel.setBounds(441, 43, 237, 24);
		headerPanel.add(freeCropSpaceLabel);
		
		JLabel dayLabel = new JLabel("Day: " + manager.returnDaysPassed() + " out of " + manager.getNumDays());
		dayLabel.setHorizontalAlignment(SwingConstants.LEFT);
		dayLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dayLabel.setBounds(898, 43, 185, 24);
		headerPanel.add(dayLabel);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(10, 100, 288, 500);
		mainScreenFrame.getContentPane().add(buttonsPanel);
		buttonsPanel.setLayout(null);
		
		JPanel noActionsRequiredPanel = new JPanel();
		noActionsRequiredPanel.setBounds(0, 0, 288, 135);
		buttonsPanel.add(noActionsRequiredPanel);
		noActionsRequiredPanel.setLayout(null);
		
		JButton visitStoreButton = new JButton("Visit Store");
		visitStoreButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				finishWindow();
				manager.launchStoreScreen();
			}
		});
		visitStoreButton.setBounds(10, 11, 268, 50);
		noActionsRequiredPanel.add(visitStoreButton);
		visitStoreButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton sleepButton = new JButton("Sleep (Move to next day)");
		sleepButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				manager.nextDay();
				JOptionPane.showMessageDialog(mainScreenFrame, "You have slept!");
				if (manager.gameFinishing())
				{
					JOptionPane.showMessageDialog(mainScreenFrame, manager.finishGame());
					finishWindow();
				}
				else 
				{
					finishWindow();
					manager.launchMainScreen();
				}
			}
		});
		sleepButton.setBounds(10, 73, 268, 50);
		noActionsRequiredPanel.add(sleepButton);
		sleepButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JPanel actionsRequiredPanel = new JPanel();
		actionsRequiredPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		actionsRequiredPanel.setBounds(0, 146, 288, 354);
		buttonsPanel.add(actionsRequiredPanel);
		actionsRequiredPanel.setLayout(null);
		
		JLabel requireActionsLabel = new JLabel("Require Actions: (" + (2 - manager.getActionsPerformed()) + " remaining)");
		requireActionsLabel.setHorizontalAlignment(SwingConstants.LEFT);
		requireActionsLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		requireActionsLabel.setBounds(10, 12, 268, 24);
		actionsRequiredPanel.add(requireActionsLabel);
		
		JButton tendToCropsButton = new JButton("Tend to crops");
		tendToCropsButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (manager.getCrops().size() > 0) 
				{
					finishWindow();
					manager.launchTendCropsScreen();
				}
				else 
				{
					JOptionPane.showMessageDialog(mainScreenFrame, "You have no crops to tend to, so no actions were used");
				}
			}
		});
		tendToCropsButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tendToCropsButton.setBounds(10, 47, 268, 50);
		actionsRequiredPanel.add(tendToCropsButton);
		
		JButton feedAnimalsButton = new JButton("Feed animals");
		feedAnimalsButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (manager.getAnimals().size() > 0 && manager.returnAnimalItemSize() > 0) 
				{
					finishWindow();
					manager.launchFeedAnimalsScreen();
				}
				else if (manager.getAnimals().size() > 0) 
				{
					JOptionPane.showMessageDialog(mainScreenFrame, "You have no items to feed your animals with, so no actions were used");
				}
				else if (manager.returnAnimalItemSize() > 0) 
				{
					JOptionPane.showMessageDialog(mainScreenFrame, "You have no animals to feed, so no actions were used");
				}
				else 
				{
					JOptionPane.showMessageDialog(mainScreenFrame, "You have no animals to feed nor any items to feed them with, so no actions were used");
				}
			}
		});
		feedAnimalsButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		feedAnimalsButton.setBounds(10, 108, 268, 50);
		actionsRequiredPanel.add(feedAnimalsButton);
		
		JButton playWithAnimalsButton = new JButton("Play With Animals");
		playWithAnimalsButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(mainScreenFrame, manager.playWithAnimals());
				finishWindow();
				manager.launchMainScreen();
			}
		});
		playWithAnimalsButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playWithAnimalsButton.setBounds(10, 169, 268, 50);
		actionsRequiredPanel.add(playWithAnimalsButton);
		
		JButton harvestCropsButton = new JButton("Harvest Crops");
		harvestCropsButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(mainScreenFrame, manager.harvestCrops());
				finishWindow();
				manager.launchMainScreen();
			}
		});
		harvestCropsButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		harvestCropsButton.setBounds(10, 230, 268, 50);
		actionsRequiredPanel.add(harvestCropsButton);
		
		JButton tendToFarmButton = new JButton("Tend to farm land");
		tendToFarmButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(mainScreenFrame, manager.tendFarmLand());
				finishWindow();
				manager.launchMainScreen();
			}
		});
		tendToFarmButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tendToFarmButton.setBounds(10, 293, 268, 50);
		actionsRequiredPanel.add(tendToFarmButton);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		statusPanel.setBounds(308, 100, 866, 500);
		mainScreenFrame.getContentPane().add(statusPanel);
		statusPanel.setLayout(null);
		
		JLabel statusCropsAnimalsLabel = new JLabel("Status of Crops and Animals");
		statusCropsAnimalsLabel.setBounds(10, 11, 846, 49);
		statusPanel.add(statusCropsAnimalsLabel);
		statusCropsAnimalsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusCropsAnimalsLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 846, 418);
		statusPanel.add(scrollPane);
		
		JTextArea cropsAnimalsStatusTextArea = new JTextArea();
		scrollPane.setViewportView(cropsAnimalsStatusTextArea);
		cropsAnimalsStatusTextArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cropsAnimalsStatusTextArea.setText(manager.returnStatusCropsAnimals());
		cropsAnimalsStatusTextArea.setEditable(false);
	}
}
