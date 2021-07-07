
package yazlab3;

import javax.swing.JButton;


public class Btn extends JButton{
     private int sutun, satir,ödüldegeri=5,cezadegeri=-5,bosgecisdegeri=3,robotkonum,hedefkonum;
    private boolean ceza,ödül,bosluk,hedef,robot;

    public Btn(int satir, int sutun) {
        this.sutun = sutun;
        this.satir = satir;
        this.bosgecisdegeri=bosgecisdegeri;
        this.cezadegeri=cezadegeri;
        this.ödüldegeri=ödüldegeri;
        this.ceza = false;
        this.ödül = false;
        this.bosluk = false;
        this.hedef = false;
        this.robot = false;
    }

    public boolean isRobot() {
        return robot;
    }

    public void setRobot(boolean robot) {
        this.robot = robot;
    }

    public boolean isHedef() {
        return hedef;
    }

    public void setHedef(boolean hedef) {
        this.hedef = hedef;
    }

    public boolean isBosluk() {
        return bosluk;
    }

    public void setBosluk(boolean bosluk) {
        this.bosluk = bosluk;
    }

    public int getÖdüldegeri() {
        return ödüldegeri;
    }

    public void setÖdüldegeri(int ödüldegeri) {
        this.ödüldegeri = ödüldegeri;
    }

    public int getCezadegeri() {
        return cezadegeri;
    }

    public void setCezadegeri(int cezadegeri) {
        this.cezadegeri = cezadegeri;
    }

    public int getBosgecisdegeri() {
        return bosgecisdegeri;
    }

    public void setBosgecisdegeri(int bosgecisdegeri) {
        this.bosgecisdegeri = bosgecisdegeri;
    }

    public int getSutun() {
        return sutun;
    }

    public void setSutun(int sutun) {
        this.sutun = sutun;
    }

    public int getSatir() {
        return satir;
    }

    public void setSatir(int satir) {
        this.satir = satir;
    }

    public boolean isCeza() {
        return ceza;
    }

    public void setCeza(boolean ceza) {
        this.ceza = ceza;
    }

    public boolean isÖdül() {
        return ödül;
    }

    public void setÖdül(boolean ödül) {
        this.ödül = ödül;
    }
    
}
