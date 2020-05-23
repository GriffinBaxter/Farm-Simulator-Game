package gui;

import main.GameEnvironment;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Feed animals screen class.
 * In this screen the user can feed their animals using a digital interface.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class FeedAnimalsScreen 
{
	/**
	 * The feed animals screen Frame, all elements of the frame are in here.
	 */
	private JFrame feedAnimalsScreenFrame;
	
	/**
	 * The manager which is the GameEnviroment Class.
	 */
	private GameEnvironment manager;
	
	/**
	 * Constructor for the feed animals screen. This constructor takes an incoming manager and makes it the manager of the screen.
	 * Then, the constructor calls initialise to initialise the screen and then makes the frame visible.
	 * @param incomingManager The manager for the screen.
	 */
	public FeedAnimalsScreen(GameEnvironment incomingManager) 
	{
		manager = incomingManager;
		initialize();
		feedAnimalsScreenFrame.setVisible(true);
	}
	
	/**
	 * A function to close the feed animals screen.
	 */
	public void closeWindow() 
	{
		feedAnimalsScreenFrame.dispose();
	}
	
	/**
	 * A function that calls closeWindow to close the feed animals screen. call this method if you want to close the screen.
	 */
	public void finishWindow() 
	{
		manager.closeFeedAnimalsScreen(this);
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
					FeedAnimalsScreen window = new FeedAnimalsScreen();
					window.feedAnimalsScreenFrame.setVisible(true);
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
	public FeedAnimalsScreen() 
	{
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() 
	{
		feedAnimalsScreenFrame = new JFrame();
		feedAnimalsScreenFrame.setTitle("Farm Simulator - Feed Animals");
		feedAnimalsScreenFrame.setBounds(100, 100, 1200, 650);
		feedAnimalsScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		feedAnimalsScreenFrame.getContentPane().setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		headerPanel.setBounds(10, 11, 1164, 78);
		feedAnimalsScreenFrame.getContentPane().add(headerPanel);
		
		JLabel feedAnimalsLabel = new JLabel("Feed Animals");
		feedAnimalsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		feedAnimalsLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		feedAnimalsLabel.setBounds(448, 11, 269, 56);
		headerPanel.add(feedAnimalsLabel);
		
		JButton goBackButton = new JButton("Go back");
		goBackButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				finishWindow();
				manager.launchMainScreen();
			}
		});
		goBackButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		goBackButton.setBounds(10, 11, 300, 56);
		headerPanel.add(goBackButton);
		
		JLabel actionsRemainingLabel = new JLabel("Actions: " + (2 - manager.getActionsPerformed()) + " remaining");
		actionsRemainingLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		actionsRemainingLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		actionsRemainingLabel.setBounds(860, 11, 294, 56);
		headerPanel.add(actionsRemainingLabel);
		
		JPanel feedAnimalsPanel = new JPanel();
		feedAnimalsPanel.setLayout(null);
		feedAnimalsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		feedAnimalsPanel.setBounds(10, 100, 1164, 500);
		feedAnimalsScreenFrame.getContentPane().add(feedAnimalsPanel);
		
		JLabel selectItemLabel = new JLabel("Select item");
		selectItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectItemLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		selectItemLabel.setBounds(425, 11, 316, 36);
		feedAnimalsPanel.add(selectItemLabel);
		
		JLabel selectItemLabel2 = new JLabel("Select the item you would like to feed all of your animals with:");
		selectItemLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		selectItemLabel2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectItemLabel2.setBounds(386, 47, 394, 22);
		feedAnimalsPanel.add(selectItemLabel2);
		
		JScrollPane selectItemScrollPane = new JScrollPane();
		selectItemScrollPane.setBounds(425, 80, 316, 409);
		feedAnimalsPanel.add(selectItemScrollPane);
		
		JList itemDisplayList = new JList();
		itemDisplayList.setFont(new Font("Tahoma", Font.PLAIN, 17));
		itemDisplayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemDisplayList.setModel(new AbstractListModel() 
		{
			String[] values = manager.returnCurrentItemsArray("Animal");
			public int getSize() 
			{
				return values.length;
			}
			public Object getElementAt(int index) 
			{
				return values[index];
			}
		});
		itemDisplayList.setSelectedIndex(0);
		selectItemScrollPane.setViewportView(itemDisplayList);
		
		JButton feedAllAnimalsButton = new JButton("Feed all animals");
		feedAllAnimalsButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(feedAnimalsScreenFrame, manager.feedAnimals(itemDisplayList.getSelectedValue().toString()));
				finishWindow();
				manager.launchFeedAnimalsScreen();
				
				
			}
		});
		feedAllAnimalsButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		feedAllAnimalsButton.setBounds(871, 233, 190, 56);
		feedAnimalsPanel.add(feedAllAnimalsButton);
		
		JLabel useActionLabel = new JLabel("Uses one action:");
		useActionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		useActionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		useActionLabel.setBounds(871, 200, 190, 22);
		feedAnimalsPanel.add(useActionLabel);
	}

}
