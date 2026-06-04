/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagerenciamentopedidos.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class ArquivoUtil {

    // Define o diretório padrão como ./data/ conforme o requisito 5.6 e 6.3
    private static final String DIRETORIO_DADOS = "./data/";

    static {
        // Bloco estático para garantir que a pasta ./data/ seja criada ao iniciar o app (Requisito 6.3)
        File pasta = new File(DIRETORIO_DADOS);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    /**
     * Salva uma String de dados em um arquivo dentro de ./data/
     * Implementa escrita atômica para evitar corrupção de dados (Requisito 5.4)
     */
    public static void salvarDados(String dados, String nomeArquivo) throws IOException {
        String caminhoFinal = DIRETORIO_DADOS + nomeArquivo;
        String caminhoTmp = caminhoFinal + ".tmp";

        File arquivoTmp = new File(caminhoTmp);
        File arquivoFinal = new File(caminhoFinal);

        // Escreve no arquivo temporário usando BufferedWriter e UTF-8 (Requisitos 5.7 e 6.3)
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(arquivoTmp), StandardCharsets.UTF_8))) {
            writer.write(dados);
        }

        // Se a escrita deu certo, substitui o arquivo final (Garante consistência)
        if (arquivoFinal.exists()) {
            arquivoFinal.delete();
        }
        
        if (!arquivoTmp.renameTo(arquivoFinal)) {
            throw new IOException("Falha grave: Não foi possível renomear o arquivo temporário para " + nomeArquivo);
        }
    }

    /**
     * Carrega e lê todo o conteúdo de um arquivo em ./data/ retornando como String
     * Utiliza BufferedReader para melhor performance (Requisito 6.3)
     */
    public static String carregarDados(String nomeArquivo) throws IOException {
        String caminho = DIRETORIO_DADOS + nomeArquivo;
        File arquivo = new File(caminho);

        // Se o arquivo ainda não existe (ex: primeira execução), retorna vazio
        if (!arquivo.exists()) {
            return "";
        }

        StringBuilder conteudo = new StringBuilder();
        
        // Lê usando BufferedReader e UTF-8 (Requisito 6.3)
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(arquivo), StandardCharsets.UTF_8))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        }

        return conteudo.toString();
    }
}