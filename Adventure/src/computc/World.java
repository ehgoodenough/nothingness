package computc;

import java.awt.Point;
import java.util.LinkedList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class World
{
	public Hero hero;
	public Room room;
	public LinkedList<Enemy> enemies;
	public Camera camera;
	
	public World() throws SlickException
	{
		this.room = new Room();
		
		this.hero = new Hero(this, 5, 0);
		
		this.enemies = new LinkedList<Enemy>();
		for(Point point : new Point[] {new Point(1, 6), new Point(5, 4), new Point(8, 5)})
		{
		 	this.enemies.add(new Thug(this, point.x, point.y));
		}
		
		this.camera = new Camera(hero);
	}
	
	public void update(Input input, int delta)
	{
		this.camera.update(delta);
		this.hero.update(input, delta);
	}
	
	public void render(Graphics graphics)
	{
		this.room.render(graphics, camera);
		
		for(Enemy enemy : this.enemies)
		{
			enemy.render(graphics, camera);
		}

		this.hero.render(graphics, camera);
	}
}