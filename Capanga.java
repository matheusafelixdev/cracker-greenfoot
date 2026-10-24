import greenfoot.*;

public class Capanga extends Actor
{
    private int vida = 2;
    private int velocidade = 1;
    private int tempoDesvio = 0;

    public Capanga()
    {
        getImage().scale(50, 50);
    }

    public Capanga(int velocidade)
    {
        getImage().scale(50, 50);
        this.velocidade = velocidade;
    }

    public void act()
    {
        FaseBase mundo = (FaseBase) getWorld();
        if (mundo.estaPausada() || mundo.terminou()) {
            return;
        }

        Byte jogador = mundo.getJogador();
        if (jogador == null || jogador.getWorld() == null) {
            return;
        }

        int xAnterior = getX();
        int yAnterior = getY();
        int rotacaoAnterior = getRotation();
        if (tempoDesvio > 0) {
            tempoDesvio = tempoDesvio - 1;
        } else {
            turnTowards(jogador.getX(), jogador.getY());
        }

        move(velocidade);

        boolean bloqueado = getX() < 50 || getX() > 850 || getY() < 120 || getY() > 550
            || isTouching(ParedeCodigo.class);
        if (mundo instanceof Fase3) {
            if (isTouching(Trojan.class) || isTouching(Capanga.class)) {
                bloqueado = true;
            }
        }
        if (bloqueado) {
            setLocation(xAnterior, yAnterior);
            if (mundo instanceof Fase3) {
                setRotation(rotacaoAnterior);
            }
            turn(90 + Greenfoot.getRandomNumber(91));
            if (mundo instanceof Fase3) {
                if (isTouching(ParedeCodigo.class) || isTouching(Trojan.class)
                    || isTouching(Capanga.class)) {
                    setRotation(rotacaoAnterior);
                }
            }
            tempoDesvio = 25;
        }
    }

    public void receberDano()
    {
        if (getWorld() == null) {
            return;
        }

        vida = vida - 1;
        if (vida <= 0) {
            FaseBase mundo = (FaseBase) getWorld();
            mundo.removeObject(this);
            mundo.capangaDerrotado();
        }
    }

    public int getVida()
    {
        return vida;
    }
}
