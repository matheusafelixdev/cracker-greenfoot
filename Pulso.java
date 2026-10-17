import greenfoot.*;

public class Pulso extends Actor
{
    private int velocidade = 8;

    public Pulso()
    {
        getImage().scale(34, 18);
    }

    public void act()
    {
        FaseBase mundo = (FaseBase) getWorld();
        if (mundo.estaPausada() || mundo.terminou()) {
            return;
        }
        move(velocidade);
        if (isAtEdge() || getY() < 110 || getY() > 560 || isTouching(ParedeCodigo.class)) {
            mundo.removeObject(this);
            return;
        }
        Bug bug = (Bug) getOneIntersectingObject(Bug.class);
        if (bug != null) {
            mundo.removeObject(this);
            bug.receberDano();
            return;
        }
        Virus virus = (Virus) getOneIntersectingObject(Virus.class);
        if (virus != null) {
            mundo.removeObject(this);
            virus.receberDano();
            return;
        }
        Capanga capanga = (Capanga) getOneIntersectingObject(Capanga.class);
        if (capanga != null) {
            mundo.removeObject(this);
            capanga.receberDano();
            return;
        }
        Trojan trojan = (Trojan) getOneIntersectingObject(Trojan.class);
        if (trojan != null) {
            mundo.removeObject(this);
            trojan.receberDano();
        }
    }
}
