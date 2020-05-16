package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;

public class TendCropsScreen 
{
	/**
	 * The tend crops screen Frame, all elements of the frame are in here.
	 */
	private JFrame tendCropsScreenFrame;
	
	/**
	 * The manager which is the GameEnviroment Class.
	 */
	private GameEnvironment manager;

	/**
	 * Constructor for the tend to crops screen. This constructor takes an incoming manager and makes it the manager of the screen.
	 * Then, the constructor calls initialise to initialise the screen and then makes the frame visible.
	 * @param incomingManager The manager for the screen.
	 */
	public TendCropsScreen(GameEnvironment incomingManager) 
	{
		manager = incomingManager;
		initialize();
		tendCropsScreenFrame.setVisible(true);
	}
	
	/**
	 * A function to close the tend to crops screen.
	 */
	public void closeWindow() 
	{
		tendCropsScreenFrame.dispose();
	}
	
	/**
	 * A function that calls closeWindow to close the tend to crops screen. call this method if you want to close the screen.
	 */
	public void finishWindow() 
	{
		manager.closeTendCropsScreen(this);
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
					TendCropsScreen window = new TendCropsScreen();
					window.tendCropsScreenFrame.setVisible(true);
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
	public TendCropsScreen() 
	{
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() 
	{
		tendCropsScreenFrame = new JFrame();
		tendCropsScreenFrame.setBounds(100, 100, 1200, 650);
		tendCropsScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tendCropsScreenFrame.getContentPane().setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(null);
		headerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		headerPanel.setBounds(10, 11, 1164, 78);
		tendCropsScreenFrame.getContentPane().add(headerPanel);
		
		JLabel tendToCropsLabel = new JLabel("Tend to Crops");
		tendToCropsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tendToCropsLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		tendToCropsLabel.setBounds(448, 11, 269, 56);
		headerPanel.add(tendToCropsLabel);
		
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
		
		JPanel tendCropsPanel = new JPanel();
		tendCropsPanel.setLayout(null);
		tendCropsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		tendCropsPanel.setBounds(10, 100, 1164, 500);
		tendCropsScreenFrame.getContentPane().add(tendCropsPanel);
		
		JLabel selectCropLabel = new JLabel("Select crop");
		selectCropLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectCropLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		selectCropLabel.setBounds(10, 11, 316, 36);
		tendCropsPanel.add(selectCropLabel);
		
		JLabel selectCropLabel2 = new JLabel("Select the type of crop you would like to tend to:");
		selectCropLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		selectCropLabel2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectCropLabel2.setBounds(10, 47, 316, 22);
		tendCropsPanel.add(selectCropLabel2);
		
		JScrollPane selectAnimalScrollPane = new JScrollPane();
		selectAnimalScrollPane.setBounds(10, 80, 316, 409);
		tendCropsPanel.add(selectAnimalScrollPane);
		
		JList cropDisplayList = new JList();
		cropDisplayList.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cropDisplayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cropDisplayList.setModel(new AbstractListModel() 
		{
			String[] values = manager.returnCropTypeArray();
			public int getSize() 
			{
				return values.length;
			}
			public Object getElementAt(int index) 
			{
				return values[index];
			}
		});
		cropDisplayList.setSelectedIndex(0);
		selectAnimalScrollPane.setViewportView(cropDisplayList);
		
		JLabel selectItemLabel = new JLabel("Select item");
		selectItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectItemLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		selectItemLabel.setBounds(425, 11, 316, 36);
		tendCropsPanel.add(selectItemLabel);
		
		JLabel selectItemLabel2 = new JLabel("Select the item you would like to tend all of that crop type with:");
		selectItemLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		selectItemLabel2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectItemLabel2.setBounds(386, 47, 394, 22);
		tendCropsPanel.add(selectItemLabel2);
		
		JScrollPane selectItemScrollPane = new JScrollPane();
		selectItemScrollPane.setBounds(425, 80, 316, 409);
		tendCropsPanel.add(selectItemScrollPane);
		
		JList itemDisplayList = new JList();
		itemDisplayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemDisplayList.setModel(new AbstractListModel() 
		{
			String[] values = manager.returnCurrentItemsArray("Crop");
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
		itemDisplayList.setFont(new Font("Tahoma", Font.PLAIN, 17));
		selectItemScrollPane.setViewportView(itemDisplayList);
		
		JButton tendCropsButton = new JButton("Tend Crops");
		tendCropsButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(tendCropsScreenFrame, manager.tendToCrops(cropDisplayList.getSelectedIndex(), itemDisplayList.getSelectedIndex()));
				finishWindow();
				manager.launchTendCropsScreen();
			}
		});
		tendCropsButton.setBounds(871, 233, 190, 56);
		tendCropsPanel.add(tendCropsButton);
		tendCropsButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel useActionLabel = new JLabel("Uses one action:");
		useActionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		useActionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		useActionLabel.setBounds(871, 200, 190, 22);
		tendCropsPanel.add(useActionLabel);
	}
}
