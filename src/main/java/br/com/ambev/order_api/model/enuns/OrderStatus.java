package br.com.ambev.order_api.model.enuns;

public enum OrderStatus {

    PENDING("Pedido pendente"),
    PROCESSING("Processando pedido"),
    COMPLETED("Pedido concluído"),
    CANCELED("Pedido cancelado"),
    FAILED("Processamento falhou"),
    SHIPPED("Pedido enviado"),
    DELIVERED("Pedido entregue"),
    RETURNED("Pedido devolvido");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
