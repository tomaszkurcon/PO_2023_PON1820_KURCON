package agh.ics.oop.model;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    @Override
    public String toString() {
        return switch (this) {
            case NORTH -> "Północ";
            case EAST -> "Wschód";
            case WEST -> "Zachód";
            case SOUTH -> "Południe";
        };
    }

    public MapDirection next() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case WEST -> NORTH;
            case SOUTH -> WEST;
        };
    }

    public MapDirection previous() {
        return switch (this) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
        };
    }

    public Vector2d toUnitVector() {
        int[] values = new int[2];
        switch (this) {
            case NORTH:
                values[0] = 0;
                values[1] = 1;
                break;
            case EAST:
                values[0] = 1;
                values[1] = 0;
                break;
            case WEST:
                values[0] = -1;
                values[1] = 0;
                break;
            case SOUTH:
                values[0] = 0;
                values[1] = -1;
                break;
        }
        ;
        return new Vector2d(values[0], values[1]);
    }

}


