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

public class TendCropsScreen {

	private JFrame frame;
	private GameEnvironment manager;
	private JList listCrop;
	private JList listItem;

	public TendCropsScreen(GameEnvironment incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishWindow() {
		manager.closeTendCropsScreen(this);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TendCropsScreen window = new TendCropsScreen();
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
	public TendCropsScreen() {
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
		
		JPanel panelTendCropsHeader = new JPanel();
		panelTendCropsHeader.setLayout(null);
		panelTendCropsHeader.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelTendCropsHeader.setBounds(10, 11, 1164, 78);
		frame.getContentPane().add(panelTendCropsHeader);
		
		JLabel lblTendToCrops = new JLabel("Tend to Crops");
		lblTendToCrops.setHorizontalAlignment(SwingConstants.CENTER);
		lblTendToCrops.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTendToCrops.setBounds(448, 11, 269, 56);
		panelTendCropsHeader.add(lblTendToCrops);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishWindow();
				manager.launchMainScreen();
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGoBack.setBounds(10, 11, 300, 56);
		panelTendCropsHeader.add(btnGoBack);
		
		JLabel label = new JLabel("Actions: " + (2 - manager.getActionsPerformed()) + " remaining");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(860, 11, 294, 56);
		panelTendCropsHeader.add(label);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 100, 1164, 500);
		frame.getContentPane().add(panel);
		
		JLabel lblPleaseSelectCrop = new JLabel("Select crop");
		lblPleaseSelectCrop.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseSelectCrop.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblPleaseSelectCrop.setBounds(10, 11, 316, 36);
		panel.add(lblPleaseSelectCrop);
		
		JLabel label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(10, 116, 190, 36);
		panel.add(label_1);
		
		JLabel lblSelectTheType = new JLabel("Select the type of crop you would like to tend to:");
		lblSelectTheType.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTheType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectTheType.setBounds(10, 47, 316, 22);
		panel.add(lblSelectTheType);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 316, 409);
		panel.add(scrollPane);
		
		listCrop = new JList();
		listCrop.setFont(new Font("Tahoma", Font.PLAIN, 17));
		listCrop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCrop.setModel(new AbstractListModel() {
			String[] values = manager.returnCropTypeArray();
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listCrop.setSelectedIndex(0);
		scrollPane.setViewportView(listCrop);
		
		JLabel lblSelectTheItem = new JLabel("Select item");
		lblSelectTheItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTheItem.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSelectTheItem.setBounds(425, 11, 316, 36);
		panel.add(lblSelectTheItem);
		
		JLabel lblSelectTheItem_1 = new JLabel("Select the item you would like to tend all of that crop type with:");
		lblSelectTheItem_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTheItem_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectTheItem_1.setBounds(386, 47, 394, 22);
		panel.add(lblSelectTheItem_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(425, 80, 316, 409);
		panel.add(scrollPane_1);
		
		listItem = new JList();
		listItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listItem.setModel(new AbstractListModel() {
			String[] values = manager.returnCurrentItemsArray("Crop");
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listItem.setSelectedIndex(0);
		listItem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		scrollPane_1.setViewportView(listItem);
		
		JButton btnTendCrops = new JButton("Tend Crops");
		btnTendCrops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, manager.tendToCrops(listCrop.getSelectedIndex(), listItem.getSelectedIndex()));
				finishWindow();
				manager.launchTendCropsScreen();
			}
		});
		btnTendCrops.setBounds(871, 233, 190, 56);
		panel.add(btnTendCrops);
		btnTendCrops.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblUsesOneAction = new JLabel("Uses one action:");
		lblUsesOneAction.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsesOneAction.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsesOneAction.setBounds(871, 200, 190, 22);
		panel.add(lblUsesOneAction);
	}
}
