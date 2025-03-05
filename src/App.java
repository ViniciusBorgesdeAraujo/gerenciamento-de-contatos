import java.util.ArrayList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class App {
    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {       

         try {
            Connection conexao = Conexao.getConexao();

            PessoasDAO.conexao = conexao;
            TelefoneDAO.conexao = conexao;
       
         } catch (Exception e) {
            e.printStackTrace();
            return;
         }
        
        //guarda a opcao selecionada pelo usuario no menu
        int opcao;

        do {
            limparTela();
            //obtem a opcao desejada pelo usuario
            opcao = obterEscolhaMenu();

            //executa a funcionalidade conforme escolhido pelo usuario
            processarEscolhaMenu(opcao);
        } while (opcao != 7);
    }

    private static void processarEscolhaMenu(int opcao){
        switch (opcao) {
            case 1:
                incluirContato();
                pausa();
                break;
            case 2:
                alterarContato();
                break;
            case 3:
                consultarContatos();
                break;
            case 4:
                consultarContatoPorID();
                pausa();
                break;
            case 5:
                consultarContatoPorNomes();
                pausa();
                break;          
            case 6:
                excluirContato();
                pausa();
                break;             
            case 7:
                System.out.println("Saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
    
    private static void consultarContatoPorID(){
        int id;
        Pessoa pessoa = null;

        System.out.println("Informe o ID do usuário ser exebido: ");
        id = teclado.nextInt();
        teclado.nextLine(); //limpeza do buffer

        try {
            pessoa = PessoasDAO.consultaPorID(id);

            if (pessoa != null)
            System.out.println(pessoa);
            else
            System.out.println("Não existem contato com este ID.");
        } catch (SQLException e) {
            System.out.println("Erro ao consulta os dados no banco de dados! " );
            System.out.println(e.getMessage());
        }

    }
    private static void consultarContatoPorNomes(){
       String nome;
       ArrayList<Pessoa> listaContatos = new ArrayList<Pessoa>(); 

        System.out.println("Informe o nome ou parte do nome do usuário a ser exibido: ");
        nome = teclado.nextLine();

        try {
            listaContatos = PessoasDAO.consultaPorNome(nome);

            if (listaContatos.isEmpty())
            System.out.println("Não existemc ontato com este nome.");
               
            else
                for (Pessoa pessoa : listaContatos) {
                     System.out.println(pessoa);
                }
            
        } catch (SQLException e) {
                System.out.println("Erro ao consulta os dados no banco de dados! " );
                e.printStackTrace();
        }
    }

    private static int obterEscolhaMenu(){
        int opcao;

        System.out.println("\n--- Menu de Gerenciamento de Contatos ---\n");

        System.out.println("1. Incluir Contato");
        System.out.println("2. Alterar Contato");
        System.out.println("3. Consultar Contatos");
        System.out.println("4. Consultar Contatos por ID");
        System.out.println("5. Consultar Contatos por Nomes");
        System.out.println("6. Excluir Contato");
        System.out.println("7. Sair");

        System.out.print("\nEscolha uma opção: ");
        opcao = teclado.nextInt();
        teclado.nextLine(); // Limpeza buffer

        return opcao;
    }

    private static void incluirContato() {
        System.out.print("Digite o nome: ");
        String nome = teclado.nextLine();

        System.out.print("Digite o email: ");
        String email = teclado.nextLine();

        ArrayList<Telefone> telefones = obterTelefones();
        Pessoa novaPessoa = new Pessoa(nome, telefones, email);

        try {
            PessoasDAO.inserir(novaPessoa);
            System.out.println("Contato incluído com sucesso!");
        } catch (SQLException e) {
          System.out.println("Erro ao tentar inserir os dados no banco de dados. Tente novamente. ");
          e.printStackTrace();
        }
        
    }

    private static ArrayList<Telefone> obterTelefones(){
        ArrayList<Telefone> telefones = new ArrayList<>();
        String numero, ddd, tipo;
        char resposta;

        do {
            System.out.print("Digite o DDD: ");
            ddd = teclado.nextLine();

            System.out.print("Digite o telefone: ");
            numero = teclado.nextLine();

            System.out.print("Digite o tipo [CELULAR, FIXO]: ");
            tipo = teclado.nextLine().toUpperCase();

            telefones.add(new Telefone(ddd, numero, Telefone.Tipo.valueOf(tipo)));

            System.out.print("Deseja cadastrar outro telefone [S/N]: ");
            resposta = teclado.nextLine().toLowerCase().charAt(0);
        } while (resposta == 's');

        return telefones;
    }

    private static void alterarContato() {
        System.out.print("Digite o ID do contato a ser alterado: ");
        int id = teclado.nextInt();
        teclado.nextLine(); // limpeza buffer

        limparTela();

        Pessoa pessoa = null;
        try {
            pessoa = PessoasDAO.consultaPorID(id);

            if (pessoa != null)
                System.out.println(pessoa);
            else {
                 System.out.println("Não existemcontato com este ID.");
                 return;
            }
           
        } catch (SQLException e) {
            System.out.println("Erro ao consulta os dados no banco de dados! " );
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("\nDeseja realmente alterar este contato " + pessoa.getNome() + "[s/n]");
        char resposta = teclado.nextLine().toLowerCase().charAt(0);

        if(resposta == 'n'){
            System.out.println("\nAutalização cancelada.");
            return;            
        }

        System.out.println("Nome atual: " + pessoa.getNome());
        System.out.print("Digite o novo nome (ou deixe em branco para manter): ");
        String nome = teclado.nextLine();
        //metodo isBlank retorna true se a string estiver vazia
        //é equivalente a fazer nome.equals("");
        if (!nome.isBlank())
            pessoa.setNome(nome);

        System.out.print("Digite o novo telefone (ou deixe em branco para manter): ");
        System.out.println("Digite o novo telefone: " + pessoa.getNome());
        String telefone = teclado.nextLine();
        if (!telefone.isBlank())
            pessoa.setTelefone(telefone);

        System.out.println("Email atual: " + pessoa.getNome());
        System.out.print("Digite o novo email (ou deixe em branco para manter): ");
        String email = teclado.nextLine();
        if (!email.isBlank())
            pessoa.setEmail(email);


        try {
            int resultado = PessoasDAO.atualizarContato(pessoa);

            String mensagem = (resultado > 0) ? "Contato alterado com sucesso!" : "Contato alterado com sucesso!";
            System.out.println(mensagem);
          
        } catch (SQLException e) { 
            System.out.println("Erro ao tentar atualizar os dados no banco de dados. Tente novamente. ");
            System.out.println(e.getMessage());
        }
       
        pausa();
    }       
    
    private static void consultarContatos() {
         ArrayList<Pessoa> listaContatos = new ArrayList<Pessoa>();

         try {
            listaContatos = PessoasDAO.consultarTodosContatos();

        //metodo isEmpty verifica se a lista esta vazia
            if (listaContatos.isEmpty()) {
                System.out.println("Nenhum contato cadastrado.");
            } else {
                System.out.println("\n--- Lista de Contatos ---");
                for (Pessoa pessoa : listaContatos) {
                    System.out.println(pessoa);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao consulta os dados no banco de dados.  Tente novamente.");
            System.out.println(e.getMessage());
        }
     
        pausa();
    }

    private static void excluirContato() {
        //obtem o id do contato;
        System.out.print("Digite o ID do contato a ser excluído: ");
        int id = teclado.nextInt();
        teclado.nextLine(); // Consumir quebra de linha

        Pessoa pessoa;
        try {
            pessoa = PessoasDAO.consultaPorID(id);

            if (pessoa != null){
                System.out.println("Contato encontrado");
                System.out.println(pessoa);
            }else {
                System.out.println("Não existe contato com este ID.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consulta os dados no banco de dados!");
            System.out.println(e.getMessage());
                return;
           
        }

        System.out.println("\nDeseja realmente excluir este contato " + pessoa.getNome() + "[s/n]");
        char resposta = teclado.nextLine().toLowerCase().charAt(0);

        if(resposta == 'n'){
            System.out.println("\nExclusão cancelada.");
            return;            
        }

        try {
            int resultado = PessoasDAO.excluirPorID(id);  
            
            if (resultado == 0) 
                System.out.println("Nenhum registro excluído pois não existe este ID no banco de dados.");
            else
            System.out.println("Registro exluído com sucesso.");
            
        } catch (Exception e) {
            System.out.println("Erro ao excluir os dados no banco de dados.  Tente novamente.");
            System.out.println(e.getMessage());
        }       
  
    }

    private static void limparTela(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();

        } catch (IOException | InterruptedException ex) {}
    }

    private static void pausa(){
        System.out.println("\nTecle ENTER para continuar.");
        teclado.nextLine();
    }

}