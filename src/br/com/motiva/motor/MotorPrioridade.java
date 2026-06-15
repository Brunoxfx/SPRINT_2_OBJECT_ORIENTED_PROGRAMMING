package br.com.motiva.motor;

import br.com.motiva.dominio.TrechoRodovia;
import br.com.motiva.intervencao.IntervencaoOperacional;
import br.com.motiva.intervencao.Pulverizacao;
import br.com.motiva.intervencao.RocadaManual;
import br.com.motiva.intervencao.RocadaMecanizada;

public class MotorPrioridade {
    public ItemRelatorioPrioridade[] gerarRelatorio(TrechoRodovia[] trechos) {
        ItemRelatorioPrioridade[] itens = new ItemRelatorioPrioridade[trechos.length];

        // Varre o array de trechos, como pedido na entrega da Sprint.
        for (int i = 0; i < trechos.length; i++) {
            TrechoRodovia trecho = trechos[i];

            // O polimorfismo permite que trechos com IoT atualizem o sensor.
            trecho.atualizarPorMonitoramento();

            itens[i] = this.avaliarTrecho(trecho);
        }

        return itens;
    }

    public ItemRelatorioPrioridade avaliarTrecho(TrechoRodovia trecho) {
        double altura = trecho.getAlturaVegetacaoCm();
        IntervencaoOperacional intervencao;
        String motivo;

        // Regra principal: altura da vegetacao e risco definem a intervencao.
        if (altura >= 80.0 && trecho.isRiscoAlto()) {
            intervencao = new RocadaManual();
            motivo = "Vegetacao alta em area com risco operacional alto.";
        } else if (altura >= 80.0) {
            intervencao = new RocadaMecanizada();
            motivo = "Vegetacao alta em area com risco operacional baixo.";
        } else if (altura >= 40.0) {
            intervencao = new Pulverizacao();
            motivo = "Vegetacao media exige controle preventivo.";
        } else {
            intervencao = null;
            motivo = "Vegetacao baixa. Nao ha prioridade de servico.";
        }

        return new ItemRelatorioPrioridade(trecho, intervencao, motivo);
    }
}
