/**
 * Copyright 2014, 2015, 2016, 2017 TAIN, Inc. all rights reserved.
 *
 * Licensed under the GNU GENERAL PUBLIC LICENSE, Version 3, 29 June 2007 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * -----------------------------------------------------------------
 * Copyright 2014, 2015, 2016, 2017 TAIN, Inc.
 *
 */
package websocket.snake;


/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : Location.java
 *   -. Package    : websocket.snake
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 21. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class Location {

    public int x;
    public int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Location getAdjacentLocation(Direction direction) {
        switch (direction) {
            case NORTH:
                return new Location(x, y - SnakeAnnotation.GRID_SIZE);
            case SOUTH:
                return new Location(x, y + SnakeAnnotation.GRID_SIZE);
            case EAST:
                return new Location(x + SnakeAnnotation.GRID_SIZE, y);
            case WEST:
                return new Location(x - SnakeAnnotation.GRID_SIZE, y);
            case NONE:
                // fall through
            default:
                return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (x != location.x) return false;
        if (y != location.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
