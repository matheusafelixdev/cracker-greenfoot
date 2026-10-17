import greenfoot.*;

public class TelaTransicao extends World
{
    private int proximaFase;
    private int pontos;
    private int vidas;
    private int combo;
    private boolean enterLiberado;

    public TelaTransicao(int proximaFase, int pontos, int vidas)
    {
        super(900, 600, 1);
        prepararTela(proximaFase, pontos, vidas, 0);
    }

    public TelaTransicao(int proximaFase, int pontos, int vidas, int combo)
    {
        super(900, 600, 1);
        prepararTela(proximaFase, pontos, vidas, combo);
    }

    private void prepararTela(int proximaFase, int pontos, int vidas, int combo)
    {
        this.proximaFase = proximaFase;
        this.pontos = pontos;
        this.vidas = vidas;
        this.combo = combo;

        GreenfootImage fundo = new GreenfootImage("transicao.png");
        fundo.scale(900, 600);
        fundo.setColor(new Color(0, 8, 20, 190));
        fundo.fillRect(190, 15, 520, 65);
        GreenfootImage titulo = new GreenfootImage("ACESSO LIBERADO", 38,
            new Color(140, 255, 235), new Color(0, 0, 0, 0));
        fundo.drawImage(titulo, (900 - titulo.getWidth()) / 2, 28);
        fundo.setColor(new Color(0, 8, 20, 205));
        fundo.fillRect(125, 365, 650, 210);
        fundo.setColor(new Color(35, 190, 210));
        fundo.drawRect(125, 365, 650, 210);
        setBackground(fundo);

        String nomeFase = "FIREWALL CENTRAL";
        if (proximaFase == 3) {
            nomeFase = "NÚCLEO DO SISTEMA";
        }
        showText("Fase completa! Próxima: " + nomeFase, 450, 389);
        showText("Pontos: " + pontos + "   Vidas: " + vidas, 450, 422);
        showText("Combo: " + combo, 450, 453);
        showText("Na próxima fase: +1 vida (máximo de 3)", 450, 484);
        showText("ENTER ou clique para continuar", 450, 526);
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
            if (proximaFase == 2) {
                Greenfoot.setWorld(new Fase2(pontos, vidas, combo));
            } else if (proximaFase == 3) {
                Greenfoot.setWorld(new Fase3(pontos, vidas, combo));
            }
        }
    }
}
