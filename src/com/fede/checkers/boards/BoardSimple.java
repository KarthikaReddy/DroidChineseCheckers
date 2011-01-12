/*******************************************************************************
 * Copyright 2011 Federico Paolinelli
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.fede.checkers.boards;

import android.R;



public class BoardSimple extends BoardType {
    public final static String NAME = "Simple";
    
    
    private final char[][] map = {   {'0', '1', '1', '1','0',},
            {'0', '1', '1', '1','0',},
            {'0', '1', '1', '1','0',},
            {'0', '1', 'X', '1','0',},
            {'0', '1', '1', '1','0'}};


    @Override
    public String getDump() {
        return getDumpFromMap(map);
    }

    @Override
    public int getHeight() {
        return getHeightFromMap(map);        
    }



    @Override
    public String getName() {
        return NAME;
    }



    @Override
    public int getWidth() {
        return getWidthFromMap(map);
    }

    @Override
    public int getImageResource() {
        return R.drawable.arrow_up_float;
    }

}
