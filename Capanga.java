import greenfoot.*;

public class Capanga extends Actor
{
    private int vida = 2;
    private int velocidade = 2;

    public Capanga()
    {
        getImage().scale(50, 50);
    }

    public void act()
    {
        moverAleatorio();
    }

    private void moverAleatorio()
    {
        if (Greenfoot.getRandomNumber(100) < 3) {
            setRotation(Greenfoot.getRandomNumber(360));
        }
        move(velocidade);
    }

    public void receberDano()
    {
        vida = vida - 1;

        if (vida <= 0) {
            getWorld().removeObject(this);
        }
    }
}