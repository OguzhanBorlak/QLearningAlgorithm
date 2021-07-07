package yazlab3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.SpringLayout.HEIGHT;

public class graf1 extends JPanel {

    JFrame graf = new JFrame();
    JPanel grafik = new JPanel();
    List yollar;
    ArrayList<Integer> noktalarx = new ArrayList();
    ArrayList<Integer> noktalary = new ArrayList();
    //int[] noktalar = new int[100];
    ArrayList<JLabel> labels = new ArrayList();

    public graf1(List yollar) {
        super();
        this.yollar = yollar;
        // this.noktalar=noktalar;
        this.setLayout(null);
        graf.setSize(1920, 1200);
        //grafik.setBounds(0, 0, 1920, 1200);

        int aralık = 333;
        int ilkaralık = 50;
        // System.out.println("en büyük yol : " + enbüyükyol);
        for (int s = 0; s < 30; s++) {
            //System.out.println("aralık:  "+aralık);
            if (s < 8) {
                JLabel a = new JLabel(ilkaralık * s + " ");
                a.setBounds(50, 995 - s * 32, 125, 10);

                this.add(a);
            } else {
                JLabel a = new JLabel(aralık * (s - 6) + " ");
                a.setBounds(50, 995 - s * 32, 125, 10);

                this.add(a);
            }

        }

        graf.add(this);
        graf.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.setColor(Color.red);
        g2.setStroke(new BasicStroke(3));
        g.drawLine(75, 1000, 1800, 1000);

        for (int i = 0; i < yollar.size(); i++) {
            for (int b = 0; b < 30; b++) {

                if (b < 8 && (int) yollar.get(i) < (b + 1) * 50 && (int) yollar.get(i) > b * 50) {
                   
                    g.drawOval(115 + i * 17, 959 - 32 * b, 10, 10);
                    g.fillOval(115 + i * 17, 959 - 32 * b, 10, 10);
                    noktalarx.add(120 + i * 17);
                    noktalary.add(963 - 32 * b);
                    break;
                }
                if ((int) yollar.get(i) < (b + 1) * 333 && (int) yollar.get(i) > b * 333) {
                   
                    g.drawOval(115 + i * 17, 959 - 32 * b, 10, 10);
                    g.fillOval(115 + i * 17, 959 - 32 * b, 10, 10);
                    noktalarx.add(120 + i * 17);
                    noktalary.add(963 - 32 * b);
                    break;
                }
                if (b >= 8 && (int) yollar.get(i) > 7659) {
              
                    g.drawOval(115 + i * 17, 15, 10, 10);
                    g.fillOval(115 + i * 17, 15, 10, 10);
                    noktalarx.add(120 + i * 17);
                    noktalary.add(20);
                    break;
                }

            }

        }
        for (int j = 0; j < noktalarx.size(); j++) {
            if (j + 1 < noktalarx.size()) {
                g.setColor(Color.black);
                g.drawLine((int) noktalarx.get(j), (int) noktalary.get(j), (int) noktalarx.get(j + 1), (int) noktalary.get(j + 1));
            }

        }

        graf.setVisible(true);
    }

}
