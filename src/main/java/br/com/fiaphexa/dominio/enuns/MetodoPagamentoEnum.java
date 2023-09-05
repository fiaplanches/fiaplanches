package br.com.fiaphexa.dominio.enuns;

public enum MetodoPagamentoEnum {

    CREDIT_CARD("Cartão de Crédito"),
    DEBIT_CARD("Cartão de Débito"),
    PIX("PIX"),
    QR_CODE("QR code");

    private String metodoPagamento;

    MetodoPagamentoEnum(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getPaymentMethod() {
        return metodoPagamento;
    }
}
