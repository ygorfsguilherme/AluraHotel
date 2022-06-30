package model;

public class HospedeModelo {
    private Integer IdHospede;
    private String Nome;
    private String Sobrenome;
    private String DataNascimento;
    private String Nacionalidade;
    private String Telefone;
    private Integer IdReservas;

    public HospedeModelo() {
    }

    public HospedeModelo(String Nome, String Sobrenome, String DataNascimento, String Nacionalidade, String Telefone,
            Integer IdResevas) {
        super();
        this.Nome = Nome;
        this.Sobrenome = Sobrenome;
        this.DataNascimento = DataNascimento;
        this.Nacionalidade = Nacionalidade;
        this.Telefone = Telefone;
        this.IdReservas = IdResevas;
    }

    public HospedeModelo(Integer IdHospede, String Nome, String Sobrenome, String DataNascimento, String Nacionalidade,
            String Telefone,
            Integer IdResevas) {
        super();
        this.IdHospede = IdHospede;
        this.Nome = Nome;
        this.Sobrenome = Sobrenome;
        this.DataNascimento = DataNascimento;
        this.Nacionalidade = Nacionalidade;
        this.Telefone = Telefone;
        this.IdReservas = IdResevas;
    }

    public Integer getIdHospede() {
        return IdHospede;
    }

    public void setIdHospede(int IdHospede) {
        this.IdHospede = IdHospede;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String Sobrenome) {
        this.Sobrenome = Sobrenome;
    }

    public String getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        DataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return Nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        Nacionalidade = nacionalidade;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public void setIdReservas(Integer idReservas) {
        IdReservas = idReservas;
    }

    public int getIdReservas() {
        return IdReservas;
    }

}
