import be.kuleuven.cs.ogp.project.Direction;
import be.kuleuven.cs.ogp.project.Dungeon;
import be.kuleuven.cs.ogp.project.Square;
import be.kuleuven.cs.ogp.project.borders.Door;
import be.kuleuven.cs.ogp.project.borders.Wall;
import be.kuleuven.cs.ogp.project.tools.Point3D;

public class Main {

    public static void main(String[] args) {
        Dungeon d = new Dungeon();
        Square sq1 = new Square();
        d.addSquare(sq1, new Point3D(1, 6, 3));
        sq1.setBorder(new Wall(false), Direction.NORTH);
        Square sq2 = new Square();
        Point3D pos = Direction.NORTH.move(new Point3D(1, 6, 3));
        d.addSquare(sq2, pos);

        sq2 = d.getSquare(pos);

        System.out.println(d.getSpace(sq1.getPos()).size());

        sq1.setBorder(new Door(true), Direction.NORTH);

        System.out.println(d.getSpace(sq1.getPos()).size());

        System.out.println(sq2);
        System.out.println(sq1.getBorder(Direction.NORTH).toString());
        System.out.println(sq2.getBorder(Direction.NORTH.opposite()).toString());
    }

}
