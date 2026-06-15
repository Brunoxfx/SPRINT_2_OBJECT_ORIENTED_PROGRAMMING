package br.com.motiva.app;

import br.com.motiva.dominio.TrechoMonitoradoIoT;
import br.com.motiva.dominio.TrechoRodovia;
import br.com.motiva.motor.ItemRelatorioPrioridade;
import br.com.motiva.motor.MotorPrioridade;
import br.com.motiva.motor.RelatorioPrioridade;

public class Main {
    public static void main(String[] args) {
        // Cenarios usados para demonstrar as regras do motor de prioridade.
        TrechoRodovia trechoSeco = new TrechoRodovia(10.0, 11.0, 35.0, TrechoRodovia.AMBIENTE_SECO,
                TrechoRodovia.RISCO_BAIXO);
        TrechoRodovia trechoUmido = new TrechoRodovia(20.0, 21.0, 70.0, TrechoRodovia.AMBIENTE_UMIDO,
                TrechoRodovia.RISCO_BAIXO);
        TrechoRodovia trechoRiscoAlto = new TrechoRodovia(30.0, 31.0, 92.0, TrechoRodovia.AMBIENTE_UMIDO,
                TrechoRodovia.RISCO_ALTO);
        TrechoMonitoradoIoT trechoComSensor = new TrechoMonitoradoIoT(40.0, 41.0, 20.0,
                TrechoRodovia.AMBIENTE_UMIDO, TrechoRodovia.RISCO_BAIXO, 88.0);

        // O trecho umido cresce mais rapido que o trecho seco.
        trechoSeco.simularCrescimentoPorDias(5);
        trechoUmido.simularCrescimentoPorDias(5);

        TrechoRodovia[] trechos = { trechoSeco, trechoUmido, trechoRiscoAlto, trechoComSensor };

        MotorPrioridade motor = new MotorPrioridade();
        ItemRelatorioPrioridade[] itens = motor.gerarRelatorio(trechos);

        RelatorioPrioridade relatorio = new RelatorioPrioridade();
        relatorio.imprimir(itens);
    }
}
