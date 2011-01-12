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
package com.fede.checkers.board;

import com.fede.checkers.ui.CheckersSpriteFactory;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;




public abstract class BoardCell{
    int x, y;
    
    public BoardCell(int x_par, int y_par){
        x = x_par;
        y = y_par;
    }
    
    public class CantFillException extends Exception{
        public CantFillException(String error){
            super(error);
        }
    }
    
    public abstract void putBall() throws CantFillException;
    public abstract void getBall() throws CantFillException;
    
    public abstract Sprite getBallSprite();
    public abstract void eraseBallSprite();
    public abstract void setBallSprite(Sprite b);
    public abstract void buildSprites(CheckersSpriteFactory f, Scene s, AndEngineBoard b);
    public abstract char getEncoding();
    
    public Boolean hasBall(){
        return false;
    }

    public Boolean fillableBall(){
        return false;
    }
}
    
