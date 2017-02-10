package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import analysis.ArtistView;
import analysis.GenreView;
import analysis.YearView;
import file.MusicFile;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import file.Directory;
import editfilename.Combobox;;

public class MainGui{
	
	private Directory directory;
	private Combobox combobox;
	private JFrame mainframe;
	private ViewEdit viewedit;
	
	public void go(){
		directory = new Directory();
		
		mainframe = new JFrame("Begin Again");
		mainframe.setLayout(null);
		mainframe.setDefaultCloseOperation(mainframe.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
        
        menuBar.setBounds(0, 0, 1600, 25);
        
        JMenu file_menu = new JMenu("File");
        menuBar.add(file_menu);
        
        JMenuItem directoryAction = new JMenuItem("Set Directory");
        JMenuItem editAction = new JMenuItem("Edit Tag");
        JMenuItem sortAction = new JMenuItem("Sort File Name");
        JMenuItem refreshAction = new JMenuItem("Refresh");
        
        
        file_menu.add(directoryAction);
        file_menu.addSeparator();
        file_menu.add(editAction);
        file_menu.add(sortAction);
        file_menu.addSeparator();
        file_menu.add(refreshAction);
        
        
        JMenu anl_menu = new JMenu("Analysis");
        menuBar.add(anl_menu);
        
        JMenuItem artist_showAction = new JMenuItem("Artist Chart");
        JMenuItem year_showAction = new JMenuItem("Year Chart");
        JMenuItem genre_showAction = new JMenuItem("Genre Chart");
        
        anl_menu.add(artist_showAction);
        anl_menu.add(year_showAction);
        anl_menu.add(genre_showAction);
        
        artist_showAction.addActionListener(new Showchart_Artist());
        year_showAction.addActionListener(new Showchart_Year());
        genre_showAction.addActionListener(new Showchart_Genre());
        
        directoryAction.addActionListener(new SetDirectoryAction());
        editAction.addActionListener(new EditAction());
        sortAction.addActionListener(new SortAction());
        refreshAction.addActionListener(new EditAction());
		
		viewedit = new ViewEdit();
		
		mainframe.add(menuBar);
		
		mainframe.setSize(1600,900);
		mainframe.setVisible(true);
	}
	
	class SetDirectoryAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			directory.guiCall();
		}
	}
	
	class EditAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0){ 
    		mainframe.remove(viewedit.panel);
    		mainframe.remove(viewedit.infopanel);
    		mainframe.revalidate();
    		viewedit = null;
    		viewedit = new ViewEdit();
            viewedit.viewTable(directory.filenamelist, directory.albumlist, directory.artistlist,
            		directory.songtitlelist, directory.genrelist, directory.yearlist, directory.lyricslist);
            viewedit.viewTaginfo(directory.albumlist, directory.artistlist,
            		directory.songtitlelist, directory.genrelist, directory.yearlist, directory.lyricslist);
            mainframe.add(viewedit.panel);
            mainframe.add(viewedit.infopanel);
        }
	}

	class SortAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			combobox = new Combobox(directory.albumlist, directory.artistlist,
            		directory.songtitlelist, directory.genrelist, directory.yearlist);
			combobox.guiCall();
		}
	}
	
	class Showchart_Artist implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			SwingUtilities.invokeLater(new Runnable() {
	            ArtistView artistview = new ArtistView();
	            
	            public void run() {
	            	artistview.initAndShowGUI();
	            }
	        });
		}
	}
	
	class Showchart_Year implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			SwingUtilities.invokeLater(new Runnable() {
	            YearView yearview = new YearView();
	            
	            public void run() {
	                yearview.initAndShowGUI();
	            }
	        });
		}
	}
	
	class Showchart_Genre implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			SwingUtilities.invokeLater(new Runnable() {
	            GenreView genreview = new GenreView();
	            
	            public void run() {
	                genreview.initAndShowGUI();
	            }
	        });
		}
	}
}

