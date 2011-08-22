package be.kuleuven.cs.ogp.project.dungeons;

import be.kuleuven.cs.ogp.project.Dungeon;
import be.kuleuven.cs.ogp.project.Square;
import be.kuleuven.cs.ogp.project.tools.Point3D;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a composite dungeon which can contain other dungeons.
 *
 * @author Frederic Hannes
 */
public class CompositeDungeon<T extends Square> extends Dungeon<T> {

    private Map<Point3D, Dungeon> dungeons = new HashMap<>();

    /**
     * Returns the dungeon at the given position. Returns null if there's no dungeon at the given position.
     */
    public Dungeon getDungeon(Point3D pos) {
        if (!isValidPos(pos))
            return null;
        for (Dungeon d : dungeons.values())
            // Check whether the given coordinate falls inside of the boundaries of the dungeon
            if ((pos.getX() >= d.getPos().getX()) && (pos.getX() < d.getPos().getX() + d.getXDim()) &&
                    (pos.getY() >= d.getPos().getY()) && (pos.getY() < d.getPos().getY() + d.getYDim()) &&
                    (pos.getZ() >= d.getPos().getZ()) && (pos.getZ() < d.getPos().getZ() + d.getZDim()))
                return d;
        return null;
    }

}
