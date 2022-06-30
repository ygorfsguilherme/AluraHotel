package model;

public class ReservasModelo {

    private Integer id;
    private String dataCheckin;
    private String dataCheckout;
    private Integer valor;
    private String formaPagamento;

    public ReservasModelo() {

    }

    public ReservasModelo(String dCheckin, String dCheckout, String formaPagamento, int valor, int id) {
        super();
        this.dataCheckin = dCheckin;
        this.dataCheckout = dCheckout;
        this.formaPagamento = formaPagamento;
        this.valor = valor;
        this.id = id;
    }

    public ReservasModelo(String dCheckin, String dCheckout, String formaPagamento, int valor) {
        super();
        this.dataCheckin = dCheckin;
        this.dataCheckout = dCheckout;
        this.formaPagamento = formaPagamento;
        this.valor = valor;
    }

    public String getDataCheckin() {
        return dataCheckin;
    }

    public void setDataCheckin(String dataCheckin) {
        this.dataCheckin = dataCheckin;
    }

    public String getDataCheckout() {
        return dataCheckout;
    }

    public void setDataCheckout(String dataCheckout) {
        this.dataCheckout = dataCheckout;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
