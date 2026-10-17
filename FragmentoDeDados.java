import greenfoot.*;

public class FragmentoDeDados extends Actor
{
    public FragmentoDeDados()
    {
        GreenfootImage imagem = new GreenfootImage("fragmento-dados.png");
        imagem.scale(30, 30);
        setImage(imagem);
    }

    public void act()
    {
        FaseBase mundo = (FaseBase) getWorld();
        if (mundo.estaPausada() || mundo.terminou()) {
            return;
        }
        if (isTouching(Byte.class)) {
            mundo.removeObject(this);
            mundo.adicionarPontos(50);
        }
    }
}
