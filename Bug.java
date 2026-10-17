import greenfoot.*;

public class Bug extends Actor
{
    private int vida = 3;
    private int velocidade = 1;
    private int cooldownTiro = 70;

    public Bug()
    {
        getImage().scale(70, 70);
        setRotation(Greenfoot.getRandomNumber(360));
    }

    public Bug(int velocidade)
    {
        getImage().scale(70, 70);
        setRotation(Greenfoot.getRandomNumber(360));
        this.velocidade = velocidade;
    }

    protected void addedToWorld(World mundo)
    {
        if (mundo instanceof Fase1) {
            vida = 5;
            cooldownTiro = 0;
            setRotation(0);
        }
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
        if (getWorld() instanceof Fase1) {
            if (Greenfoot.getRandomNumber(100) < 5) {
                turn(Greenfoot.getRandomNumber(61) - 30);
            }
            if (getX() < 90 || getX() > 810 || getY() < 135 || getY() > 500) {
                turnTowards(450, 300);
            }
            move(velocidade);
            return;
        }
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
        if (mundo instanceof Fase1) {
            TiroInimigo tiro = new TiroInimigo(5);
            mundo.addObject(tiro, getX(), getY());
            tiro.setRotation(getRotation());
            cooldownTiro = 60;
            return;
        }
        Byte jogador = mundo.getJogador();
        if (jogador != null && jogador.getWorld() != null) {
            TiroInimigo tiro = new TiroInimigo();
            mundo.addObject(tiro, getX(), getY());
            tiro.turnTowards(jogador.getX(), jogador.getY());
        }
        cooldownTiro = 150;
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
            mundo.bugDerrotado();
        }
    }

    public int getVida()
    {
        return vida;
    }
}
