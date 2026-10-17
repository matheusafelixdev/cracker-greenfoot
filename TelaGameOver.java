import greenfoot.*;

public class TelaGameOver extends World
{
    private boolean enterLiberado;

    public TelaGameOver()
    {
        super(900, 600, 1);
        prepararTela(0);
    }

    public TelaGameOver(int pontos)
    {
        super(900, 600, 1);
        prepararTela(pontos);
    }

    private void prepararTela(int pontos)
    {
        GreenfootImage arte = new GreenfootImage("gameover.png");
        double proporcaoLargura = 900.0 / arte.getWidth();
        double proporcaoAltura = 600.0 / arte.getHeight();
        double proporcao = proporcaoLargura;
        if (proporcaoAltura < proporcaoLargura) {
            proporcao = proporcaoAltura;
        }
        int novaLargura = (int) (arte.getWidth() * proporcao);
        int novaAltura = (int) (arte.getHeight() * proporcao);
        arte.scale(novaLargura, novaAltura);

        GreenfootImage fundo = new GreenfootImage(900, 600);
        fundo.setColor(Color.BLACK);
        fundo.fillRect(0, 0, 900, 600);
        fundo.drawImage(arte, (900 - novaLargura) / 2, (600 - novaAltura) / 2);
        fundo.setColor(new Color(0, 0, 0, 230));
        fundo.fillRect(90, 450, 720, 140);
        setBackground(fundo);

        showText("Pontuação final: " + pontos, 450, 480);
        showText("Pressione ENTER ou clique para tentar novamente", 450, 525);
        showText("ESC: voltar ao menu", 450, 565);
    }

    public void act()
    {
        if (Greenfoot.isKeyDown("escape")) {
            Greenfoot.setWorld(new Menu());
            return;
        }

        if (!Greenfoot.isKeyDown("enter")) {
            enterLiberado = true;
        }

        if ((enterLiberado && Greenfoot.isKeyDown("enter")) || Greenfoot.mouseClicked(null)) {
            Greenfoot.setWorld(new Fase1());
        }
    }
}
