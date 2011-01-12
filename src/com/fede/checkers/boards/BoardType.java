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

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;


public abstract class BoardType {
    String mSavedDump;
    Date mSavedDate;
    Long maxScore;
    Long rowId;
    Boolean mLoaded = false;
    
    public abstract String getDump();
    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getImageResource();
    public abstract String getName();
    
    public void saveDump(String dump, Context ctx){
        CheckersDbHelper db = new CheckersDbHelper(ctx);
        db.open();
        Cursor saved = db.getSavedBoard(getName());
        if(saved.getCount() > 0){
            db.updateBoard(saved.getLong(0), 
                    getName(), dump, new Date(), Long.valueOf(getWidth()), 
                    Long.valueOf(getHeight()));
        }else{
            db.addBoard(getName(), dump, new Date(), Long.valueOf(getWidth()), 
                    Long.valueOf(getHeight()));
        }
        db.close();
        
    }   
    
    public String getSavedDump(){
        return mSavedDump;
    }
    
    public Date getSavedDate(){
        return mSavedDate;
    }
        
    public Boolean load(Context ctx){
        CheckersDbHelper db = new CheckersDbHelper(ctx);
        db.open();
        Cursor saved = db.getSavedBoard(getName());
        if(saved.getCount() > 0){
            rowId = saved.getLong(0);
            mSavedDump =  saved.getString(CheckersDbHelper.BOARD_DUMP_COLUMN);
            mLoaded = true;
            this.mSavedDate = new Date(saved.getInt(CheckersDbHelper.BOARD_SAVEDDATE_COLUMN)); 
            maxScore = saved.getLong(CheckersDbHelper.BOARD_SAVEDDATE_COLUMN); // TODO
            return true;
        }
        return false;       
    }
    
    public void delete(Context ctx){
        CheckersDbHelper db = new CheckersDbHelper(ctx);
        db.open();
        db.removeBoard(this.getName());
        db.close();
    }
    
    String getDumpFromMap(char[][] map) {
        StringBuffer res = new StringBuffer();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                res.append(map[i][j]);
            }
        }    
        return res.toString();
    }
    
    
    public int getWidthFromMap(char[][] map) {
        return map[0].length;   // Here I am assuming squared maps
    }
    
    public int getHeightFromMap(char[][] map) {
        return map.length;  
    }
    
    /** ADD HERE NEW BOARDS CREATION */
    public static BoardType getBoardFromName(String name){
        if(name.equals(BoardClassic.NAME)){
            return new BoardClassic();
        }
        if(name.equals(BoardSimple.NAME)){
            return new BoardSimple();
        }
        if(name.equals(BoardStar.NAME)){
            return new BoardStar();
        }
        return null;
    }
    
    public static ArrayList<BoardType> getAllBoards(){
        ArrayList<BoardType> res = new ArrayList<BoardType>();
        res.add(getBoardFromName(BoardClassic.NAME));
        res.add(getBoardFromName(BoardSimple.NAME));
        res.add(getBoardFromName(BoardStar.NAME));

        return res;
    }
}
