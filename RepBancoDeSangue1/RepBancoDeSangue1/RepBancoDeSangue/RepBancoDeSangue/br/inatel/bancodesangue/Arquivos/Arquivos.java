package br.inatel.bancodesangue.Arquivos;

import br.inatel.bancodesangue.Banco.BancoSangue;
import br.inatel.bancodesangue.Sangue.BolsaSangue;

import javax.sound.midi.Patch;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Arquivos {
    private Path caminho;

    public Arquivos(String nomeArquivo) {
        this.caminho = Path.of(nomeArquivo);
    }

    public void salvarEstoque(BancoSangue banco) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(caminho)) {
            writer.write("id;tipo;dataColeta;dataValidade;liberada");
            writer.newLine();
            for (BolsaSangue bolsa : banco.getEstoque()) {
                writer.write(bolsa.toString());
                writer.newLine();
            }
        }
        System.out.println("Estoque salvo em " + caminho.toAbsolutePath());
    }

    public void mostrarArquivo() throws IOException {
        if (!Files.exists(caminho)) {
            System.out.println("Arquivo ainda não existe. Salve o estoque primeiro.");
            return;
        }
        System.out.println("===== CONTEÚDO DO ARQUIVO =====");
        for (String linha : Files.readAllLines(caminho)) {
            System.out.println(linha);
        }
    }
}
