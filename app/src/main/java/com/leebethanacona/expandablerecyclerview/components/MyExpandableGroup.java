package com.leebethanacona.expandablerecyclerview.components;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class MyExpandableGroup extends ExpandableGroup {
    private boolean isExpanded;

    public MyExpandableGroup(String title, List items) {
        super(title, items);
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
