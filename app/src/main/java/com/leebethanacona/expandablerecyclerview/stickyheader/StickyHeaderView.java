package com.leebethanacona.expandablerecyclerview.stickyheader;

import android.widget.ImageView;

import com.leebethanacona.expandablerecyclerview.stickyheader.model.Category;
import com.leebethanacona.expandablerecyclerview.stickyheader.model.Product;

interface StickyHeaderView {
    void itemSelected(Product product);

    void setExpandedIcon(boolean z, ImageView imageView);

    void setVisibilityToHeader(boolean b, ImageView ivArrowHeader, Category category);
}
