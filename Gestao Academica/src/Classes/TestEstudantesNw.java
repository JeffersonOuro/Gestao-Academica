/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.IOException;

/**
 *
 * @author Gustavo
 */
public class TestEstudantesNw {
    
    public static void main(String[] args) throws IOException {
        CentroUniversitario facul = new CentroUniversitario("Senac");
        facul.carregarDados("arqDisciplinas.txt", "estudantes.txt", "arqMatriculas.txt"); 
        
        /*Listando 1 a 1 para verificar se cada objeto está no seu devido lugar */
        
        //pos
        System.out.println("\n\nPos");
        System.out.println(facul.getEstudantes().get(0));
        System.out.println(facul.getEstudantes().get(0).getCreditos());
        //grad
        System.out.println("\n\nGRAD");
        System.out.println(facul.getEstudantes().get(1));
        System.out.println(facul.getEstudantes().get(1).getCreditos());
        //Pos
        System.out.println("\n\nPos");
        System.out.println(facul.getEstudantes().get(2));
        System.out.println(facul.getEstudantes().get(2).getCreditos());
        
        /*Listar All Estudantes*/
        System.out.println(facul.getEstudantes());
        
    }
    
}
