package com.leebethanacona.expandablerecyclerview.stickyheader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leebethanacona.expandablerecyclerview.R;
import com.leebethanacona.expandablerecyclerview.stickyheader.model.Category;
import com.leebethanacona.expandablerecyclerview.stickyheader.model.Product;

import java.util.List;

public class ProductAdapter extends Adapter<ProductAdapter.MyViewHolder> implements StickyHeaderInterface {
    private static final int CHILD = 1;
    private static final int HEADER = 0;
    private List<Category> categoryList;
    private StickyHeaderView stickyHeaderView;

    class MyViewHolder extends ViewHolder {
        MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ChildViewHolder extends MyViewHolder {
        ConstraintLayout cell1;
        ConstraintLayout cell2;

        ChildViewHolder(View itemView) {
            super(itemView);
            this.cell1 = itemView.findViewById(R.id.cell_1);
            this.cell2 = itemView.findViewById(R.id.cell_2);
        }
    }

    class HeaderViewHolder extends MyViewHolder {
        private TextView categoryTitle = (this.itemView.findViewById(R.id.tvTittleHeader));
        private ConstraintLayout constraintHeader = (this.itemView.findViewById(R.id.constraintHeaderLayout));
        private Context context = this.itemView.getContext();
        private ImageView ivArrowHeader = (this.itemView.findViewById(R.id.ivArrowHeader));

        HeaderViewHolder(View view) {
            super(view);
        }

        void setIndicatorExpandCollapse(boolean expand) {
            if (expand) {
                this.ivArrowHeader.setImageResource(R.drawable.btn_menos_producto);
            } else {
                this.ivArrowHeader.setImageResource(R.drawable.btn_mas_producto);
            }
        }
    }

    ProductAdapter(List<Category> categoryList, StickyHeaderView stickyHeaderView) {
        this.categoryList = categoryList;
        this.stickyHeaderView = stickyHeaderView;
    }

    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.addItemDecoration(new StickHeaderItemDecoration(this));
    }

    public final int getItemViewType(int position) {
        if ((this.categoryList.get(position)).isHeader()) {
            return HEADER;
        }
        return CHILD;
    }

    public boolean isHeader(int childAdapterPosition) {
        return (this.categoryList.get(childAdapterPosition)).isHeader();
    }

    public boolean isHeaderExpanded(int topChildPosition) {
        return true;
    }

    public int getItemCount() {
        return this.categoryList.size();
    }

    public int getHeaderLayout(int headerPosition) {
        return R.layout.expandable_header_row;
    }

    public void bindHeaderData(View header, final int headerPosition) {
        TextView textView = header.findViewById(R.id.tvTittleHeader);
        if (textView != null) {
            textView.setText((this.categoryList.get(headerPosition)).getName());
        }
        final ImageView ivArrowHeader = header.findViewById(R.id.ivArrowHeader);
        if (ivArrowHeader != null && (this.categoryList.get(headerPosition)).isHeader()) {

            stickyHeaderView.setVisibilityToHeader(true, ivArrowHeader, categoryList.get(headerPosition));

            if ((this.categoryList.get(headerPosition)).isGroupExpanded()) {
                this.stickyHeaderView.setExpandedIcon(true, ivArrowHeader);
            } else {
                this.stickyHeaderView.setExpandedIcon(false, ivArrowHeader);
            }
        } else
            stickyHeaderView.setVisibilityToHeader(false, ivArrowHeader, categoryList.get(headerPosition));

    }

    public int getHeaderPositionForItem(int itemPosition) {
        while (!isHeader(itemPosition)) {
            itemPosition--;
            if (itemPosition < 0) {
                return 0;
            }
        }
        return itemPosition;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType != HEADER) {
            return new ChildViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_item_row, parent, false));
        }
        return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_header_row, parent, false));
    }

    public void onBindViewHolder(@NonNull MyViewHolder holderBase, int position) {
        if (holderBase instanceof HeaderViewHolder) {
            final HeaderViewHolder holder = (HeaderViewHolder) holderBase;
            final Category category = this.categoryList.get(position);
            holder.categoryTitle.setText((this.categoryList.get(position)).getName());
            if ((this.categoryList.get(position)).isGroupExpanded()) {
                holder.setIndicatorExpandCollapse(true);
            } else {
                holder.setIndicatorExpandCollapse(false);
            }
            holder.constraintHeader.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    ProductAdapter.this.toggleHeader(holder, category);
                }
            });
        } else if (holderBase instanceof ChildViewHolder) {
            ChildViewHolder holder2 = (ChildViewHolder) holderBase;
            final Product[] products = (this.categoryList.get(position)).getProductArray();
            ((TextView) holder2.cell1.findViewById(R.id.productNameLeftTextView)).setText(products[0].getName());
            holder2.cell1.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    ProductAdapter.this.stickyHeaderView.itemSelected(products[0]);
                }
            });
            if (products[1] != null) {
                holder2.cell2.setVisibility(View.VISIBLE);
                ((TextView) holder2.cell2.findViewById(R.id.productNameRightTextView)).setText(products[1].getName());
                holder2.cell2.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        ProductAdapter.this.stickyHeaderView.itemSelected(products[1]);
                    }
                });
                return;
            }
            holder2.cell2.setVisibility(View.INVISIBLE);
        }
    }

    private void toggleHeader(HeaderViewHolder holder, Category category) {
        if (category.isGroupExpanded()) {
            this.stickyHeaderView.setExpandedIcon(false, holder.ivArrowHeader);
            collapse(category);
            return;
        }
        this.stickyHeaderView.setExpandedIcon(true, holder.ivArrowHeader);
        expand(category);
    }

    public void toggleHeader(ImageView ivArrowHeader, Category category) {
        if (category.isGroupExpanded()) {
            this.stickyHeaderView.setExpandedIcon(false, ivArrowHeader);
            collapse(category);
            return;
        }
        this.stickyHeaderView.setExpandedIcon(true, ivArrowHeader);
        expand(category);
    }

    private void expand(Category category) {
        for (int i = 0; i < this.categoryList.size(); i++) {
            if ((this.categoryList.get(i)).isHeader()) {
                if (category.getName().equals((this.categoryList.get(i)).getName())) {
                    (this.categoryList.get(i)).setGroupExpanded(true);
                    int position = i;
                    if (!category.getProductsList().isEmpty()) {
                        position++;
                        for (int j = 0; j < category.getProductsList().size(); j++) {
                            this.categoryList.add(position + j, new Category(category.getProductsList().get(j)));
                        }
                    }
                    notifyItemRangeInserted(position, category.getProductsList().size());
                } else if ((this.categoryList.get(i)).isGroupExpanded()) {
                    (this.categoryList.get(i)).setGroupExpanded(false);
                    notifyItemChanged(i);
                    collapse(this.categoryList.get(i));
                }
            }
        }
    }

    private void collapse(Category category) {
        boolean categoryFound = false;
        int i = 0;
        while (i < this.categoryList.size() && !categoryFound) {
            if ((this.categoryList.get(i)).isHeader() && category.getName().equals((this.categoryList.get(i)).getName())) {
                (this.categoryList.get(i)).setGroupExpanded(false);
                categoryFound = true;
                int position = i;
                if (!category.getProductsList().isEmpty()) {
                    position++;
                    for (int j = 0; j < category.getProductsList().size(); j++) {
                        this.categoryList.remove(position);
                    }
                }
                notifyItemRangeRemoved(position, category.getProductsList().size());
            }
            i++;
        }
    }
}
