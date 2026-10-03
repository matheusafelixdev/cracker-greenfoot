import greenfoot.*;

public class Fase2 extends FaseBase
{
    public Fase2(int pontos, int vidas)
    {
        super(pontos, vidas);
        iniciarFase("fase2.png", 100, 300);

        meta = 1;

        Virus virus = new Virus();
        addObject(virus, 700, 300);

        showText("Derrotados: " + derrotados + "/" + meta, 450, 20);
    }

    protected void proximaFase()
    {
        showText("FASE COMPLETA!", 450, 300);
        Greenfoot.stop();
    }
}