import greenfoot.*;

public class Virus extends Actor
{
    private int vida = 10;
    private int vidaMaxima = 10;
    private boolean invocouCapangas = false;
    private int velocidade = 1;
    private int cooldownTiro = 0;
    private final int intervaloTiro = 60;

    public Virus()
    {
        getImage().scale(90, 90);
    }

    public void act()
    {
        moverAleatorio();
        verificarTiro();

        if (invocouCapangas == true && temCapangasVivos() == true) {
            getImage().setTransparency(120);
        } else {
            getImage().setTransparency(255);
        }
    }

    private void moverAleatorio()
    {
        if (Greenfoot.getRandomNumber(100) < 5) {
            turn(Greenfoot.getRandomNumber(61) - 30);
        }

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
        if (invocouCapangas == true && temCapangasVivos() == true) {
            return;
        }

        vida = vida - 1;

        if (vida <= vidaMaxima / 2 && invocouCapangas == false) {
            invocarCapangas();
        }

        if (vida <= 0) {
            FaseBase mundo = (FaseBase) getWorld();
            mundo.virusDerrotado();
            mundo.removeObject(this);
        }
    }

    private void invocarCapangas()
    {
        invocouCapangas = true;

        getWorld().addObject(new Capanga(), getX() + 80, getY());
        getWorld().addObject(new Capanga(), getX() - 80, getY());
    }

    private boolean temCapangasVivos()
    {
        if (getWorld().getObjects(Capanga.class).size() > 0) {
            return true;
        }
        return false;
    }
}