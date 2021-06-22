/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Classes.CentroUniversitario;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gustavo
 */
public class NewTest {
    
    private final CentroUniversitario centro_universitario;
    private final String nomeCentro_UniversitarioEsperado;
    private String[][] disciplinasArray;
    private String[][] estudantesArray;
    private int[] matriculasPorEstudante;
    private int[] matriculasPorDisciplina;
    private String[][] matriculasArray;

    public NewTest() throws FileNotFoundException, IOException {
        String testeId = randomString("abcde", 10);
        String nomeArquivoDisciplinas = "disciplinas-" + testeId + ".txt";
        String nomeArquivoEstudantes = "estudantes-" + testeId + ".txt";
        String nomeArquivoMatriculas = "matriculas-" + testeId + ".txt";

        

        nomeCentro_UniversitarioEsperado = "Centro_Universitario SENAC" + testeId;
        centro_universitario = new CentroUniversitario(nomeCentro_UniversitarioEsperado);
        centro_universitario.carregarDados(nomeArquivoDisciplinas, nomeArquivoEstudantes,nomeArquivoMatriculas);
    }

    /*public void criarArquivoEstudantes(String nomeArquivo) throws FileNotFoundException {
        estudantesArray = new String[3][];
        String matricula;
        matricula = randomString("1141629800", 8);
        estudantesArray[0] = new String[]{matricula, "Jonatas Gomes", matricula + "@sp.senac.br"};
        matricula = randomString("1140250885", 8);
        estudantesArray[1] = new String[]{matricula, "Lucas Lopes", matricula + "@sp.senac.br"};
        matricula = randomString("1140887929", 8);
        estudantesArray[2] = new String[]{matricula, "Renata Silveira", matricula + "@sp.senac.br"};

        try (PrintWriter pw = new PrintWriter(nomeArquivo)) {
            for (String[] estudante : estudantesArray) {
                pw.println(estudante[0] + ":" + estudante[1] + ":" + estudante[2]);
            }
        }
    }

    public void criarArquivoDisciplinas(String nomeArquivo) throws FileNotFoundException {
        disciplinasArray = new String[3][];
        disciplinasArray[0] = new String[]{"ED002266", "80"};
        disciplinasArray[1] = new String[]{"EC002155", "40"};
        disciplinasArray[2] = new String[]{"POO002067", "80"};

        try (PrintWriter pw = new PrintWriter(nomeArquivo)) {
            for (String[] disciplina : disciplinasArray) {
                pw.println(disciplina[0] + ":" + disciplina[1]);
            }
        }
    }

    public void criarArquivoMatriculas(String nomeArquivo) throws FileNotFoundException {
        matriculasArray = new String[7][];
        matriculasPorEstudante = new int[]{3, 2, 2};
        matriculasPorDisciplina = new int[]{2, 3, 2};
        matriculasArray[0] = new String[]{estudantesArray[0][0], disciplinasArray[0][0]};
        matriculasArray[1] = new String[]{estudantesArray[0][0], disciplinasArray[1][0]};
        matriculasArray[2] = new String[]{estudantesArray[0][0], disciplinasArray[2][0]};
        matriculasArray[3] = new String[]{estudantesArray[1][0], disciplinasArray[1][0]};
        matriculasArray[4] = new String[]{estudantesArray[1][0], disciplinasArray[0][0]};
        matriculasArray[5] = new String[]{estudantesArray[2][0], disciplinasArray[1][0]};
        matriculasArray[6] = new String[]{estudantesArray[2][0], disciplinasArray[2][0]};
        try (PrintWriter pw = new PrintWriter(nomeArquivo)) {
            for (String[] matricula : matriculasArray) {
                pw.println(matricula[0] + ":" + matricula[1]);
            }
        }
    }

    public String randomString(String chars, int length) {
        Random rand = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buf.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return buf.toString();
    }

    public Estudante findEstudanteById(int id, List<Estudante> estudantes) {
        for (Estudante estudante : estudantes) {
            if (id == estudante.getId()) {
                return estudante; //estudante encontrado
            }
        }
        return null; //estudante não encontrado
    }

    public Disciplina findDisciplinaByCodigo(String codigo, List<Disciplina> disciplinas) {
        for (Disciplina disciplina : disciplinas) {
            if (codigo.equals(disciplina.getCodigo())) {
                return disciplina;//disciplina encontrada
            }
        }
        return null;//disciplina não encontrada
    }

    @Test
    public void testNomeCentro_Universitario() {
        String nomeObtido = centro_universitario.getNome();
        assertEquals(nomeCentro_UniversitarioEsperado, nomeObtido);
    }

    @Test
    public void testEstudantes() {
        List<Estudante> estudantes = centro_universitario.getEstudantes();
        int numeroEsperado = estudantesArray.length;
        int numeroObtido = estudantes.size();
        Assert.assertEquals(numeroEsperado, numeroObtido);
        for (String[] estudanteArray : estudantesArray) {
            int id = Integer.parseInt(estudanteArray[0]);
            String nomeEsperado = estudanteArray[1];
            String emailEsperado = estudanteArray[2];
            Estudante estudanteEncontrado = findEstudanteById(id, estudantes);
            assertNotNull(estudanteEncontrado);
            assertEquals(nomeEsperado, estudanteEncontrado.getNome());
            assertEquals(emailEsperado, estudanteEncontrado.getEmail());
        }
    }

    @Test
    public void testDisciplinas() {
        List<Disciplina> disciplinas = centro_universitario.getDisciplinas();
        int numeroEsperado = disciplinasArray.length;
        int numeroObtido = disciplinas.size();
        assertEquals(numeroEsperado, numeroObtido);
        for (String[] disciplinaArray : disciplinasArray) {
            String codigo = disciplinaArray[0];
            int creditos = Integer.parseInt(disciplinaArray[1]);
            Disciplina disciplinaEncontrada = findDisciplinaByCodigo(codigo, disciplinas);
            assertNotNull(disciplinaEncontrada);
            assertEquals(creditos, disciplinaEncontrada.getCreditos());
        }
    }

    @Test
    public void testMatriculas() {
        List<Estudante> estudantes = centro_universitario.getEstudantes();
        List<Disciplina> disciplinas = centro_universitario.getDisciplinas();
        List<Matricula> matriculas = null;

        for (int i = 0; i < estudantesArray.length; i++) {
            String[] estudanteArray = estudantesArray[i];
            int id = Integer.parseInt(estudanteArray[0]);
            Estudante estudante = findEstudanteById(id, estudantes);
            assertEquals(matriculasPorEstudante[i], estudante.getMatriculas().size());
        }

        for (int i = 0; i < disciplinasArray.length; i++) {
            String[] disciplinaArray = disciplinasArray[i];
            String codigo = disciplinaArray[0];
            Disciplina disciplina = findDisciplinaByCodigo(codigo, disciplinas);
            assertEquals(matriculasPorDisciplina[i], disciplina.getMatriculas().size());
        }

        for (String[] matriculaArray : matriculasArray) {
            int idEstudante = Integer.parseInt(matriculaArray[0]);
            String codigoDisciplina = matriculaArray[1];
            Estudante estudante = findEstudanteById(idEstudante, estudantes);
            Disciplina disciplina = findDisciplinaByCodigo(codigoDisciplina, disciplinas);

            Matricula matriculaEncontradaInEstudante = null;
            matriculas = estudante.getMatriculas();
            for (Matricula matricula : matriculas) {
                if (codigoDisciplina.equals(matricula.getDisciplina().getCodigo())
                        && idEstudante == matricula.getEstudante().getId()) {
                    matriculaEncontradaInEstudante = matricula;
                    break;
                }
            }
            assertNotNull(matriculaEncontradaInEstudante);

            Matricula matriculaEncontradaInDisciplina = null;
            matriculas = disciplina.getMatriculas();
            for (Matricula matricula : matriculas) {
                if (codigoDisciplina.equals(matricula.getDisciplina().getCodigo())
                        && idEstudante == matricula.getEstudante().getId()) {
                    matriculaEncontradaInDisciplina = matricula;
                    break;
                }
            }
            assertNotNull(matriculaEncontradaInDisciplina);
        }*/

    private String randomString(String abcde, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
