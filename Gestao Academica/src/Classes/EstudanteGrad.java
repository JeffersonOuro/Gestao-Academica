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
public class EstudanteGrad extends Estudante{
    
    protected int horasAtividade;

    public EstudanteGrad(long id, String nome, String email,int horasAtividade) {
        super(id, nome, email);
        this.horasAtividade = horasAtividade;
    }

    public int getHorasAtividade() {
        return horasAtividade;
    }

    public void setHorasAtividade(int horasAtividade) {
        this.horasAtividade = horasAtividade;
    }
    
    @Override
    public double getCreditos() {
        return horasAtividade + this.somaCreditos();
    }

    @Override
    public String toString() {
        return "\nEstudanteGrad Id:" + id + " | Nome: " + nome + " | Email: " + email + " + horasAtividade=" + horasAtividade + ']'+" creditos = "+getCreditos();
    }


    
}
