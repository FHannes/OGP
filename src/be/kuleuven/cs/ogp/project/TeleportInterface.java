package be.kuleuven.cs.ogp.project;

import java.util.List;

/**
 * This interface defines the methods required for teleportation squares.
 *
 * @author  Frederic Hannes
 */
public interface TeleportInterface {

    /**
     *
     * @param   square
     *          The given square.
     * @pre     The given square must be valid, not solid, the current square must be in a dungeon and the given square
     *          in the same one.
     *          | (square != null) && (!square.isSolid()) && (this.getDungeonAt() != null) &&
     *          |   this.getDungeonAt().equals(square.getDungeonAt()))
     * @post    The given square is now part of the destination squares.
     *          | getDest().contains(square) == true
     */
    public void addDest(Square square);

    /**
     * Returns a random destination square to teleport to.
     *
     * @return  If there's no destination squares, the method returns null.
     *          | if (getDest().size() == 0)
     *          |   result == null
     * @return  If there's one or more destination squares, the method returns a random one.
     *          | if (getDest().size() != 0)
     *          |   result == getDest().get(Tools.random(getDest().size()))
     */
    public Square teleport();

    /**
     * Returns the list containing all destination squares.
     */
    public List<Square> getDest();

}
