package com.leebethanacona.expandablerecyclerview.stickyheader.model;

import java.util.List;

public class Category {
    private String name;
    private Product[] productList;
    private int headerLayout;
    public int getViewType;
    private boolean isHeader;
    private boolean groupExpanded;

    public Category(Product[] productArray) {
        this.productList = productArray;
    }

    public Category(String name) {
        this.name = name;
        isHeader = true;
    }

    public Product[] getProductList() {
        return productList;
    }

    public void setProductList(Product[] productList) {
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeaderLayout() {
        return headerLayout;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public boolean isGroupExpanded() {
        return groupExpanded;
    }
}
