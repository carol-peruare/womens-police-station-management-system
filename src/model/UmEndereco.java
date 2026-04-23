package model;

import java.util.ArrayList;
import java.util.List;

public class UmEndereco {
    private String rua;
    private String cep;
    private String bairro;
    private String numero;
    private String complemento;

    public UmEndereco(String rua, String cep, String bairro, String numero, String complemento){
        this.rua = rua;
        this.cep = cep;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }
}
