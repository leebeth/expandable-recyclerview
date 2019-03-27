package com.leebethanacona.expandablerecyclerview.stickyheader;

import android.view.View;

public interface StickyHeaderInterface {
    int getHeaderPositionForItem(int topChildPosition);

    int getHeaderLayout(int headerPosition);

    void bindHeaderData(View header, int headerPosition);

    boolean isHeader(int childAdapterPosition);

    boolean isHeaderExpanded(int topChildPosition);
}
