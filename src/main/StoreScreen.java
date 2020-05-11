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
	private JLabel lblCropMessage;

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
		btnViewCurrentlyOwned.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnViewCurrentlyOwned.setBounds(854, 11, 300, 56);
		panelStoreHeader.add(btnViewCurrentlyOwned);
		
		JPanel panelCropsForSale = new JPanel();
		panelCropsForSale.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCropsForSale.setBounds(10, 100, 1164, 150);
		frame.getContentPane().add(panelCropsForSale);
		panelCropsForSale.setLayout(null);
		
		JLabel lblCropsForSale = new JLabel("Crops for sale");
		lblCropsForSale.setHorizontalAlignment(SwingConstants.CENTER);
		lblCropsForSale.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCropsForSale.setBounds(10, 11, 385, 36);
		panelCropsForSale.add(lblCropsForSale);
		
		lblCropMessage = new JLabel("");
		lblCropMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblCropMessage.setForeground(Color.RED);
		lblCropMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCropMessage.setBounds(10, 101, 240, 36);
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
		btnBuyCrops.setBounds(260, 101, 135, 36);
		panelCropsForSale.add(btnBuyCrops);
		
		JLabel lblselectWhichCrop = new JLabel("Select which crop you would like to purchase on the right");
		lblselectWhichCrop.setBounds(10, 47, 385, 22);
		panelCropsForSale.add(lblselectWhichCrop);
		lblselectWhichCrop.setHorizontalAlignment(SwingConstants.CENTER);
		lblselectWhichCrop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblThenPressThe = new JLabel("(you can use the scroll bar), then press the buy button below");
		lblThenPressThe.setBounds(10, 68, 385, 22);
		panelCropsForSale.add(lblThenPressThe);
		lblThenPressThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblThenPressThe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(405, 11, 749, 128);
		panelCropsForSale.add(scrollPane);
		
		listCrops = new JList();
		scrollPane.setViewportView(listCrops);
		listCrops.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCrops.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
	}
}
