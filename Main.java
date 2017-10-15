package equipotential;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Main {
	public static void main(String argv[]){
		Design d = new Design("Equipotential");
		d.setBackground(Color.white);

		d.setVisible(true);
		d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		d.setSize(700, 500);
	}

}
