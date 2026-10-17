import greenfoot.*;

public class FaseBase extends World
{
    protected int pontuacao;
    protected Byte byte1;
    protected int derrotados = 0;
    protected int meta = 10;
    protected int combo;
    private int vidasIniciais;
    private boolean recebeuDano = false;
    private boolean encerrada = false;
    private boolean pausada = false;
    private boolean teclaP = false;
    private boolean teclaE = false;
    private long ultimoTempo;
    private long tempoOverclock = 0;
    private long recargaOverclock = 0;
    private String nomeFase = "";

    public FaseBase(int pontosIniciais, int vidasIniciais)
    {
        super(900, 600, 1);
        prepararDados(pontosIniciais, vidasIniciais, 0);
    }

    public FaseBase(int pontosIniciais, int vidasIniciais, int comboInicial)
    {
        super(900, 600, 1);
        prepararDados(pontosIniciais, vidasIniciais, comboInicial);
    }

    private void prepararDados(int pontosIniciais, int vidasIniciais, int comboInicial)
    {
        pontuacao = pontosIniciais;
        this.vidasIniciais = vidasIniciais;
        combo = comboInicial;
        ultimoTempo = System.currentTimeMillis();
        setPaintOrder(Byte.class, Pulso.class, TiroInimigo.class,
            Trojan.class, Virus.class, Bug.class, Capanga.class,
            FragmentoDeDados.class, ParedeCodigo.class);
    }

    protected void iniciarFase(String imagemFundo, String nome, int byteX, int byteY)
    {
        nomeFase = nome;
        GreenfootImage fundo = new GreenfootImage(imagemFundo);
        fundo.scale(900, 600);
        fundo.setColor(new Color(8, 14, 26, 235));
        fundo.fillRect(0, 0, 900, 50);
        setBackground(fundo);
        byte1 = new Byte();
        byte1.setVidas(vidasIniciais);
        addObject(byte1, byteX, byteY);
        atualizarPainel();
    }

    public void act()
    {
        long agora = System.currentTimeMillis();
        long tempoPassado = agora - ultimoTempo;
        ultimoTempo = agora;
        if (Greenfoot.isKeyDown("escape")) {
            encerrada = true;
            Greenfoot.setWorld(new Menu());
            return;
        }
        boolean apertouP = Greenfoot.isKeyDown("p");
        if (apertouP && !teclaP) {
            if (pausada) {
                pausada = false;
            } else {
                pausada = true;
            }
            if (pausada) {
                showText("PAUSADO", 450, 300);
            } else {
                showText("", 450, 300);
            }
            tempoPassado = 0;
        }
        teclaP = apertouP;
        boolean apertouE = Greenfoot.isKeyDown("e");
        if (pausada || encerrada) {
            teclaE = apertouE;
            return;
        }
        if (tempoOverclock > 0) {
            tempoOverclock = tempoOverclock - tempoPassado;
            if (tempoOverclock <= 0) {
                tempoOverclock = 0;
                recargaOverclock = 20000;
            }
        } else if (recargaOverclock > 0) {
            recargaOverclock = recargaOverclock - tempoPassado;
            if (recargaOverclock < 0) {
                recargaOverclock = 0;
            }
        }
        if (apertouE && !teclaE && tempoOverclock == 0 && recargaOverclock == 0) {
            tempoOverclock = 15000;
        }
        teclaE = apertouE;
        atualizarPainel();
    }

    public void started()
    {
        ultimoTempo = System.currentTimeMillis();
    }

    public boolean estaPausada()
    {
        return pausada;
    }

    public boolean terminou()
    {
        return encerrada;
    }

    public boolean overclockAtivo()
    {
        if (tempoOverclock > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Byte getJogador()
    {
        return byte1;
    }

    public void bugDerrotado()
    {
        inimigoDerrotado(10, true);
    }

    public void virusDerrotado()
    {
        inimigoDerrotado(20, true);
    }

    public void capangaDerrotado()
    {
        if (this instanceof Fase2) {
            inimigoDerrotado(10, true);
        } else {
            inimigoDerrotado(10, false);
        }
    }

    private void inimigoDerrotado(int pontos, boolean contaNaMeta)
    {
        if (encerrada) {
            return;
        }
        combo = combo + 1;
        adicionarPontos(pontos);
        if (contaNaMeta) {
            derrotados = derrotados + 1;
        }
        atualizarPainel();
        if (!(this instanceof Fase3) && derrotados >= meta) {
            concluirFase();
        }
    }

    public void trojanDerrotado()
    {
        if (encerrada) {
            return;
        }
        combo = combo + 1;
        adicionarPontos(500);
        concluirFase();
    }

    private void concluirFase()
    {
        if (!recebeuDano) {
            pontuacao = pontuacao + 200;
        }
        encerrada = true;
        proximaFase();
    }

    protected void proximaFase()
    {
    }

    public void adicionarPontos(int pontos)
    {
        double multiplicador = 1;
        if (combo >= 6) {
            multiplicador = 2;
        } else if (combo >= 3) {
            multiplicador = 1.5;
        }
        if (overclockAtivo()) {
            multiplicador = multiplicador * 1.5;
        }
        pontuacao = pontuacao + (int) (pontos * multiplicador);
        atualizarPainel();
    }

    public void registrarDano()
    {
        recebeuDano = true;
        if (!overclockAtivo()) {
            combo = 0;
        }
        atualizarPainel();
    }

    public void atualizarVidas()
    {
        atualizarPainel();
    }

    private void atualizarPainel()
    {
        if (byte1 == null) {
            return;
        }
        showText("Pontos: " + pontuacao, 110, 24);
        showText("Vidas: " + byte1.getVidas(), 450, 24);
        if (!(this instanceof Fase3)) {
            showText(nomeFase.substring(0, 6) + ": " + derrotados + "/" + meta, 775, 24);
        }

        GreenfootImage fundo = getBackground();
        fundo.setColor(new Color(35, 50, 60));
        fundo.fillRect(410, 42, 80, 3);
        int larguraBarra = 80;
        if (overclockAtivo()) {
            larguraBarra = (int) (80 * tempoOverclock / 15000);
            fundo.setColor(new Color(60, 235, 255));
        } else if (recargaOverclock > 0) {
            larguraBarra = (int) (80 * (20000 - recargaOverclock) / 20000);
            fundo.setColor(new Color(80, 135, 150));
        } else {
            fundo.setColor(new Color(85, 225, 165));
        }
        fundo.fillRect(410, 42, larguraBarra, 3);
    }

    public void atualizarChefe(int vida, int vidaMaxima)
    {
        showText("Trojan: " + vida + "/" + vidaMaxima, 775, 24);
    }

    public int getPontuacao()
    {
        return pontuacao;
    }

    public void fimDeJogo()
    {
        if (!encerrada) {
            encerrada = true;
            Greenfoot.setWorld(new TelaGameOver(pontuacao));
        }
    }

    public boolean tiroBloqueado(int x, int y)
    {
        if (getObjectsAt(x, y, ParedeCodigo.class).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    protected void criarInimigo(Actor inimigo)
    {
        int x = 0;
        int y = 0;
        int tentativas = 0;
        boolean livre = false;
        while (!livre && tentativas < 100) {
            x = 60 + Greenfoot.getRandomNumber(780);
            y = 150 + Greenfoot.getRandomNumber(380);
            int distanciaX = x - byte1.getX();
            int distanciaY = y - byte1.getY();
            if (distanciaX < 0) {
                distanciaX = -distanciaX;
            }
            if (distanciaY < 0) {
                distanciaY = -distanciaY;
            }
            livre = false;
            if (distanciaX > 250 || distanciaY > 220) {
                livre = true;
            }
            for (int i = 0; i < getObjects(ParedeCodigo.class).size(); i = i + 1) {
                ParedeCodigo parede = getObjects(ParedeCodigo.class).get(i);
                if (parede.bloqueia(x, y, 60)) {
                    livre = false;
                }
            }
            tentativas = tentativas + 1;
        }
        if (livre) {
            addObject(inimigo, x, y);
        }
    }

    public void criarFragmento(int x, int y)
    {
        if (!encerrada) {
            addObject(new FragmentoDeDados(), x, y);
        }
    }
}
