package Classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JPG
 */
public class CentroUniversitario {

    private String nome;
    private List<Estudante> estudantes;
    private List<Disciplina> disciplinas;
    private List<Matricula> matriculas;//**
    
    public CentroUniversitario(String nome) {
        this.nome = nome;
        this.estudantes = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
        this.matriculas = new ArrayList<>();//**
    }

    public void carregarDados(String arqDisciplinas, String arqEstudantes, String arqMatriculas) throws FileNotFoundException, IOException {
        
        try {
            BufferedReader lerEstudantes = new BufferedReader(new FileReader(arqEstudantes));
            BufferedReader lerDisciplinas = new BufferedReader(new FileReader(arqDisciplinas));
            BufferedReader lerMatriculas = new BufferedReader(new FileReader(arqMatriculas));

            String linha = "";
            while ((linha = lerEstudantes.readLine()) != null) {
                
                String[] dadosAlunos = linha.split(":");
                
                long id = Long.parseLong(dadosAlunos[0]);
                String nome = dadosAlunos[1];
                String email = dadosAlunos[2];
                
                String tipo = dadosAlunos[3]; 
                
                switch(tipo){
                    
                    case "GRAD":
                        
                        System.out.println("Entrou em Graduação");
                        int horasComplementares = Integer.parseInt(dadosAlunos[4]);
                        Estudante G = new EstudanteGrad(id,nome,email,horasComplementares);
                        AddEstudante(G);
                        break;
                        
                    case"POS":
                        
                        System.out.println("Entrou em Pos");
                        String tema = dadosAlunos[4];
                        String orientador = dadosAlunos[5];
                        Estudante P = new EstudantePos(id,nome,email,tema,orientador);
                        AddEstudante(P);
                        break;
                }
                
                //estudantes.add(estudante);
            }
            
            while ((linha = lerDisciplinas.readLine()) != null) {
                String[] dadosDisciplinas = linha.split(":");
                String codigo = dadosDisciplinas[0];
                int creditos = Integer.parseInt(dadosDisciplinas[1]);
                Disciplina disciplina = new Disciplina(codigo, creditos);
                disciplinas.add(disciplina);
            }
            
            while ((linha = lerMatriculas.readLine()) != null) {
                String[] dadosDisciplinas = linha.split(":");
                long codigoAluno = Long.parseLong(dadosDisciplinas[0]);
                String codigoDisciplina = dadosDisciplinas[1];

                Estudante estudanteRef = null;

                for (Estudante estudante : estudantes) {
                    if (estudante.getId() == codigoAluno) {
                        estudanteRef = estudante;
                    }
                }

                Disciplina disciplinaRef = null;

                for (Disciplina disciplina : disciplinas) {
                    if (disciplina.getCodigo().equals(codigoDisciplina)) {
                        disciplinaRef = disciplina;
                    }
                }

                if (disciplinaRef == null || estudanteRef == null) {
                    System.out.println("Aluno ou disciplina não encontrados");
                } else {
                    Matricula matricula = new Matricula(estudanteRef, disciplinaRef);
                    estudanteRef.addMatricula(matricula);
                    disciplinaRef.addMatricula(matricula);
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
    
    public String getNome() {
        return nome;
    }
    
    public void AddEstudante(Estudante a ){
        estudantes.add(a);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Estudante> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(List<Estudante> estudantes) {
        this.estudantes = estudantes;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public List<Matricula> getMatriculas() {//*
        return matriculas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
    
    public Estudante FindEstudanteByTest(long id,String nome,String email){
        for (Estudante e : estudantes) {
            if(e.getId() == id || e.getNome().equals(nome) || e.getEmail().equals(email)){
                return e;
            }
        }
        return null;
    }
    
    public Disciplina FindDisciplinaByTest(String cod, int creditos){
        for (Disciplina d: disciplinas) {
            if(d.getCodigo().equals(cod)|| d.getCreditos() == creditos){
                return d;
            }
        }
        return null;
    }
    
    public void exibirEstundantes() {

        for (Estudante e : estudantes) {
            if (e instanceof EstudanteGrad) {
                System.out.println("Graduação:  ID :"+e.getId()+", Nome "+ e.getNome()+","
                + "creditos : "+e.getCreditos()+" Horas Complementares: "+((EstudanteGrad) e).getHorasAtividade());
            }
            if (e instanceof EstudantePos) {
                System.out.println("Pós-Graduação:  ID :"+e.getId()+", Nome "+ e.getNome()+","
                + "creditos : "+e.getCreditos()+" Tema: "+((EstudantePos) e).getTema()
                +"Orientador : "+((EstudantePos) e).getOrientador());
            }
        }
    }
    
    public void exibirDisciplinas(){
        for (Disciplina d : disciplinas) {
            System.out.println(d.getCodigo()+"        "+d.getCreditos());
        }
    }
    
    public void FindDisciplina(String cod){
        
        for (Disciplina d: disciplinas) {
            if(d.getCodigo().equals(cod)){
                System.out.println("Total de alunos na Disciplina ("+cod+")  = "+d.getEstudantesMatriculados().size()+"\n");
                System.out.println(d.getEstudantesMatriculados());
            }
        }
    }
    
    public void FindEstudante(long id){
        
        for (Estudante e: estudantes) {
            if(e.getId() == id){
                System.out.println("Total de disciplinas matriculadas pelo aluno ( "+e.getNome()+" )  = "+e.getDisciplinasMatriculadas().size()+"\n");
                System.out.println(e.getDisciplinasMatriculadas());
            }
        }
    }
    public int FindTodasMatriculas(){
        int cont = 0;
        for (int i = 0; i < this.estudantes.size(); i++) {
            if(estudantes.get(i).getId() > 0 ){
                cont += estudantes.get(i).getDisciplinasMatriculadas().size();
            }
        }
        return cont;
    }
    public int FindEstudanteByTest(long id){
        
        for (Estudante e: estudantes) {
            if(e.getId() == id){
                int tam = e.getDisciplinasMatriculadas().size();
                return  tam;
            }
        }
        return -1;
    }
    
    public int FindDisciplinaByTest(String cod){
        
        for (Disciplina d: disciplinas) {
            if(d.getCodigo() == cod){
                int tam = d.getEstudantesMatriculados().size();
                return  tam;
            }
        }
        return -1;
    }
    
    public Matricula FindEstudanteMatricula(long id , String cod){
        
        for (int i = 0; i < FindTodasMatriculas(); i++) {
            if (disciplinas.get(i).getCodigo().equals(cod) && estudantes.get(i).getId() == id) {
                Matricula resultado = estudantes.get(i).getMatriculas().get(i);
                return resultado;
            }
        }
        return null;
    }
    
    public void listarEstudantesMatriculados(String cod){
        int total =0;
        for (int i = 0; i < disciplinas.size(); i++){
            if(cod.equals(disciplinas.get(i).getCodigo())) {
                System.out.println(disciplinas.get(i).getEstudantesMatriculados());
                total = disciplinas.get(i).getTotalEstudantes();
            }
        }  
        System.out.println("\nTotal de estudantes matriculados = "+total);
    }
    
    public void listarDisciplinasMatriculados(long id){
        int total = 0;
        for (Estudante e : estudantes){
            if(id == e.getId()){
                System.out.println(e);
                total = e.somaCreditos();
            }
        }
        System.out.println("Total de estudantes matriculados: "+total);
    }

    @Override
    public String toString() {
        return "Centro Universitario " + nome + "\n\n" 
                + ">>>LISTA de ESTUDANTES<<<"+estudantes 
                + "\n\n>>>LISTA de DISCIPLINAS<<<" + disciplinas;
    }
}