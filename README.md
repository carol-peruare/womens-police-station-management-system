# 🚓 Fork's Women Police Department - Sistema de Delegacia da Mulher

Este é um projeto acadêmico desenvolvido para a disciplina de Programação Orientada a Objetos (POO). O sistema simula o ambiente digital de uma delegacia de polícia da mulher, com interface gráfica interativa e persistência em banco de dados.

O software é dividido em duas frentes de acesso: a visão do **Cidadão** e a visão do **Policial**, cada um com suas próprias permissões e funcionalidades.

## 👩‍💻 Autoras

* **Carolina Peruare Ferreira** - RGA: 202412901003 | [GitHub](https://github.com/carol-peruare)

* **Maressa Luiza dos Reis Machado** - RGA: 202421901046 | [GitHub](https://github.com/Malu006)
---

## ⚙️ Funcionalidades do Sistema

### 👤 Painel do Cidadão

* **Cadastro e Login:** Autenticação segura validando CPF, e-mail e formato de datas com tratamento de erros.
* **Registrar Boletim de Ocorrência (B.O.):** Permite relatar um fato, inserir a data, possíveis evidências e classificar o tipo de crime (Feminicídio, Agressão, Violência Doméstica, Sequestro).
* **Verificar Status do B.O.:** Acompanhamento em tempo real do andamento das ocorrências registradas (Ex: Registrado, Em Análise, Em Andamento, Concluído).
* **Meus Antecedentes:** Consulta rápida para verificar se há alguma passagem criminal vinculada ao próprio CPF.

### 👮‍♀️ Painel do Policial

Por motivos de segurança (licença poética do projeto), policiais não podem se cadastrar sozinhos, seus registros são fornecidos pelo órgão responsável.

* **Login Oficial:** Acesso via Matrícula de Policial e senha.
* **Bater Ponto:** Registro de entrada e saída no sistema.
* **Consultar Boletins:** Visualização de todos os boletins de ocorrência abertos ou em andamento.
* **Atualizar Status de B.O.:** Mudança do status da ocorrência (Em Análise, Em Andamento, Concluído) para manter o cidadão informado.
* **Verificar Ficha de Antecedentes:** Consulta da ficha criminal de qualquer cidadão através do CPF, retornando o histórico e status detalhados (Nada Consta ou Possui Passagens).
* **Meu Histórico:** Relatório completo das ocorrências assumidas e log de operações do policial no sistema.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 
* **Interface Gráfica (GUI):** Java Swing (AWT/Swing)
* **Padrões de Projeto:** DAO (Data Access Object) e MVC (Model-View-Controller implícito).
* **Banco de Dados:** Integração via JDBC (Tabelas para Usuários, Policiais, Boletins, Status, Classificações, Histórico e Antecedentes).

---

## 🔑 Credenciais para Teste

Credenciais pré-cadastradas no banco de dados:

### Acesso Policial (Matrícula e Senha)

| Nome | Matrícula | Senha |
| --- | --- | --- |
| Carlos Augusto Silva | `PM202400001` | `senhaPolicial1` |
| Mariana Costa Ramos | `PM202400002` | `senhaPolicial2` |
| Roberto Souza Almeida | `PM202400003` | `senhaPolicial3` |

### Acesso Cidadão (E-mail e Senha)

| Nome | E-mail | Senha |
| --- | --- | --- |
| Fernanda | `fernanda@gmail.com` | `1234` |
| Maria | `maria@gmail.com` | `1234` |

**CPFs de Cidadãos para testes de busca de antecedentes (Acesso Policial):**

* `06618954322`
* `12345678900`
* `12587452102`

---
