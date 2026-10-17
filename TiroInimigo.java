import greenfoot.*;

public class TiroInimigo extends Actor
{
    private int velocidade = 3;

    public TiroInimigo()
    {
        getImage().scale(30, 16);
    }

    public TiroInimigo(int velocidade)
    {
        this.velocidade = velocidade;
        getImage().scale(30, 16);
    }

    public void act()
    {
        FaseBase mundo = (FaseBase) getWorld();
        if (mundo.estaPausada() || mundo.terminou()) {
            return;
        }
        move(velocidade);
        if (isAtEdge() || getY() < 50 || isTouching(ParedeCodigo.class)) {
            mundo.removeObject(this);
            return;
        }
        Byte alvo = (Byte) getOneIntersectingObject(Byte.class);
        if (alvo != null) {
            mundo.removeObject(this);
            alvo.perderVida();
        }
    }
}
