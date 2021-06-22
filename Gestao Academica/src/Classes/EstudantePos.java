/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Gustavo
 */
public class EstudantePos extends Estudante {
    
    protected String tema;
    protected String orientador;

    public EstudantePos(long id, String nome, String email,String tema, String orientador) {
        super(id, nome, email);
        this.tema = tema;
        this.orientador = orientador;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getOrientador() {
        return orientador;
    }

    public void setOrientador(String orientador) {
        this.orientador = orientador;
    }
    
    @Override
    public double getCreditos() {
        return this.somaCreditos();
    }
    
    @Override
    public String toString() {
        return "\nEstudantePosId:" + id + " | Nome: " + nome + " | Email: " + email + "[ + tema=" + tema + ", orientador=" + orientador + ']'+" creditos = "+getCreditos();
    }
    
    
    
    
    
}
