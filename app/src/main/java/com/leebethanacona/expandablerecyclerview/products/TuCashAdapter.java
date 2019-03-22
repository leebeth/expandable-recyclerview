package com.leebethanacona.expandablerecyclerview.products;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leebethanacona.expandablerecyclerview.R;
import com.leebethanacona.expandablerecyclerview.components.MyChildViewHolder;
import com.leebethanacona.expandablerecyclerview.components.MyExpandableGroup;
import com.leebethanacona.expandablerecyclerview.components.MyGroupViewHolder;
import com.leebethanacona.expandablerecyclerview.products.model.Product;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;

import java.util.ArrayList;
import java.util.List;

public class TuCashAdapter extends ExpandableRecyclerViewAdapter<MyGroupViewHolder, MyChildViewHolder> {

    private final FragmentProductView fragmentProductView;
    private List<MyExpandableGroup> groups;
    private ExpandableGroup lastItemExpanded;
    private int lastIndexExpanded;
    private boolean toggleGroup;

    public TuCashAdapter(List<? extends ExpandableGroup> groups, FragmentProductView fragmentProductView) {
        super(groups);
        this.fragmentProductView = fragmentProductView;
        this.groups = ( List<MyExpandableGroup> ) groups;
    }


    @Override
    public MyGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expandable_header_row, parent, false);

        return new MyGroupViewHolder(view);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expandable_item_row, parent, false);

        return new MyChildViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        lastItemExpanded = group;
        final Product[] products = ( Product[] ) group.getItems().get(childIndex);

        TextView name = holder.cell1.findViewById(R.id.productNameLeftTextView);
        name.setText(products[0].getName());
        holder.cell1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentProductView.itemSelected(products[0]);
            }
        });

        if (products[1] != null) {
            holder.cell2.setVisibility(View.VISIBLE);
            TextView nameProduct2 = holder.cell2.findViewById(R.id.productNameRightTextView);
            nameProduct2.setText(products[1].getName());
            holder.cell2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragmentProductView.itemSelected(products[1]);
                }
            });
        } else
            holder.cell2.setVisibility(View.GONE);

    }

    @Override
    public void onBindGroupViewHolder(MyGroupViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setCategoryTitle(group.getTitle());
        if (isGroupExpanded(group)) {
            holder.setIndicatorExpandCollapse(true);
        } else
            holder.setIndicatorExpandCollapse(false);
    }

    public void setLastIndexExpanded(int lastIndexExpanded) {
        this.lastIndexExpanded = lastIndexExpanded;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExpandableListPosition listPos = expandableList.getUnflattenedPosition(position);
        ExpandableGroup group = expandableList.getExpandableGroup(listPos);
        switch (listPos.type) {
            case ExpandableListPosition.GROUP:
                onBindGroupViewHolder(( MyGroupViewHolder ) holder, position, group);
                break;
            case ExpandableListPosition.CHILD:
                onBindChildViewHolder(( MyChildViewHolder ) holder, position, group, listPos.childPos);
                break;
        }
    }

    @Override
    public void onGroupExpanded(int positionStart, int itemCount) {
        if (itemCount > 0) {
            int groupIndex = expandableList.getUnflattenedPosition(positionStart).groupPos;
            notifyItemRangeInserted(positionStart, itemCount);
            for (ExpandableGroup grp : groups) {
                if (grp != groups.get(groupIndex)) {
                    if (this.isGroupExpanded(grp)) {
                        this.toggleGroup(grp);
                        fragmentProductView.refreshRecycler();
                        toggleGroup = true;
                        //this.notifyItemChanged(groupIndex);
                        //this.notifyItemChanged(lastIndexExpanded);
                        lastIndexExpanded = groupIndex;
                    }
                }
            }
        }
    }

}
