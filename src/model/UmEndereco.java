package model;

import java.util.ArrayList;
import java.util.List;

public class UmEndereco{
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

    public String getRua() {
        return rua;
    }

    public String getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void mostrarEndereco(){
        System.out.println("Rua: " + rua);
        System.out.println("CEP: " + cep);
        System.out.println("Bairro: " + bairro);
        System.out.println("Número: " + numero);
        System.out.println("Complemento: " + complemento);
        System.out.println("-------------------");
    }

    public void listarEnderecos(){
        if(enderecos.isEmpty()) {
            System.out.println("Nenhum endereço cadastrado.");

    } else {
            for (UmEndereco endereco : enderecos){
                endereco.mostrarEndereco();
            }
        }
    }
}
