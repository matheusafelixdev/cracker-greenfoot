import greenfoot.*;

public class TiroInimigo extends Actor
{
    private int velocidade = 5;

    
    public TiroInimigo()
    {
        getImage().scale(30, 30);
    }
    
    public void act()
    {
        move(velocidade);

        if (isAtEdge()) {
            getWorld().removeObject(this);
            return;
        }

        Actor alvo = getOneIntersectingObject(Byte.class);
        if (alvo != null) {
            Byte byte1 = (Byte) alvo;
            byte1.perderVida();
            getWorld().removeObject(this);
        }
    }
}
