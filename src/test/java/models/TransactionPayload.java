package models;

public class TransactionPayload {
    private Integer conta_id;
    private String descricao;
    private String envolvido;
    private String tipo;
    private String data_transacao;
    private String data_pagamento;
    private double valor;
    private boolean status;

    public TransactionPayload() {}

    public TransactionPayload(Integer conta_id, String descricao, String envolvido, String tipo,
                              String data_transacao, String data_pagamento, double valor, boolean status) {
        this.conta_id = conta_id;
        this.descricao = descricao;
        this.envolvido = envolvido;
        this.tipo = tipo;
        this.data_transacao = data_transacao;
        this.data_pagamento = data_pagamento;
        this.valor = valor;
        this.status = status;
    }

    public Integer getConta_id() {
        return conta_id;
    }

    public void setConta_id(Integer conta_id) {
        this.conta_id = conta_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEnvolvido() {
        return envolvido;
    }

    public void setEnvolvido(String envolvido) {
        this.envolvido = envolvido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData_transacao() {
        return data_transacao;
    }

    public void setData_transacao(String data_transacao) {
        this.data_transacao = data_transacao;
    }

    public String getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(String data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
