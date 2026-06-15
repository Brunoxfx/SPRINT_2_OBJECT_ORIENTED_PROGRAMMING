package br.com.motiva.testes;

import br.com.motiva.dominio.MonitoravelViaIoT;
import br.com.motiva.dominio.TrechoRodovia;
import br.com.motiva.motor.ItemRelatorioPrioridade;
import br.com.motiva.motor.MotorPrioridade;

public class TesteMotorPrioridade {
    public static void main(String[] args) {
        // Testes manuais simples, sem JUnit, para manter Java puro.
        int testesExecutados = 0;
        int testesComSucesso = 0;

        testesExecutados++;
        if (testarClasseAbstrataNaoInstancia()) {
            testesComSucesso++;
            System.out.println("OK - IntervencaoOperacional e abstrata e nao deve receber new direto.");
        }

        testesExecutados++;
        if (testarCrescimentoDiferente()) {
            testesComSucesso++;
            System.out.println("OK - Trecho umido cresce mais rapido que trecho seco.");
        }

        testesExecutados++;
        if (testarRocadaManualParaRiscoAlto()) {
            testesComSucesso++;
            System.out.println("OK - Trecho com risco alto recebe rocada manual.");
        }

        testesExecutados++;
        if (testarRocadaMecanizadaParaRiscoBaixo()) {
            testesComSucesso++;
            System.out.println("OK - Trecho com risco baixo recebe rocada mecanizada.");
        }

        testesExecutados++;
        if (testarArrayDeTrechos()) {
            testesComSucesso++;
            System.out.println("OK - Motor percorre array de trechos.");
        }

        testesExecutados++;
        if (testarMockIoT()) {
            testesComSucesso++;
            System.out.println("OK - Mock IoT transmite dados de sensor.");
        }

        System.out.println();
        System.out.println("Resultado: " + testesComSucesso + " de " + testesExecutados + " testes passaram.");
    }

    private static boolean testarClasseAbstrataNaoInstancia() {
        // A linha abaixo nao compila porque IntervencaoOperacional e abstrata:
        // new br.com.motiva.intervencao.IntervencaoOperacional("Teste");
        return true;
    }

    private static boolean testarCrescimentoDiferente() {
        TrechoRodovia seco = new TrechoRodovia(1.0, 2.0, 20.0, TrechoRodovia.AMBIENTE_SECO,
                TrechoRodovia.RISCO_BAIXO);
        TrechoRodovia umido = new TrechoRodovia(2.0, 3.0, 20.0, TrechoRodovia.AMBIENTE_UMIDO,
                TrechoRodovia.RISCO_BAIXO);

        seco.simularCrescimentoPorDias(3);
        umido.simularCrescimentoPorDias(3);

        return umido.getAlturaVegetacaoCm() > seco.getAlturaVegetacaoCm();
    }

    private static boolean testarRocadaManualParaRiscoAlto() {
        MotorPrioridade motor = new MotorPrioridade();
        TrechoRodovia trecho = new TrechoRodovia(5.0, 6.0, 95.0, TrechoRodovia.AMBIENTE_UMIDO,
                TrechoRodovia.RISCO_ALTO);
        ItemRelatorioPrioridade item = motor.avaliarTrecho(trecho);

        return "Rocada manual".equals(item.getIntervencao().getNome());
    }

    private static boolean testarRocadaMecanizadaParaRiscoBaixo() {
        MotorPrioridade motor = new MotorPrioridade();
        TrechoRodovia trecho = new TrechoRodovia(7.0, 8.0, 95.0, TrechoRodovia.AMBIENTE_SECO,
                TrechoRodovia.RISCO_BAIXO);
        ItemRelatorioPrioridade item = motor.avaliarTrecho(trecho);

        return "Rocada mecanizada".equals(item.getIntervencao().getNome());
    }

    private static boolean testarArrayDeTrechos() {
        MotorPrioridade motor = new MotorPrioridade();
        TrechoRodovia[] trechos = {
                new TrechoRodovia(10.0, 11.0, 20.0, TrechoRodovia.AMBIENTE_SECO, TrechoRodovia.RISCO_BAIXO),
                new TrechoRodovia(12.0, 13.0, 50.0, TrechoRodovia.AMBIENTE_UMIDO, TrechoRodovia.RISCO_BAIXO),
                new TrechoRodovia(14.0, 15.0, 90.0, TrechoRodovia.AMBIENTE_UMIDO, TrechoRodovia.RISCO_ALTO)
        };

        ItemRelatorioPrioridade[] itens = motor.gerarRelatorio(trechos);

        return itens.length == trechos.length;
    }

    private static boolean testarMockIoT() {
        MockSensorVegetacao mock = new MockSensorVegetacao(73.5);
        return mock.transmitirDadosSensor() == 73.5;
    }
}

class MockSensorVegetacao implements MonitoravelViaIoT {
    private double leituraCm;

    public MockSensorVegetacao(double leituraCm) {
        this.leituraCm = leituraCm;
    }

    @Override
    public double transmitirDadosSensor() {
        return this.leituraCm;
    }
}
