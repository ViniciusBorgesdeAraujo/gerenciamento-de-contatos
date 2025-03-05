import java.util.ArrayList;


public class Pessoa {
    private int id;
    private String nome;
    private String telefone;
    private ArrayList<Telefone> telefones = new ArrayList<>();
    private String email;

    //construtor da classe pessoa
    public Pessoa(String nome,ArrayList<Telefone> telefones, String email) {
        setNome(nome);
        setTelefones(telefones);
        setEmail(email);
    }

    public Pessoa(String nome, String telefone, String email) {
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
    }

    public Pessoa(int id, String nome, String telefone, String email) {
        setId(id);
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
    }

    public Pessoa(int id, String nome, ArrayList<Telefone> telefones, String email) {
        setId(id);
        setNome(nome);
        setTelefones(telefones);
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        //aqui implementariamos as validações necessárias
        //antes de inserir o nome  
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setTelefone(String telefone) {
        //aqui implementariamos as validações necessárias
        //antes de inserir o telefone 
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public ArrayList<Telefone> getTelefones() {
        return telefones;
    }    

    public void setTelefones(ArrayList<Telefone> telefones) {
        this.telefones = telefones;
    }

    public void setEmail(String email) {
        //aqui implementariamos as validações necessárias
        //antes de inserir o email 
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    //cria o método to string utilizado para converter o objeto para string
    //quando for necessário imprimir os dados do objeto na tela por outra parte do nosso
    //programa
    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Telefone: " + telefone + " - " + telefones + ", Email: " + email;
    }
}