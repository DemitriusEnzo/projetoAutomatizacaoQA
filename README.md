# projetoAutomatizacaoQA

Projeto Java criado com o intuito de automatizar tarefas manuais repetitivas de um projeto empresarial. Desenvolvido para automatizar etapas de preparação de artefatos de QA a partir de uma User Story. Autorizado para uso corporativo.

## Sobre o projeto

- Objetivo  
  Automatizar tarefas manuais repetitivas relacionadas à preparação de artefatos de QA (roteiros de teste, planilhas e documentos de evidências) a partir de uma User Story fornecida.

- Contexto técnico  
  Aplicação Java organizada por Maven, utilizando Apache POI para manipulação de arquivos Excel e Word, e `java.net.http.HttpClient` para realização de requisições HTTP a um serviço externo de geração de texto.

- Funcionamento em alto nível  
  O fluxo principal solicita ao usuário um identificador de User Story e o texto da User Story, cria uma estrutura de diretórios associada à US, obtém texto de roteiro de testes e texto de evidências via serviço de IA, grava o roteiro em arquivo texto, gera uma planilha Excel a partir do roteiro e gera um documento Word de evidências.

## Tecnologias utilizadas

- Linguagem: Java (propriedade `java.version` = 21 no `pom.xml`)
- Build: Maven (`pom.xml`)
- Maven Wrapper: `mvnw`, `mvnw.cmd`, diretório `.mvn/`
- Bibliotecas (declaradas no `pom.xml`):
  - Spring Boot (`spring-boot-starter`)
  - Apache POI (`poi-ooxml` versão 5.2.5)
  - `spring-boot-starter-test` (escopo `test`)
- Cliente HTTP: `java.net.http.HttpClient`

## Funcionalidades

- Leitura interativa de identificador e texto da User Story (classe `br.automatiza.projetoAutomatizacaoQA.Main`).
- Criação de estrutura de diretórios para a User Story (método `FileOrganizer.criarEstrutura`).
- Geração de roteiro de testes em formato de texto via `IAService`.
- Escrita do roteiro em arquivo texto na pasta `roteiro`.
- Geração de planilha Excel a partir do roteiro (`ExcelGenerator.gerarExcel`), com cabeçalhos e estilização de células.
- Geração de documento Word de evidências a partir de texto (`WordGenerator.gerarEvidencias`), com formatação para títulos.
- Inicialização via Spring Boot com chamada a `Main.main` (`ProjetoAutomatizacaoQaApplication`).

## Estrutura do projeto (principais arquivos)

- .gitattributes  
- .gitignore  
- mvnw, mvnw.cmd, .mvn/  
- pom.xml  
- src/main/java/br/automatiza/projetoAutomatizacaoQA/  
  - Main.java  
  - ProjetoAutomatizacaoQaApplication.java  
  - excel/ExcelGenerator.java  
  - word/WordGenerator.java  
  - ia/IAService.java  
  - utils/FileOrganizer.java  
- src/test/ (diretório presente)

## Como executar

Pré-requisitos
- JDK compatível com `java.version` = 21 (conforme `pom.xml`).
- Git (opcional, para clonar o repositório).

Passos

1. Clonar o repositório
   git clone https://github.com/DemitriusEnzo/projetoAutomatizacaoQA.git
   cd projetoAutomatizacaoQA

2. Tornar o wrapper executável (em sistemas Unix-like)
   chmod +x mvnw

3. Compilar / empacotar com o Maven Wrapper
   - Unix / macOS:
     ./mvnw clean package
   - Windows:
     mvnw.cmd clean package

4. Executar a aplicação
   - Via Maven:
     ./mvnw spring-boot:run
   - Ou executar o JAR gerado após o `package`:
     java -jar target/projetoAutomatizacaoQA-0.0.1-SNAPSHOT.jar

Interação durante a execução
- A aplicação solicitará no console:
  - "Digite o valor da US:" — inserir identificador da User Story (string)
  - "Cole a US:" — colar o texto da User Story

Mensagens exibidas no fluxo
- "Gerando roteiro de testes..."
- "Gerando documento de evidências..."
- "Concluído."
- "Pasta: " seguido do valor retornado por `FileOrganizer.criarEstrutura`

## Exemplos de uso (interativo)

1. Inicie a aplicação:
   ./mvnw spring-boot:run

2. No console, forneça entradas quando solicitado:
   Digite o valor da US:
   3458
   Cole a US:
   [colar aqui o texto completo da User Story]

3. Exemplos de arquivos gerados (conforme concatenações no código):
   - <baseDir>/roteiro/ia_output.txt
   - <baseDir>/roteiro/Roteiro_<US>.xlsx
   - <baseDir>/evidencias/Evidencias_<US>.docx

---
