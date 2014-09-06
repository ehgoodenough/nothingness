package computc;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainGameState extends BasicGameState
{
	public Hero hero;
	public Dungeon dungeon;
	public OldMan oldman;
	public Camera camera;
	public Menu menu;
	
	private Image menuBox;
	private Image largeTextBox;
	private int counter, counter2;
	Animation textBox;
	public Color textColor = Color.white;
	private boolean nextLevel = false;
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.dungeon = new Dungeon();
		this.hero = new Hero(dungeon, dungeon.getRoom(3, 0), 5, 1);
		this.camera = new Camera(hero);
		this.menu = new Menu(dungeon, hero);
		
		this.menuBox = new Image("res/textBox.png");
		this.largeTextBox = new Image("res/largeTextBox.png");
		this.textBox = new Animation(new SpriteSheet(largeTextBox, 585, 100), 100);
		this.oldman = new OldMan(dungeon, 38, 12);
		
		Tile.WALL_IMAGE = new Image("./res/wall.png");
		Tile.FLOOR_IMAGE = new Image("./res/floor.png");
		Tile.STAIR_IMAGE = new Image("./res/stairs.png");
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		Input input = container.getInput();
		
		if(!hero.isDead() == true)
		{
			this.hero.update(input, delta);
		
			this.dungeon.update(delta);
			this.camera.update(delta);
			this.oldman.update(delta);
		
			hero.checkAttack(dungeon.thugs);
		}
		else
			if(hero.isDead() == true)
			{
				if(input.isKeyDown(Input.KEY_R))
				{
					Game.reset = true;
					this.hero = new Hero(dungeon, dungeon.getRoom(3, 0), 5, 1);
					this.camera = new Camera(hero);
					this.hero.setAlive();
				}
				if(input.isKeyDown(Input.KEY_Q))
					System.exit(0);
			}
		
		if(hero.getRoomyX() == 3 && hero.getRoomyY() == 1 && oldman.y < Tile.SIZE * 14.5)
		{
			oldman.y += .005 * delta;
		}
		
		if(dungeon.getTile(hero.x, hero.y).isStairs)
		{
			nextLevel = true;
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException
	{
		this.dungeon.render(graphics, camera);
		this.hero.render(graphics, camera);
		this.oldman.render(graphics, camera);
		this.menu.render(graphics, camera);
		
		if(hero.isDead() == true)
		{
			graphics.setColor(textColor);
			menuBox.draw(Room.WIDTH/5, Room.HEIGHT/3);
			graphics.drawString("Restart (R)", Room.WIDTH/3, Room.HEIGHT/3 + 10);
			graphics.drawString("Main Menu (M)", Room.WIDTH/3, Room.HEIGHT/3 + 30);
			graphics.drawString("Quit Game (Q)", Room.WIDTH/3, Room.HEIGHT/3 + 50);
		}
		
		if(nextLevel)
		{
			graphics.setColor(textColor);
			menuBox.draw(Room.WIDTH/5, Room.HEIGHT/3);
			graphics.drawString("Congrats! 1st floor complete!", Room.WIDTH/3, Room.HEIGHT/3 + 5);
			graphics.drawString("Restart (R)", Room.WIDTH/3, Room.HEIGHT/3 + 20);
			graphics.drawString("Main Menu (M)", Room.WIDTH/3, Room.HEIGHT/3 + 35);
			graphics.drawString("Quit Game (Q)", Room.WIDTH/3, Room.HEIGHT/3 + 50);
		}
	}
	
	public int getID()
	{
		return MainGameState.ID;
	}
	
	public static final int ID = 0;
}