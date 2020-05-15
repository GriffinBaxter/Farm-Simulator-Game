package main;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class MainScreen {

	private JFrame frmSengFarm;
	private GameEnvironment manager;
	
	public MainScreen(GameEnvironment incomingManager) {
		manager = incomingManager;
		initialize();
		frmSengFarm.setVisible(true);
	}
	
	public void closeWindow() {
		frmSengFarm.dispose();
	}
	
	public void finishWindow() {
		manager.closeMainScreen(this);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frmSengFarm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frmSengFarm = new JFrame();
		frmSengFarm.setTitle("SENG 201 Farm Simulator Project - By Griffin Baxter and Rutger van Kruiningen");
		frmSengFarm.setBounds(100, 100, 1200, 650);
		frmSengFarm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSengFarm.getContentPane().setLayout(null);
		
		JPanel panelFarmStatus = new JPanel();
		panelFarmStatus.setBounds(10, 11, 1164, 78);
		panelFarmStatus.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmSengFarm.getContentPane().add(panelFarmStatus);
		panelFarmStatus.setLayout(null);
		
		JLabel lblFarmStatus = new JLabel("Farm Status");
		lblFarmStatus.setHorizontalAlignment(SwingConstants.LEFT);
		lblFarmStatus.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblFarmStatus.setBounds(10, 11, 151, 24);
		panelFarmStatus.add(lblFarmStatus);
		
		JLabel lblCurrentMoney = new JLabel("Current Money: $" + manager.returnMoneyString());
		lblCurrentMoney.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurrentMoney.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCurrentMoney.setBounds(10, 43, 347, 24);
		panelFarmStatus.add(lblCurrentMoney);
		
		JLabel lblFreeCropSpace = new JLabel("Free crop space: " + manager.returnFreeCropSpace());
		lblFreeCropSpace.setHorizontalAlignment(SwingConstants.LEFT);
		lblFreeCropSpace.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFreeCropSpace.setBounds(441, 43, 237, 24);
		panelFarmStatus.add(lblFreeCropSpace);
		
		JLabel lblDayOutOf = new JLabel("Day: " + manager.returnDays() + " out of " + manager.getNumDays());
		lblDayOutOf.setHorizontalAlignment(SwingConstants.LEFT);
		lblDayOutOf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDayOutOf.setBounds(898, 43, 185, 24);
		panelFarmStatus.add(lblDayOutOf);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBounds(10, 100, 288, 500);
		frmSengFarm.getContentPane().add(panelButtons);
		panelButtons.setLayout(null);
		
		JPanel panelNoActionsReq = new JPanel();
		panelNoActionsReq.setBounds(0, 0, 288, 135);
		panelButtons.add(panelNoActionsReq);
		panelNoActionsReq.setLayout(null);
		
		JButton btnVisitStore = new JButton("Visit Store");
		btnVisitStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishWindow();
				manager.launchStoreScreen();
			}
		});
		btnVisitStore.setBounds(10, 11, 268, 50);
		panelNoActionsReq.add(btnVisitStore);
		btnVisitStore.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton BtnSleep = new JButton("Sleep (Move to next day)");
		BtnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				manager.nextDay();
				JOptionPane.showMessageDialog(frmSengFarm, "You have slept!");
				if (manager.gameFinishing())
				{
					JOptionPane.showMessageDialog(frmSengFarm, manager.finishGame());
					finishWindow();
				}
				else {
					finishWindow();
					manager.launchMainScreen();
				}
			}
		});
		BtnSleep.setBounds(10, 73, 268, 50);
		panelNoActionsReq.add(BtnSleep);
		BtnSleep.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JPanel panelActionsReq = new JPanel();
		panelActionsReq.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelActionsReq.setBounds(0, 146, 288, 354);
		panelButtons.add(panelActionsReq);
		panelActionsReq.setLayout(null);
		
		JLabel lblRequireActions = new JLabel("Require Actions: (" + (2 - manager.getActionsPerformed()) + " remaining)");
		lblRequireActions.setHorizontalAlignment(SwingConstants.LEFT);
		lblRequireActions.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRequireActions.setBounds(10, 12, 268, 24);
		panelActionsReq.add(lblRequireActions);
		
		JButton btnTendToCrops = new JButton("Tend to crops");
		btnTendToCrops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (manager.getCrops().size() > 0) {
					finishWindow();
					manager.launchTendCropsScreen();
				}
				else {
					JOptionPane.showMessageDialog(frmSengFarm, "You have no crops to tend to, so no actions were used");
				}
			}
		});
		btnTendToCrops.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnTendToCrops.setBounds(10, 47, 268, 50);
		panelActionsReq.add(btnTendToCrops);
		
		JButton btnFeedAnimals = new JButton("Feed animals");
		btnFeedAnimals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (manager.getAnimals().size() > 0 && manager.returnAnimalItemSize() > 0) {
					finishWindow();
					manager.launchFeedAnimalsScreen();
				}
				else if (manager.getAnimals().size() > 0) {
					JOptionPane.showMessageDialog(frmSengFarm, "You have no items to feed your animals with, so no actions were used");
				}
				else if (manager.returnAnimalItemSize() > 0) {
					JOptionPane.showMessageDialog(frmSengFarm, "You have no animals to feed, so no actions were used");
				}
				else {
					JOptionPane.showMessageDialog(frmSengFarm, "You have no animals to feed nor any items to feed them with, so no actions were used");
				}
			}
		});
		btnFeedAnimals.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFeedAnimals.setBounds(10, 108, 268, 50);
		panelActionsReq.add(btnFeedAnimals);
		
		JButton btnPlayWithAnimals = new JButton("Play With Animals");
		btnPlayWithAnimals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmSengFarm, manager.playWithAnimals());
				finishWindow();
				manager.launchMainScreen();
			}
		});
		btnPlayWithAnimals.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPlayWithAnimals.setBounds(10, 169, 268, 50);
		panelActionsReq.add(btnPlayWithAnimals);
		
		JButton btnHarvestCrops = new JButton("Harvest Crops");
		btnHarvestCrops.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmSengFarm, manager.harvestCrops());
				finishWindow();
				manager.launchMainScreen();
			}
		});
		btnHarvestCrops.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHarvestCrops.setBounds(10, 230, 268, 50);
		panelActionsReq.add(btnHarvestCrops);
		
		JButton btnTendToFarm = new JButton("Tend to farm land");
		btnTendToFarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmSengFarm, manager.tendFarmLand());
				finishWindow();
				manager.launchMainScreen();
			}
		});
		btnTendToFarm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnTendToFarm.setBounds(10, 293, 268, 50);
		panelActionsReq.add(btnTendToFarm);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(308, 100, 866, 500);
		frmSengFarm.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblStatusOfCrops = new JLabel("Status of Crops and Animals");
		lblStatusOfCrops.setBounds(10, 11, 846, 49);
		panel.add(lblStatusOfCrops);
		lblStatusOfCrops.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatusOfCrops.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 846, 418);
		panel.add(scrollPane);
		
		JTextArea txtCropsAnimalsStatus = new JTextArea();
		scrollPane.setViewportView(txtCropsAnimalsStatus);
		txtCropsAnimalsStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCropsAnimalsStatus.setText(manager.returnStatusCropsAnimals());
		txtCropsAnimalsStatus.setEditable(false);
	}
}
