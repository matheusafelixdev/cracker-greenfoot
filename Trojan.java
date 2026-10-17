import greenfoot.*;

public class Trojan extends Actor
{
    private int vida = 20;
    private int vidaMaxima = 20;
    private int velocidade = 1;
    private int cooldownTiro = 100;
    private int tempoDesvio = 0;
    private int tempoParado = 0;
    private boolean invocouCapangas = false;
    private boolean proximaRajada = true;

    public Trojan()
    {
        setImage("trojan.png");
        getImage().scale(120, 120);
    }

    protected void addedToWorld(World mundo)
    {
        FaseBase fase = (FaseBase) mundo;
        fase.atualizarChefe(vida, vidaMaxima);

        if (invocouCapangas == false) {
            invocouCapangas = true;
            int esquerda = getX() - 110;
            int direita = getX() + 110;
            int altura = getY();

            if (esquerda < 50) {
                esquerda = 50;
            }
            if (direita > 850) {
                direita = 850;
            }
            if (altura < 120) {
                altura = 120;
            }
            if (altura > 550) {
                altura = 550;
            }

            mundo.addObject(new Capanga(), esquerda, altura);
            mundo.addObject(new Capanga(), direita, altura);
        }
    }

    public void act()
    {
        FaseBase mundo = (FaseBase) getWorld();
        if (mundo.estaPausada() || mundo.terminou()) {
            return;
        }

        if (tempoParado > 0) {
            tempoParado = tempoParado - 1;
        } else {
            mover();
        }
        verificarTiro();
    }

    private void mover()
    {
        FaseBase mundo = (FaseBase) getWorld();
        Byte jogador = mundo.getJogador();
        if (jogador == null || jogador.getWorld() == null) {
            return;
        }

        if (tempoDesvio > 0) {
            tempoDesvio = tempoDesvio - 1;
        } else {
            turnTowards(jogador.getX(), jogador.getY());
        }

        int xAnterior = getX();
        int yAnterior = getY();
        move(velocidade);

        if (getX() < 50 || getX() > 850 || getY() < 120 || getY() > 550
            || isTouching(ParedeCodigo.class)) {
            setLocation(xAnterior, yAnterior);
            turn(90 + Greenfoot.getRandomNumber(91));
            tempoDesvio = 35;
        }
    }

    private void verificarTiro()
    {
        cooldownTiro = cooldownTiro - 1;
        if (cooldownTiro > 0) {
            return;
        }

        disparar(0);
        if (vida <= vidaMaxima / 2) {
            if (proximaRajada) {
                disparar(-18);
                disparar(18);
            }
            if (proximaRajada == true) {
                proximaRajada = false;
            } else {
                proximaRajada = true;
            }
            cooldownTiro = 110;
        } else {
            cooldownTiro = 100;
        }
        tempoParado = 25;
    }

    private void disparar(int angulo)
    {
        FaseBase mundo = (FaseBase) getWorld();
        Byte jogador = mundo.getJogador();
        if (jogador != null && jogador.getWorld() != null) {
            TiroInimigo tiro = new TiroInimigo();
            mundo.addObject(tiro, getX(), getY());
            tiro.turnTowards(jogador.getX(), jogador.getY());
            tiro.turn(angulo);
        }
    }

    public void receberDano()
    {
        if (getWorld() == null) {
            return;
        }

        vida = vida - 1;
        FaseBase mundo = (FaseBase) getWorld();
        mundo.atualizarChefe(vida, vidaMaxima);

        if (vida <= 0) {
            mundo.removeObject(this);
            mundo.trojanDerrotado();
        }
    }

    public int getVida()
    {
        return vida;
    }
}
