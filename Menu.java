import greenfoot.*;

public class Menu extends World
{
    private boolean enterLiberado;

    public Menu()
    {
        super(900, 600, 1);

        GreenfootImage fundo = new GreenfootImage("menu.png");
        fundo.scale(900, 600);
        setBackground(fundo);
    }

    public void act()
    {
        if (!Greenfoot.isKeyDown("enter")) {
            enterLiberado = true;
        }

        if ((enterLiberado && Greenfoot.isKeyDown("enter")) || Greenfoot.mouseClicked(null)) {
            Greenfoot.setWorld(new Fase1());
        }
    }
}
