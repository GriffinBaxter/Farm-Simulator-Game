package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
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

public class StoreScreen {

	private JFrame frame;
	private GameEnvironment manager;
	private JList listCrops;
	private JList listAnimals;
	private JList listItems;
	private JLabel lblCropMessage;
	private JLabel lblAnimalMessage;
	private JLabel lblItemMessage;

	public StoreScreen(GameEnvironment incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishWindow() {
		manager.closeStoreScreen(this);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoreScreen window = new StoreScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StoreScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelStoreHeader = new JPanel();
		panelStoreHeader.setLayout(null);
		panelStoreHeader.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelStoreHeader.setBounds(10, 11, 1164, 78);
		frame.getContentPane().add(panelStoreHeader);
		
		JLabel lblStore = new JLabel("Store");
		lblStore.setHorizontalAlignment(SwingConstants.CENTER);
		lblStore.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblStore.setBounds(469, 11, 226, 56);
		panelStoreHeader.add(lblStore);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishWindow();
				manager.launchMainScreen();
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoBack.setBounds(10, 11, 300, 56);
		panelStoreHeader.add(btnGoBack);
		
		JButton btnViewCurrentlyOwned = new JButton("View currently owned items");
		btnViewCurrentlyOwned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(frame, manager.returnItemsString(), "Items owned", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnViewCurrentlyOwned.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnViewCurrentlyOwned.setBounds(854, 11, 300, 56);
		panelStoreHeader.add(btnViewCurrentlyOwned);
		
		JPanel panelCropsForSale = new JPanel();
		panelCropsForSale.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCropsForSale.setBounds(10, 100, 1164, 163);
		frame.getContentPane().add(panelCropsForSale);
		panelCropsForSale.setLayout(null);
		
		JLabel lblCropsForSale = new JLabel("Crops for sale");
		lblCropsForSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblCropsForSale.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCropsForSale.setBounds(10, 11, 300, 36);
		panelCropsForSale.add(lblCropsForSale);
		
		lblCropMessage = new JLabel("");
		lblCropMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblCropMessage.setForeground(Color.RED);
		lblCropMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCropMessage.setBounds(10, 116, 155, 36);
		panelCropsForSale.add(lblCropMessage);
		
		JButton btnBuyCrops = new JButton("Buy");
		btnBuyCrops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String test = manager.purchaseCrop(listCrops.getSelectedIndex());
				if (test.endsWith("bought!")) { // Might change it so even this case shows a message dialogue
					lblCropMessage.setText(test);
				}
				else {
					lblCropMessage.setText("");
					JOptionPane.showMessageDialog(frame, test);
				}
			}
		});
		btnBuyCrops.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBuyCrops.setBounds(175, 116, 135, 36);
		panelCropsForSale.add(btnBuyCrops);
		
		JLabel lblselectWhichCrop = new JLabel("Select which crop you would like to purchase");
		lblselectWhichCrop.setBounds(10, 47, 300, 22);
		panelCropsForSale.add(lblselectWhichCrop);
		lblselectWhichCrop.setHorizontalAlignment(SwingConstants.CENTER);
		lblselectWhichCrop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblThenPressThe = new JLabel("on the right, then press the buy button below");
		lblThenPressThe.setBounds(10, 68, 300, 22);
		panelCropsForSale.add(lblThenPressThe);
		lblThenPressThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblThenPressThe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 11, 834, 141);
		panelCropsForSale.add(scrollPane);
		
		listCrops = new JList();
		scrollPane.setViewportView(listCrops);
		listCrops.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCrops.setFont(new Font("Tahoma", Font.PLAIN, 17));
		listCrops.setModel(new AbstractListModel() {
			String[] values = manager.returnCropArray();
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listCrops.setSelectedIndex(0);
		listCrops.setToolTipText("");
		
		JPanel panelAnimalsForSale = new JPanel();
		panelAnimalsForSale.setLayout(null);
		panelAnimalsForSale.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAnimalsForSale.setBounds(10, 274, 1164, 152);
		frame.getContentPane().add(panelAnimalsForSale);
		
		JLabel lblAnimalsForSale = new JLabel("Animals for sale");
		lblAnimalsForSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnimalsForSale.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblAnimalsForSale.setBounds(10, 11, 300, 36);
		panelAnimalsForSale.add(lblAnimalsForSale);
		
		lblAnimalMessage = new JLabel("");
		lblAnimalMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnimalMessage.setForeground(Color.RED);
		lblAnimalMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAnimalMessage.setBounds(10, 101, 155, 36);
		panelAnimalsForSale.add(lblAnimalMessage);
		
		JButton btnBuyAnimals = new JButton("Buy");
		btnBuyAnimals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String test = manager.purchaseAnimal(listAnimals.getSelectedIndex());
				if (test.endsWith("bought!")) { // Might change it so even this case shows a message dialogue
					lblAnimalMessage.setText(test);
				}
				else {
					lblAnimalMessage.setText("");
					JOptionPane.showMessageDialog(frame, test);
				}
			}
		});
		btnBuyAnimals.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBuyAnimals.setBounds(175, 101, 135, 36);
		panelAnimalsForSale.add(btnBuyAnimals);
		
		JLabel lblSelectWhichAnimal = new JLabel("Select which animal you would like to purchase");
		lblSelectWhichAnimal.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectWhichAnimal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectWhichAnimal.setBounds(10, 47, 300, 22);
		panelAnimalsForSale.add(lblSelectWhichAnimal);
		
		JLabel lblThenPressThe_1 = new JLabel("on the right, then press the buy button below");
		lblThenPressThe_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblThenPressThe_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThenPressThe_1.setBounds(10, 68, 300, 22);
		panelAnimalsForSale.add(lblThenPressThe_1);
		
		JScrollPane scrollPaneAnimals = new JScrollPane();
		scrollPaneAnimals.setBounds(320, 11, 834, 72);
		panelAnimalsForSale.add(scrollPaneAnimals);
		
		listAnimals = new JList();
		scrollPaneAnimals.setViewportView(listAnimals);
		listAnimals.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listAnimals.setFont(new Font("Tahoma", Font.PLAIN, 17));
		listAnimals.setModel(new AbstractListModel() {
			String[] values = manager.returnAnimalArray();
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listAnimals.setSelectedIndex(0);
		listAnimals.setToolTipText("");
		
		JPanel panelItemsForSale = new JPanel();
		panelItemsForSale.setLayout(null);
		panelItemsForSale.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelItemsForSale.setBounds(10, 437, 1164, 163);
		frame.getContentPane().add(panelItemsForSale);
		
		JLabel lblItemsForSale = new JLabel("Items for sale");
		lblItemsForSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemsForSale.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblItemsForSale.setBounds(10, 11, 300, 36);
		panelItemsForSale.add(lblItemsForSale);
		
		JLabel lblItemMessage = new JLabel("");
		lblItemMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemMessage.setForeground(Color.RED);
		lblItemMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblItemMessage.setBounds(10, 116, 155, 36);
		panelItemsForSale.add(lblItemMessage);
		
		JButton button = new JButton("Buy");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String test = manager.purchaseItem(listItems.getSelectedIndex());
				if (test.endsWith("bought!")) { // Might change it so even this case shows a message dialogue
					lblItemMessage.setText(test);
				}
				else {
					lblItemMessage.setText("");
					JOptionPane.showMessageDialog(frame, test);
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(175, 116, 135, 36);
		panelItemsForSale.add(button);
		
		JLabel lblSelectWhichItem = new JLabel("Select which item you would like to purchase");
		lblSelectWhichItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectWhichItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectWhichItem.setBounds(10, 47, 300, 22);
		panelItemsForSale.add(lblSelectWhichItem);
		
		JLabel lblThenPressThe_2 = new JLabel("on the right, then press the buy button below");
		lblThenPressThe_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblThenPressThe_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblThenPressThe_2.setBounds(10, 68, 300, 22);
		panelItemsForSale.add(lblThenPressThe_2);
		
		JScrollPane scrollPaneItems = new JScrollPane();
		scrollPaneItems.setBounds(320, 11, 834, 141);
		panelItemsForSale.add(scrollPaneItems);
		
		listItems = new JList();
		scrollPaneItems.setViewportView(listItems);
		listItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listItems.setFont(new Font("Tahoma", Font.PLAIN, 17));
		listItems.setModel(new AbstractListModel() {
			String[] values = manager.returnItemArray();
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listItems.setSelectedIndex(0);
		listItems.setToolTipText("");
		
	}
}
