package computc;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import computc.entities.Hero;
import computc.worlds.Dungeon;
import computc.worlds.Room;

public class Menu
{
	private Hero hero;
	private Dungeon dungeon;
	
	private Image heart;
	
	public Menu(Dungeon dungeon, Hero hero) throws SlickException
	{
		this.hero = hero;
		this.dungeon = dungeon;
		
		this.heart = new Image("res/heart.png");
	}
	
	public void update(Input input, StateBasedGame game)
	{
		if(input.isKeyDown(Input.KEY_M))
		{
			game.enterState(1);
		}
	}
	
	public void render(Graphics graphics, Camera camera)
	{
		graphics.setColor(Color.black);
		graphics.fillRect(0, Room.HEIGHT, Room.WIDTH, Menu.HEIGHT);
		
		int MAP_WIDTH = 5, MAP_HEIGHT = 5;
		int UNIT = 12, MARGIN = 3, OFFSET = 29, MARKER = 6;
		
		for(int i = 0; i < MAP_WIDTH; i++)
		{
			for(int j = 0; j < MAP_HEIGHT; j++)
			{
				int dx = (int)(Math.floor((float)(this.hero.getRoomyX()) / MAP_WIDTH));
				int dy = (int)(Math.floor((float)(this.hero.getRoomyY()) / MAP_HEIGHT));
				
				int rx = (dx * MAP_WIDTH) + i;
				int ry = (dy * MAP_HEIGHT) + j;
				
				int x = OFFSET + MARGIN + (i * (UNIT + MARGIN));
				int y = OFFSET + MARGIN + (j * (UNIT + MARGIN));
				
				Room room = this.dungeon.getRoom(rx, ry);
				
				if(room != null)
				{
					if(room.visited)
					{
						graphics.setColor(Color.lightGray);
					}
					else
					{
						graphics.setColor(Color.gray);
					}
					
					graphics.fillRoundRect(x, y, UNIT, UNIT, 3);
					

					if(room.hasNorthernRoom()) {graphics.fillRect(x + (UNIT / 2) - 1, y - MARGIN, MARGIN, MARGIN);}
					if(room.hasSouthernRoom()) {graphics.fillRect(x + (UNIT / 2) - 1, y + UNIT, MARGIN, MARGIN);}
					if(room.hasEasternRoom()) {graphics.fillRect(x + UNIT, y + (UNIT / 2) - 1, MARGIN, MARGIN);}
					if(room.hasWesternRoom()) {graphics.fillRect(x - MARGIN, y + (UNIT / 2) - 1, MARGIN, MARGIN);}
					
					if(rx == this.hero.getRoomyX()
					&& ry == this.hero.getRoomyY())
					{
						graphics.setColor(Color.white);
						graphics.fillOval(x + (UNIT / 2) - (MARKER / 2), y + (UNIT / 2) - (MARKER / 2), MARKER, MARKER);
					}
				}
				else
				{
					graphics.setColor(Color.darkGray);
					graphics.fillRoundRect(x, y, UNIT, UNIT, 3);
				}
			}
		}
		
		for(int h = 0; h < hero.getHealth(); h++)
		{
			heart.draw(540 + (40 * h), 30);
		}
	}
	
	public static final int HEIGHT = 128;
}