import greenfoot.*;

public class Fase3 extends FaseBase
{
    private int tempoInimigo = 240;
    private int criados = 0;

    public Fase3()
    {
        super(0, 3);
        prepararFase(3);
    }

    public Fase3(int pontos, int vidas)
    {
        super(pontos, vidas);
        prepararFase(vidas);
    }

    public Fase3(int pontos, int vidas, int combo)
    {
        super(pontos, vidas, combo);
        prepararFase(vidas);
    }

    private void prepararFase(int vidas)
    {
        int vidasDaFase = vidas + 1;
        if (vidasDaFase > 3) {
            vidasDaFase = 3;
        }
        iniciarFase("fase2.png", "FASE 3 - NÚCLEO DO SISTEMA", 100, 300);
        byte1.setVidas(vidasDaFase);
        atualizarVidas();
        addObject(new Trojan(), 720, 300);
        criarFragmento(450, 180);
        criarFragmento(450, 480);
    }

    public void act()
    {
        super.act();
        if (estaPausada() || terminou()) {
            return;
        }
        int vivos = getObjects(Bug.class).size() + getObjects(Virus.class).size();
        if (criados >= 2 || vivos >= 1 || getObjects(Capanga.class).size() > 0) {
            return;
        }
        tempoInimigo = tempoInimigo - 1;
        if (tempoInimigo <= 0) {
            Actor inimigo;
            if (criados == 0) {
                inimigo = new Bug();
            } else {
                inimigo = new Virus();
            }
            criarInimigo(inimigo);
            if (inimigo.getWorld() != null) {
                criados = criados + 1;
            }
            tempoInimigo = 240;
        }
    }

    protected void proximaFase()
    {
        Greenfoot.setWorld(new TelaVitoria(pontuacao));
    }
}
