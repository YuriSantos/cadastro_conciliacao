package br.jus.jfpb.cadastro.model;

import java.io.Serializable;

public class Estatistica implements Serializable{

    private String name;
    private int y;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /* @Override
    public String toString() {
        return "[{" +
                "\""+"label\": \"" + name + '\"' +','+'\n'+"\"total \": \"" +id+"\""+
                "},]";
    }*/
}
