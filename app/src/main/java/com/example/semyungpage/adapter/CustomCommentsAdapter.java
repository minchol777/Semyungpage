package com.example.semyungpage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.semyungpage.DTO.CommentsDTO;
import com.example.semyungpage.R;

import java.util.List;

public class CustomCommentsAdapter extends ArrayAdapter<CommentsDTO> {
private final Context context;
private final List<CommentsDTO> comments;

public CustomCommentsAdapter(Context context, int resource, List<CommentsDTO> comments) {
        super(context, resource, comments);
        this.context = context;
        this.comments = comments;
        }

@NonNull
@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.borad_view, parent, false);
        TextView mainTextView = (TextView) rowView.findViewById(R.id.boardTitle);

        CommentsDTO comment = comments.get(position);
        mainTextView.setText(comment.getMain());

        return rowView;
        }
        }