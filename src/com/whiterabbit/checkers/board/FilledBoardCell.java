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
package com.whiterabbit.checkers.board;


import com.whiterabbit.checkers.exceptions.CantFillException;
import com.whiterabbit.checkers.ui.BallSprite;
import com.whiterabbit.checkers.ui.CheckersSpriteFactory;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;


public class FilledBoardCell extends BoardCell{
    
    public FilledBoardCell(int xPar, int yPar) {
        super(xPar, yPar);
    }

    @Override
    public void putBall() throws CantFillException{
        throw new CantFillException("Filled cell");
    }

    @Override
    public void getBall() throws CantFillException {
        throw new CantFillException("Filled cell");         
    }



    @Override
    public BallSprite getBallSprite() {
        return null;
    }



    @Override
    public void eraseBallSprite() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setBallSprite(Sprite b) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public Boolean fillableBall(){
        return false;
    }

    @Override
    public char getEncoding() {
        return '0';
    }

    @Override
    public void buildSprites(CheckersSpriteFactory f, Scene s, AndEngineBoard b, float size) {
        // TODO Auto-generated method stub
        
    }
    
}
