package br.com.fiaphexa.dominio.enuns;

public enum StatusPagamentoEnum {
    APPROVED("Aprovado"),
    REJECTED("Recusado");

    private String statusPagamento;

    StatusPagamentoEnum(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public String getPaymentMethod() {
        return statusPagamento;
    }
}
