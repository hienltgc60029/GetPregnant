package vn.theagency.helper;

import java.util.ArrayList;

import vn.theagency.objects.Songs;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;

public class GetSongsAll {
	/*final String MEDIA_PATH = Environment.getExternalStorageDirectory()
	        .getPath() + "/";
	private ArrayList<Songs> songsList = new ArrayList<Songs>();
	private String mp3Pattern = ".mp3";
	
	
	public ArrayList<Songs> getPlayList() {
	    
	    if (MEDIA_PATH != null) {
	        File home = new File(MEDIA_PATH);
	        File[] listFiles = home.listFiles();
	        if (listFiles != null && listFiles.length > 0) {
	            for (File file : listFiles) {
	                System.out.println(file.getAbsolutePath());
	                if (file.isDirectory()) {
	                    scanDirectory(file);
	                } else {
	                    addSongToList(file);
	                }
	            }
	        }
	    }
	    // return songs list array
	    return songsList;
	}

	private void scanDirectory(File directory) {
	    if (directory != null) {
	        File[] listFiles = directory.listFiles();
	        if (listFiles != null && listFiles.length > 0) {
	            for (File file : listFiles) {
	                if (file.isDirectory()) {
	                    scanDirectory(file);
	                } else {
	                    addSongToList(file);
	                }

	            }
	        }
	    }
	}

	private void addSongToList(File song) {
	    if (song.getName().endsWith(mp3Pattern)) {
	        Songs songMap = new Songs(song.getName().substring(0, (song.getName().length() - 4))
	        		,"", song.getPath());       
	        songsList.add(songMap);
	    }
	}*/
	private String mp3Pattern = ".mp3";
	
	private static final Uri Content_URI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	
	public ArrayList<Songs> getPlayList(Context context){
		ArrayList<Songs> list = new ArrayList<Songs>();
		Songs songs;
		Cursor cursor = context.getContentResolver().query(Content_URI, null, null, null, null);
		
		if (cursor!=null) {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				String path = cursor.getString(cursor.getColumnIndex(Media.DATA));
				String name = cursor.getString(cursor.getColumnIndex(Media.DISPLAY_NAME));
				if(path.length()>0){
					if(path.endsWith(mp3Pattern)){
						songs = new Songs(name, "", path);
						list.add(songs);
					}
				}else{
					Log.i("LTH", "path error");
				}
				
				
				
				cursor.moveToNext();
				
			}
			cursor =null;
		}
		return list;
	}
}
