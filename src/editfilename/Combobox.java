package editfilename;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.RowFilter;

import file.MusicFile;

public class Combobox {
	
	private SortFileName sortFileName = new SortFileName();
	
	private JLabel label;
	private JLabel label2;
	private JLabel label3;
	private JFrame comboboxFrame;
	private JButton button;
	
	private JComboBox combobox1;
	private JComboBox combobox2;
	private JComboBox combobox3;
	private JComboBox combobox4;
	
	private int info_combo1;
	private int info_combo2;
	private int info_combo3;
	private int info_combo4;
	
	private ArrayList<String> albumlist;
	private ArrayList<String> artistlist;
	private ArrayList<String> songtitlelist;
	private ArrayList<String> genrelist;
	private ArrayList<String> yearlist;
	
	public Combobox(ArrayList<String> albumlist, ArrayList<String> artistlist,
			ArrayList<String> songtitlelist, ArrayList<String> genrelist, ArrayList<String> yearlist){
		this.albumlist=albumlist;
		this.artistlist=artistlist;
		this.songtitlelist=songtitlelist;
		this.genrelist=genrelist;
		this.yearlist=yearlist;
	}
	
	public void guiCall(){
	
		comboboxFrame = new JFrame("Sorting File Name");
		comboboxFrame.setLayout(null);
		comboboxFrame.setDefaultCloseOperation(comboboxFrame.DISPOSE_ON_CLOSE);
		label = new JLabel("Select How To Sort File Name");
		label2 = new JLabel("ex) artist title album  -");
		label3 = new JLabel("    Maroon5 - This Love - Songs About Jane.mp3");
		
		label.setBounds(25, 0, 300, 50);
		label2.setBounds(25, 50, 300, 50);
		label3.setBounds(25, 75, 300, 50);
		comboboxFrame.add(label);
		comboboxFrame.add(label2);
		comboboxFrame.add(label3);
		
		setCombobox();
	
		comboboxFrame.setSize(550, 250);
		comboboxFrame.setVisible(true);
	}
	
	private void setCombobox(){
		String[] datePatterns = {"album","artist","genre","title","year"};
		String[] datePatterns2 = {"album","artist","genre","title","year","null"};
		String[] distinct_datePatterns = {"-", ",", "_", "space", "&"};

		combobox1 = new JComboBox(datePatterns);
		combobox2 = new JComboBox(datePatterns2);
		combobox3 = new JComboBox(datePatterns2);
		combobox4 = new JComboBox(distinct_datePatterns);
		
		combobox1.setEditable(true);
		combobox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbDates = (JComboBox) e.getSource();
				String seletedDate = (String) jcmbDates.getSelectedItem();
				
				if(seletedDate.equals("album")){
					info_combo1=1;
				}
				if(seletedDate.equals("artist")){
					info_combo1=2;
				}
				if(seletedDate.equals("genre")){
					info_combo1=3;
				}
				if(seletedDate.equals("title")){
					info_combo1=4;
				}
				if(seletedDate.equals("year")){
					info_combo1=5;
				}
			}
		});
		
		combobox2.setEditable(true);
		combobox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbDates = (JComboBox) e.getSource();
				String seletedDate = (String) jcmbDates.getSelectedItem();
				
				if(seletedDate.equals("album")){
					info_combo2=1;
				}
				if(seletedDate.equals("artist")){
					info_combo2=2;
				}
				if(seletedDate.equals("genre")){
					info_combo2=3;
				}
				if(seletedDate.equals("title")){
					info_combo2=4;
				}
				if(seletedDate.equals("year")){
					info_combo2=5;
				}
				if(seletedDate.equals("null")){
					info_combo2=6;
				}
			}
		});
		
		combobox3.setEditable(true);
		combobox3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbDates = (JComboBox) e.getSource();
				String seletedDate = (String) jcmbDates.getSelectedItem();
				
				if(seletedDate.equals("album")){
					info_combo3=1;
				}
				if(seletedDate.equals("artist")){
					info_combo3=2;
				}
				if(seletedDate.equals("genre")){
					info_combo3=3;
				}
				if(seletedDate.equals("title")){
					info_combo3=4;
				}
				if(seletedDate.equals("year")){
					info_combo3=5;
				}
				if(seletedDate.equals("null")){
					info_combo3=6;
				}
			}
		});
		
		combobox4.setEditable(true);
		combobox4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox jcmbDates = (JComboBox) e.getSource();
				String seletedDate = (String) jcmbDates.getSelectedItem();

				if(seletedDate.equals("-")){
					info_combo4=1;
				}
				if(seletedDate.equals(",")){
					info_combo4=2;
				}
				if(seletedDate.equals("_")){
					info_combo4=3;
				}
				if(seletedDate.equals("space")){
					info_combo4=4;
				}
				if(seletedDate.equals("&")){
					info_combo4=5;
				}
			}
		});
		
		button = new JButton("Sorting");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startSorting();
				comboboxFrame.dispose();
		    }
		});
		
		combobox1.setBounds(25, 150, 75, 25);
		combobox2.setBounds(115, 150, 75, 25);
		combobox3.setBounds(205, 150, 75, 25);
		combobox4.setBounds(325, 150, 75, 25);
		button.setBounds(425, 150, 75, 25);
		
		comboboxFrame.add(combobox1);
		comboboxFrame.add(combobox2);
		comboboxFrame.add(combobox3);
		comboboxFrame.add(combobox4);
		comboboxFrame.add(button);
	}
	
	private void startSorting(){
		
		for(int i = 0; i < MusicFile.filenamelist.size(); i++){
			MusicFile.filenamelist.set(i, getSortedString(i));
			sortFileName.sorting(MusicFile.files[i], getSortedString(i));
			MusicFile.files[i]= sortFileName.changeArrayInfo(MusicFile.files[i], getSortedString(i));
		}
	}
	
	private String getSortedString(int index){
		String temp1="";
		String temp2="";
		String temp3="";
		String temp4="";
		String temp_final=null;
		int nullcount=0;
		
		switch(info_combo1){
		case 1 : {
			temp1 = albumlist.get(index);
			break;
		}
		
		case 2 : {
			temp1 = artistlist.get(index);
			break;
		}
		
		case 3 : {
			temp1 = genrelist.get(index);
			break;
		}
		
		case 4 : {
			temp1 = songtitlelist.get(index);
			break;
		}
		
		case 5 : {
			temp1 = yearlist.get(index);
			break;
		}
		
		default : {
			nullcount++;
			break;
		}
		}
		
		switch(info_combo2){
		case 1 : {
			temp2 = albumlist.get(index);
			break;
		}
		
		case 2 : {
			temp2 = artistlist.get(index);
			break;
		}
		
		case 3 : {
			temp2 = genrelist.get(index);
			break;
		}
		
		case 4 : {
			temp2 = songtitlelist.get(index);
			break;
		}
		
		case 5 : {
			temp2 = yearlist.get(index);
			break;
		}
		
		case 6 : {
			nullcount++;
			break;
		}
		
		default : {
			nullcount++;
			break;
		}
		}
		
		switch(info_combo3){
		case 1 : {
			temp3 = albumlist.get(index);
			break;
		}
		
		case 2 : {
			temp3 = artistlist.get(index);
			break;
		}
		
		case 3 : {
			temp3 = genrelist.get(index);
			break;
		}
		
		case 4 : {
			temp3 = songtitlelist.get(index);
			break;
		}
		
		case 5 : {
			temp3 = yearlist.get(index);
			break;
		}
		
		case 6 : {
			nullcount++;
			break;
		}
		
		default : {
			nullcount++;
			break;
		}
		}
		
		switch(info_combo4){
		case 1 : {
			temp4 = " - ";
			break;
		}
		
		case 2 : {
			temp4 = ", ";
			break;
		}
		
		case 3 : {
			temp4 = "_";
			break;
		}
		
		case 4 : {
			temp4 = " ";
			break;
		}
		
		case 5 : {
			temp4 = " & ";
			break;
		}
		
		case 6 : {
			break;
		}		
		
		default : break;
		}
		
		if(nullcount==1){
			temp_final = temp1 + temp4 + temp2 + temp3;
			return temp_final;
		}
		
		if(nullcount==2){
			temp_final = temp1;
			return temp_final;
		}
		
		else{
			temp_final = temp1 + temp4 + temp2 + temp4 + temp3;
		}
		
		return temp_final;
	}
}
