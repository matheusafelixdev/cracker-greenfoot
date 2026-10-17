import greenfoot.*;

public class Fase1 extends FaseBase
{
    public Fase1()
    {
        super(0, 3);
        meta = 1;
        iniciarFase("fase1.png", "FASE 1 - CAMADA DE ACESSO", 100, 300);
        criarInimigo(new Bug());
    }

    protected void proximaFase()
    {
        Greenfoot.setWorld(new TelaTransicao(2, pontuacao, byte1.getVidas(), combo));
    }
}
