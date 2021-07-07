package yazlab3;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.K;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements MouseListener, ActionListener {

    Btn[][] tahta = new Btn[50][50];
    JButton qhesapla, hareket, rhesapla, grafik1, grafik2;
    JFrame pencere;
    JPanel büyükpanel, oyunpaneli;
    File engeldosyası;
    FileWriter engelwriter;
    BufferedWriter engelbufferedwriter;
    boolean robotsec = false;
    boolean hedefsec = false;
    int robotkonum = 0, hedefkonum = 0;
    int[] robotkare = new int[2];
    int[] hedefkare = new int[2];
    int[][] R;
    double[][] Q;
    List yollar = new ArrayList();
    ArrayList<Double> qlar = new ArrayList();

    public MainPanel() throws IOException {

        engeldosyası = new File("engel.txt");
        engelwriter = new FileWriter(engeldosyası.getAbsoluteFile());
        engelbufferedwriter = new BufferedWriter(engelwriter);

        pencere = new JFrame();
        oyunpaneli = new JPanel();
        büyükpanel = new JPanel();
        qhesapla = new JButton();
        hareket = new JButton();

        grafik1 = new JButton();
        grafik2 = new JButton();
        rhesapla = new JButton();

        pencere.setSize(1920, 1200);

        rhesapla.setBounds(1650, 100, 200, 70);
        rhesapla.setText("R MATRİSİ");
        rhesapla.addActionListener(this);
        rhesapla.setBackground(Color.white);

        qhesapla.setBounds(1650, 250, 200, 70);
        qhesapla.setText("Q MATRİSİ");
        qhesapla.addActionListener(this);
        qhesapla.setBackground(Color.white);

        hareket.setBounds(1650, 400, 200, 70);
        hareket.setText("HAREKET");
        hareket.addActionListener(this);
        hareket.setBackground(Color.white);

        grafik1.setBounds(1650, 550, 200, 70);
        grafik1.setText("ADIM GRAFİĞİ");
        grafik1.addActionListener(this);
        grafik1.setBackground(Color.white);

        grafik2.setBounds(1650, 700, 200, 70);
        grafik2.setText("MALİYET GRAFİĞİ");
        grafik2.addActionListener(this);
        grafik2.setBackground(Color.white);

        büyükpanel.setBounds(0, 0, 1920, 950);
        oyunpaneli.setBounds(0, 0, 1600, 950);
        oyunpaneli.setLayout(new GridLayout(50, 50, 0, 0));
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int satir = 0; satir < 50; satir++) {
            for (int sutun = 0; sutun < 50; sutun++) {
                Btn b = new Btn(satir, sutun);
                oyunpaneli.add(b);
                b.addMouseListener(this);
                b.setBackground(java.awt.Color.white);
                tahta[satir][sutun] = b;

            }
        }
        // büyükpanel.setBackground(Color.white);
        büyükpanel.setLayout(null);
        büyükpanel.add(qhesapla);
        büyükpanel.add(rhesapla);
        büyükpanel.add(hareket);
        büyükpanel.add(grafik1);
        büyükpanel.add(grafik2);
        pencere.add(büyükpanel);
        büyükpanel.add(oyunpaneli);

        pencere.setVisible(true);
        büyükpanel.setVisible(true);
        oyunpaneli.setVisible(true);

        DuvarUret();
        DuvarBas();

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (tahta[i][j].isCeza()) {
                    try {

                        engelbufferedwriter.write("(" + i + "," + j + "," + "B)");
                        engelbufferedwriter.newLine();
                    } catch (IOException ex) {

                    }
                } else if (tahta[i][j].isCeza() == false) {
                    try {
                        engelbufferedwriter.write("(" + i + "," + j + "," + "W)");
                        engelbufferedwriter.newLine();
                    } catch (IOException ex) {

                    }
                }

            }
        }
        engelbufferedwriter.close();

    }

    public void DuvarUret() {
        int i = 0, j = 0;

        while (i < (50 * 50) * 30 / 100) {
            int randsatir = (int) (Math.random() * tahta.length);
            int randsutun = (int) (Math.random() * tahta[0].length);
            while (tahta[randsatir][randsutun].isCeza()) {

                randsatir = (int) (Math.random() * tahta.length);
                randsutun = (int) (Math.random() * tahta[0].length);

            }

            tahta[randsatir][randsutun].setCeza(true);

            i++;

        }

    }

    public void DuvarBas() throws IOException {

        for (int satir = 0; satir < tahta.length; satir++) {
            for (int sutun = 0; sutun < tahta[0].length; sutun++) {
                if (tahta[satir][sutun].isCeza()) {

                    tahta[satir][sutun].setBackground(java.awt.Color.black);
                    tahta[satir][sutun].setCezadegeri(-5);

                }
            }
        }
        for (int satir = 0; satir < tahta.length; satir++) {
            for (int sutun = 0; sutun < tahta[0].length; sutun++) {
                if (tahta[satir][sutun].isCeza() == false && tahta[satir][sutun].isHedef() == false) {

                    tahta[satir][sutun].setBosluk(true);

                    tahta[satir][sutun].setBackground(java.awt.Color.white);
                    tahta[satir][sutun].setBosgecisdegeri(3);

                }

            }
        }
    }

    public void R_Matrisi() throws IOException {

        R = new int[2500][2500];
        Q = new double[2500][2500];

        int i = 0;
        int j = 0;

        for (int k = 0; k < 2500; k++) {

            i = k / 50;
            j = k - i * 50;

            for (int s = 0; s < 2500; s++) {
                R[k][s] = -5;
            }

            if (!tahta[i][j].isHedef()) {

                // sol
                int sol = j - 1;
                if (sol >= 0) {
                    int hedef = i * 50 + sol;
                    if (tahta[i][sol].isBosluk()) {
                        R[k][hedef] = 0;

                    }
                    if (tahta[i][sol].isHedef()) {

                        R[k][hedef] = 100;
                    }
                    if (tahta[i][sol].isCeza()) {
                        R[k][hedef] = -5;
                    }
                }

                //sağ
                int sag = j + 1;
                if (sag < 50) {
                    int hedef = i * 50 + sag;
                    if (tahta[i][sag].isBosluk()) {
                        R[k][hedef] = 0;
                    }
                    if (tahta[i][sag].isHedef()) {
                        R[k][hedef] = 100;

                    }
                    if (tahta[i][sag].isCeza()) {
                        R[k][hedef] = -5;
                    }
                }

                // yukarı
                int yukari = i - 1;
                if (yukari >= 0) {
                    int hedef = yukari * 50 + j;
                    if (tahta[yukari][j].isBosluk()) {
                        R[k][hedef] = 0;
                    }
                    if (tahta[yukari][j].isHedef()) {

                        R[k][hedef] = 100;
                    }
                    if (tahta[yukari][j].isCeza()) {
                        R[k][hedef] = -5;
                    }
                }

                // aşağı
                int asagi = i + 1;
                if (asagi < 50) {
                    int hedef = asagi * 50 + j;
                    if (tahta[asagi][j].isBosluk()) {

                        R[k][hedef] = 0;
                    }
                    if (tahta[asagi][j].isHedef()) {
                        R[k][hedef] = 100;

                    }
                    if (tahta[asagi][j].isCeza()) {
                        R[k][hedef] = -5;

                    }
                }
                // sağ üst
                int ust = i - 1;
                int sag2 = j + 1;
                if (sag2 < 50 && ust >= 0) {
                    int hedef = ust * 50 + sag;
                    if (tahta[ust][sag2].isBosluk()) {
                        R[k][hedef] = 0;
                    }
                    if (tahta[ust][sag2].isHedef()) {

                        R[k][hedef] = 100;
                    }
                    if (tahta[ust][sag2].isCeza()) {
                        R[k][hedef] = -5;
                    }
                }

                // sol üst
                int ust2 = i - 1;
                int sol2 = j - 1;
                if (sol >= 0 && ust2 >= 0) {
                    int hedef = ust2 * 50 + sol2;
                    if (tahta[ust2][sol2].isBosluk()) {
                        R[k][hedef] = 0;
                    }
                    if (tahta[ust2][sol2].isHedef()) {

                        R[k][hedef] = 100;
                    }
                    if (tahta[ust2][sol2].isCeza()) {
                        R[k][hedef] = -5;
                    }
                }

                // sol alt
                int alt = i + 1;
                int sol3 = j - 1;
                if (sol >= 0 && alt < 50) {
                    int hedef = alt * 50 + sol3;
                    if (tahta[alt][sol3].isBosluk()) {
                        R[k][hedef] = 0;
                    }
                    if (tahta[alt][sol3].isHedef()) {

                        R[k][hedef] = 100;
                    }
                    if (tahta[alt][sol3].isCeza()) {
                        R[k][hedef] = -5;
                    }
                }

                // sağ alt
                int alt2 = i + 1;
                int sag3 = j + 1;
                if (sag3 < 50 && alt < 50) {
                    int hedef = alt2 * 50 + sag3;
                    if (tahta[alt2][sag3].isBosluk()) {
                        R[k][hedef] = 0;
                    }
                    if (tahta[alt2][sag3].isHedef()) {

                        R[k][hedef] = 100;
                    }
                    if (tahta[alt2][sag3].isCeza()) {
                        R[k][hedef] = -5;
                    }
                }

            }
        }
        System.out.println("R matrisi oluşturuldu");
        Q_Doldur();

    }

    void Q_Doldur() {
        for (int i = 0; i < 2500; i++) {
            for (int j = 0; j < 2500; j++) {
                Q[i][j] = 0.0;

            }

        }
    }

    void Q_Matrisi() {
        System.out.println("Q matrisine girildi.");
        Random rand = new Random();

        int a = 0;
        for (int i = 0; i < 100; i++) { // Train cycles
            double b = 0;
            int enbuyuk = 0;
            List tektek = new ArrayList();
            System.out.println("i:             " + i);
            int anlikdurum = robotkonum;

            try {
                while (!sondurummu(anlikdurum)) {
                    int[] aksiyon = olasiaksiyonlar(anlikdurum);

                    int index = rand.nextInt(aksiyon.length);
                    int sonrakidurum = aksiyon[index];

                    double q = Q[anlikdurum][sonrakidurum];

                    double maxQ = maxQ(sonrakidurum);
                    int r = R[anlikdurum][sonrakidurum];

                    double formül = r + (0.9 * maxQ);
                    Q[anlikdurum][sonrakidurum] = formül;

                    anlikdurum = sonrakidurum;
                    //    System.out.println(a);
                    //  System.out.println(a + ".qqqqqq:: " + formül);
                    //System.out.println("b"+b);
                    b++;
                    a++;

                    tektek.add(b);

                }

            } catch (Exception e) {

            }

            enbuyuk = tektek.indexOf(Collections.max(tektek)) + 1;
            //System.out.println("max :   " + enbuyuk); 
            yollar.add(enbuyuk);

        }
        for (int s = 0; s < yollar.size(); s++) {
            System.out.println(s + ".yol: " + yollar.get(s));

        }
        System.out.println("Q hesaplandı.");
    }

    boolean sondurummu(int durum) {
        int i = robotkare[0];
        int j = robotkare[1];

        return tahta[i][j].isHedef();
    }

    synchronized void hareket() {

        while (robotkonum != hedefkonum) {

            int[] gecisler = olasiaksiyonlar(robotkonum);
            ArrayList<Double> result = new ArrayList<>();
            int enbüyük = 0;

            for (int i = 0; i < gecisler.length; i++) {

                result.add(Q[robotkonum][gecisler[i]]);
                System.out.println(Q[robotkonum][gecisler[i]] + " ");
            }

            System.out.println("robot konum" + robotkonum);

            enbüyük = result.indexOf(Collections.max(result));
            // System.out.println(" qqq:" + result.get(maxAt));
            qlar.add(result.get(enbüyük));
            int sıradaki = gecisler[enbüyük];
            System.out.println("sıradaki" + sıradaki);

            int[] gecilecek_kare = new int[2];
            gecilecek_kare[0] = sıradaki / 50;
            gecilecek_kare[1] = sıradaki - gecilecek_kare[0] * 50;

            robotkare[0] = gecilecek_kare[0];
            robotkare[1] = gecilecek_kare[1];

            System.out.println("robotun anlık konumu: " + robotkonum);
            System.out.println("hedefin konumu: " + hedefkonum);
            System.out.println("\n");
            try {

                wait(500);
                if (!tahta[gecilecek_kare[0]][gecilecek_kare[1]].isHedef()) {
                    tahta[gecilecek_kare[0]][gecilecek_kare[1]].setBackground(Color.yellow);
                }

                robotkonum = robotkare[0] * 50 + robotkare[1];
            } catch (InterruptedException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    int[] olasiaksiyonlar(int state) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < 2500; i++) {
            if (R[state][i] != -5) {

                result.add(i);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    double maxQ(int sonrakiadim) {
        int[] aksiyon = olasiaksiyonlar(sonrakiadim);
        double maxValue = -10;
        for (int sonrakiaksiyon : aksiyon) {
            double value = Q[sonrakiadim][sonrakiaksiyon];

            if (value > maxValue) {
                maxValue = value;
            }

        }
        return maxValue;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Btn b = (Btn) e.getComponent();
        if (robotsec == false && e.getButton() == 1 && b.isCeza() == false) {

            b.setBackground(Color.red);
            robotkare[0] = b.getBounds().y / 19;
            robotkare[1] = b.getBounds().x / 32;
            System.out.println("robot:  " + robotkare[0] + "   " + robotkare[1]);
            robotsec = true;
            b.setRobot(true);
            robotkonum = b.getBounds().x / 32 + (b.getBounds().y / 19 * 50);
            System.out.println("robot kareee: " + robotkonum);

        }
        if (hedefsec == false && e.getButton() == 3 && b.isCeza() == false) {

            b.setBackground(Color.blue);

            hedefkare[0] = b.getBounds().y / 19;
            hedefkare[1] = b.getBounds().x / 32;
            System.out.println("hedef:  " + hedefkare[0] + "   " + hedefkare[1]);
            hedefkonum = b.getBounds().x / 32 + b.getBounds().y / 19 * 50;
            System.out.println("hedef karee: " + hedefkonum);
            hedefsec = true;
            b.setHedef(true);
            b.setÖdüldegeri(100);

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "R MATRİSİ") {

            try {
                R_Matrisi();
            } catch (IOException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getActionCommand() == "Q MATRİSİ") {

            Q_Matrisi();
        }
        if (e.getActionCommand() == "HAREKET") {

            hareket();

        }

        if (e.getActionCommand() == "ADIM GRAFİĞİ") {

            graf1 grafik1 = new graf1(yollar);

        }
        if (e.getActionCommand() == "MALİYET GRAFİĞİ") {

            graf2 grafik2 = new graf2(qlar);
        }
    }
}
