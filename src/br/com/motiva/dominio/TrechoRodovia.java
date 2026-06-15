package br.com.motiva.dominio;

public class TrechoRodovia {
    public static final String AMBIENTE_SECO = "seco";
    public static final String AMBIENTE_UMIDO = "umido";

    public static final String RISCO_BAIXO = "baixo";
    public static final String RISCO_ALTO = "alto";

    private double kmInicial;
    private double kmFinal;
    private double alturaVegetacaoCm;
    private String tipoAmbiente;
    private String riscoOperacional;

    public TrechoRodovia(double kmInicial, double kmFinal, double alturaVegetacaoCm, String tipoAmbiente,
            String riscoOperacional) {
        this.setKmInicial(kmInicial);
        this.setKmFinal(kmFinal);
        this.setAlturaVegetacaoCm(alturaVegetacaoCm);
        this.setTipoAmbiente(tipoAmbiente);
        this.setRiscoOperacional(riscoOperacional);
    }

    public double getKmInicial() {
        return this.kmInicial;
    }

    public double getKmFinal() {
        return this.kmFinal;
    }

    public double getAlturaVegetacaoCm() {
        return this.alturaVegetacaoCm;
    }

    public String getTipoAmbiente() {
        return this.tipoAmbiente;
    }

    public String getRiscoOperacional() {
        return this.riscoOperacional;
    }

    public String getDescricaoKm() {
        return "KM " + this.kmInicial + " ao KM " + this.kmFinal;
    }

    public void atualizarAlturaVegetacao(double novaAlturaCm) {
        this.setAlturaVegetacaoCm(novaAlturaCm);
    }

    public void simularCrescimentoPorDias(int dias) {
        if (dias <= 0) {
            System.out.println("Erro: a quantidade de dias deve ser maior que zero.");
            return;
        }

        // A taxa de crescimento depende do tipo de ambiente do trecho.
        double crescimento = this.calcularCrescimentoDiarioCm() * dias;
        this.setAlturaVegetacaoCm(this.alturaVegetacaoCm + crescimento);
    }

    public double calcularCrescimentoDiarioCm() {
        // Trechos umidos acumulam vegetacao mais rapido.
        if (AMBIENTE_UMIDO.equalsIgnoreCase(this.tipoAmbiente)) {
            return 3.0;
        }

        return 1.2;
    }

    public boolean isRiscoAlto() {
        return RISCO_ALTO.equalsIgnoreCase(this.riscoOperacional);
    }

    private void setKmInicial(double kmInicial) {
        if (kmInicial >= 0) {
            this.kmInicial = kmInicial;
        }
    }

    private void setKmFinal(double kmFinal) {
        if (kmFinal > this.kmInicial) {
            this.kmFinal = kmFinal;
        }
    }

    private void setAlturaVegetacaoCm(double alturaVegetacaoCm) {
        if (alturaVegetacaoCm >= 0) {
            this.alturaVegetacaoCm = alturaVegetacaoCm;
        }
    }

    private void setTipoAmbiente(String tipoAmbiente) {
        if (tipoAmbiente == null || tipoAmbiente.trim().isEmpty()) {
            this.tipoAmbiente = AMBIENTE_SECO;
            return;
        }

        this.tipoAmbiente = tipoAmbiente;
    }

    private void setRiscoOperacional(String riscoOperacional) {
        if (riscoOperacional == null || riscoOperacional.trim().isEmpty()) {
            this.riscoOperacional = RISCO_BAIXO;
            return;
        }

        this.riscoOperacional = riscoOperacional;
    }
}
