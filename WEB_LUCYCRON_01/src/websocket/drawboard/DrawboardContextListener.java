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
package websocket.drawboard;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : DrawboardContextListener.java
 *   -. Package    : websocket.drawboard
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 6. 21. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class DrawboardContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // NO-OP
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Shutdown our room.
        Room room = DrawboardEndpoint.getRoom(false);
        if (room != null) {
            room.shutdown();
        }
    }
}
