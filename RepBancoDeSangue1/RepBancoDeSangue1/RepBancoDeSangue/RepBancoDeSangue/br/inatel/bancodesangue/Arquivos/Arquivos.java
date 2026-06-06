package br.inatel.bancodesangue.Arquivos;

import br.inatel.bancodesangue.Banco.BancoSangue;
import br.inatel.bancodesangue.Sangue.BolsaSangue;

import javax.sound.midi.Patch;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//classe que permite salvar as bolsas cadastradas e vizualizar as informações cadastradas
public class Arquivos {
    private Path caminho; //caminho do arquivo em que os dados serão armazenados

    public Arquivos(String nomeArquivo) { //construtor da classe
        this.caminho = Path.of(nomeArquivo);
    }

    //salva o estoque atual do banco de sangue em arquivo de texto
    public void salvarEstoque(BancoSangue banco) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(caminho)) { //abre o arquivo para escrita 
            writer.write("id;tipo;dataColeta;dataValidade;liberada"); //escrita do cabeçalho do arquivo
            writer.newLine();
            for (BolsaSangue bolsa : banco.getEstoque()) { //percorre todas as bolsas do estoque e escreve os dados de cada uma 
                writer.write(bolsa.toString()); 
                writer.newLine();
            }
        }
        System.out.println("Estoque salvo em " + caminho.toAbsolutePath()); //mensagem de confirmação
    }

    public void mostrarArquivo() throws IOException { //se o arquivo não existir, exibe o erro
        if (!Files.exists(caminho)) {
            System.out.println("Arquivo ainda não existe. Salve o estoque primeiro.");
            return;
        }
        System.out.println("===== CONTEÚDO DO ARQUIVO ====="); //se o arquivo existir, le e exibe cada linha do arquivo
        for (String linha : Files.readAllLines(caminho)) {
            System.out.println(linha);
        }
    }
}
