package com.leebethanacona.expandablerecyclerview.components;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leebethanacona.expandablerecyclerview.R;
import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class MyGroupViewHolder extends GroupViewHolder {

    private Context context;
    private TextView categoryTitle;
    private ImageView ivArrowHeader;

    public MyGroupViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        categoryTitle = itemView.findViewById(R.id.tvTittleHeader);
        ivArrowHeader = itemView.findViewById(R.id.ivArrowHeader);
    }


    @Override
    public void expand() {
        super.expand();
        ivArrowHeader.setImageDrawable(context.getResources().getDrawable(R.drawable.btn_menos_producto));
    }

    @Override
    public void collapse() {
        super.collapse();
        ivArrowHeader.setImageDrawable(context.getResources().getDrawable(R.drawable.btn_mas_producto));
    }

    public void setIndicatorExpandCollapse(boolean expand) {
        if (!expand)
            ivArrowHeader.setImageDrawable(context.getResources().getDrawable(R.drawable.btn_mas_producto));
        else
            ivArrowHeader.setImageDrawable(context.getResources().getDrawable(R.drawable.btn_menos_producto));

    }

    public void setCategoryTitle(String name) {
        categoryTitle.setText(name);
    }
}
