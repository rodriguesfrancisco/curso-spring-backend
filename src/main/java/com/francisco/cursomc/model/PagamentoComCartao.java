package com.francisco.cursomc.model;

import com.francisco.cursomc.model.enums.EstadoPagamento;

public class PagamentoComCartao extends Pagamento{

    private Integer numeroDeParcelas;

    public PagamentoComCartao(){

    }

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
