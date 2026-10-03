import greenfoot.*;

public class TelaTransicao extends World
{
    private int proximaFase;
    private int pontos;
    private int vidas;

    public TelaTransicao(int proximaFase, int pontos, int vidas)
    {
        super(900, 600, 1);

        this.proximaFase = proximaFase;
        this.pontos = pontos;
        this.vidas = vidas;

        GreenfootImage fundo = new GreenfootImage(900, 600);
        fundo.setColor(Color.BLACK);
        fundo.fillRect(0, 0, 900, 600);
        setBackground(fundo);

        showText("PARABÉNS! FASE COMPLETA!", 450, 220);
        showText("Pontos: " + pontos, 450, 280);
        showText("Vidas: " + vidas, 450, 320);
        showText("Pressione ENTER ou clique para a próxima fase", 450, 400);
    }

    public void act()
    {
        if (Greenfoot.isKeyDown("enter")) {
            irParaProximaFase();
        }

        if (Greenfoot.mouseClicked(null)) {
            irParaProximaFase();
        }
    }

    private void irParaProximaFase()
    {
        if (proximaFase == 2) {
            Greenfoot.setWorld(new Fase2(pontos, vidas));
        }
    }
}