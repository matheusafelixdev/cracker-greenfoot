import greenfoot.*;

public class Byte extends Actor
{
    private int velocidade = 4;
    private int cooldownTiro = 0;
    private int intervaloTiro = 15;
    private int vidas = 3;
    private int cooldownDano = 0;
    private int direcaoTiro = 0;
    private boolean olhandoEsquerda = false;
    private int knockbackX = 0;
    private int knockbackY = 0;

    public Byte()
    {
        getImage().scale(70, 70);
    }

    public void act()
    {
        FaseBase mundo = (FaseBase) getWorld();
        if (mundo.estaPausada() || mundo.terminou()) {
            return;
        }
        if (cooldownDano > 0) {
            cooldownDano = cooldownDano - 1;
            if (cooldownDano % 10 < 5) {
                getImage().setTransparency(110);
            } else {
                getImage().setTransparency(255);
            }
        } else {
            getImage().setTransparency(255);
        }
        verificarMovimento();
        aplicarKnockback();
        verificarTiro();
        verificarColisaoComInimigo();
    }

    private void verificarMovimento()
    {
        int passo = velocidade;
        FaseBase mundo = (FaseBase) getWorld();
        if (mundo.overclockAtivo()) {
            passo = 6;
        }
        int x = getX();
        int y = getY();
        if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
            x = x - passo;
            direcaoTiro = 180;
            if (!olhandoEsquerda) {
                getImage().mirrorHorizontally();
                olhandoEsquerda = true;
            }
        }
        if (Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
            x = x + passo;
            direcaoTiro = 0;
            if (olhandoEsquerda) {
                getImage().mirrorHorizontally();
                olhandoEsquerda = false;
            }
        }
        if (Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) {
            y = y - passo;
            direcaoTiro = 270;
        }
        if (Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down")) {
            y = y + passo;
            direcaoTiro = 90;
        }
        moverPara(x, y);
    }

    private void moverPara(int x, int y)
    {
        if (x < 35) {
            x = 35;
        }
        if (x > 865) {
            x = 865;
        }
        if (y < 135) {
            y = 135;
        }
        if (y > 533) {
            y = 533;
        }
        int antigoX = getX();
        int antigoY = getY();
        setLocation(x, antigoY);
        if (isTouching(ParedeCodigo.class)) {
            setLocation(antigoX, antigoY);
        }
        setLocation(getX(), y);
        if (isTouching(ParedeCodigo.class)) {
            setLocation(getX(), antigoY);
        }
    }

    private void verificarTiro()
    {
        FaseBase mundo = (FaseBase) getWorld();
        int intervalo = 10;
        if (mundo instanceof Fase1) {
            intervalo = intervaloTiro;
        }
        boolean clique = false;
        if (Greenfoot.mouseClicked(null)) {
            if (mundo instanceof Fase1) {
                clique = false;
            } else {
                clique = true;
            }
        }
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse == null || mouse.getY() < 110 || mouse.getY() > 560) {
            clique = false;
        }
        if (cooldownTiro > 0) {
            cooldownTiro = cooldownTiro - 1;
        }
        if ((Greenfoot.isKeyDown("space") || clique) && cooldownTiro == 0) {
            Pulso pulso = new Pulso();
            getWorld().addObject(pulso, getX(), getY());
            pulso.setRotation(direcaoTiro);
            if (clique) {
                pulso.turnTowards(mouse.getX(), mouse.getY());
            }
            cooldownTiro = intervalo;
        }
    }

    private void verificarColisaoComInimigo()
    {
        Actor inimigo = getOneIntersectingObject(Bug.class);
        if (inimigo == null) {
            inimigo = getOneIntersectingObject(Virus.class);
        }
        if (inimigo == null) {
            inimigo = getOneIntersectingObject(Capanga.class);
        }
        if (inimigo == null) {
            inimigo = getOneIntersectingObject(Trojan.class);
        }
        if (inimigo != null && cooldownDano == 0) {
            int distanciaX = getX() - inimigo.getX();
            int distanciaY = getY() - inimigo.getY();
            knockbackX = 0;
            knockbackY = 0;
            if (distanciaX > 0) {
                knockbackX = 60;
            } else if (distanciaX < 0) {
                knockbackX = -60;
            }
            if (distanciaY > 0) {
                knockbackY = 60;
            } else if (distanciaY < 0) {
                knockbackY = -60;
            }
            if (knockbackX == 0 && knockbackY == 0) {
                knockbackX = -60;
            }
            perderVida();
        }
    }

    public int getVidas()
    {
        return vidas;
    }

    public void setVidas(int novasVidas)
    {
        vidas = novasVidas;
    }

    public void perderVida()
    {
        if (getWorld() == null || cooldownDano > 0) {
            return;
        }
        FaseBase mundo = (FaseBase) getWorld();
        if (mundo.estaPausada() || mundo.terminou()) {
            return;
        }
        vidas = vidas - 1;
        cooldownDano = 100;
        if (mundo instanceof Fase1) {
            cooldownDano = 60;
        }
        mundo.registrarDano();
        mundo.atualizarVidas();
        if (vidas <= 0) {
            mundo.fimDeJogo();
        }
    }

    private void aplicarKnockback()
    {
        int passoX = 0;
        int passoY = 0;
        if (knockbackX > 0) {
            passoX = 5;
        } else if (knockbackX < 0) {
            passoX = -5;
        }
        if (knockbackY > 0) {
            passoY = 5;
        } else if (knockbackY < 0) {
            passoY = -5;
        }
        if (passoX != 0 || passoY != 0) {
            moverPara(getX() + passoX, getY() + passoY);
            knockbackX = knockbackX - passoX;
            knockbackY = knockbackY - passoY;
        }
    }
}
