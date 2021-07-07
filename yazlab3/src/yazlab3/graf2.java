package yazlab3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class graf2 extends JPanel {

    ArrayList<Double> qlar = new ArrayList();
    JFrame graf = new JFrame();
    ArrayList<Integer> noktalarx = new ArrayList();
    ArrayList<Integer> noktalary = new ArrayList();
    JLabel bir = new JLabel("10");
    JLabel iki = new JLabel("20");
    JLabel uc = new JLabel("30");
    JLabel dort = new JLabel("40");
    JLabel bes = new JLabel("50");
    JLabel altı = new JLabel("60");
    JLabel yedi = new JLabel("70");
    JLabel sekiz = new JLabel("80");
    JLabel dokuz = new JLabel("90+");

    public graf2(ArrayList<Double> qlar) {
        super();
        this.qlar = qlar;
        this.setLayout(null);
        graf.setSize(1920, 1200);

        for (int a = 0; a < qlar.size(); a++) {
            System.out.println(a + ".adımdaki q değeri: " + qlar.get(a));
        }
        bir.setBounds(50, 900, 125, 50);
        bir.setForeground(Color.black);
        bir.setFont(new Font("Serif", Font.PLAIN, 20));

        iki.setBounds(50, 800, 125, 50);
        iki.setForeground(Color.black);
        iki.setFont(new Font("Serif", Font.PLAIN, 20));

        uc.setBounds(50, 700, 125, 50);
        uc.setForeground(Color.black);
        uc.setFont(new Font("Serif", Font.PLAIN, 20));

        dort.setBounds(50, 600, 125, 50);
        dort.setForeground(Color.black);
        dort.setFont(new Font("Serif", Font.PLAIN, 20));

        bes.setBounds(50, 500, 125, 50);
        bes.setForeground(Color.black);
        bes.setFont(new Font("Serif", Font.PLAIN, 20));

        altı.setBounds(50, 400, 125, 50);
        altı.setForeground(Color.black);
        altı.setFont(new Font("Serif", Font.PLAIN, 20));

        yedi.setBounds(50, 300, 125, 50);
        yedi.setForeground(Color.black);
        yedi.setFont(new Font("Serif", Font.PLAIN, 20));

        sekiz.setBounds(50, 200, 125, 50);
        sekiz.setForeground(Color.black);
        sekiz.setFont(new Font("Serif", Font.PLAIN, 20));

        dokuz.setBounds(50, 100, 125, 50);
        dokuz.setForeground(Color.black);
        dokuz.setFont(new Font("Serif", Font.PLAIN, 20));

        this.add(bir);
        this.add(iki);
        this.add(uc);
        this.add(dort);
        this.add(bes);
        this.add(altı);
        this.add(yedi);
        this.add(sekiz);
        this.add(dokuz);
        graf.add(this);
        graf.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g.setColor(Color.red);
        g.drawLine(50, 1000, 1800, 1000);

        int bölme = 1800 / qlar.size();

        for (int i = 0; i < qlar.size(); i++) {

            //noktalar[i]=new Integer();
            //System.out.println("i:"+i);
            if ((double) qlar.get(i) < 5) {

                g.drawOval(118 + i * bölme, 996, 10, 10);
                g.fillOval(118 + i * bölme, 996, 10, 10);
                noktalarx.add(120 + i * bölme);
                noktalary.add(1000);

            }

            if ((double) qlar.get(i) > 5 && (double) qlar.get(i) < 10) {

                g.drawOval(118 + i * bölme, 915, 10, 10);
                g.fillOval(118 + i * bölme, 915, 10, 10);
                noktalarx.add(120 + i * bölme);
                noktalary.add(920);

            }
            if ((double) qlar.get(i) > 20 && (double) qlar.get(i) < 30) {

                g.drawOval(118 + i * bölme, 815, 10, 10);
                g.fillOval(118 + i * bölme, 815, 10, 10);
                noktalarx.add(120 + i * bölme);
                noktalary.add(820);
            }
            if ((double) qlar.get(i) > 30 && (double) qlar.get(i) < 40) {
                g.drawOval(118 + i * bölme, 715, 10, 10);
                g.fillOval(118 + i * bölme, 715, 10, 10);
                noktalarx.add(120 + i * bölme);
                noktalary.add(720);
            }
            if ((double) qlar.get(i) > 40 && (double) qlar.get(i) < 50) {
                g.drawOval(118 + i * bölme, 615, 10, 10);
                g.fillOval(118 + i * bölme, 615, 10, 10);
                noktalarx.add(120 + i * bölme);
                noktalary.add(620);
            }
            if ((double) qlar.get(i) > 50 && (double) qlar.get(i) < 60) {
                g.drawOval(118 + i * bölme, 515, 10, 10);
                g.fillOval(118 + i * bölme, 515, 10, 10);
                noktalarx.add(120 + i * bölme);
                noktalary.add(520);

            }
            if ((double) qlar.get(i) > 60 && (double) qlar.get(i) < 70) {
                g.drawOval(118 + i * bölme, 415, 10, 10);
                g.fillOval(118 + i * bölme, 415, 10, 10);
                noktalarx.add(120 + i * bölme);
                noktalary.add(420);

            }
            if ((double) qlar.get(i) > 70 && (double) qlar.get(i) < 80) {
                g.drawOval(118 + i * bölme, 315, 10, 10);
                g.fillOval(118 + i * bölme, 315, 10, 10);
                noktalarx.add(120 + i * bölme);
                noktalary.add(320);

            }
            if ((double) qlar.get(i) > 80 && (double) qlar.get(i) < 90) {
                g.drawOval(118 + i * bölme, 215, 10, 10);
                g.fillOval(118 + i * bölme, 215, 10, 10);
                noktalarx.add(120 + i * bölme);
                noktalary.add(220);
            }
            if ((double) qlar.get(i) > 90) {
                g.drawOval(118 + i * bölme, 115, 10, 10);
                g.fillOval(118 + i * bölme, 115, 10, 10);
                noktalarx.add(120 + i * bölme);
                noktalary.add(120);

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
