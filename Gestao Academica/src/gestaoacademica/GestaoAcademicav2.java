/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestaoacademica;

import Classes.CentroUniversitario;
import static gestaoacademica.GestaoAcademica.entrada;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Gustavo
 */
public class GestaoAcademicav2 {
    
    public static void main(String[] args) throws IOException {
        entrada = new Scanner(System.in);
        boolean sair = false;

        CentroUniversitario centroUniversitario = new CentroUniversitario("Senac");
        centroUniversitario.carregarDados("arqDisciplinas.txt", "estudantes.txt", "arqMatriculas.txt");
        //System.out.println(centroUniversitario);
        
        while (!sair) {

            System.out.print("|===============>> ESCOLHA UMA OPÇÃO <<================|\n"
                    + "|------------------------------------------------------|\n"
                    + "| (1) Listar os números e nomes de todos os estudantes |\n"
                    + "|------------------------------------------------------|\n"
                    + "| (2) Listar os códigos de todas as disciplinas        |\n"
                    + "|------------------------------------------------------|\n"
                    + "| (3) Listar todas as informações dos estudantes       |\n"
                    + "|     matriculados em uma determinada disciplina       |\n"
                    + "|------------------------------------------------------|\n"
                    + "| (4) Listar todas as informações das disciplinas em   |\n"
                    + "|     que um determinado estudante está matriculado    |\n"
                    + "|------------------------------------------------------|\n"
                    + "| (5) Sair                                             |\n"
                    + "|======================================================|\n"
                    + "\n"
                    + "Digite uma opção: ");

            int opt = entrada.nextInt();
            System.out.println("");
            
            switch (opt) {
                case 1:
                    /*Feito com sucesso*/
                    centroUniversitario.exibirEstundantes();
                    break;
                    
                case 2:
                    /*Feito com sucesso*/
                    centroUniversitario.exibirDisciplinas();
                    break;
                    
                case 3:
                    /*Feito com sucesso*/
                    System.out.print("Digite o cod da disciplina: ");
                    String codDis = entrada.next();
                    centroUniversitario.listarEstudantesMatriculados(codDis);
                    break;
                    
                case 4:
                    /*Feito com sucesso*/
                    System.out.print("Digite ID do estudante: ");
                    long id = entrada.nextLong();
                    centroUniversitario.FindEstudante(id);
                    break;
                
                case 5:
                    sair = true;
                    break;
                
                default:
                    System.out.println("Opção inválida ' "+opt+" '");
            }
            System.out.println();
        }
        System.out.println("Fim do programa ");
       
        
    
    }
}
