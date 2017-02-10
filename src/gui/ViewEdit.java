package gui;

import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import file.EditTag;
import file.FileTag;
import file.MusicFile;
import edit.gui.*;

public class ViewEdit{

	protected JPanel panel = new JPanel();
	protected JPanel infopanel = new JPanel();
	private FileTableModel model;
	
	private JButton changebutton = new JButton("Change All");
	private JLabel albumlabel = new JLabel("  album  ");
	private JLabel artistlabel = new JLabel("  artist  ");
	private JLabel songtitlelabel = new JLabel("  title");
	private JLabel genrelabel = new JLabel("  genre  ");
	private JLabel yearlabel = new JLabel("  year");
	private JLabel lyricslabel = new JLabel("  Lyrics  ");
	private JLabel coverlabel = new JLabel("  cover  ");
	private TextField albumText;
	private TextField artistText;
	private TextField songtitleText;
	private TextField genreText;
	private TextField yearText;
	private JTextArea lyricsArea;
	
	private JButton setimagebutton = new JButton("put");
	private File loadedimage=null;
	private ImageIcon coverimage;
	private JLabel imagelabel = new JLabel();
	
	private ArrayList<Integer> real_indexlist;
	private String albumname, artistname, songtitlename, genrename, yearname, lyrics = null;
	
	private JLabel label = new JLabel("  Filter  ");
	private JButton button = new JButton("Find");
	private JTextField filterText = new JTextField("");
	
	public void viewTable(ArrayList<String> filenamelist, ArrayList<String> albumlist, ArrayList<String> artistlist,
			ArrayList<String> songtitlelist, ArrayList<String> genrelist, ArrayList<String> yearlist, ArrayList<String> lyricslist){
		
		panel.setLayout(null);
		panel.setBounds(300, 25, 1282, 830);
		
		model = new FileTableModel(filenamelist, albumlist, artistlist, songtitlelist, genrelist, yearlist);
		JTable table = new JTable(model);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
	    table.setRowSorter(sorter);
	    
	    ListSelectionModel rowSelMod = table.getSelectionModel();
	    rowSelMod.addListSelectionListener(new ListSelectionListener(){
	    	public void valueChanged(ListSelectionEvent arg0) {
	    		int temp[] = table.getSelectedRows();
	    		
	    		real_indexlist = new ArrayList<Integer>();
	    		
	    		for (int i = 0; i < temp.length; i++){
	    			real_indexlist.add(filenamelist.indexOf(table.getValueAt(temp[i], 0).toString()));
	    		}
	    		if(temp.length==1){
	    			albumText.setText(albumlist.get(real_indexlist.get(0)));
	    			artistText.setText(artistlist.get(real_indexlist.get(0)));
	    			songtitleText.setText(songtitlelist.get(real_indexlist.get(0)));
	    			genreText.setText(genrelist.get(real_indexlist.get(0)));
	    			yearText.setText(yearlist.get(real_indexlist.get(0)));
	    			lyricsArea.setText(lyricslist.get(real_indexlist.get(0)));
	    			imagelabel.setIcon(null); // 기존 라벨에 있는 이미지 제거용
	    			
	    			if(FileTag.readImageTag(MusicFile.files[real_indexlist.get(0)])!=null){ // 이미지가 있을 때만 실행
	    				try { 																// 파일의 이미지 태그를 보여주는 이벤트 처리
	    					BufferedImage img = ImageIO.read(new ByteArrayInputStream(FileTag.readImageTag(MusicFile.files[real_indexlist.get(0)])));
	    					Image resized = img.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH);
	    					coverimage = new ImageIcon(resized);
	    					imagelabel.setIcon(coverimage);
	    					loadedimage=null; // 클릭 초기화시 로딩된 이미지도 초기화
	    				} catch (IOException e) {
	    					e.printStackTrace();
	    				}
	    			}
	    			
	    		}
	    		else{
	    			albumText.setText(null);
	    			artistText.setText(null);
	    			songtitleText.setText(null);
	    			genreText.setText(null);
	    			yearText.setText(null);
	    			lyricsArea.setText(null);
	    			imagelabel.setIcon(null); 	
	    		}
	    	}
	    });
	    
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0,25, 1282, 800);
		
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
		celAlignLeft.setHorizontalAlignment(JLabel.LEFT);
		
		table.getColumn("File name").setPreferredWidth(400);
		table.getColumn("File name").setCellRenderer(celAlignLeft);
		table.getColumn("album").setPreferredWidth(30);
		table.getColumn("album").setCellRenderer(celAlignCenter);
		table.getColumn("artist").setPreferredWidth(30);
		table.getColumn("artist").setCellRenderer(celAlignCenter);
		table.getColumn("songtitle").setPreferredWidth(30);
		table.getColumn("songtitle").setCellRenderer(celAlignCenter);
		table.getColumn("genre").setPreferredWidth(30);
		table.getColumn("genre").setCellRenderer(celAlignCenter);
		table.getColumn("year").setPreferredWidth(10);
		table.getColumn("year").setCellRenderer(celAlignCenter);
		
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        String text = filterText.getText();
	        if (text.length() == 0) {
	          sorter.setRowFilter(null);
	        } else {
	          sorter.setRowFilter(RowFilter.regexFilter(text));
	        }
	      }
	    });
	    
	    label.setBounds(0,0,50,25);
	    button.setBounds(1212, 0, 68, 24);
	    filterText.setBounds(46, 0, 1172,25);
	    
	    panel.add(button);
	    panel.add(label);
	    panel.add(filterText);
	    panel.add(scroll);
	}
	
	public void viewTaginfo(ArrayList<String> albumlist, ArrayList<String> artistlist,
			ArrayList<String> songtitlelist, ArrayList<String> genrelist, ArrayList<String> yearlist, ArrayList<String> lyricslist){
		
		infopanel.setBounds(0, 25, 300, 825);
		infopanel.setLayout(null);
		
		albumText = new TextField(80);
		albumText.setBounds(60, 25, 220, 25);
		artistText = new TextField(80);
		artistText.setBounds(60, 75, 220, 25);
		songtitleText = new TextField(80);
		songtitleText.setBounds(60, 125, 220, 25);
		genreText = new TextField(80);
		genreText.setBounds(60, 175, 220, 25);
		yearText = new TextField(80);
		yearText.setBounds(60, 225, 220, 25);
		lyricsArea = new JTextArea();
		JScrollPane scrollingArea = new JScrollPane(lyricsArea);
		scrollingArea.setBounds(60, 275, 220, 220);
		
		albumlabel.setBounds(3, 25, 53, 25);
		artistlabel.setBounds(3, 75, 53, 25);
		songtitlelabel.setBounds(3, 125, 53, 25);
		genrelabel.setBounds(3, 175, 53, 25);
		yearlabel.setBounds(3, 225, 53, 25);
		lyricslabel.setBounds(3, 275, 53, 25);
		coverlabel.setBounds(3, 525, 53, 25);
		
		changebutton.setBounds(12, 780, 275, 25);
		imagelabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		imagelabel.setBounds(60, 525, 220, 220);
		setimagebutton.setBounds(5, 555, 52, 25);
				
		infopanel.add(albumText);
		infopanel.add(artistText);
		infopanel.add(songtitleText);
		infopanel.add(genreText);
		infopanel.add(yearText);
		
		infopanel.add(albumlabel);
		infopanel.add(artistlabel);
		infopanel.add(songtitlelabel);
		infopanel.add(genrelabel);
		infopanel.add(yearlabel);
		infopanel.add(lyricslabel);
		infopanel.add(coverlabel);
		
		infopanel.add(imagelabel);
		infopanel.add(setimagebutton);
		infopanel.add(changebutton);
		infopanel.add(scrollingArea);
		
		setimagebutton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  
		    	  InsertImage insert = new InsertImage();
		    	  
		    	  try {
					loadedimage = insert.guiCall();
		    	  } catch (IOException e1) {
		    		  e1.printStackTrace();
		    	  }
		    	  
		    	  BufferedImage img=null;
		    	  
		    	  try {
					img = ImageIO.read(loadedimage);
		    	  } catch (IOException e1) {
					e1.printStackTrace();
		    	  }
		    	  
		    	  Image resized = img.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH);
		    	  coverimage = new ImageIcon(resized);
		    	  imagelabel.setIcon(coverimage);
		    	  
			      }
			    });
				
		changebutton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        
		        albumname = albumText.getText();
		        artistname = artistText.getText();
		        songtitlename = songtitleText.getText();
		        genrename = genreText.getText();
		        yearname = yearText.getText();
		        lyrics = lyricsArea.getText();
		        
    			albumText.setText(null);
    			artistText.setText(null);
    			songtitleText.setText(null);
    			genreText.setText(null);
    			yearText.setText(null);
    			lyricsArea.setText(null);
    			imagelabel.setIcon(null);
    			
    			for(int i = 0; i < real_indexlist.size(); i++){
    				
    				if(albumname.length()>0){
    					albumlist.set(real_indexlist.get(i), albumname);
    				}
    				if(artistname.length()>0){
    					artistlist.set(real_indexlist.get(i), artistname);
    				}
    				if(songtitlename.length()>0){
    					songtitlelist.set(real_indexlist.get(i), songtitlename);
    				}
    				if(genrename.length()>0){
    					genrelist.set(real_indexlist.get(i), genrename);
    				}
    				if(yearname.length()>0){
    					yearlist.set(real_indexlist.get(i), yearname);
    				}
    				if(lyrics.length()>0){
    					lyricslist.set(real_indexlist.get(i), lyrics);
    				}
    			}
    			
    			for(int j = 0; j < real_indexlist.size(); j++){
    				if(albumname.length()>0){
    					EditTag.editAlbumTag(MusicFile.files[real_indexlist.get(j)], albumname);
    				}
    				if(artistname.length()>0){
    					EditTag.editArtistTag(MusicFile.files[real_indexlist.get(j)], artistname);
    				}
    				if(songtitlename.length()>0){
    					EditTag.editSongTitleTag(MusicFile.files[real_indexlist.get(j)], songtitlename);
    				}
    				if(genrename.length()>0){
    					EditTag.editGenreTag(MusicFile.files[real_indexlist.get(j)], genrename);
    				}
    				if(yearname.length()>0){
    					EditTag.editYearTag(MusicFile.files[real_indexlist.get(j)], yearname);
    				}
    				if(lyrics.length()>0){
    					EditTag.editLyricsTag(MusicFile.files[real_indexlist.get(j)], lyrics);
    				}
    				if(loadedimage!=null){
    					try {
							EditTag.editImageTag(MusicFile.files[real_indexlist.get(j)], loadedimage);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
    				}
    			}
		      }
		    });
	}
}
