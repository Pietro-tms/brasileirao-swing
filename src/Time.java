/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author strim
 */
public class Time {
    private String nome;
    private int p=0;
    private int v=0;
    private int e=0;
    private int d=0;
    private int gp=0;
    private int gn=0;
    private int sg=0;

    public Time(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getP() {
        return p;
    }

    public int getV() {
        return v;
    }

    public int getE() {
        return e;
    }

    public int getD() {
        return d;
    }

    public int getGp() {
        return gp;
    }

    public int getGn() {
        return gn;
    }

    public int getSg() {
        return sg;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setP(int p) {
        this.p = p;
    }

    public void setV(int v) {
        this.v = v;
    }

    public void setE(int e) {
        this.e = e;
    }

    public void setD(int d) {
        this.d = d;
    }

    public void setGp(int gp) {
        this.gp = gp;
    }

    public void setGn(int gn) {
        this.gn = gn;
    }

    public void setSg(int sg) {
        this.sg = sg;
    }
    
    
}
