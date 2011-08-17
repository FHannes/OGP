package be.kuleuven.cs.ogp.project;

import be.kuleuven.cs.ogp.project.tools.Point3D;

import java.security.PublicKey;

/**
 * This enumeration specifies a 3 dimensional direction in a confined space.
 *
 * @author  Frederic Hannes
 */
public enum Direction {

    NORTH {
        /**
         * Returns the opposite direction.
         */
        @Override
        public Direction opposite() {
            return SOUTH;
        }

        /**
         * Moves the point into the given direction and returns the moved point.
         *
         * @param   pos
         *          The given point.
         * @return  The new moved point.
         */
        @Override
        public Point3D move(Point3D pos) {
            if (pos == null)
                return null;
            Point3D res = (Point3D) pos.clone();
            res.setY(res.getY() + 1);
            return res;
        }
    },
    EAST {
        /**
         * Returns the opposite direction.
         */
        @Override
        public Direction opposite() {
            return WEST;
        }

        /**
         * Moves the point into the given direction and returns the moved point.
         *
         * @param   pos
         *          The given point.
         * @return  The new moved point.
         */
        @Override
        public Point3D move(Point3D pos) {
            if (pos == null)
                return null;
            Point3D res = (Point3D) pos.clone();
            res.setX(res.getX() + 1);
            return res;
        }
    },
    SOUTH {
        /**
         * Returns the opposite direction.
         */
        @Override
        public Direction opposite() {
            return NORTH;
        }

        /**
         * Moves the point into the given direction and returns the moved point.
         *
         * @param   pos
         *          The given point.
         * @return  The new moved point.
         */
        @Override
        public Point3D move(Point3D pos) {
            if (pos == null)
                return null;
            Point3D res = (Point3D) pos.clone();
            res.setY(res.getY() - 1);
            return res;
        }
    },
    WEST {
        /**
         * Returns the opposite direction.
         */
        @Override
        public Direction opposite() {
            return EAST;
        }

        /**
         * Moves the point into the given direction and returns the moved point.
         *
         * @param   pos
         *          The given point.
         * @return  The new moved point.
         */
        @Override
        public Point3D move(Point3D pos) {
            if (pos == null)
                return null;
            Point3D res = (Point3D) pos.clone();
            res.setX(res.getX() - 1);
            return res;
        }
    },
    CEILING {
        /**
         * Returns the opposite direction.
         */
        @Override
        public Direction opposite() {
            return FLOOR;
        }

        /**
         * Moves the point into the given direction and returns the moved point.
         *
         * @param   pos
         *          The given point.
         * @return  The new moved point.
         */
        @Override
        public Point3D move(Point3D pos) {
            if (pos == null)
                return null;
            Point3D res = (Point3D) pos.clone();
            res.setZ(res.getZ() + 1);
            return res;
        }
    },
    FLOOR {
        /**
         * Returns the opposite direction.
         */
        @Override
        public Direction opposite() {
            return CEILING;
        }

        /**
         * Moves the point into the given direction and returns the moved point.
         *
         * @param   pos
         *          The given point.
         * @return  The new moved point.
         */
        @Override
        public Point3D move(Point3D pos) {
            if (pos == null)
                return null;
            Point3D res = (Point3D) pos.clone();
            res.setZ(res.getZ() - 1);
            return res;
        }
    };

    /**
     * Moves the point into the given direction and returns the moved point.
     *
     * @param   pos
     *          The given point.
     * @return  The new moved point.
     */
    public abstract Point3D move(Point3D pos);

    /**
     * Returns the opposite direction.
     */
    public abstract Direction opposite();

}
