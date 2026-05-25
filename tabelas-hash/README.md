# Tabelas Hash

Repositório de um projeto de Estruturas de Dados em Java focado na implementação de tabelas hash e algoritmos relacionados.

Este projeto organiza os exercícios em blocos temáticos e inclui também um desafio extra com simulações de comportamento interno do `HashMap`.

## Estrutura do projeto

- `desafio1/`
  - Implementa uma simulação de colisão no `HashMap` usando chaves com `hashCode()` fixo.
  - Contém `ChaveColisora`, `SimuladorTreeify` e `SimuladorTreeifyTest` para verificar a transição de lista para árvore.

- `bloco1/`
  - Exercícios sobre contrato `hashCode()`/`equals()` e tabela hash com encadeamento separado.
  - Inclui `TabelaHashEncadeada` e testes de inserção, atualização e colisão.

- `bloco2/`
  - Exercícios sobre endereçamento aberto, sondagem linear, rehashing e uma tabela hash aberta genérica.
  - Inclui `TabelaHashAberta` com rehash automático e testes de fator de carga.

- `bloco3/`
  - Problemas contextualizados em sistemas web: cache de sessão, índice invertido e rate limiting.
  - Inclui classes de domínio, mecanismos de busca e controle de requisição por IP.

## Como executar

No diretório `tabelas-hash`, execute:

```bash
mvn test
```

Isso rodará todos os testes dos blocos e do desafio.
