package com.leebethanacona.expandablerecyclerview.stickyheader;

import android.view.View;

public interface StickyHeaderInterface {
    void bindHeaderData(View view, int i);

    int getHeaderLayout(int i);

    int getHeaderPositionForItem(int i);

    boolean isHeader(int i);

    boolean isHeaderExpanded(int i);
}
