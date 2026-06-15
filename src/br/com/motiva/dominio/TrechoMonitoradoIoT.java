package br.com.motiva.dominio;

public class TrechoMonitoradoIoT extends TrechoRodovia implements MonitoravelViaIoT {
    private double ultimaLeituraSensorCm;

    public TrechoMonitoradoIoT(double kmInicial, double kmFinal, double alturaVegetacaoCm, String tipoAmbiente,
            String riscoOperacional, double ultimaLeituraSensorCm) {
        super(kmInicial, kmFinal, alturaVegetacaoCm, tipoAmbiente, riscoOperacional);
        this.setUltimaLeituraSensorCm(ultimaLeituraSensorCm);
    }

    public double getUltimaLeituraSensorCm() {
        return this.ultimaLeituraSensorCm;
    }

    public void atualizarLeituraSensor(double novaLeituraSensorCm) {
        this.setUltimaLeituraSensorCm(novaLeituraSensorCm);
    }

    @Override
    public void atualizarPorMonitoramento() {
        this.transmitirDadosSensor();
    }

    @Override
    public double transmitirDadosSensor() {
        // A leitura do sensor atualiza automaticamente a altura da vegetacao.
        this.atualizarAlturaVegetacao(this.ultimaLeituraSensorCm);
        return this.ultimaLeituraSensorCm;
    }

    private void setUltimaLeituraSensorCm(double ultimaLeituraSensorCm) {
        if (ultimaLeituraSensorCm >= 0) {
            this.ultimaLeituraSensorCm = ultimaLeituraSensorCm;
        }
    }
}
