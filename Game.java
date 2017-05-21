import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;

public class Game extends App{
	//senos e cossenos
	float sen15 = 0.23f,sen30 = 0.45f,cos15 = 0.97f,cos30 = 0.89f;
	
	//bola
	int x_bola,y_bola,t_bola,a_bola;
	int[] v_bola =  new int[2];
	int v_player = 7;
	//player1
	int x_player1,y_player1,l_player1,a_player1,p_player1 = 0;
	//player2
	int x_player2,y_player2,l_player2,a_player2,p_player2 = 0;


	public void setup(){
		setFps(60);
		setAltura(400);
		setLargura(700);
		//bola
		t_bola = 20;
		x_bola = getLargura()/2-t_bola/2;
		y_bola = getAltura()/2-t_bola/2;
		a_bola = 10;
		v_bola[0] = a_bola;
		v_bola[1] = 0;
		//player1
		l_player1 = 30;
		a_player1 = 100;
		x_player1 = l_player1;
		y_player1 = getAltura()/2-a_player1/2;
		//player2
		l_player2 = 30;
		a_player2 = 100;
		x_player2 = getLargura()-l_player2*2;
		y_player2 = getAltura()/2-a_player1/2;

		createKey("PLAYER1CIMA",KeyEvent.VK_W);
		createKey("PLAYER1BAIXO",KeyEvent.VK_S);
		createKey("PLAYER2CIMA",KeyEvent.VK_UP);
		createKey("PLAYER2BAIXO",KeyEvent.VK_DOWN);
	}
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0,0,getLargura(),getAltura());
		//bola
		g.setColor(Color.WHITE);
		g.fillRect(x_bola,y_bola,t_bola,t_bola);
		//players
		g.fillRect(x_player1,y_player1,l_player1,a_player1);
		g.fillRect(x_player2,y_player2,l_player2,a_player2);
		//pontuação
		g.drawRect(getLargura()/2-50,0,100,15);
		g.drawString(""+p_player1,getLargura()/2-45,10);
		g.drawString(""+p_player2,getLargura()/2+40,10);

	}
	public void update(){
		x_bola += v_bola[0];
		y_bola += v_bola[1];
		colisao();
	}
	public void checkInput(){
		if(isKeyPressed("PLAYER1CIMA") && y_player1>0){
			y_player1 -= v_player;
		}
		if(isKeyPressed("PLAYER1BAIXO") && y_player1+a_player1<getAltura()){
			y_player1 += v_player;
		}
		if(isKeyPressed("PLAYER2CIMA") && y_player2>0){
			y_player2 -= v_player;
		}
		if(isKeyPressed("PLAYER2BAIXO") && y_player2+a_player2<getAltura()){
			y_player2 += v_player;
		}
	}
	public void colisao(){
		//player1
		if(x_bola < x_player1+l_player1 && x_bola+t_bola > x_player1
			&& y_bola < y_player1+a_player1 && y_bola+t_bola > y_player1){
			if(y_bola < y_player1+(int)(a_player1*1/5)){
				v_bola[0] = (int)(a_bola*cos30);
				v_bola[1] = -(int)(a_bola*sen30);
			}else if(y_bola < y_player1+(int)(a_player1*2/5)){
				v_bola[0] = (int)(a_bola*cos15);
				v_bola[1] = (int)(a_bola*sen15);
			}else if(y_bola < y_player1+(int)(a_player1*3/5)){
				v_bola[0] *= -1;
				v_bola[1] = 0;
			}else if(y_bola < y_player1+(int)(a_player1*4/5)){
				v_bola[0] = (int)(a_bola*cos15);
				v_bola[1] = (int)(a_bola*sen15);
			}else{
				v_bola[0] = (int)(a_bola*cos30);
				v_bola[1] = (int)(a_bola*sen30);
			}
		}
		//player2
		if(x_bola < x_player2+l_player2 && x_bola+t_bola > x_player2
			&& y_bola < y_player2+a_player2 && y_bola+t_bola > y_player2){
			if(y_bola < y_player2+(int)(a_player2*1/5)){
				v_bola[0] = -(int)(a_bola*cos30);
				v_bola[1] = -(int)(a_bola*sen30);
			}else if(y_bola < y_player2+(int)(a_player2*2/5)){
				v_bola[0] = -(int)(a_bola*cos15);
				v_bola[1] = -(int)(a_bola*sen15);
			}else if(y_bola < y_player2+(int)(a_player2*3/5)){
				v_bola[0] *= -1;
				v_bola[1] = 0;
			}else if(y_bola < y_player2+(int)(a_player2*4/5)){
				v_bola[0] = -(int)(a_bola*cos15);
				v_bola[1] = (int)(a_bola*sen15);
			}else{
				v_bola[0] = -(int)(a_bola*cos30);
				v_bola[1] = (int)(a_bola*sen30);
			}
		}
		//bola
		if(y_bola < 0 || y_bola+t_bola > getAltura())
			v_bola[1] *= -1;
		if(x_bola+t_bola < 0){
			p_player2 ++;
			x_bola = getLargura()/2-t_bola/2;
			y_bola = getAltura()/2-t_bola/2;
			v_bola[0] = a_bola;
			v_bola[1] = 0;
		}else if(x_bola > getLargura()){
			p_player1 ++;
			x_bola = getLargura()/2-t_bola/2;
			y_bola = getAltura()/2-t_bola/2;
			v_bola[0] = a_bola;
			v_bola[1] = 0;
		}
	}
}