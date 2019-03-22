package com.leebethanacona.expandablerecyclerview.components;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leebethanacona.expandablerecyclerview.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class MyChildViewHolder extends ChildViewHolder {
    public TextView title, subtitle;
    public ImageView image_logo, image_more;
    public ConstraintLayout cell1, cell2;

    public MyChildViewHolder(View view) {
        super(view);
        /**
        title = view.findViewById(R.id.text_title);
        subtitle = view.findViewById(R.id.text_subtitle);
        image_logo = view.findViewById(R.id.image_logo);
        //image_more = view.findViewById(R.id.image_more);
        constraint_main = view.findViewById(R.id.constraint_main);**/

        cell1 = view.findViewById(R.id.cell_1);
        cell2 = view.findViewById(R.id.cell_2);
    }
}
