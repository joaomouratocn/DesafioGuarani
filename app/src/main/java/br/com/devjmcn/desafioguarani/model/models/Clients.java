package br.com.devjmcn.desafioguarani.model.models;

//NÃO SABIA SE PODERIA USAR LOMBOK, FIZ NA MÃO MESMO.
public class Clients {
    String cod;
    String razaoSocial;
    String cnpjCpf;
    String endereco;
    String numero;
    String complemento;
    String cep;
    String bairro;
    String codMunicipio;
    String dataCadastro;
    String nomeFantasia;
    String telefone;
    String email;

    public Clients(String cod,
                   String razaoSocial,
                   String cnpjCpf,
                   String endereco,
                   String numero,
                   String complemento,
                   String cep,
                   String bairro,
                   String codMunicipio,
                   String dataCadastro,
                   String nomeFantasia,
                   String telefone,
                   String email) {
        this.cod = cod;
        this.razaoSocial = razaoSocial;
        this.cnpjCpf = cnpjCpf;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.bairro = bairro;
        this.codMunicipio = codMunicipio;
        this.dataCadastro = dataCadastro;
        this.nomeFantasia = nomeFantasia;
        this.telefone = telefone;
        this.email = email;
    }

    public String getCod() {
        return cod;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCep() {
        return cep;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCodMunicipio() {
        return codMunicipio;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }
}
