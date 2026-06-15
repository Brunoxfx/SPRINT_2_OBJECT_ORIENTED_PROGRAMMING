# Challenge Sprint 2 - Motor de Regras de Rocada

## Equipe

| Nome | RM |
|------|----|
| Bruno Anselmo Da Silva | RM 566521 |
| Fernando de Almeida Godoi | RM 564820 |
| Gabriel Ber Soares | RM 563520 |
| Guilherme de Freitas Salgado | RM 562494 |
| Vinicius Ribeiro Dias | RM 566468 |

## Objetivo

Este projeto cria um sistema simples em Java puro para monitorar trechos de rodovia e gerar um Relatorio de Prioridade automatico. O relatorio indica quais KMs precisam de rocada mecanizada, rocada manual, pulverizacao ou nenhuma intervencao.

O foco da Sprint 2 e praticar:

- classes e objetos;
- encapsulamento;
- construtores;
- heranca;
- polimorfismo;
- classes abstratas;
- interfaces.

## Regras do motor

O `MotorPrioridade` percorre um array de `TrechoRodovia` e aplica as regras abaixo:

- vegetacao com 80 cm ou mais e risco alto: `RocadaManual`;
- vegetacao com 80 cm ou mais e risco baixo: `RocadaMecanizada`;
- vegetacao entre 40 cm e 79.9 cm: `Pulverizacao`;
- vegetacao abaixo de 40 cm: sem intervencao.

O crescimento da vegetacao tambem muda conforme o ambiente:

- trecho seco cresce 1.2 cm por dia;
- trecho umido cresce 3.0 cm por dia.

## Classes principais

- `TrechoRodovia`: representa um trecho com KM inicial, KM final, altura da vegetacao, tipo de ambiente e risco operacional.
- `TrechoMonitoradoIoT`: especializacao de `TrechoRodovia` que implementa monitoramento por sensor.
- `MonitoravelViaIoT`: interface com o contrato `transmitirDadosSensor()`.
- `IntervencaoOperacional`: classe abstrata base para servicos operacionais.
- `RocadaMecanizada`, `RocadaManual` e `Pulverizacao`: intervencoes concretas.
- `MotorPrioridade`: classe que aplica as regras de decisao.
- `RelatorioPrioridade`: imprime o resultado no console.

## Estrutura do codigo

```text
src/br/com/motiva
|-- app
|   `-- Main.java
|-- dominio
|   |-- MonitoravelViaIoT.java
|   |-- TrechoMonitoradoIoT.java
|   `-- TrechoRodovia.java
|-- intervencao
|   |-- IntervencaoOperacional.java
|   |-- Pulverizacao.java
|   |-- RocadaManual.java
|   `-- RocadaMecanizada.java
|-- motor
|   |-- ItemRelatorioPrioridade.java
|   |-- MotorPrioridade.java
|   `-- RelatorioPrioridade.java
`-- testes
    `-- TesteMotorPrioridade.java
```

## Onde cada requisito foi implementado

- Classe abstrata: `IntervencaoOperacional`.
- Metodo abstrato: `executarServico(TrechoRodovia trecho)`.
- Intervencoes concretas: `RocadaMecanizada`, `RocadaManual` e `Pulverizacao`.
- Interface IoT: `MonitoravelViaIoT`.
- Trecho com tecnologia IoT: `TrechoMonitoradoIoT`.
- Crescimento diferente por ambiente: metodo `calcularCrescimentoDiarioCm()` em `TrechoRodovia`.
- Motor que percorre array de trechos: metodo `gerarRelatorio()` em `MotorPrioridade`.
- Relatorio automatico: `RelatorioPrioridade`.
- Testes manuais: `TesteMotorPrioridade`.

## Como compilar

O projeto foi compilado e testado com Java 21.

No PowerShell, dentro da pasta do projeto:

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
```

## Como executar

Executar o exemplo principal:

```powershell
java -cp out br.com.motiva.app.Main
```

Executar os testes manuais:

```powershell
java -cp out br.com.motiva.testes.TesteMotorPrioridade
```

## Perguntas de reflexao

### Por que nao faz sentido executar uma Intervencao Operacional generica?

Porque a equipe precisa saber qual servico sera feito. Uma intervencao generica nao informa se o trabalho sera rocada mecanizada, rocada manual ou pulverizacao. Por isso `IntervencaoOperacional` e abstrata: ela serve como molde, mas as filhas executam o servico real.

### Qual a diferenca entre classe abstrata e interface?

Classe abstrata e usada quando existe uma base comum entre classes da mesma familia. Interface e usada quando classes diferentes precisam seguir o mesmo contrato de comportamento. Neste projeto, as intervencoes compartilham a base `IntervencaoOperacional`, enquanto o monitoramento por IoT fica em uma interface porque pode ser implementado por qualquer classe que transmita dados de sensor.
