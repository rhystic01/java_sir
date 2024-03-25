package pl.edu.pw.fizyka.pojava.nguyen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMenuController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Wyczyść parametry")) {
			System.out.println("Wyczyść parametry");
		}
		else if(e.getActionCommand().equals("Pokaż wykresy")) {
			System.out.println("Pokaż wykresy");
		}
		else if(e.getActionCommand().equals("Wczytaj parametry z pliku")) {
			System.out.println("Wczytaj parametry z pliku");
		}
		else if(e.getActionCommand().equals("Zapisz dane symulacji do pliku")) {
			System.out.println("Zapisz dane symulacji do pliku");
		}
		
	}
}
