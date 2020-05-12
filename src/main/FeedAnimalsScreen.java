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
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FeedAnimalsScreen {

	private JFrame frame;
	private GameEnvironment manager;
	
	public FeedAnimalsScreen(GameEnvironment incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishWindow() {
		manager.closeFeedAnimalsScreen(this);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeedAnimalsScreen window = new FeedAnimalsScreen();
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
	public FeedAnimalsScreen() {
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
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 1164, 78);
		frame.getContentPane().add(panel);
		
		JLabel lblFeedAnimals = new JLabel("Feed Animals");
		lblFeedAnimals.setHorizontalAlignment(SwingConstants.CENTER);
		lblFeedAnimals.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblFeedAnimals.setBounds(448, 11, 269, 56);
		panel.add(lblFeedAnimals);
		
		JButton button = new JButton("Go back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishWindow();
				manager.launchMainScreen();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(10, 11, 300, 56);
		panel.add(button);
		
		JLabel label_1 = new JLabel("Actions: " + (2 - manager.getActionsPerformed()) + " remaining");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(860, 11, 294, 56);
		panel.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(10, 100, 1164, 500);
		frame.getContentPane().add(panel_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(10, 116, 190, 36);
		panel_1.add(label_2);
		
		JLabel label_4 = new JLabel("Select item");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 24));
		label_4.setBounds(425, 11, 316, 36);
		panel_1.add(label_4);
		
		JLabel lblSelectTheItem = new JLabel("Select the item you would like to feed all of your animals with:");
		lblSelectTheItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTheItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectTheItem.setBounds(386, 47, 394, 22);
		panel_1.add(lblSelectTheItem);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(425, 80, 316, 409);
		panel_1.add(scrollPane_1);
		
		JList listItem = new JList();
		listItem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		listItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listItem.setModel(new AbstractListModel() {
			String[] values = manager.returnCurrentItemsArray("Animal");
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listItem.setSelectedIndex(0);
		scrollPane_1.setViewportView(listItem);
		
		JButton btnFeedAllAnimals = new JButton("Feed all animals");
		btnFeedAllAnimals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, manager.feedAnimals(listItem.getSelectedIndex()));
				finishWindow();
				manager.launchFeedAnimalsScreen();
				
				
			}
		});
		btnFeedAllAnimals.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFeedAllAnimals.setBounds(871, 233, 190, 56);
		panel_1.add(btnFeedAllAnimals);
		
		JLabel label_6 = new JLabel("Uses one action:");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_6.setBounds(871, 200, 190, 22);
		panel_1.add(label_6);
	}

}
