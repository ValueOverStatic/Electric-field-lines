package equipotential;
import java.awt.*;
import javax.swing.*;

public class Design extends JFrame{
	public static double Fi(double fi, double E0){
		return fi/(E0*3.0/(2+9));
	}
	public static double Fey(double r, double R, double E0, double fe){
		return r*fe*r*r/(E0*(r*r*r+R*R*R*(1-9)/(2+9)));
	}
	public static double Fex(double r, double R, double E0, double fe){
		return Math.sqrt(r*r-Fey(r, R, E0, fe)*Fey(r, R, E0, fe));
	}
	public Design(String name) {
		super(name);
	}
	public void paint(Graphics g){
		int fx1, fx2, fy1, fy2, mas = 3120;
		double E0=15000, R=0.05, dr=0.00025, fx_double;
		int[] O = {350, 350};
		int[][] eArrow = {{O[0]-30-(int)(15*Math.tan(Math.PI/8)), O[0]-30, O[0]-30+(int)(15*Math.tan(Math.PI/8))}, {110, 130, 110}};
		int[][] zArrow = {{O[0]-(int)(15*Math.tan(Math.PI/8)), O[0], O[0]+(int)(15*Math.tan(Math.PI/8))}, {47, 27, 47}};
		int[][] rArrow = {{O[0]+(int)(mas*R)-20, O[0]+(int)(mas*R), O[0]+(int)(mas*R)-20}, {(int)(O[1]-15*Math.tan(Math.PI/8)), O[1], (int)(O[1]+15*Math.tan(Math.PI/8))}};
		for (int x = -(int)(mas*R); x <= mas*R; x++)
			g.drawLine(x+O[0], -(int)Math.sqrt(mas*R*mas*R-x*x)+O[1], x+1+O[0], -(int)Math.sqrt(mas*R*mas*R-(x+1)*(x+1))+O[1]);
		g.setFont(new Font("Bold1", 1, 15));
		g.drawString("0", O[0]-15, O[1]+20);
		g.drawLine(O[0], 0, O[0], 500); //axis z
		g.drawString("Z", O[0]+10, 50);
		g.drawString("R=5sm", O[0]+(int)(mas*R/2), O[1]+20);
		g.drawLine(O[0]-30, 50, O[0]-30, 130);
		g.fillPolygon(eArrow[0], eArrow[1], 3); //for E0
		g.fillPolygon(rArrow[0], rArrow[1], 3); //for radius
		g.fillPolygon(zArrow[0], zArrow[1], 3); //for z
		g.drawString("E", O[0]-50, 100);
		g.setFont(new Font("BoldIndex", 1, 10));
		g.drawString("0", O[0]-40, 105);

		for (int f = 0; f <= 500; f += 50){
			for (double r = 0; r <= O[0]; r += dr){
				fy1=(int)(mas*Fey(r, R, E0, f));
				fy2=(int)(mas*Fey(r+dr, R, E0, f));
				fx1=(int)(mas*Fex(r, R, E0, f));
				fx2=(int)(mas*Fex(r+dr, R, E0, f));
				fx_double = mas*Fex(r, R, E0, f);
				if (Fi(f, E0)*Fi(f, E0)+fx_double*fx_double/(mas*mas) <= R*R && r <= R){
					g.drawLine(-fx2+O[0], -(int)(mas*Fi(f, E0))+O[1], -fx1+O[0], -(int)(mas*Fi(f, E0))+O[1]);
					g.drawLine(fx1+O[0], -(int)(mas*Fi(f, E0))+O[1], fx2+O[0], -(int)(mas*Fi(f, E0))+O[1]);
				}
				if (r > R){
					g.drawLine(-fx1+O[0], -fy1+O[1], -fx2+O[0], -fy2+O[1]);
					g.drawLine(fx1+O[0], -fy1+O[1], fx2+O[0], -fy2+O[1]);
				}
			}
		}
		g.clearRect(0, O[1]+5, 50, -120);
		g.setFont(new Font("Bold2", 1, 13));
		for (int f = 0; f <= 500; f += 50)
			g.drawString(Integer.toString(f)+"Â", 10, O[1]+5-1*f/50-(int)Math.round((mas*Fey(O[0], R, E0, f))));
	}
}
