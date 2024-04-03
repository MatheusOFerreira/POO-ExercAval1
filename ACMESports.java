import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ACMESports {
    private Plantel plantel;
    private Medalheiro medalheiro;
    private Scanner sc = new Scanner(System.in);  // Atributo para entrada de dados
    private PrintStream saidaPadrao = System.out;   // Guarda a saida padrao - tela (console)
    private final String nomeArquivoEntrada = "dadosin.txt";  // Nome do arquivo de entrada de dados
    private final String nomeArquivoSaida = "dadosout.txt";  // Nome do arquivo de saida de dados

    public ACMESports(){
        plantel = new Plantel();
        medalheiro = new Medalheiro();
        redirecionaES();    // Redireciona E/S para arquivos
    }

    public void executar(){
        adicionaAtletas();
        adicionaMedalhas();
        cadastraMedalhasAtletasCorrespondentes();
        mostraDadosAtletaPorNumero();
        mostraDadosAtletaPorNome();
        mostraDadosMedalha();
        mostraDadosAtletaPorPais();
        mostraDadosAtletaPorTipoMedalha();
        mostraDadosAtletaPorModalidade();
        mostraDadosAtletaComMaisMedalhas();
        mostraQuadroGeralMedalhasPais();
        mostraQuadroGeralMedalhas();

    }

    //lê todos os dados de cada atleta e, se o número não for
    //repetido, cadastra-o no sistema. Para cada atleta cadastrado com sucesso no
    //sistema, mostra os dados do atleta no formato: 1:número,nome,país

    private void adicionaAtletas(){
        int numero;
        String nome;
        String pais;
        numero = sc.nextInt();
        sc.nextLine();
        while(numero != -1){
            nome = sc.nextLine();
            pais = sc.nextLine();
            Atleta a = new Atleta(numero, nome, pais);
            if(plantel.cadastraAtleta(a)){
                System.out.println("1: " + a.getNumero() + ", " + a.getNome() + ", " + a.getPais());
            }
            numero = sc.nextInt();
            sc.nextLine();
        }
    }

    //lê todos os dados de cada medalha e, se o código não for
    //repetido, cadastra-a no sistema. Para cada medalha cadastrada com sucesso no
    //sistema, mostra os dados da medalha no formato: 2:codigo,tipo,é
    //individual?,modalidade

    private void adicionaMedalhas(){
        int codigo;
        int tipo;
        boolean individual;
        String modalidade;
        codigo = sc.nextInt();
        sc.nextLine();
        while(codigo != -1){
            tipo = sc.nextInt();
            sc.nextLine();
            individual = Boolean.parseBoolean(sc.next());
            sc.nextLine();
            modalidade = sc.nextLine();
            Medalha m = new Medalha(codigo, tipo, individual, modalidade);
            if(medalheiro.cadastraMedalha(m)){
                System.out.println("2: " + m.getCodigo() + ", " + m.getTipo() + ", " + m.isIndividual() + ", " + m.getModalidade());
            }
            codigo = sc.nextInt();
        }
    }

    //adiciona uma medalha para
    //cada atleta e vice-versa. Para cada cadastramento com sucesso mostra os dados
    //no formato: 3:código,número

    private void cadastraMedalhasAtletasCorrespondentes() {
        int codigo;
        int numero;
        codigo = sc.nextInt();
        sc.nextLine();
        while(codigo != -1) {
            numero = sc.nextInt();
            sc.nextLine();
            if (medalheiro.consultaMedalha(codigo) != null && plantel.consultaAtleta(numero) != null) {
                Medalha m = medalheiro.consultaMedalha(codigo);
                Atleta a = plantel.consultaAtleta(numero);
                a.adicionaMedalha(m);
                m.adicionaAtelta(a);
                System.out.println("3: " + m.getCodigo() + ", " + a.getNumero());
            }
            codigo = sc.nextInt();
        }
    }

    //lê o número de um
    //determinado atleta. Se não existir um atleta com o número indicado, mostra a
    //mensagem de erro: “4:Nenhum atleta encontrado.”. Se existir, mostra os
    //dados do atleta no formato: 4:número,nome,país

    private void mostraDadosAtletaPorNumero(){
        sc.nextLine();
        int numero = sc.nextInt();
        Atleta a = plantel.consultaAtleta(numero);
        if(a != null){
            System.out.println("4: " + a.getNumero() + ", " + ", " + a.getNome() + ", " + a.getPais());
        } else {
            System.out.println("4: Nenhum atleta encontrado");
        }

    }

    //lê o nome de um
    //determinado atleta. Se não existir um atleta com o nome indicado, mostra a
    //mensagem de erro: “5:Nenhum atleta encontrado.”. Se existir, mostra os
    //dados do atleta no formato: 5:número,nome,país

    private void mostraDadosAtletaPorNome(){
        sc.nextLine();
        String nome = sc.nextLine();
        Atleta a = plantel.consultaAtleta(nome);
        if(a != null){
            System.out.println("5: " + a.getNumero() + ", " + ", " + a.getNome() + ", " + a.getPais());
        } else{
            System.out.println("5: Nenhum atleta encontrado");
        }
    }

    //lê um código de medalha. Se
    //não existir uma medalha com o código indicado, mostra a mensagem de erro:
    //“6:Nenhuma medalha encontrada.”. Se existir, mostra os dados da medalha
    //no formato: 6:codigo,tipo,é individual?,modalidade

    private void mostraDadosMedalha(){
        int codigo = sc.nextInt();
        sc.nextLine();
        Medalha m = medalheiro.consultaMedalha(codigo);
        if(m != null){
            System.out.println("6: " + m.getCodigo() + ", " + m.getTipo() + ", " + m.isIndividual()
                    + ", " + m.getModalidade());
        } else{
            System.out.println("6: Nenhuma medalha encontrada");
        }
    }

    //lê o nome de um país.
    //Se não existir nenhum país com o nome indicado, mostra a mensagem de erro:
    //“7:Pais nao encontrado.”. Se existir, mostra os dados de cada atleta no
    //formato: 7:número,nome,país

    private void mostraDadosAtletaPorPais(){
        String pais;
        pais = sc.nextLine();
        ArrayList<Atleta> atletasDoPais = new ArrayList<>();
        for(Atleta a : plantel.getAtletas()){
            if(a.getPais().equals(pais)){
                atletasDoPais.add(a);
            }
        }

        if(atletasDoPais.isEmpty()){
            System.out.println("7: Pais nao encontrado.");
        } else {
            for(Atleta a : atletasDoPais){
                if(atletasDoPais != null){
                    System.out.println("7:" + a.getNumero() + " ," + a.getNome() + " ," + a.getPais());
                }
            }
        }
    }

    //lê o tipo de uma
    //medalha. Se não houver nenhum atleta com o tipo de medalha indicado, mostra a
    //mensagem de erro: “8:Nenhum atleta encontrado.”, caso contrário, mostra
    //os dados de cada atleta no formato: 8:número,nome,país

    private void mostraDadosAtletaPorTipoMedalha(){
        int tipo;
        tipo = sc.nextInt();
        sc.nextLine();
        ArrayList<Atleta> atletasComTipoMedalha = new ArrayList<>();
        for(Atleta a : plantel.getAtletas()){
            if(a.temMedalhaTipo(tipo)){
                atletasComTipoMedalha.add(a);
            }
        }

        if(atletasComTipoMedalha.isEmpty()){
            System.out.println("8: Nenhum atleta encontrado");
        } else {
            for(Atleta a : atletasComTipoMedalha){
                if(atletasComTipoMedalha != null){
                    System.out.println("8:" + a.getNumero() + ", " + a.getNome() + ", " + a.getPais());
                }
            }
        }



    }

    //lê uma modalidade.
    //Se não houver a modalidade no sistema, mostra a mensagem de erro:
    //“9:Modalidade não encontrada.” Se uma medalha não tiver atleta, mostra
    //uma mensagem no formato: 9:modalidade,tipo,Sem atletas com
    //medalha. Caso contrário, mostra os dados de cada atleta da medalha no formato:
    //9:modalidade,tipo,número,nome,país

    private void mostraDadosAtletaPorModalidade(){
        String modalidade = sc.nextLine();
        ArrayList<Medalha> testes = medalheiro.consultaMedalhas(modalidade);
        if(testes == null){
            System.out.println("9: Modalidade não encontrada");
        } else {
            for(Medalha m : testes){
                if(m.getAtletas().isEmpty()){
                    System.out.println("9:" + m.getModalidade() + "," + m.getTipo() + ",Sem atletas com medalha.");
                } else {
                    for(Atleta a : m.getAtletas()){
                        System.out.println("9:" + m.getModalidade() + "," + m.getTipo() + "," +
                                a.getNumero() + "," + a.getNome() + "," + a.getPais());
                    }
                }
            }
        }
    }

    //: localiza o atleta com maior
    //número de medalhas. Se não houver atletas com medalhas, mostra a mensagem
    //de erro: “10:Nenhum atleta com medalha.”. Caso contrário, mostra os dados
    //do atleta e medalhas no formato:
    //10:número,nome,país,Ouro:quantidade,Prata:quantidade,Bronze:
    //quantidade

    private void mostraDadosAtletaComMaisMedalhas(){
        ArrayList<Atleta> atletaComMedalhas = new ArrayList<>();

        for(Atleta a : plantel.getAtletas()){
            if(a.consultaQuantidadedeMedalhas() > 0){
                atletaComMedalhas.add(a);
            }
        }
        if(!atletaComMedalhas.isEmpty()){
            Atleta atletaComMaisMedalhas = atletaComMedalhas.get(0);
            int maiorQuantidadeDeMedalhas = atletaComMaisMedalhas.consultaQuantidadedeMedalhas();

            for(Atleta a : atletaComMedalhas){
                int quantidadeMedalhas = a.consultaQuantidadedeMedalhas();
                if(quantidadeMedalhas > maiorQuantidadeDeMedalhas){
                    atletaComMaisMedalhas = a;
                    maiorQuantidadeDeMedalhas = quantidadeMedalhas;
                }
            }
            System.out.println("10:" + atletaComMaisMedalhas.getNumero() + "," +
                    atletaComMaisMedalhas.getNome() + "," +
                    atletaComMaisMedalhas.getPais() + "," +
                    "Ouro:" + atletaComMaisMedalhas.getTipoMedalha(1) + "," +
                    "Prata:" + atletaComMaisMedalhas.getTipoMedalha(2) + "," +
                    "Bronze:" + atletaComMaisMedalhas.getTipoMedalha(3));
        } else{
            System.out.println("Nenhum atleta com medalha");
        }
    }

    //Mostrar o quadro geral de medalhas por país: nome de cada país, quantidade de
    //medalhas de ouro, quantidade de medalhas de prata, quantidade de medalha de
    //bronze

    private void mostraQuadroGeralMedalhasPais(){
        int tipo1 = 0;
        int tipo2 = 0;
        int tipo3 = 0;
        System.out.println("===========================================");
        System.out.println("MÉTODO EXTRA 01:");
        for(String pais : plantel.getPaises()){
            for(Atleta a : plantel.getAtletas()) {
                if (a.getPais().equalsIgnoreCase(pais)){
                    tipo1 += a.getTipoMedalha(1);
                    tipo2 += a.getTipoMedalha(2);
                    tipo3 += a.getTipoMedalha(3);
                }
            }
            System.out.println("Pais: " + pais + ", " +
                    "Ouro:" + tipo1 + "," +
                    "Prata:" + tipo2 + "," +
                    "Bronze:" + tipo3);
            tipo1 = 0;
            tipo2 = 0;
            tipo3 = 0;

        }
    }

    //Mostrar o quadro geral de medalhas completo: para cada país o seu nome,
    //quantidade total de medalhas, e dados de cada atleta (nome do atleta e a
    //quantidade de cada tipo de medalha)

    private void mostraQuadroGeralMedalhas(){
        System.out.println("===========================================");
        System.out.println("MÉTODO EXTRA 02");
        int tipo1 = 0;
        int tipo2 = 0;
        int tipo3 = 0;
        for(String pais : plantel.getPaises()){
            for(Atleta a : plantel.getAtletas()) {
                if (a.getPais().equalsIgnoreCase(pais)){
                    tipo1 += a.getTipoMedalha(1);
                    tipo2 += a.getTipoMedalha(2);
                    tipo3 += a.getTipoMedalha(3);
                }
            }
            System.out.println("Pais: " + pais + ", " +
                    "Ouro:" + tipo1 + "," +
                    "Prata:" + tipo2 + "," +
                    "Bronze:" + tipo3);
            tipo1 = 0;
            tipo2 = 0;
            tipo3 = 0;

            for (Atleta atleta : plantel.getAtletas()) {
                if (atleta.getPais().equalsIgnoreCase(pais)) {
                    System.out.println();
                    System.out.println("Dados do Atleta:");
                    System.out.println("Nome: " + atleta.getNome());
                    System.out.println("Número: " + atleta.getNumero());
                    System.out.println("País: " + atleta.getPais());
                    System.out.println("Quantidade de medalhas:");
                    System.out.println("Ouro: " + atleta.getTipoMedalha(1));
                    System.out.println("Prata: " + atleta.getTipoMedalha(2));
                    System.out.println("Bronze: " + atleta.getTipoMedalha(3));
                }
            }
        }
    }

    // Redireciona E/S para arquivos
    // Chame este metodo para redirecionar a leitura e escrita de dados para arquivos
    private void redirecionaES() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
            sc = new Scanner(streamEntrada);   // Usa como entrada um arquivo
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida);             // Usa como saida um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
        sc.useLocale(Locale.ENGLISH);   // Ajusta para leitura para ponto decimal
    }

}
