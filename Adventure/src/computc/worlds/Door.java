package computc.worlds;

import computc.Game;
import computc.worlds.rooms.Room;
import computc.worlds.dungeons.DungeonException;

public class Door
{
	private float tx, ty;
	
	public Door(Room alphaRoom, Room omegaRoom)
	{
		int drx = Math.abs(alphaRoom.getRoomyX() - omegaRoom.getRoomyX());
		int dry = Math.abs(alphaRoom.getRoomyY() - omegaRoom.getRoomyY());
		
		if(drx + dry != 1)
		{
			//the rooms must not be same.
			//the rooms must be orthogonal.
			//the rooms must be adjacent.
			throw new DungeonException();
		}
		
		if(alphaRoom.getRoomyY() > omegaRoom.getRoomyY())
		{
			this.ty = alphaRoom.getTileyY() - 0.5f;
			this.tx = alphaRoom.getTileyX() + alphaRoom.getRandomTileyY();
		}
		else if(alphaRoom.getRoomyY() < omegaRoom.getRoomyY())
		{
			this.ty = alphaRoom.getTileyY() + alphaRoom.getTileyHeight() - 1 + 0.5f;
			this.tx = alphaRoom.getTileyX() + alphaRoom.getRandomTileyY();
		}
		else if(alphaRoom.getRoomyX() < omegaRoom.getRoomyX())
		{
			this.tx = alphaRoom.getTileyX() + alphaRoom.getTileyWidth() - 1 + 0.5f;
			this.ty = alphaRoom.getTileyY() + alphaRoom.getRandomTileyY();
		}
		else if(alphaRoom.getRoomyX() > omegaRoom.getRoomyX())
		{
			this.tx = alphaRoom.getTileyX() - 0.5f;
			this.ty = alphaRoom.getTileyY() + alphaRoom.getRandomTileyY();
		}
		
		alphaRoom.addDoor(this);
		omegaRoom.addDoor(this);
	}
	
	public float getTileyX()
	{
		return this.tx;
	}
	
	public float getTileyY()
	{
		return this.ty;
	}
}