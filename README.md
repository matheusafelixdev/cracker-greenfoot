# Cracker — Fuga do Servidor

Jogo de ação 2D desenvolvido em **Java com Greenfoot**, com temática de hacking e três fases. O jogador controla Byte, enfrenta inimigos e tenta escapar de um servidor.

O projeto foi desenvolvido como exercício de aprendizado para aplicar lógica de programação e orientação a objetos em um jogo completo, com movimento, colisões, combate e transição entre cenários.

![Menu do Cracker](images/menu.png)

## Tecnologias e ferramentas

| Tecnologia | Uso no projeto |
| --- | --- |
| **Java** | Classes, métodos, variáveis, regras de combate e controle das fases. |
| **Greenfoot** | Execução do jogo, cenários com `World`, objetos com `Actor`, teclado, mouse e colisões. |
| **JDK 21** | Compilação e validação local do código, usando o JDK da instalação do Greenfoot. |
| **Git** | Registro das versões do projeto e preparação das alterações para o repositório. |
| **Imagens PNG** | Personagens, cenários, itens e telas, carregados e ajustados com `GreenfootImage`. |

## Funcionalidades

- Movimento por teclado, com limites de arena e bloqueio por paredes.
- Disparos direcionais e mira pelo mouse nas fases 2 e 3.
- Vidas, dano, proteção temporária após um acerto e empurrão por contato.
- Pontuação, combo de eliminações e coleta de fragmentos de dados.
- Overclock por 15 segundos, com aumento de velocidade e de pontos e recarga de 20 segundos.
- Pausa, menu, transições, vitória e derrota.
- Geração de inimigos com limite de quantidade e intervalo de reposição.

| Fase | Objetivo |
| --- | --- |
| **1 — Camada de Acesso** | Derrotar um Bug de tamanho maior, que precisa receber 10 tiros. |
| **2 — Firewall Central** | Derrotar 12 inimigos entre Capangas e Vírus, com até dois ao mesmo tempo. |
| **3 — Núcleo do Sistema** | Derrotar Trojan, chefe com 20 pontos de vida, capangas e ataques que mudam na metade da vida. |

## Conceitos de programação aplicados

| Conceito | Aplicação no código |
| --- | --- |
| **Classes e objetos** | Jogador, inimigos, tiros e itens têm suas próprias classes e são criados conforme a fase. |
| **Herança** | `Fase1`, `Fase2` e `Fase3` herdam de `FaseBase`, que herda de `World`. Personagens e objetos herdam de `Actor`. |
| **Encapsulamento** | Informações como as vidas de Byte ficam em campos `private`, consultados ou alterados por métodos como `getVidas()` e `setVidas()`. |
| **Polimorfismo** | `criarInimigo(Actor inimigo)` recebe diferentes tipos de inimigos por meio do tipo comum `Actor`. |
| **Sobrecarga de construtores** | As fases podem receber pontuação, vidas e combo por construtores com parâmetros diferentes. |
| **Sobrescrita de métodos** | `act()` define o comportamento de cada classe; `proximaFase()` define para onde cada fase deve avançar. |
| **Condições e repetições** | `if` e `else` verificam teclas, colisões e vidas; `for` e `while` ajudam na criação e no posicionamento dos inimigos. |

### Movimento e colisões

O Greenfoot chama `act()` repetidamente durante a execução. Em `Byte`, esse método aciona as verificações de movimento, disparo e contato com inimigos.

O movimento do jogador parte da leitura do teclado e da alteração das coordenadas. Este trecho de `Byte.java` verifica o deslocamento para a direita:

```java
if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
    x = x + passo;
    direcaoTiro = 0;
}
```

Depois, `moverPara(x, y)` aplica a posição com `setLocation()`. O método verifica cada eixo separadamente e desfaz o deslocamento daquele eixo ao tocar uma `ParedeCodigo`, permitindo deslizar ao lado dos obstáculos.

Os inimigos e os projéteis também usam `move()` para avançar na direção atual. `turnTowards()` permite orientar um inimigo para o jogador. As colisões são verificadas com `isTouching()` e `getOneIntersectingObject()`.

### Combate e progressão

`verificarTiro()` cria um objeto `Pulso`. Ao atingir um inimigo, o projétil chama `receberDano()`. Quando a vida do inimigo chega a zero, ele é removido e a fase atualiza pontos e progresso.

`FaseBase` concentra as regras compartilhadas de pontuação, combo, pausa e Overclock. Pontos, vidas e combo são enviados aos construtores da próxima fase. A troca de cenário é feita com `Greenfoot.setWorld()`.

### Tempo e estado da partida

O intervalo entre tiros e a reposição de inimigos usam contadores de chamadas de `act()`. Esses ciclos não representam segundos fixos. Já o Overclock usa `System.currentTimeMillis()` para calcular o tempo decorrido em milissegundos.

Variáveis como `pausada` e `encerrada` controlam a execução das ações. Durante a pausa, o movimento, os ataques e a contagem restante do poder ficam suspensos.

## Organização do projeto

O projeto contém **17 classes Java**:

| Responsabilidade | Classes |
| --- | --- |
| Regras comuns e fases | `FaseBase`, `Fase1`, `Fase2`, `Fase3` |
| Jogador | `Byte` |
| Inimigos | `Bug`, `Virus`, `Capanga`, `Trojan` |
| Tiros, item e obstáculo | `Pulso`, `TiroInimigo`, `FragmentoDeDados`, `ParedeCodigo` |
| Menu e resultados | `Menu`, `TelaTransicao`, `TelaVitoria`, `TelaGameOver` |

O arquivo `project.greenfoot` abre o projeto. A pasta `images/` contém as artes, e o [README.TXT](README.TXT) detalha as regras. O `.gitignore` exclui arquivos compilados e arquivos temporários do editor.

## Como executar

1. Instale o [Greenfoot](https://www.greenfoot.org/download).
2. Baixe o repositório em **Code > Download ZIP** e extraia a pasta, ou clone com:

   ```bash
   git clone https://github.com/matheusafelixdev/cracker-greenfoot.git
   ```

3. Abra `project.greenfoot` no Greenfoot e compile as classes.
4. Clique em **Run / Executar** e pressione **Enter** ou clique no menu para começar.

Se o menu não aparecer, clique com o botão direito na classe `Menu` e selecione `new Menu()`.

## Controles

| Entrada | Ação |
| --- | --- |
| WASD ou setas | Mover Byte |
| Espaço | Atirar na última direção de movimento |
| Clique na arena | Mirar e atirar nas fases 2 e 3 |
| E | Ativar Overclock |
| P | Pausar ou continuar |
| Esc | Voltar ao menu |
| Enter ou clique | Iniciar ou avançar nas telas de resultado |

## Escopo e créditos

O jogo funciona localmente e mantém a pontuação durante a partida, sem salvar um ranking após o encerramento.

O desenvolvimento contou com apoio de inteligência artificial na revisão e evolução do código e na criação de parte das artes. O código utiliza Java e a biblioteca do Greenfoot.
