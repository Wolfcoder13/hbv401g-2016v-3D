package hbv.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import hbv.controller.SearchManager;
import hbv.model.Tour;

// Við bjuggum til nokkrar útgáfur af þessum klasa, sem allir implementa Display,
// fyrir prófanir í staðinn fyrir að vera með settera fyrir breyturnar þar sem 
// okkur fannst klasinn vera orðinn ansi stór fyrir mockObject.
public class MockDisplay extends JFrame implements Display{
	private static final long serialVersionUID = 1L;
	
	private GridLayout frameGrid;
	private GridLayout leftGrid;
	private GridLayout rightGrid;
	private FlowLayout panelsLeft;
	
	private JPanel leftPan;
	private JPanel labelsPan;
	private JPanel inputsPan;
	
	private JPanel durationPan;
	private JPanel pricePan;
	private JPanel tourTypePan;
	private JPanel datePan;
	private JPanel seatsPan;
	private JPanel namePan;
	private JPanel destinationPan;
	private JPanel departurePan;
	private JPanel searchPan;
	
	
	private JPanel rightPan;
	private JPanel listPan;
	private JPanel descPan;
	
	private JPanel durationLabelPan;
	private JPanel priceLabelPan;
	private JPanel tourTypeLabelPan;
	private JPanel dateLabelPan;
	private JPanel seatsLabelPan;
	private JPanel nameLabelPan;
	private JPanel destinationLabelPan;
	private JPanel departureLabelPan;
	
	private JLabel durationLab;
	private JLabel fromDurationLab;
	private JComboBox<String> durationFromCom;
	private JLabel durationToLab;
	private JComboBox<String> durationToCom;
	private JLabel priceLab;
	private JLabel fromPriceLab;
	private JComboBox<String> priceFromCom;
	private JLabel priceToLab;
	private JComboBox<String> priceToCom;
	private JLabel typeLab;
	private JComboBox<String> typeCom;
	private JLabel dateLab;
	private JLabel fromDateLab;
	private JDateChooser calendarFrom;
	private JLabel dateToLab;
	private JDateChooser calendarTo;
	private JLabel seatsLab;
	private JTextField seatsTxt;
	private JLabel seatsLabError;
	private JLabel nameLab;
	private JTextField nameTxt;
	private JLabel destinationLab;
	private JTextField destinationTxt;
	private JLabel departureLab;
	private JTextField departureTxt;
	private JButton searchBtn;
	private JTable tourTable;
	private DefaultTableModel tableModel;
	private JScrollPane scroller;
	private JLabel tourLab;
	private JTextArea descTxt;
	
	
	
	
	private DefaultTableCellRenderer ratingRenderer;
	private DefaultTableCellRenderer nameRenderer;
	
	private ArrayList<Tour> tours;
	

	public MockDisplay(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000,400);
		this.setLocationRelativeTo(null);
		
		initComponents();
		this.setVisible(true);
		searchBtn.requestFocusInWindow();
	}
	
	private void initComponents(){
		
		tours = null;
		
		this.frameGrid = new GridLayout(1,2);
		this.leftGrid = new GridLayout(9,1);
		this.rightGrid = new GridLayout(2,1);
		this.panelsLeft = new FlowLayout(FlowLayout.LEFT);
		
		this.getContentPane().setLayout(frameGrid);
		leftPan = new JPanel(new BorderLayout());
		
		labelsPan = new JPanel(leftGrid);
		inputsPan = new JPanel(leftGrid);
		
		durationPan = new JPanel(panelsLeft);
		pricePan = new JPanel(panelsLeft);
		tourTypePan = new JPanel(panelsLeft);
		datePan = new JPanel(panelsLeft);
		seatsPan = new JPanel(panelsLeft);
		namePan = new JPanel(panelsLeft);
		destinationPan = new JPanel(panelsLeft);
		departurePan = new JPanel(panelsLeft);
		searchPan = new JPanel(panelsLeft);
		
		durationLabelPan = new JPanel(panelsLeft);
		priceLabelPan = new JPanel(panelsLeft);
		tourTypeLabelPan = new JPanel(panelsLeft);
		dateLabelPan = new JPanel(panelsLeft);
		seatsLabelPan = new JPanel(panelsLeft);
		nameLabelPan = new JPanel(panelsLeft);
		destinationLabelPan = new JPanel(panelsLeft);
		departureLabelPan = new JPanel(panelsLeft);

		rightPan = new JPanel();
		rightPan.setLayout(rightGrid);
		
		listPan = new JPanel(new GridLayout(1,1));
		descPan = new JPanel(new BorderLayout());

		this.getContentPane().add(leftPan);
		this.getContentPane().add(rightPan);
		
		leftPan.add(labelsPan,"West");
		leftPan.add(inputsPan);
		
		labelsPan.add(tourTypeLabelPan);
		labelsPan.add(priceLabelPan);
		labelsPan.add(dateLabelPan);
		labelsPan.add(durationLabelPan);
		labelsPan.add(departureLabelPan);
		labelsPan.add(destinationLabelPan);
		labelsPan.add(nameLabelPan);
		labelsPan.add(seatsLabelPan);
		
		inputsPan.add(tourTypePan);
		inputsPan.add(pricePan);
		inputsPan.add(datePan);
		inputsPan.add(durationPan);
		inputsPan.add(departurePan);
		inputsPan.add(destinationPan);
		inputsPan.add(namePan);
		inputsPan.add(seatsPan);
		inputsPan.add(searchPan);
		
		
		this.rightPan.add(listPan);
		this.rightPan.add(descPan);
		
		
		
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Name");
		tableModel.addColumn("Rating");
		
		tourTable = new JTable(tableModel){
			private static final long serialVersionUID = 1L;
			// Kemur í veg fyrir að hægt sé að yfirskrifa hluti í table.
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		tourTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tourTable.setCellSelectionEnabled(false);
		tourTable.setRowHeight(30);
		tourTable.getColumnModel().getColumn(0).setPreferredWidth(400);
		tourTable.setShowGrid(false);
		tourTable.getTableHeader().setReorderingAllowed(false);
		tourTable.getTableHeader().setResizingAllowed(false);
		
		tourTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent evt) {
	        	if(tourTable.getSelectedRow()!=-1){
	        		tourSelectionActionPerformed(String.valueOf(tourTable.getValueAt(tourTable.getSelectedRow(), 0))); 
	        	}
	        }
	    });

		ratingRenderer = new betterRenderer();
		ratingRenderer.setHorizontalAlignment( JLabel.CENTER );
		ratingRenderer.setBorder(null);
		tourTable.getColumnModel().getColumn(1).setCellRenderer(ratingRenderer);
		nameRenderer = new betterRenderer();
		tourTable.getColumnModel().getColumn(0).setCellRenderer(nameRenderer);
		
		
		scroller = new JScrollPane(tourTable);
		scroller.setBorder(BorderFactory.createEmptyBorder());
		scroller.setVisible(false);
		
		priceLab = new JLabel("Price: ");
		String[] priceValues = new String[]{"","2.500kr.","5.000kr.","7.500kr.",
				"10.000kr.","12.500kr.","15.000kr.","17.500kr.","20.000kr.","25.000kr.",
				"30.000kr.","35.000kr.","40.000kr.","50.000kr","60.000kr.","75.000kr.","100.000kr."};
		priceFromCom = new JComboBox<String>(priceValues);
		priceFromCom.setSelectedIndex(0);
		priceFromCom.setBackground(Color.WHITE);
		priceToLab = new JLabel("to: ");
		priceToCom = new JComboBox<String>(priceValues);
		priceToCom.setSelectedIndex(0);
		priceToCom.setBackground(Color.WHITE);
		
		dateLab = new JLabel("Date: ");
		calendarFrom = new JDateChooser();
		calendarFrom.setMinSelectableDate(new Date());
		dateToLab = new JLabel("to: ");
		calendarTo = new JDateChooser();
		calendarTo.setMinSelectableDate(new Date());
		
		departureLab = new JLabel("Departure location: ");
		departureTxt = new JTextField(8);
		
		destinationLab = new JLabel("Destination: ");
		destinationTxt = new JTextField(8);
		
		durationLab = new JLabel("Duration: ");
		String[] durationValues = new String[]{"","2","5","8","12","15","18","20","24"};
		durationFromCom = new JComboBox<String>(durationValues);
		durationFromCom.setSelectedIndex(0);
		durationFromCom.setBackground(Color.WHITE);
		durationToLab = new JLabel("to: ");
		durationToCom = new JComboBox<String>(durationValues);
		durationToCom.setSelectedIndex(0);
		durationToCom.setBackground(Color.WHITE);
		
		seatsLab = new JLabel("Minimum seats: ");
		seatsLabError = new JLabel("Invalid character(s).");
		seatsLabError.setForeground(Color.RED);
		seatsLabError.setVisible(false);
		seatsTxt = new JTextField(2);
		seatsTxt.getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				checkInput();
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				checkInput();
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				checkInput();
			}
			private void checkInput(){
				if(!seatsTxt.getText().equals("")){
					try{
						int tmp = Integer.parseInt(seatsTxt.getText());
						if(tmp<0) throw new NumberFormatException();
						seatsLabError.setVisible(false);
					}catch(NumberFormatException e){
						seatsLabError.setVisible(true);
					}
				} else seatsLabError.setVisible(false);
			}
		});

		typeLab = new JLabel("Type of tour: ");
		String[] types = new String[]{"","Adventure","Golden Circle","Nature",
				"Northern Lights","Reykjavik"};
		typeCom = new JComboBox<String>(types);
		typeCom.setSelectedIndex(0);
		typeCom.setBackground(Color.WHITE);
		
		nameLab = new JLabel("Tour name: ");
		nameTxt = new JTextField(8);
		
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				searchBtnActionPerformed(evt);
			}
		});
		
		fromDurationLab = new JLabel("from: ");
		fromPriceLab = new JLabel("from: ");
		fromDateLab = new JLabel("from: ");
		
		tourLab = new JLabel("",SwingConstants.CENTER);
		descTxt = new JTextArea("");
		descTxt.setVisible(false);
		

		priceLabelPan.add(priceLab);
		pricePan.add(fromPriceLab);
		pricePan.add(priceFromCom);
		pricePan.add(priceToLab);
		pricePan.add(priceToCom);
		
		dateLabelPan.add(dateLab);
		datePan.add(fromDateLab);
		datePan.add(calendarFrom);
		datePan.add(dateToLab);
		datePan.add(calendarTo);
		
		departureLabelPan.add(departureLab);
		departurePan.add(departureTxt);
		
		destinationLabelPan.add(destinationLab);
		destinationPan.add(destinationTxt);
		
		durationLabelPan.add(durationLab);
		durationPan.add(fromDurationLab);
		durationPan.add(durationFromCom);
		durationPan.add(durationToLab);
		durationPan.add(durationToCom);
		
		seatsLabelPan.add(seatsLab);
		seatsPan.add(seatsTxt);
		seatsPan.add(seatsLabError);
		
		tourTypeLabelPan.add(typeLab);
		tourTypePan.add(typeCom);
		
		nameLabelPan.add(nameLab);
		namePan.add(nameTxt);
				
		searchPan.add(searchBtn);
		
		
		listPan.add(scroller);
		descPan.add(tourLab,"North");
		descPan.add(descTxt);
	}
	
	private void tourSelectionActionPerformed(String tourName){
		for(Tour tour: tours){
			if(tour.getName()==tourName){
				tourLab.setText(tourName);
				descTxt.setVisible(true);
				descTxt.setText(tour.getDescription());
			}
		}
		
	}
	
	private void searchBtnActionPerformed(ActionEvent evt){
		tableModel.setRowCount(0);
		tourTable.setRowSelectionAllowed(true);
		nameRenderer.setBackground(Color.WHITE);
		ratingRenderer.setBackground(Color.WHITE);
		
		
		String priceLowStr = String.valueOf((priceFromCom.getSelectedItem()));
		int priceLow;
		if(priceLowStr.equals("")) priceLow = -1;
		else priceLow = priceToInt(priceLowStr);
		String priceHighStr = String.valueOf((priceToCom.getSelectedItem()));
		int priceHigh;
		if(priceHighStr.equals("")) priceHigh = -1;
		else priceHigh = priceToInt(priceHighStr);
		int durationLow;
		if(durationFromCom.getSelectedItem().equals("")) durationLow = -1;
		else durationLow = Integer.parseInt(String.valueOf(durationFromCom.getSelectedItem()));
		
		int durationHigh;
		if(durationToCom.getSelectedItem().equals("")) durationHigh = -1;
		else durationHigh = Integer.parseInt(String.valueOf(durationToCom.getSelectedItem()));
		String tourType = String.valueOf(typeCom.getSelectedItem());
		
		int minSeats = -1;
		if(!seatsTxt.getText().equals("")){
			try {
				minSeats = Integer.parseInt(seatsTxt.getText());
				if(minSeats<0) throw new NumberFormatException();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Please only enter positive whole numbers in Minimum Seats field.");
				return;
			}
		}

		
		Date dateFrom = calendarFrom.getDate();
		Date dateTo = calendarTo.getDate();
		String destination = destinationTxt.getText();
		String departure = departureTxt.getText();
		String tourName = nameTxt.getText();
		try {		
			tours = SearchManager.createList(priceLow, priceHigh, durationLow, durationHigh, dateFrom, dateTo, minSeats, destination, departure, tourType, tourName);

			scroller.setVisible(true);
			for(Tour x: tours){
				tableModel.addRow(new Object[]{x.getName(),x.getRating()});
			}
			
		} catch (NoSuchElementException e) {
			// TODO birtist í listanum, ekki popup.
			nameRenderer.setBackground(this.getContentPane().getBackground());
			ratingRenderer.setBackground(this.getContentPane().getBackground());
			tableModel.addRow(new String[]{"No match found."});
			tourTable.setRowSelectionAllowed(false);
		}
	}
	
	private int priceToInt(String priceStr){
		StringBuilder priceB = new StringBuilder(priceStr);
		priceB = priceB.delete(priceB.length()-3, priceB.length());
		int index = priceB.indexOf(".");
		
		return Integer.parseInt(priceB.deleteCharAt(index).toString());	
	}
	
	
	public class betterRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;
		@Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
	        setBorder(noFocusBorder);
	        return this;
	    }

	}
		
}
