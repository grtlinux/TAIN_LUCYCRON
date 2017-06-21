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
package websocket.tc7.snake;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

import org.apache.catalina.websocket.WsOutbound;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : Snake.java
 *   -. Package    : websocket.tc7.snake
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 21. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
@Deprecated
public class Snake {

    private static final int DEFAULT_LENGTH = 5;

    private final int id;
    private final WsOutbound outbound;

    private Direction direction;
    private int length = DEFAULT_LENGTH;
    private Location head;
    private Deque<Location> tail = new ArrayDeque<Location>();
    private String hexColor;

    public Snake(int id, WsOutbound outbound) {
        this.id = id;
        this.outbound = outbound;
        this.hexColor = SnakeWebSocketServlet.getRandomHexColor();
        resetState();
    }

    private void resetState() {
        this.direction = Direction.NONE;
        this.head = SnakeWebSocketServlet.getRandomLocation();
        this.tail.clear();
        this.length = DEFAULT_LENGTH;
    }

    private synchronized void kill() {
        resetState();
        try {
            CharBuffer response = CharBuffer.wrap("{'type': 'dead'}");
            outbound.writeTextMessage(response);
        } catch (IOException ioe) {
            // Ignore
        }
    }

    private synchronized void reward() {
        length++;
        try {
            CharBuffer response = CharBuffer.wrap("{'type': 'kill'}");
            outbound.writeTextMessage(response);
        } catch (IOException ioe) {
            // Ignore
        }
    }

    public synchronized void update(Collection<Snake> snakes) {
        Location nextLocation = head.getAdjacentLocation(direction);
        if (nextLocation.x >= SnakeWebSocketServlet.PLAYFIELD_WIDTH) {
            nextLocation.x = 0;
        }
        if (nextLocation.y >= SnakeWebSocketServlet.PLAYFIELD_HEIGHT) {
            nextLocation.y = 0;
        }
        if (nextLocation.x < 0) {
            nextLocation.x = SnakeWebSocketServlet.PLAYFIELD_WIDTH;
        }
        if (nextLocation.y < 0) {
            nextLocation.y = SnakeWebSocketServlet.PLAYFIELD_HEIGHT;
        }
        if (direction != Direction.NONE) {
            tail.addFirst(head);
            if (tail.size() > length) {
                tail.removeLast();
            }
            head = nextLocation;
        }

        handleCollisions(snakes);
    }

    private void handleCollisions(Collection<Snake> snakes) {
        for (Snake snake : snakes) {
            boolean headCollision = id != snake.id && snake.getHead().equals(head);
            boolean tailCollision = snake.getTail().contains(head);
            if (headCollision || tailCollision) {
                kill();
                if (id != snake.id) {
                    snake.reward();
                }
            }
        }
    }

    public synchronized Location getHead() {
        return head;
    }

    public synchronized Collection<Location> getTail() {
        return tail;
    }

    public synchronized void setDirection(Direction direction) {
        this.direction = direction;
    }

    public synchronized String getLocationsJson() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("{x: %d, y: %d}",
                Integer.valueOf(head.x), Integer.valueOf(head.y)));
        for (Location location : tail) {
            sb.append(',');
            sb.append(String.format("{x: %d, y: %d}",
                    Integer.valueOf(location.x), Integer.valueOf(location.y)));
        }
        return String.format("{'id':%d,'body':[%s]}",
                Integer.valueOf(id), sb.toString());
    }

    public int getId() {
        return id;
    }

    public String getHexColor() {
        return hexColor;
    }
}
