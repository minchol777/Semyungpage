package com.example.semyungpage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.semyungpage.DTO.PostDTO;
import com.example.semyungpage.DTO.PostTitleResponseDTO;
import com.example.semyungpage.R;

import java.util.List;

public class CustomArrayAdapter2 extends ArrayAdapter<PostDTO> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<PostDTO> items;
    private final int mResource;

    public CustomArrayAdapter2(@NonNull Context context, int resource,
                               @NonNull List<PostDTO> objects) {
        super(context, resource, 0, objects);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(mResource, parent, false);
        }

        PostDTO item = items.get(position);

        TextView title = (TextView) view.findViewById(R.id.boardTitle);
        title.setText(item.getTitle());

        return view;
    }
}
