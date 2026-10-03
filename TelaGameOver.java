import greenfoot.*;

public class TelaGameOver extends World
{
    public TelaGameOver(int pontos)
    {
        super(900, 600, 1);

        GreenfootImage arte = new GreenfootImage("gameover.png");

        double proporcao = Math.min(900.0 / arte.getWidth(), 600.0 / arte.getHeight());
        int novaLargura = (int) (arte.getWidth() * proporcao);
        int novaAltura = (int) (arte.getHeight() * proporcao);
        arte.scale(novaLargura, novaAltura);

        GreenfootImage fundo = new GreenfootImage(900, 600);
        fundo.setColor(Color.BLACK);
        fundo.fillRect(0, 0, 900, 600);
        fundo.drawImage(arte, (900 - novaLargura) / 2, (600 - novaAltura) / 2);
        setBackground(fundo);

        showText("Pontuação final: " + pontos, 450, 540);
        showText("Pressione ENTER ou clique para tentar novamente", 450, 575);
    }

    public void act()
    {
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new Fase1());
        }

        if (Greenfoot.mouseClicked(null)) {
            Greenfoot.setWorld(new Fase1());
        }
    }
}