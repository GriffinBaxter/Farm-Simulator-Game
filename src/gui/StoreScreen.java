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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

/**
 * Store screen class.
 * In this screen the user buys crops, animals and items using a digital interface.
 * @author Griffin Baxter and Rutger van Kruiningen
 */
public class StoreScreen 
{

	/**
	 * The store Frame, all elements of the frame are in here.
	 */
	private JFrame storeFrame;
	
	/**
	 * The manager which is the GameEnviroment Class.
	 */
	private GameEnvironment manager;
	
	/**
	 * List of crops in a Jlist.
	 */
	private JList cropsList;
	
	/**
	 * List of animals in a Jlist.
	 */
	private JList animalsList;
	
	/**
	 * List of items in a Jlist.
	 */
	private JList itemsList;
	
	/**
	 * Label that displays if a crop has been purchased.
	 */
	private JLabel cropMessageLabel;
	
	/**
	 * Label that displays if a animal has been purchased.
	 */
	private JLabel animalMessageLabel;
	
	/**
	 * Label that displays if a item has been purchased.
	 */
	private JLabel itemMessageLabel;
	
	/**
	 * Label that displays the money available.
	 */
	private JLabel CurrentMoneyLabel;

	/**
	 * Constructor for the store screen. This constructor takes an incoming manager and makes it the manager of the screen.
	 * Then, the constructor calls initialise to initialise the screen and then makes the frame visible.
	 * @param incomingManager The manager for the screen.
	 */
	public StoreScreen(GameEnvironment incomingManager) 
	{
		manager = incomingManager;
		initialize();
		storeFrame.setVisible(true);
	}
	
	/**
	 * A function to close the store screen.
	 */
	public void closeWindow() 
	{
		storeFrame.dispose();
	}
	
	/**
	 * A function that calls closeWindow to close the store screen. call this method if you want to close the screen.
	 */
	public void finishWindow() 
	{
		manager.closeStoreScreen(this);
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
					StoreScreen window = new StoreScreen();
					window.storeFrame.setVisible(true);
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
	public StoreScreen() 
	{
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() 
	{
		storeFrame = new JFrame();
		storeFrame.setTitle("Farm Simulator - Store");
		storeFrame.setBounds(100, 100, 1200, 650);
		storeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		storeFrame.getContentPane().setLayout(null);
		
		JPanel storeHeaderPanel = new JPanel();
		storeHeaderPanel.setLayout(null);
		storeHeaderPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		storeHeaderPanel.setBounds(10, 11, 1164, 78);
		storeFrame.getContentPane().add(storeHeaderPanel);
		
		CurrentMoneyLabel = new JLabel("Current Money: $<dynamic>");
		setCurrentMoneyLabel();
		CurrentMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CurrentMoneyLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		CurrentMoneyLabel.setBounds(408, 43, 347, 24);
		storeHeaderPanel.add(CurrentMoneyLabel);
		
		JLabel storeLabel = new JLabel("Store");
		storeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		storeLabel.setFont(new Font("Tahoma", Font.BOLD, 32));
		storeLabel.setBounds(469, 11, 226, 29);
		storeHeaderPanel.add(storeLabel);
		
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
		storeHeaderPanel.add(goBackButton);
		
		JButton viewCurrentlyOwnedButton = new JButton("View currently owned items");
		viewCurrentlyOwnedButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(storeFrame, manager.returnItemsString(), "Items owned", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		viewCurrentlyOwnedButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		viewCurrentlyOwnedButton.setBounds(854, 11, 300, 56);
		storeHeaderPanel.add(viewCurrentlyOwnedButton);
		
		JPanel cropsForSalePanel = new JPanel();
		cropsForSalePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		cropsForSalePanel.setBounds(10, 100, 1164, 163);
		storeFrame.getContentPane().add(cropsForSalePanel);
		cropsForSalePanel.setLayout(null);
		
		JLabel cropsForSaleLabel = new JLabel("Crops for sale");
		cropsForSaleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cropsForSaleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		cropsForSaleLabel.setBounds(10, 11, 300, 36);
		cropsForSalePanel.add(cropsForSaleLabel);
		
		cropMessageLabel = new JLabel("");
		cropMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cropMessageLabel.setForeground(Color.RED);
		cropMessageLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		cropMessageLabel.setBounds(10, 116, 190, 36);
		cropsForSalePanel.add(cropMessageLabel);
		
		JButton buyCropButton = new JButton("Buy");
		buyCropButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String printMessage = manager.purchaseCrop(cropsList.getSelectedIndex());
				if (printMessage.endsWith("bought!")) 
				{
					cropMessageLabel.setText(printMessage);
					animalMessageLabel.setText("");
					itemMessageLabel.setText("");
				}
				else 
				{
					cropMessageLabel.setText("");
					JOptionPane.showMessageDialog(storeFrame, printMessage);
				}
				setCurrentMoneyLabel();
			}
		});
		buyCropButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buyCropButton.setBounds(210, 116, 100, 36);
		cropsForSalePanel.add(buyCropButton);
		
		JLabel selectCropLabel = new JLabel("Select which crop you would like to purchase");
		selectCropLabel.setBounds(10, 47, 300, 22);
		cropsForSalePanel.add(selectCropLabel);
		selectCropLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectCropLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel selectCropLabel2 = new JLabel("on the right, then press the buy button below");
		selectCropLabel2.setBounds(10, 68, 300, 22);
		cropsForSalePanel.add(selectCropLabel2);
		selectCropLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		selectCropLabel2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane cropsScrollPane = new JScrollPane();
		cropsScrollPane.setBounds(320, 11, 834, 141);
		cropsForSalePanel.add(cropsScrollPane);
		
		cropsList = new JList();
		cropsScrollPane.setViewportView(cropsList);
		cropsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cropsList.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cropsList.setModel(new AbstractListModel() 
		{
			String[] values = manager.returnCropArray();
			public int getSize() 
			{
				return values.length;
			}
			public Object getElementAt(int index) 
			{
				return values[index];
			}
		});
		cropsList.setSelectedIndex(0);
		cropsList.setToolTipText("");
		
		JPanel animalsForSalePanel = new JPanel();
		animalsForSalePanel.setLayout(null);
		animalsForSalePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		animalsForSalePanel.setBounds(10, 274, 1164, 152);
		storeFrame.getContentPane().add(animalsForSalePanel);
		
		JLabel animalsForSaleLabel = new JLabel("Animals for sale");
		animalsForSaleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		animalsForSaleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		animalsForSaleLabel.setBounds(10, 11, 300, 36);
		animalsForSalePanel.add(animalsForSaleLabel);
		
		animalMessageLabel = new JLabel("");
		animalMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		animalMessageLabel.setForeground(Color.RED);
		animalMessageLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		animalMessageLabel.setBounds(10, 101, 190, 36);
		animalsForSalePanel.add(animalMessageLabel);
		
		JButton buyAnimalButton = new JButton("Buy");
		buyAnimalButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String message = manager.purchaseAnimal(animalsList.getSelectedIndex());
				if (message.endsWith("bought!")) 
				{
					cropMessageLabel.setText("");
					animalMessageLabel.setText(message);
					itemMessageLabel.setText("");
				}
				else 
				{
					animalMessageLabel.setText("");
					JOptionPane.showMessageDialog(storeFrame, message);
				}
				setCurrentMoneyLabel();
			}
		});
		buyAnimalButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buyAnimalButton.setBounds(210, 101, 100, 36);
		animalsForSalePanel.add(buyAnimalButton);
		
		JLabel selectAnimalLabel = new JLabel("Select which animal you would like to purchase");
		selectAnimalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectAnimalLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectAnimalLabel.setBounds(10, 47, 300, 22);
		animalsForSalePanel.add(selectAnimalLabel);
		
		JLabel selectAnimalLabel2 = new JLabel("on the right, then press the buy button below");
		selectAnimalLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		selectAnimalLabel2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectAnimalLabel2.setBounds(10, 68, 300, 22);
		animalsForSalePanel.add(selectAnimalLabel2);
		
		JScrollPane animalsScrollPane = new JScrollPane();
		animalsScrollPane.setBounds(320, 11, 834, 126);
		animalsForSalePanel.add(animalsScrollPane);
		
		animalsList = new JList();
		animalsScrollPane.setViewportView(animalsList);
		animalsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		animalsList.setFont(new Font("Tahoma", Font.PLAIN, 17));
		animalsList.setModel(new AbstractListModel() 
		{
			String[] values = manager.returnAnimalArray();
			public int getSize() 
			{
				return values.length;
			}
			public Object getElementAt(int index) 
			{
				return values[index];
			}
		});
		animalsList.setSelectedIndex(0);
		animalsList.setToolTipText("");
		
		JPanel itemsForSalePanel = new JPanel();
		itemsForSalePanel.setLayout(null);
		itemsForSalePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		itemsForSalePanel.setBounds(10, 437, 1164, 163);
		storeFrame.getContentPane().add(itemsForSalePanel);
		
		JLabel itemsForSaleLabel = new JLabel("Items for sale");
		itemsForSaleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		itemsForSaleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		itemsForSaleLabel.setBounds(10, 11, 300, 36);
		itemsForSalePanel.add(itemsForSaleLabel);
		
		itemMessageLabel = new JLabel("");
		itemMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		itemMessageLabel.setForeground(Color.RED);
		itemMessageLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		itemMessageLabel.setBounds(10, 116, 190, 36);
		itemsForSalePanel.add(itemMessageLabel);
		
		JButton buyItemButton = new JButton("Buy");
		buyItemButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String message = manager.purchaseItem(itemsList.getSelectedIndex());
				if (message.endsWith("bought!")) 
				{
					cropMessageLabel.setText("");
					animalMessageLabel.setText("");
					itemMessageLabel.setText(message);
				}
				else 
				{
					itemMessageLabel.setText("");
					JOptionPane.showMessageDialog(storeFrame, message);
				}
				setCurrentMoneyLabel();
			}
		});
		buyItemButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buyItemButton.setBounds(210, 116, 100, 36);
		itemsForSalePanel.add(buyItemButton);
		
		JLabel selectItemLabel = new JLabel("Select which item you would like to purchase");
		selectItemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectItemLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectItemLabel.setBounds(10, 47, 300, 22);
		itemsForSalePanel.add(selectItemLabel);
		
		JLabel selectItemLabel2 = new JLabel("on the right, then press the buy button below");
		selectItemLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		selectItemLabel2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		selectItemLabel2.setBounds(10, 68, 300, 22);
		itemsForSalePanel.add(selectItemLabel2);
		
		JScrollPane itemsScrollPane = new JScrollPane();
		itemsScrollPane.setBounds(320, 11, 834, 141);
		itemsForSalePanel.add(itemsScrollPane);
		
		itemsList = new JList();
		itemsScrollPane.setViewportView(itemsList);
		itemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemsList.setFont(new Font("Tahoma", Font.PLAIN, 17));
		itemsList.setModel(new AbstractListModel() 
		{
			String[] values = manager.returnItemArray();
			public int getSize() 
			{
				return values.length;
			}
			public Object getElementAt(int index) 
			{
				return values[index];
			}
		});
		itemsList.setSelectedIndex(0);
		itemsList.setToolTipText("");
		
	}
	
	/**
	 * Sets the current money label <code>CurrentMoneyLabel</code> with the amount of money the farm has.
	 */
	public void setCurrentMoneyLabel()
	{
		CurrentMoneyLabel.setText("Current Money: $" + manager.returnDollarsCents(manager.getCurrentMoney()));
	}
}
