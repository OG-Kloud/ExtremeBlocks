package main.extremeblocks.client.containers;

import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerRobotCommands extends Container
{
	private final EntityRobot robot;

	public ContainerRobotCommands(EntityRobot robot)
	{
		this.robot = robot;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return player.getDistanceToEntity(robot) <= 8.0D;
	}
}
