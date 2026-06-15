package br.com.motiva.intervencao;

import br.com.motiva.dominio.TrechoRodovia;

// Classe base: define o que toda intervencao operacional precisa ter.
public abstract class IntervencaoOperacional {
    private String nome;

    public IntervencaoOperacional(String nome) {
        this.setNome(nome);
    }

    public String getNome() {
        return this.nome;
    }

    // Cada filha decide como o servico sera executado no trecho.
    public abstract void executarServico(TrechoRodovia trecho);

    private void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            this.nome = "Intervencao sem nome";
            return;
        }

        this.nome = nome;
    }
}
