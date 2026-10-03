import greenfoot.*;

public class Byte extends Actor
{
    private int velocidade = 4;
    private int cooldownTiro = 0;
    private final int intervaloTiro = 15;
    private int vidas = 3;

    private int knockbackX = 0;
    private int knockbackY = 0;

    private int cooldownDano = 0;
    private final int intervaloDano = 40;

    private int direcaoTiro = 0;
    private boolean olhandoEsquerda = false;

    public Byte()
    {
        getImage().scale(70, 70);
    }

    public void act()
    {
        verificarMovimento();
        verificarTiro();
        verificarColisaoComInimigo();
        aplicarKnockback();
    }

    private void verificarMovimento()
    {
        int x = getX();
        int y = getY();

        if (Greenfoot.isKeyDown("a")) {
            x -= velocidade;
            direcaoTiro = 180;
            if (!olhandoEsquerda) {
                getImage().mirrorHorizontally();
                olhandoEsquerda = true;
            }
        }
        if (Greenfoot.isKeyDown("d")) {
            x += velocidade;
            direcaoTiro = 0;
            if (olhandoEsquerda) {
                getImage().mirrorHorizontally();
                olhandoEsquerda = false;
            }
        }
        if (Greenfoot.isKeyDown("w")) {
            y -= velocidade;
            direcaoTiro = 270;
        }
        if (Greenfoot.isKeyDown("s")) {
            y += velocidade;
            direcaoTiro = 90;
        }

        if (x < 0) {
            x = 0;
        }
        if (x > getWorld().getWidth() - 1) {
            x = getWorld().getWidth() - 1;
        }
        if (y < 0) {
            y = 0;
        }
        if (y > getWorld().getHeight() - 1) {
            y = getWorld().getHeight() - 1;
        }

        setLocation(x, y);
    }

    private void verificarTiro()
    {
        if (cooldownTiro > 0) {
            cooldownTiro = cooldownTiro - 1;
        }

        if (Greenfoot.isKeyDown("space") && cooldownTiro == 0) {
            Pulso pulso = new Pulso();
            getWorld().addObject(pulso, getX(), getY());
            pulso.setRotation(direcaoTiro);
            cooldownTiro = intervaloTiro;
        }
    }

    private void verificarColisaoComInimigo()
    {
        if (cooldownDano > 0) {
            cooldownDano = cooldownDano - 1;
            return;
        }

        Actor inimigo = getOneIntersectingObject(Bug.class);
        if (inimigo == null) {
            inimigo = getOneIntersectingObject(Virus.class);
        }
        if (inimigo == null) {
            inimigo = getOneIntersectingObject(Capanga.class);
        }

        if (inimigo != null) {
            perderVida();
            cooldownDano = intervaloDano;

            if (getWorld() != null) {
                int direcaoX = getX() - inimigo.getX();
                int direcaoY = getY() - inimigo.getY();

                int distanciaKnockback = 130;

                knockbackX = Integer.signum(direcaoX) * distanciaKnockback;
                knockbackY = Integer.signum(direcaoY) * distanciaKnockback;
            }
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
        vidas = vidas - 1;

        FaseBase mundo = (FaseBase) getWorld();
        mundo.atualizarVidas();

        if (vidas <= 0) {
            mundo.fimDeJogo();
        }
    }

    private void aplicarKnockback()
    {
        if (getWorld() == null) {
            return;
        }

        if (knockbackX == 0 && knockbackY == 0) {
            return;
        }

        int passoX = Integer.signum(knockbackX) * 5;
        int passoY = Integer.signum(knockbackY) * 5;

        int novoX = getX() + passoX;
        int novoY = getY() + passoY;

        if (novoX < 0) {
            novoX = 0;
        }
        if (novoX > getWorld().getWidth() - 1) {
            novoX = getWorld().getWidth() - 1;
        }
        if (novoY < 0) {
            novoY = 0;
        }
        if (novoY > getWorld().getHeight() - 1) {
            novoY = getWorld().getHeight() - 1;
        }

        setLocation(novoX, novoY);

        knockbackX = knockbackX - passoX;
        knockbackY = knockbackY - passoY;
    }
}