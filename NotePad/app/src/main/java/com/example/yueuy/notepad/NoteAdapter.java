package com.example.yueuy.notepad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by yueuy on 17-12-3.
 */

public class NoteAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Note> mNoteList;
    private Context mContext;
    private SqlDao dao;

    public NoteAdapter(Context context,List<Note> notesList){
        mInflater = LayoutInflater.from(context);
        mNoteList = notesList;
        mContext = context;
    }

    @Override
    public Object getItem(int position){
        return mNoteList.get(position);
    }
    @Override
    public int getCount(){
        return mNoteList.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView==null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item,parent,false);
            viewHolder.title =convertView.findViewById(R.id.tv_title);
            viewHolder.content = convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder =(ViewHolder) convertView.getTag();
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.title.setText(mNoteList.get(position).getTitle());
        viewHolder.content.setText(mNoteList.get(position).getContent());
        return convertView;
    }
    class ViewHolder{
        TextView title;
        TextView content;
    }

    public void delete(final int position){
        final String toBeDelete = mNoteList.get(position).getTitle();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("Delete");
        alertDialog.setMessage("Do you want to delete "+ toBeDelete + "?");
        alertDialog.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mNoteList.remove(position);
                dao.delete(position,toBeDelete);
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

}
