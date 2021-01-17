package com.sokah.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FolderAdapter extends BaseAdapter {

    ArrayList<Folder> folders;

    public FolderAdapter(){
        folders= new ArrayList<>();
    }

    public void AddFolder(Folder folder){

        folders.add(folder);
        notifyDataSetChanged();
    }

    public void clear(){

        folders.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return folders.size();
    }

    @Override
    public Object getItem(int position) {
        return folders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View cardFolder = layoutInflater.inflate(R.layout.card_folder,null);
        TextView name = cardFolder.findViewById(R.id.nameFolder);
        TextView numbre = cardFolder.findViewById(R.id.numberFolder);
        name.setText(folders.get(position).getName());

        return cardFolder;
    }
}
