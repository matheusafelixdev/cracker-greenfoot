import greenfoot.*;

public class ParedeCodigo extends Actor
{
    private int largura;
    private int altura;

    public ParedeCodigo(int largura, int altura)
    {
        this.largura = largura;
        this.altura = altura;
        GreenfootImage imagem = new GreenfootImage("parede-codigo.png");
        imagem.scale(largura, altura);
        setImage(imagem);
    }

    public boolean bloqueia(int x, int y, int margem)
    {
        int distanciaX = x - getX();
        int distanciaY = y - getY();
        if (distanciaX < 0) {
            distanciaX = -distanciaX;
        }
        if (distanciaY < 0) {
            distanciaY = -distanciaY;
        }
        if (distanciaX < largura / 2 + margem && distanciaY < altura / 2 + margem) {
            return true;
        } else {
            return false;
        }
    }
}
