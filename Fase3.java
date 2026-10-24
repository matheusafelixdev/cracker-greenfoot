import greenfoot.*;

public class Fase3 extends FaseBase
{
    private int tempoInimigo = 180;
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
        addObject(new ParedeCodigo(40, 80), 330, 180);
        addObject(new ParedeCodigo(40, 80), 330, 480);
        addObject(new ParedeCodigo(40, 80), 580, 330);
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
        if (criados >= 4 || getObjects(Capanga.class).size() >= 2) {
            return;
        }
        tempoInimigo = tempoInimigo - 1;
        if (tempoInimigo <= 0) {
            if (adicionarCapanga()) {
                criados = criados + 1;
                tempoInimigo = 180;
            } else {
                tempoInimigo = 30;
            }
        }
    }

    private boolean adicionarCapanga()
    {
        for (int tentativa = 0; tentativa < 20; tentativa = tentativa + 1) {
            Capanga capanga = new Capanga();
            criarInimigo(capanga);
            if (capanga.getWorld() == null) {
                return false;
            }
            if (posicaoLivre(capanga)) {
                return true;
            }
            removeObject(capanga);
        }
        return false;
    }

    private boolean posicaoLivre(Capanga capanga)
    {
        for (Actor outro : getObjects(Actor.class)) {
            if (outro != capanga && (outro instanceof Trojan || outro instanceof Capanga)) {
                int margem = 80;
                if (outro instanceof Trojan) {
                    margem = 150;
                }
                int distanciaX = capanga.getX() - outro.getX();
                int distanciaY = capanga.getY() - outro.getY();
                if (distanciaX < 0) {
                    distanciaX = -distanciaX;
                }
                if (distanciaY < 0) {
                    distanciaY = -distanciaY;
                }
                if (distanciaX < margem && distanciaY < margem) {
                    return false;
                }
            }
        }
        return true;
    }

    protected void proximaFase()
    {
        Greenfoot.setWorld(new TelaVitoria(pontuacao));
    }
}
