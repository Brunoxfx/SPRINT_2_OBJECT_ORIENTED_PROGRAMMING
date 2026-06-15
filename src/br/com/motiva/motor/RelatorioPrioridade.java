package br.com.motiva.motor;

import br.com.motiva.dominio.TrechoRodovia;
import br.com.motiva.intervencao.IntervencaoOperacional;

public class RelatorioPrioridade {
    public void imprimir(ItemRelatorioPrioridade[] itens) {
        System.out.println("=== Relatorio de Prioridade ===");

        for (int i = 0; i < itens.length; i++) {
            ItemRelatorioPrioridade item = itens[i];
            TrechoRodovia trecho = item.getTrecho();
            IntervencaoOperacional intervencao = item.getIntervencao();

            System.out.println();
            System.out.println("Trecho: " + trecho.getDescricaoKm());
            System.out.println("Ambiente: " + trecho.getTipoAmbiente());
            System.out.println("Risco operacional: " + trecho.getRiscoOperacional());
            System.out.println("Altura da vegetacao: " + trecho.getAlturaVegetacaoCm() + " cm");

            if (item.precisaIntervencao()) {
                System.out.println("Prioridade: " + intervencao.getNome());
            } else {
                System.out.println("Prioridade: sem intervencao");
            }

            System.out.println("Motivo: " + item.getMotivo());
        }
    }
}
