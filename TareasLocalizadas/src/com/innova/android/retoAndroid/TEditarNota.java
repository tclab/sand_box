/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.innova.android.retoAndroid;

import com.innova.android.retoAndroid.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TEditarNota extends Activity {
	
	private static final int MAPA_EDIT = 1;

	private EditText mTitleText;
    private EditText mBodyText;
    private Long mRowId;
    private TNotasDAO mDbHelper;
    private Double latitud;
    private Double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new TNotasDAO(this);
        mDbHelper.open();
        setContentView(R.layout.note_edit);
        
       
        mTitleText = (EditText) findViewById(R.id.title);
        mBodyText = (EditText) findViewById(R.id.body);
      
        Button guardarButton = (Button) findViewById(R.id.guardar);
        Button mapaButton = (Button) findViewById(R.id.mapaButton);
       
        mRowId = savedInstanceState != null ? savedInstanceState.getLong(TNotasDAO.KEY_ROWID) 
                							: null;
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();            
			mRowId = extras != null ? extras.getLong(TNotasDAO.KEY_ROWID) 
									: null;
		}

		populateFields();
		
        guardarButton.setOnClickListener(new View.OnClickListener() {

        	public void onClick(View view) {
        	    setResult(RESULT_OK);
        	    finish();
        	}
          
        });
        
        mapaButton.setOnClickListener(new View.OnClickListener(){
        	
			public void onClick(View view) {
				showMap();
			}
        });
    }
    
    /**
     * Llena la vista con los datos de la nota (titulo y texto)
     */
    private void populateFields() {
        try {
			if (mRowId != null) {
				Cursor note = mDbHelper.fetchNote(mRowId);
				startManagingCursor(note);

				mTitleText.setText(note.getString(note
						.getColumnIndexOrThrow(TNotasDAO.KEY_TITLE)));
				mBodyText.setText(note.getString(note
						.getColumnIndexOrThrow(TNotasDAO.KEY_BODY)));
				latitud = Double.parseDouble(note.getString(note
						.getColumnIndexOrThrow(TNotasDAO.KEY_LAT)));
				longitud = Double.parseDouble(note.getString(note
						.getColumnIndexOrThrow(TNotasDAO.KEY_LNG)));
				System.out.println("baba");
			}
		} catch (Exception e) {
		}
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        try {
			super.onSaveInstanceState(outState);
			outState.putLong(TNotasDAO.KEY_ROWID, mRowId);
		} catch (Exception e) {
		}
    }
    
    @Override
    protected void onPause() {
        try {
			super.onPause();
			saveState();
		} catch (Exception e) {
		}
    }
    
    @Override
    protected void onResume() {
        try {
			super.onResume();
			populateFields();
		} catch (Exception e) {
		}
    }
    
    private void saveState() {
        try {
			String title = mTitleText.getText().toString();
			String body = mBodyText.getText().toString();
			
			if (mRowId == null) {
				long id = mDbHelper.createNote(title, body,latitud.toString(),longitud.toString());
				if (id > 0) {
					mRowId = id;
				}
			} else {
				mDbHelper.updateNote(mRowId, title, body,latitud.toString(),longitud.toString());
			}
		} catch (Exception e) {
		}
		
		
    }
    
    /**
     * Muestra el mapa asociado a la nota
     */
    private void showMap(){
    	try {
			Intent intent = new Intent(this, TMapa.class);
			
			intent.putExtra(TNotasDAO.KEY_LAT, latitud);
			intent.putExtra(TNotasDAO.KEY_LNG, longitud);
			
			startActivityForResult(intent, MAPA_EDIT);
		} catch (Exception e) {
		}
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {

		try {
			super.onActivityResult(requestCode, resultCode, intent);
			Bundle extras = intent.getExtras();
			
			switch (requestCode) {
			case MAPA_EDIT:
				if (extras != null) {
					latitud = extras.getDouble(TNotasDAO.KEY_LAT);
					longitud = extras.getDouble(TNotasDAO.KEY_LNG);
					System.out.println("baba");
				}
				break;
			}
		} catch (Exception e) {
		}
	}
    
}
