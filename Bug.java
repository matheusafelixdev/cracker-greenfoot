import greenfoot.*;

public class Bug extends Actor
{
    private int vida = 5;
    private int velocidade = 1;
    private int cooldownTiro = 0;
    private final int intervaloTiro = 60;

    public Bug()
    {
        getImage().scale(70, 70);
    }

    public void act()
    {
        moverAleatorio();
        verificarTiro();
    }

    private void moverAleatorio()
    {
        // De vez em quando faz uma curva pequena
        if (Greenfoot.getRandomNumber(100) < 5) {
            turn(Greenfoot.getRandomNumber(61) - 30);
        }

        // Se estiver perto da parede, vira para o centro da tela
        if (getX() < 90 || getX() > 810 || getY() < 100 || getY() > 500) {
            turnTowards(450, 300);
        }

        move(velocidade);
    }

    private void verificarTiro()
    {
        if (cooldownTiro > 0) {
            cooldownTiro--;
            return;
        }

        TiroInimigo tiro = new TiroInimigo();
        getWorld().addObject(tiro, getX(), getY());
        tiro.setRotation(getRotation());
        cooldownTiro = intervaloTiro;
    }

    public void receberDano()
    {
        vida = vida - 1;

        if (vida <= 0) {
            FaseBase mundo = (FaseBase) getWorld();
            mundo.bugDerrotado();
            mundo.removeObject(this);
        }
    }

    public int getVida()
    {
        return vida;
    }
}