import greenfoot.*;

public class Pulso extends Actor
{
    private int velocidade = 8;

    public Pulso()
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

        Bug bug = (Bug) getOneIntersectingObject(Bug.class);
        if (bug != null) {
            bug.receberDano();
            getWorld().removeObject(this);
            return;
        }

        Virus virus = (Virus) getOneIntersectingObject(Virus.class);
        if (virus != null) {
            virus.receberDano();
            getWorld().removeObject(this);
            return;
        }

        Capanga capanga = (Capanga) getOneIntersectingObject(Capanga.class);
        if (capanga != null) {
            capanga.receberDano();
            getWorld().removeObject(this);
        }
    }
}