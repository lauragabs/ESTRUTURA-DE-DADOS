# Estudo Dirigido

Este repositório contém exercícios e projetos para a disciplina de Estrutura de Dados, organizados em módulos (`trilha1` a `trilha5`) e um `projeto integrador` em desenvolvimento.

## Estrutura do repositório

- `trilha1/` - Exercícios iniciais e fundamentos de Java com Maven.
- `trilha2/` - Exercícios intermediários com aplicações Java e recursos básicos de `resources`.
- `trilha3/` - Implementações de cache e benchmarking com JMH, usando Spring Boot para explorar performance.
- `trilha4/` - Trabalhos com mensageria RabbitMQ, controle de taxa (rate limiting) e Trie para autocomplete.
- `trilha5/` - Módulo adicional de estudos avançados (implementação em andamento ou testes de integração).
- `projeto integrador/` - Projeto integrador com arquitetura Spring Boot, que reúne padrões de cache, mensageria, segurança e APIs REST.

## Como executar

Cada módulo é um projeto Maven independente. 

### Executar testes

Para executar os testes unitários de um módulo Maven:

```bash
cd "projeto integrador"
mvn test
```

Substitua `projeto integrador` pelo módulo desejado.

## Observações do conteúdo

- `trilha3` aborda benchmarking e comparação de desempenho entre estruturas de dados.
- `trilha4` explora integração com RabbitMQ, controle de tráfego por IP e funções de autocomplete com Trie.
- `projeto integrador` é um esqueleto Spring Boot que inclui serviços de relatório, cache LRU, autocomplete e proteção por rate limiter.

## Dicas

- Use `mvn -q` para saída mais limpa.
- Se precisar executar o `projeto integrador`, certifique-se de ter o Java 17 instalado.
- Para experimentar o RabbitMQ localmente, instale ou execute um broker RabbitMQ antes de iniciar o módulo.