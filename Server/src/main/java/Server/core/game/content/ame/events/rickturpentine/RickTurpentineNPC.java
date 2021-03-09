package core.game.content.ame.events.rickturpentine;

import core.game.content.ame.AntiMacroEvent;
import core.game.content.ame.AntiMacroNPC;
import core.game.node.entity.Entity;
import core.game.node.entity.player.Player;
import core.game.system.task.Pulse;
import core.game.world.GameWorld;
import core.game.world.map.Location;

/**
 * Handles the drunken dwarf npc.
 * @author Vexia
 */
public final class RickTurpentineNPC extends AntiMacroNPC {

	/**
	 * The quotes that the dwarf will say.
	 */
	private static final String[] QUOTES = new String[] { "Greetings, @gender @name!", "Your money or your life, @name!" };

	/**
	 * Constructs a new {@code RickTurpentineNPC} {@code Object}.
	 * @param id the id.
	 * @param location the loc.
	 * @param event the event.
	 * @param player the player.
	 */
	public RickTurpentineNPC(int id, Location location, AntiMacroEvent event, Player player) {
		super(id, location, event, player, QUOTES);
	}

	@Override
	public void handleTickActions() {
		super.handleTickActions();
		if (timeUp && getDialoguePlayer() == null) {
			if (!getProperties().getCombatPulse().isAttacking()) {
				getProperties().getCombatPulse().attack(player);
			}
		}
	}

	@Override
	public void handlePlayerLeave() {
		sendChat("What a dismal little spot this is");
		GameWorld.getPulser().submit(new Pulse(3) {
			@Override
			public boolean pulse() {
				clear();
				return true;
			}
		});
	}

	@Override
	public void handleTimeUp() {
	}

	@Override
	public boolean isIgnoreAttackRestrictions(Entity victim) {
		return true;
	}

	@Override
	public boolean isIgnoreMultiBoundaries(Entity victim) {
		return victim == player;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2476 };
	}

}
