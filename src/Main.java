import be.kuleuven.cs.ogp.project.Direction;
import be.kuleuven.cs.ogp.project.Dungeon;
import be.kuleuven.cs.ogp.project.Square;
import be.kuleuven.cs.ogp.project.borders.Door;
import be.kuleuven.cs.ogp.project.borders.Wall;
import be.kuleuven.cs.ogp.project.squares.Rock;
import be.kuleuven.cs.ogp.project.tools.Point3D;

public class Main {

    public static void main(String[] args) {
        Dungeon<Square> d = new Dungeon<>();
        // Square 1
        Square sq1 = new Rock();
        d.addSquare(sq1, new Point3D(1, 6, 3));
        sq1.setBorder(new Wall(false), Direction.NORTH);
        // Square 2
        Square sq2 = new Square(0, 30);
        Point3D pos = Direction.NORTH.move(new Point3D(1, 6, 3));
        d.addSquare(sq2, pos);
        // Square 3
        Square sq3 = new Square(0, 20);
        d.addSquare(sq3, Direction.SOUTH.move(new Point3D(1, 6, 3)));

        System.out.println(sq2);
        System.out.println(sq1);
        System.out.println(sq3);
    }

}
