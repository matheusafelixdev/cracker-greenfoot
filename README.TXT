CRACKER - FUGA DO SERVIDOR

COMO ABRIR
Abra project.greenfoot no Greenfoot, compile e clique em Executar (Run).
O mundo inicial é Menu. Se aparecer outro mundo, clique com o botão direito
na classe Menu e escolha new Menu().

CONTROLES
WASD ou setas: mover.
Espaço: atirar na última direção de movimento.
Nas fases 2 e 3, clique na arena para atirar na direção do cursor.
E: ativar Overclock.
P: pausar ou continuar.
Esc: voltar ao menu e abandonar a partida atual.
Enter ou clique: iniciar, avançar ou jogar novamente nas telas de resultado.
Solte Enter entre as telas para evitar avançar sem querer.

OBJETIVO
Byte foi detectado no servidor e precisa escapar.
Fase 1: derrotar um único Bug, como no projeto original.
O Bug fica maior (110 por 110 pixels) e precisa receber 10 tiros para morrer.
Ele anda devagar e atira na direção para a qual está virado.
Não há novas ondas ou fragmentos nessa fase.
Fase 2: derrotar 12 inimigos entre Capangas e Vírus, com até 2 ao mesmo tempo.
Os Capangas também contam para completar a fase.
As paredes de código bloqueiam personagens e tiros, com espaço para desviar.
Fase 3: derrotar Trojan, que invoca 2 capangas.
Depois dos capangas, podem aparecer apenas 2 reforços no total: Bug e Vírus,
um por vez. Eles não são necessários para vencer.
Trojan mantém 20 de vida. Na metade da vida, alterna rajadas de três tiros
e tiros simples. Após disparar, para brevemente para permitir um contra-ataque.

VIDAS E PONTUAÇÃO
A partida começa com 3 vidas. Ao entrar nas fases 2 e 3, recupera 1 vida,
sem ultrapassar o máximo de 3 vidas.
Após sofrer dano, Byte pisca e fica protegido por 60 ciclos na fase 1
ou por 100 ciclos nas fases seguintes.
Bug: 10 pontos; Vírus: 20; capanga: 10; fragmento: 50; Trojan: 500.
Cada eliminação aumenta o combo antes de calcular seus pontos.
Combo de 0 a 2: x1; de 3 a 5: x1,5; de 6 em diante: x2.
O multiplicador também vale para fragmentos.
Sofrer dano zera o combo, exceto durante o Overclock.
Concluir uma fase sem sofrer dano dá exatamente 200 pontos extras.
Pontos e combo passam para a fase seguinte.

COMBATE
Nas fases 2 e 3, Byte dispara mais rápido e os tiros inimigos são mais lentos.
Bugs têm 3 de vida nessas fases, Vírus têm 4 e capangas têm 2.
O chefe e os capangas se movimentam mais devagar.
A fase 2 aguarda 90 ciclos com vaga antes de repor um inimigo.
Na fase 3, há 240 ciclos de intervalo sem capangas ou reforços vivos.
O chefe usa 100 ciclos entre tiros normais e 110 quando chega à metade da vida.
Após cada disparo, ele para de andar por 25 ciclos.
A fase 1 conserva o ritmo de disparo original de Byte e do Bug.
Byte pode chegar mais perto das bordas: x entre 20 e 880, y entre 85 e 565.
Os tiros e a mira por clique funcionam em toda a área abaixo do painel.

OVERCLOCK
Dura 15 segundos, aumenta a velocidade de 4 para 6 e dá +50% de pontos.
Após o efeito, há 20 segundos de recarga para usar novamente.
O tempo do poder e da recarga fica congelado ao pausar.
O poder começa pronto em cada fase.
Os segundos usam o relógio; tiros e inimigos usam ciclos do Greenfoot.
Valores fracionados de pontos são arredondados para baixo.

ESTRUTURA
FaseBase reúne as regras usadas pelas três fases.
Byte controla o jogador. Bug, Virus, Capanga e Trojan são inimigos.
Pulso e TiroInimigo cuidam dos tiros.
FragmentoDeDados é o item de pontos. ParedeCodigo é o obstáculo.
Menu, TelaTransicao, TelaGameOver e TelaVitoria cuidam da navegação.
Não são necessários bibliotecas externas, downloads ou conexão à internet.

ARTES
As telas de transição e vitória têm artes próprias em images/transicao.png
e images/vitoria.png, geradas no tema de hacking e pixel art do projeto.
Pontuação, vidas e controles são desenhados pelo Java sobre essas imagens.

VISUAL DO MENU E DAS PAREDES
O menu usa images/menu.png com o mesmo conteúdo dos controles anteriores.
A classe Menu carrega a arte e mantém o início por Enter ou clique.
As paredes usam images/parede-codigo.png, ajustada ao tamanho de cada parede.
Os tamanhos, as colisões e as regras do jogo foram preservados.

TELA DURANTE AS FASES
A tela mostra apenas pontos, vidas e progresso da fase ou vida do chefe.
Os comandos podem ser consultados no menu inicial.
O combo continua funcionando, sem texto permanente durante a partida.
A pequena barra abaixo das vidas indica o Overclock:
verde quando pronto, ciano durante o efeito e cinza azulado enquanto recarrega.

FRAGMENTO DE DADOS
O item usa images/fragmento-dados.png, um chip de dados com fundo transparente.
Ele continua valendo 50 pontos base, com os multiplicadores normais.

REVISAO DA ESCRITA DO CODIGO
A escrita foi simplificada com contas por etapas, if/else e construtores diretos.
Foram mantidos os valores de combate, controles, imagens, telas e regras.
As classes continuam usando apenas Java e a biblioteca do Greenfoot.
Nao foram acrescentados comentarios aos arquivos Java.
