package Zegana;

public class Mana{
    public int white;
    public int blue;
    public int black;
    public int red;
    public int green;
    public int colorless;

    public Mana(int w, int u, int b, int r, int g, int c){
	white = w;
	blue = u;
	black = b;
	red = r;
	green = g;
	colorless = c;
    }

    public Mana(int c){
	white = 0;
	blue = 0;
	black = 0;
	red = 0;
	green = 0;
	colorless = c;
    }

    public int cmc(){
	return white+blue+black+red+green+colorless;
    }
}
