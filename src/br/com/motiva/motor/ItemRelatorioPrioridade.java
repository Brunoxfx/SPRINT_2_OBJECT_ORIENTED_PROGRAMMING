package br.com.motiva.motor;

import br.com.motiva.dominio.TrechoRodovia;
import br.com.motiva.intervencao.IntervencaoOperacional;

public class ItemRelatorioPrioridade {
    private TrechoRodovia trecho;
    private IntervencaoOperacional intervencao;
    private String motivo;

    public ItemRelatorioPrioridade(TrechoRodovia trecho, IntervencaoOperacional intervencao, String motivo) {
        this.trecho = trecho;
        this.intervencao = intervencao;
        this.motivo = motivo;
    }

    public TrechoRodovia getTrecho() {
        return this.trecho;
    }

    public IntervencaoOperacional getIntervencao() {
        return this.intervencao;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public boolean precisaIntervencao() {
        return this.intervencao != null;
    }
}
