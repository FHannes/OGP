package be.kuleuven.cs.ogp.project;

import be.kuleuven.cs.ogp.project.dungeons.Level;
import be.kuleuven.cs.ogp.project.dungeons.Shaft;
import be.kuleuven.cs.ogp.project.tools.Point3D;
import be.kuleuven.cs.som.annotate.Basic;

import java.util.*;

/**
 * This class represents a composite dungeon which can contain other dungeons.
 *
 * @author Frederic Hannes
 */
public class CompositeDungeon<T extends Square> extends Dungeon<T> {

    private Map<Point3D, Dungeon> dungeons = new HashMap<>();

    /**
     * Returns the map containing the dungeons.
     */
    @Basic
    private Map<Point3D, Dungeon> getDungeons() {
        return dungeons;
    }

    /**
     * Returns the dungeon at the given position. Returns null if there's no dungeon at the given position. This
     * function iterates recursively through all sub dungeons.
     *
     * @param   pos
     *          The given position.
     */
    public Dungeon getDungeonAt(Point3D pos) {
        if (!isValidPos(pos))
            return null;
        for (Dungeon d : getDungeons().values()) {
            if (d instanceof CompositeDungeon) {
                Dungeon sub = ((CompositeDungeon) d).getDungeonAt(pos);
                if (sub != null)
                    return sub;
            }
            // Check whether the given coordinate falls inside of the boundaries of the dungeon
            if ((pos.getX() >= d.getPos().getX()) && (pos.getX() < d.getPos().getX() + d.getXDim()) &&
                    (pos.getY() >= d.getPos().getY()) && (pos.getY() < d.getPos().getY() + d.getYDim()) &&
                    (pos.getZ() >= d.getPos().getZ()) && (pos.getZ() < d.getPos().getZ() + d.getZDim()))
                return d;
        }
        return null;
    }

    /**
     * Adds a dungeon to this dungeon. Checks if a dungeon is already present at the given position.
     *
     * @param   pos
     *          The given position.
     * @param   dungeon
     *          The given dungeon.
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if one of the arguments is invalid.
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the given dungeon already has a parent dungeon.
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if a dungeon is already present at the given position.
     */
    // TODO: Check overlap
    public void addDungeon(Point3D pos, Dungeon dungeon) throws IllegalArgumentException {
        if ((pos == null) || (dungeon == null))
            throw new IllegalArgumentException("Invalid position or dungeon!");
        if (dungeon.getDungeon() != null)
            throw new IllegalArgumentException("Dungeon already assigned!");
        if (getDungeonAt(pos) != null)
            throw new IllegalArgumentException("Dungeon already present at the given position!");
        getDungeons().put(pos, dungeon);
        dungeon.setDungeon(this);
        dungeon.setPos((Point3D) pos.clone());
    }

    /**
     * Internal method to generate a recursive list of sub dungeons of the type level or shaft.
     *
     * @param   dungeons
     *          The given list to add the results to.
     */
    private void getLevelsAndShafts(List<Dungeon> dungeons) {
        for (Dungeon d : getDungeons().values())
            if ((d instanceof Level) || (d instanceof Shaft))
                dungeons.add(d);
            else if (d instanceof CompositeDungeon)
                ((CompositeDungeon) d).getLevelsAndShafts(dungeons);
    }

    /**
     * Returns a list containing all sub dungeons of the type level or shaft recursively.
     */
    public List<Dungeon> getLevelsAndShafts() {
        List<Dungeon> res = new ArrayList<>();
        getLevelsAndShafts(res);
        return res;
    }

}
