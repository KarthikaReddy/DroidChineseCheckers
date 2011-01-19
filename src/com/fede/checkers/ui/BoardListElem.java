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
package com.fede.checkers.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.fede.checkers.R;
import com.fede.checkers.boards.BoardType;


public class BoardListElem extends TableLayout {
    private TextView mName;
    private TextView mSaved;
    private TextView mMaxScore;
    private ImageView mBoardImg;
    
    

    public BoardListElem(Context context, BoardType board) {
        super(context);
        
        
        String infService = Context.LAYOUT_INFLATER_SERVICE; 
        LayoutInflater li; 
        li = (LayoutInflater)getContext().getSystemService(infService); 
        li.inflate(R.layout.board_list_elem, this, true);
        
        
                
        mName = (TextView) findViewById(R.id.board_elem_name);
        mSaved = (TextView) findViewById(R.id.exists_saved);
        mMaxScore = (TextView) findViewById(R.id.max_score);
        mBoardImg = (ImageView) findViewById(R.id.BoardIcon);
        
        setFromBoard(board);
    }
    
    
    /** 
     * Sets current element from board
     * @param board
     */
    public void setFromBoard(BoardType board){
        mName.setText(board.getName());
        Boolean existsSaved = board.load(getContext());
        mSaved.setText(existsSaved? "Saved" : "Not Saved");
        
        mMaxScore.setText(String.valueOf(board.getMaxScore()));
        mBoardImg.setImageResource(board.getImageResource());        
    }

}
