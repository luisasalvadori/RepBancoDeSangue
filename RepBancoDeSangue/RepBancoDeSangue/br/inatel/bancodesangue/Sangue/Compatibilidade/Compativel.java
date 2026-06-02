package br.inatel.bancodesangue.Sangue.Compatibilidade;

import br.inatel.bancodesangue.Sangue.BolsaSangue;

// interface responsável por definir o comportamento de compatibilidade sanguínea.
public interface Compativel {
    // verifica se a bolsa de sangue recebida é compatível. true: compatível; false: não é compatível.
    boolean verificarCompatibilidade(BolsaSangue bolsa);
}
