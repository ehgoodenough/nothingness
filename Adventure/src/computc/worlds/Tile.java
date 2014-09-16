package computc.worlds;

import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;

import computc.Camera;

public class Tile
{
	private Room room;
	
	private int tx;
	private int ty;
	
	public boolean isBlocked;
	public boolean isStairs;
	
	public Tile(Room room, int tx, int ty)
	{
		this.room = room;
		
		this.tx = tx;
		this.ty = ty;
	}
	
	public void render(Graphics graphics, Camera camera)
	{
		int x = this.getX() - camera.getX();
		int y = this.getY() - camera.getY();
		
		if(this.isBlocked)
		{
			Tile.WALL_IMAGE.draw(x, y);
		}
		else if(this.isStairs)
		{
			Tile.STAIR_IMAGE.draw(x,y);
			this.isBlocked = false;
		}
		else
		{
			Tile.FLOOR_IMAGE.draw(x, y);
			this.isBlocked = false;
		}
	}
	
	/*
	 * Returns the horizontal position
	 * of this tile in units of pixels
	 * and relative to the dungeon.
	 * 
	 * @units_of		pixels
	 * @relative_to		dungeon
	 */
	public int getX()
	{
		return this.getTileyX() * Tile.SIZE + this.room.getX();
	}
	
	/*
	 * Returns the vertical position
	 * of this tile in units of pixels
	 * and relative to the dungeon.
	 * 
	 * @units_of		pixels
	 * @relative_to		dungeon
	 */
	public int getY()
	{
		return this.getTileyY() * Tile.SIZE + this.room.getY();
	}
	
	/*
	 * Returns the horizontal position
	 * of this tile in units of tiles
	 * and relative to the room.
	 * 
	 * @units_of		tiles
	 * @relative_to		room
	 */
	public int getTileyX()
	{
		return this.tx;
	}
	
	/*
	 * Returns the vertical position
	 * of this tile in units of tiles
	 * and relative to the room.
	 * 
	 * @units_of		tiles
	 * @relative_to		room
	 */
	public int getTileyY()
	{
		return this.ty;
	}

	/*
	 * Returns the horizontal dimension
	 * of this tile in units of pixels.
	 * 
	 * @units_of		pixels
	 */
	public int getWidth()
	{
		return Tile.SIZE;
	}
	
	/*
	 * Returns the vertical dimension
	 * of this tile in units of pixels.
	 * 
	 * @units_of		pixels
	 */
	public int getHeight()
	{
		return Tile.SIZE;
	}
	
	public static Image WALL_IMAGE;
	public static Image FLOOR_IMAGE;
	public static Image STAIR_IMAGE;
	
	public final static int SIZE = 64;
}