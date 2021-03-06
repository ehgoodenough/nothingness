package computc.worlds.dungeons;

import org.newdawn.slick.SlickException;

import computc.Direction;
import computc.GameData;
import computc.entities.Ladder;
import computc.states.ToNextLevelGameState;
import computc.worlds.rooms.Room;

public class OneRoomDungeon extends Dungeon
{
	public OneRoomDungeon(GameData gamedata, String tileset)
	{
		super(gamedata, tileset);
		
		int tilesetid = 0;
		
		this.firstRoom = new Room(this, 2, 2);
		this.firstRoom.setTileSet(this.getTileSet(tilesetid));
		this.firstRoom.setRoomLayout(this.getSpecialRoomLayout("first room"));
		this.firstRoom.critdir = Direction.SOUTH;
		
		this.lastRoom = new Room(this, 2, 3);
		this.lastRoom.setTileSet(this.getTileSet(tilesetid));
		this.lastRoom.setRoomLayout(this.getSpecialRoomLayout("last room"));
		
		if(ToNextLevelGameState.transitionRoom)
		{
			this.lastRoom.setRoomLayout(this.getSpecialRoomLayout("transition room"));
		}
		
		
		this.firstRoom.makeDoor(Direction.SOUTH, true);
		this.firstRoom.getCritDoor().lock();
		
		this.ladder = new Ladder(this, this.lastRoom, 5, 4);
	}
	
	public void initiate()
	{
		super.initiate();
		
		Room last_room = this.firstRoom;
		Direction last_room_critdir = last_room.critdir;
		
		if(!ToNextLevelGameState.transitionRoom)
		{
			if(last_room_critdir == Direction.NORTH)
			{
				last_room.northDoorTile.lock();
			}
			else if(last_room_critdir == Direction.SOUTH)
			{
				last_room.southDoorTile.lock();
			}
			else if(last_room_critdir == Direction.EAST)
			{
				last_room.eastDoorTile.lock();
			}
			else if(last_room_critdir == Direction.WEST)
			{
				last_room.westDoorTile.lock();
			}
		}
	}
}