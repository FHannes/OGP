import be.kuleuven.cs.ogp.project.Direction;
import be.kuleuven.cs.ogp.project.Dungeon;
import be.kuleuven.cs.ogp.project.Square;
import be.kuleuven.cs.ogp.project.borders.Wall;
import be.kuleuven.cs.ogp.project.tools.Point3D;

public class Main {

    public static void main(String[] args) {
        Dungeon d = new Dungeon();
        Square sq = new Square();
        d.addSquare(sq, new Point3D(1, 6, 3));
        sq.setBorder(new Wall(false), Direction.NORTH);
        sq = new Square();
        Point3D pos = Direction.NORTH.move(new Point3D(1, 6, 3));
        d.addSquare(sq, pos);

        sq = d.getSquare(pos);

        System.out.println(sq);
        System.out.println(sq.getBorder(Direction.NORTH.opposite()).toString());
    }

}
