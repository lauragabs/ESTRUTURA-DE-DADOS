package br.edu.iftm.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transacao {

    private Long id;
    private Long contaId;
    private BigDecimal valor;
    private LocalDate data;

    public Transacao() {
    }

    public Transacao(Long id, Long contaId, BigDecimal valor, LocalDate data) {
        this.id = id;
        this.contaId = contaId;
        this.valor = valor;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContaId() {
        return contaId;
    }

    public void setContaId(Long contaId) {
        this.contaId = contaId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
