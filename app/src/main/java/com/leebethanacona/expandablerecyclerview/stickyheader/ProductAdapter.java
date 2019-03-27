package com.leebethanacona.expandablerecyclerview.stickyheader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leebethanacona.expandablerecyclerview.R;
import com.leebethanacona.expandablerecyclerview.stickyheader.model.Category;
import com.leebethanacona.expandablerecyclerview.stickyheader.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> implements StickyHeaderInterface {
    private static final int HEADER = 0;
    private static final int CHILD = 1;

    private List<Category> categoryList;
    private StickyHeaderView stickyHeaderView;

    public ProductAdapter(List<Category> categoryList, StickyHeaderView stickyHeaderView) {
        this.categoryList = categoryList;
        this.stickyHeaderView = stickyHeaderView;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        StickHeaderItemDecoration stickHeaderDecoration = new StickHeaderItemDecoration(this);
        recyclerView.addItemDecoration(stickHeaderDecoration);
    }


    @Override
    public final int getItemViewType(int position) {
        if (categoryList.get(position).isHeader()) {
            return HEADER;
        }
        return CHILD;
    }


    @Override
    public boolean isHeader(int childAdapterPosition) {
        return categoryList.get(childAdapterPosition).isHeader();
    }

    @Override
    public boolean isHeaderExpanded(int topChildPosition) {
        return true;
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    @Override
    public int getHeaderLayout(int headerPosition) {
        return R.layout.expandable_header_row;
    }

    @Override
    public void bindHeaderData(View header, int headerPosition) {
        TextView textView = header.findViewById(R.id.tvTittleHeader);
        if (textView != null)
            textView.setText(categoryList.get(headerPosition).getName());
    }

    @Override
    public int getHeaderPositionForItem(int itemPosition) {
        int headerPosition = 0;
        do {
            if (this.isHeader(itemPosition)) {
                headerPosition = itemPosition;
                break;
            }
            itemPosition -= 1;
        } while (itemPosition >= 0);
        return headerPosition;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER:
                return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_header_row, parent, false));
            default:
                return new ChildViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_item_row, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holderBase, int position) {
        if (holderBase instanceof HeaderViewHolder) {
            HeaderViewHolder holder = (( HeaderViewHolder ) holderBase);
            holder.categoryTitle.setText(categoryList.get(position).getName());
            if (categoryList.get(position).isGroupExpanded()) {
                holder.setIndicatorExpandCollapse(true);
            } else
                holder.setIndicatorExpandCollapse(false);
        } else if (holderBase instanceof ChildViewHolder) {
            ChildViewHolder holder = (( ChildViewHolder ) holderBase);
            final Product[] products = ( Product[] ) categoryList.get(position).getProductList();

            TextView name = holder.cell1.findViewById(R.id.productNameLeftTextView);
            name.setText(products[0].getName());
            holder.cell1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stickyHeaderView.itemSelected(products[0]);
                }
            });

            if (products[1] != null) {
                holder.cell2.setVisibility(View.VISIBLE);
                TextView nameProduct2 = holder.cell2.findViewById(R.id.productNameRightTextView);
                nameProduct2.setText(products[1].getName());
                holder.cell2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stickyHeaderView.itemSelected(products[1]);
                    }
                });
            } else
                holder.cell2.setVisibility(View.GONE);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    class HeaderViewHolder extends MyViewHolder {

        private Context context;
        private TextView categoryTitle;
        private ImageView ivArrowHeader;

        HeaderViewHolder(View view) {
            super(view);
            context = itemView.getContext();
            categoryTitle = itemView.findViewById(R.id.tvTittleHeader);
            ivArrowHeader = itemView.findViewById(R.id.ivArrowHeader);
        }

        void setIndicatorExpandCollapse(boolean expand) {
            if (!expand)
                ivArrowHeader.setImageDrawable(context.getResources().getDrawable(R.drawable.btn_mas_producto));
            else
                ivArrowHeader.setImageDrawable(context.getResources().getDrawable(R.drawable.btn_menos_producto));

        }
    }

    class ChildViewHolder extends MyViewHolder {
        ConstraintLayout cell1, cell2;

        ChildViewHolder(View itemView) {
            super(itemView);
            cell1 = itemView.findViewById(R.id.cell_1);
            cell2 = itemView.findViewById(R.id.cell_2);
        }
    }
}
