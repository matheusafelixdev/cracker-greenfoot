import greenfoot.*;

public class TelaVitoria extends World
{
    private boolean enterLiberado;

    public TelaVitoria()
    {
        super(900, 600, 1);
        prepararTela(0);
    }

    public TelaVitoria(int pontos)
    {
        super(900, 600, 1);
        prepararTela(pontos);
    }

    private void prepararTela(int pontos)
    {
        GreenfootImage fundo = new GreenfootImage("vitoria.png");
        fundo.scale(900, 600);
        fundo.setColor(new Color(0, 8, 20, 190));
        fundo.fillRect(260, 15, 380, 65);
        GreenfootImage titulo = new GreenfootImage("VITÓRIA!", 44,
            new Color(140, 255, 185), new Color(0, 0, 0, 0));
        fundo.drawImage(titulo, (900 - titulo.getWidth()) / 2, 24);
        fundo.setColor(new Color(0, 8, 20, 205));
        fundo.fillRect(125, 365, 650, 210);
        fundo.setColor(new Color(35, 210, 165));
        fundo.drawRect(125, 365, 650, 210);
        setBackground(fundo);

        showText("Byte derrotou o Trojan e escapou do servidor!", 450, 395);
        showText("As três camadas foram superadas.", 450, 429);
        showText("Pontuação final: " + pontos, 450, 467);
        showText("ENTER ou clique para jogar novamente", 450, 524);
        showText("ESC: voltar ao menu", 450, 553);
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
