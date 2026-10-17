import greenfoot.*;

public class Virus extends Actor
{
    private int vida = 4;
    private int velocidade = 2;
    private int cooldownTiro = 90;

    public Virus()
    {
        getImage().scale(90, 90);
        setRotation(Greenfoot.getRandomNumber(360));
    }

    public Virus(int velocidade)
    {
        getImage().scale(90, 90);
        setRotation(Greenfoot.getRandomNumber(360));
        this.velocidade = velocidade;
    }

    public void act()
    {
        FaseBase mundo = (FaseBase) getWorld();
        if (mundo.estaPausada() || mundo.terminou()) {
            return;
        }

        mover();
        verificarTiro();
    }

    private void mover()
    {
        int xAnterior = getX();
        int yAnterior = getY();

        if (Greenfoot.getRandomNumber(100) < 4) {
            turn(Greenfoot.getRandomNumber(61) - 30);
        }

        move(velocidade);

        if (getX() < 50 || getX() > 850 || getY() < 120 || getY() > 550
            || isTouching(ParedeCodigo.class)) {
            setLocation(xAnterior, yAnterior);
            turn(90 + Greenfoot.getRandomNumber(91));
        }
    }

    private void verificarTiro()
    {
        cooldownTiro = cooldownTiro - 1;
        if (cooldownTiro > 0) {
            return;
        }

        FaseBase mundo = (FaseBase) getWorld();
        Byte jogador = mundo.getJogador();
        if (jogador != null && jogador.getWorld() != null) {
            TiroInimigo tiro = new TiroInimigo();
            mundo.addObject(tiro, getX(), getY());
            tiro.turnTowards(jogador.getX(), jogador.getY());
        }
        cooldownTiro = 130;
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
            mundo.virusDerrotado();
        }
    }

    public int getVida()
    {
        return vida;
    }
}
