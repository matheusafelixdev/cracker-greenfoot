import greenfoot.*;

public abstract class FaseBase extends World
{
    protected int pontuacao;
    protected Byte byte1;
    protected int derrotados = 0;
    protected int meta = 1;
    private int vidasIniciais;

    public FaseBase(int pontosIniciais, int vidasIniciais)
    {
        super(900, 600, 1);
        this.pontuacao = pontosIniciais;
        this.vidasIniciais = vidasIniciais;
    }

    protected void iniciarFase(String imagemFundo, int byteX, int byteY)
    {
        GreenfootImage fundo = new GreenfootImage(imagemFundo);
        fundo.scale(900, 600);
        setBackground(fundo);

        byte1 = new Byte();
        byte1.setVidas(vidasIniciais);
        addObject(byte1, byteX, byteY);

        showText("Pontos: " + pontuacao, 80, 20);
        atualizarVidas();
    }

    public void bugDerrotado()
    {
        inimigoDerrotado(10);
    }

    public void virusDerrotado()
    {
        inimigoDerrotado(20);
    }

    private void inimigoDerrotado(int pontos)
    {
        derrotados = derrotados + 1;
        pontuacao = pontuacao + pontos;

        showText("Pontos: " + pontuacao, 80, 20);
        showText("Derrotados: " + derrotados + "/" + meta, 450, 20);

        if (derrotados >= meta) {
            proximaFase();
        }
    }

    protected void proximaFase()
    {
    }

    public void adicionarPontos(int pontos)
    {
        pontuacao += pontos;
        showText("Pontos: " + pontuacao, 80, 20);
    }

    public void atualizarVidas()
    {
        showText("Vidas: " + byte1.getVidas(), 700, 20);
    }

    public int getPontuacao()
    {
        return pontuacao;
    }

    public void fimDeJogo()
    {
        Greenfoot.setWorld(new TelaGameOver(pontuacao));
    }
}