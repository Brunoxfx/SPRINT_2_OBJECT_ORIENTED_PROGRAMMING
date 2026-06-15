package br.com.motiva.intervencao;

import br.com.motiva.dominio.TrechoRodovia;

public abstract class IntervencaoOperacional {
    private String nome;

    public IntervencaoOperacional(String nome) {
        this.setNome(nome);
    }

    public String getNome() {
        return this.nome;
    }

    public abstract void executarServico(TrechoRodovia trecho);

    private void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            this.nome = "Intervencao sem nome";
            return;
        }

        this.nome = nome;
    }
}
