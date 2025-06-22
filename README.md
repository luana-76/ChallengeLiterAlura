# ChallengeLiterAlura

# 📚 Challenge LiterAlura

> ⚠ **Aviso importante**:  
> Este projeto **não utiliza PostgreSQL** como banco de dados devido a **limitações de memória** na máquina local durante o desenvolvimento.  
> Todas as informações são armazenadas **em memória (RAM)** e são perdidas ao finalizar a aplicação.

---

## 💡 Sobre o projeto

O **Challenge LiterAlura** é uma aplicação Java com Spring Boot que consome a API pública do [Project Gutendex](https://gutendex.com/) para buscar informações sobre livros e autores clássicos.

A interface é feita no **console (terminal)** e oferece as seguintes funcionalidades:

---

### 🎯 Funcionalidades disponíveis

1. **Buscar livro pelo título ou nome do autor**  
   → Realiza uma requisição para a API Gutendex com o termo pesquisado e exibe informações como título, autor, idioma e número de downloads.  
   → Os resultados são armazenados temporariamente em memória.

2. **Listar livros registrados**  
   → Mostra os livros que já foram pesquisados e salvos localmente durante a execução atual.

3. **Listar autores registrados**  
   → Exibe os autores registrados com seus nomes, datas de nascimento, falecimento (se disponível) e títulos das obras.

4. **Listar autores vivos em um determinado ano**  
   → Informa quais autores registrados estavam vivos em um ano escolhido pelo usuário com base em suas datas de nascimento e falecimento.

5. **Filtrar livros por idioma**  
   → Filtra os livros salvos em memória conforme o código do idioma (ex: `en`, `pt`, `fr`).

6. **Sair do programa**  
   → Finaliza a aplicação de forma segura.

---

### 🧰 Tecnologias utilizadas

- Java 21
- Spring Boot
- Biblioteca `java.net.http` (HTTP Client)
- Biblioteca `org.json` para parsing de JSON
- API pública [Gutendex](https://gutendex.com/)

---

### 🚀 Como rodar o projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/luana-76/ChallengeLiterAlura.git

2. Ao rodar a aplicação, você verá um menu no terminal com as seguintes opções:

```text
Escolha o número de sua opção:

1) Buscar livro pelo título
2) Listar livros registrados
3) Listar autores registrados
4) Listar autores vivos em um determinado ano
5) Listar livros em um determinado idioma
6) Sair
