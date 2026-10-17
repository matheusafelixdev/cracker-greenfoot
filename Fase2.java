import greenfoot.*;

public class Fase2 extends FaseBase
{
    private int tempoInimigo = 90;
    private int criados = 0;

    public Fase2(int pontos, int vidas)
    {
        super(pontos, vidas);
        prepararFase(vidas);
    }

    public Fase2(int pontos, int vidas, int combo)
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
        meta = 12;
        iniciarFase("fase2.png", "FASE 2 - FIREWALL CENTRAL", 100, 300);
        byte1.setVidas(vidasDaFase);
        atualizarVidas();
        addObject(new ParedeCodigo(40, 100), 340, 215);
        addObject(new ParedeCodigo(40, 100), 340, 435);
        addObject(new ParedeCodigo(40, 130), 560, 325);
        for (int i = 0; i < 2; i = i + 1) {
            adicionarInimigo();
        }
        criarFragmento(450, 170);
        criarFragmento(450, 510);
    }

    public void act()
    {
        super.act();
        if (estaPausada() || terminou()) {
            return;
        }
        int vivos = getObjects(Bug.class).size() + getObjects(Virus.class).size();
        if (vivos >= 2 || derrotados + vivos >= meta) {
            return;
        }
        tempoInimigo = tempoInimigo - 1;
        if (tempoInimigo <= 0) {
            adicionarInimigo();
            tempoInimigo = 90;
        }
    }

    private void adicionarInimigo()
    {
        Actor inimigo;
        if (criados % 2 == 0) {
            inimigo = new Bug();
        } else {
            inimigo = new Virus();
        }
        criarInimigo(inimigo);
        if (inimigo.getWorld() != null) {
            criados = criados + 1;
        }
    }

    protected void proximaFase()
    {
        Greenfoot.setWorld(new TelaTransicao(3, pontuacao, byte1.getVidas(), combo));
    }
}
