## 📞 Gerenciamento de Contatos - Java JDBC & MySQL  

Este é um projeto de gerenciamento de contatos desenvolvido em **Java**, utilizando **JDBC** para conexão com um banco de dados **MySQL**. O sistema permite adicionar, editar, excluir e buscar contatos, além de gerenciar múltiplos números de telefone por contato.  

## 🚀 Funcionalidades  

✔ **Adicionar Contatos**: Insere um novo contato no banco de dados.  
✔ **Listar Contatos**: Exibe todos os contatos cadastrados.  
✔ **Buscar por ID ou Nome**: Pesquisa um contato específico.  
✔ **Atualizar Contato**: Edita os dados de um contato existente.  
✔ **Excluir Contato**: Remove um contato do banco de dados.  
✔ **Gerenciar Telefones**: Permite adicionar mais de um telefone por contato.  

## 🛠 Tecnologias Utilizadas  

- **Java**  
- **JDBC**  
- **MySQL**  
- **DAO (Data Access Object)**  
- **Tratamento de Exceções**  

## 🎯 Como Executar o Projeto  

### 📌 Pré-requisitos  
- **Java JDK 8+** instalado.  
- **MySQL Server** configurado.  
- **Biblioteca JDBC** (caso necessário, baixar o **MySQL Connector/J**).  

### 🏗 Passos para Rodar  

1. **Clone o repositório:**  
   ```bash
   git clone https://github.com/ViniciusBorgesdeAraujo/gerenciamento-de-contatos
   cd gerenciamento-de-contatos
   ```  
2. **Configure o Banco de Dados:**  
   - Crie um banco de dados MySQL chamado `contatos_db`.  
   - Execute o seguinte script SQL:  
     ```sql
     CREATE DATABASE contatos_db;
     USE contatos_db;

     CREATE TABLE contato (
         id INT PRIMARY KEY AUTO_INCREMENT,
         nome VARCHAR(100) NOT NULL,
         email VARCHAR(100),
         telefone VARCHAR(20) NOT NULL
     );
     ```  
3. **Compile e execute o projeto:**  
   ```bash
   javac src/*.java  
   java src.Main
   ```  

## 📌 Estrutura do Projeto  

```
📂 gerenciamento-contatos-java
 ┣ 📂 src
 ┃ ┣ 📜 Main.java
 ┃ ┣ 📜 Contato.java
 ┃ ┣ 📜 ContatoDAO.java
 ┃ ┣ 📜 DatabaseConnection.java
 ┃ ┣ 📜 Menu.java
 ┣ 📜 README.md
 ┗ 📜 contatos.sql
```  

## 📜 Trecho do Código Principal  

```java
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/contatos_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão com o banco de dados", e);
        }
    }
}
```  

## 📌 Contribuição  

Sinta-se à vontade para abrir **Issues** ou enviar um **Pull Request** para melhorias!  

## 📄 Licença  

Este projeto está sob a licença **MIT**.  

