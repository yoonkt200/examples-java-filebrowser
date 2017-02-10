package main;

import java.awt.Font;
import gui.MainGui;

public class MusicFileProgram {

	public static void main(String[] args) {
		SetUIFont.setUIFont (new javax.swing.plaf.FontUIResource("#HeadLineA", Font.PLAIN, 13));
		String encoding = "UTF-8";
		MainGui program = new MainGui();
		program.go();		
	}
}
