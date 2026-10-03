import greenfoot.*;

public class Fase1 extends FaseBase
{
    public Fase1()
    {
        super(0, 3);
        iniciarFase("fase1.png", 100, 300);

        meta = 1;

        int bugX = Greenfoot.getRandomNumber(900);
        int bugY = Greenfoot.getRandomNumber(600);

        while (Math.abs(bugX - byte1.getX()) < 250 && Math.abs(bugY - byte1.getY()) < 250) {
            bugX = Greenfoot.getRandomNumber(900);
            bugY = Greenfoot.getRandomNumber(600);
        }

        Bug bug1 = new Bug();
        addObject(bug1, bugX, bugY);

        showText("Derrotados: " + derrotados + "/" + meta, 450, 20);
    }

    protected void proximaFase()
    {
        Greenfoot.setWorld(new TelaTransicao(2, pontuacao, byte1.getVidas()));
    }
}