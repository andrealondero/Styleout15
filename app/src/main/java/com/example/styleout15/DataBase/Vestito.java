package com.example.styleout15.DataBase;

public class Vestito {

    private Integer id;

    private Integer selected;

    private Integer posFatto;

    private Integer giorni;

    public Integer getGiorni() {
        return giorni;
    }

    public void setGiorni(Integer giorni) {
        this.giorni = giorni;
    }

    public Integer getPosFatto() {
        return posFatto;
    }

    public void setPosFatto(Integer posFatto) {
        this.posFatto = posFatto;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String colore;
    private String colorCode;

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    private int disponibile;
    private String nome;
    private String tessuto;
    private String tipoVestito;
    private String style;
    private int pic_tag;

    public int isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(int disponibile) {
        this.disponibile = disponibile;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTessuto() {
        return tessuto;
    }

    public void setTessuto(String tessuto) {
        this.tessuto = tessuto;
    }

    public String getTipoVestito() {
        return tipoVestito;
    }

    public void setTipoVestito(String tipoVestito) {
        this.tipoVestito = tipoVestito;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getPic_tag() {
        return pic_tag;
    }

    public void setPic_tag(int pic_tag) {
        this.pic_tag = pic_tag;
    }
}
