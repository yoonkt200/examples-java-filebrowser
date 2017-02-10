package edit.gui;

import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;

public class FileTableModel extends AbstractTableModel {
	
	private String[] filenamelist;
	private String[] albumlist;
	private String[] artistlist;
	private String[] songtitlelist;
	private String[] genrelist;
	private String[] yearlist;
	private String[] columnNames = new String[] {
			"File name", "album", "artist", "songtitle", "genre", "year"
	};

	protected Class[] columnClasses = new Class[] { 
			String.class,String.class,String.class,String.class,String.class,String.class
	};

	public FileTableModel(ArrayList<String> filenamelist, ArrayList<String> albumlist, ArrayList<String> artistlist,
			ArrayList<String> songtitlelist, ArrayList<String> genrelist, ArrayList<String> yearlist) { 
		
		this.filenamelist = new String[filenamelist.size()];
		this.filenamelist=filenamelist.toArray(this.filenamelist);

		this.albumlist = new String[albumlist.size()];
		this.albumlist=albumlist.toArray(this.albumlist);
		
		this.artistlist = new String[artistlist.size()];
		this.artistlist=artistlist.toArray(this.artistlist);
		
		this.songtitlelist = new String[songtitlelist.size()];
		this.songtitlelist=songtitlelist.toArray(this.songtitlelist);

		this.genrelist = new String[genrelist.size()];
		this.genrelist=genrelist.toArray(this.genrelist);

		this.yearlist = new String[yearlist.size()];
		this.yearlist=yearlist.toArray(this.yearlist);
		
	}

	public int getColumnCount() { return 6; } 
	public int getRowCount() { return filenamelist.length; }

	public String getColumnName(int col) { return columnNames[col]; }
	public Class getColumnClass(int col) { return columnClasses[col]; }

	public Object getValueAt(int row, int col) {
		
    switch(col) {
    case 0: return filenamelist[row];
    case 1: return albumlist[row];
    case 2: return artistlist[row];
    case 3: return songtitlelist[row];
    case 4: return genrelist[row];
    case 5: return yearlist[row];

    default: return null;
    }
  }
}
