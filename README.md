<h1 align="center">ToolsChallenge</h1>

<p align="center">Desafio sicred</p>


<h4 align="center"> 
	🚧  Finalizado 🚧
</h4>

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[intellij](https://www.jetbrains.com/pt-br/idea/), [Git](https://git-scm.com/). 

### 🎲 Rodando o Back End (servidor)

```bash
# Clone este repositório
$ git clone <https://github.com/robertThallisson/ToolsChallenge>
abra o projeto no intellij e clique no "RUN"

# O servidor inciará na porta:8080 - acesse <http://localhost:8080>

### 🎲 Execução
 Todos os dados serão salvos apenas em memória
 swagger disponibilizado após servidor ser iniciado em  <http://localhost:8080/swagger-ui/index.html>

### End point /transacao/autorizar
  recebe uma transação caso valor seja diferente de 500.50 será negada

### End point /transacao/estornar
  recebe uma transação ja existente retornará com status pagamento "CANCELADA" 
