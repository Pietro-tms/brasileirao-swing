/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author strim
 */
public class Jogo {
    private Time time1;
    private Time time2;
    private int gol1;
    private int gol2;

    public Jogo(Time time1, Time time2, int gol1, int gol2) {
        this.time1 = time1;
        this.time2 = time2;
        this.gol1 = gol1;
        this.gol2 = gol2;
    }

    Jogo(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Time getTime1() {
        return time1;
    }

    public Time getTime2() {
        return time2;
    }

    public int getGol1() {
        return gol1;
    }

    public int getGol2() {
        return gol2;
    }
    
    
}
