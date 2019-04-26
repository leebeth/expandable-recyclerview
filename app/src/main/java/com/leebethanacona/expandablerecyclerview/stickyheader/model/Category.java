package com.leebethanacona.expandablerecyclerview.stickyheader.model;

import java.util.ArrayList;

public class Category {
    public int getViewType;
    private boolean groupExpanded;
    private int headerLayout;
    private boolean isHeader;
    private String name;
    private Product[] productArray;
    private ArrayList<Product[]> productsList;

    public Category(Product[] productArray) {
        this.productArray = productArray;
    }

    public Category(String name, ArrayList<Product[]> productsList) {
        this.name = name;
        this.isHeader = true;
        this.productsList = productsList;
    }

    public Category(String name, ArrayList<Product[]> productsList, boolean groupExpanded) {
        this.name = name;
        this.isHeader = true;
        this.productsList = productsList;
        this.groupExpanded = groupExpanded;
    }

    public Product[] getProductArray() {
        return this.productArray;
    }

    public void setProductArray(Product[] productArray) {
        this.productArray = productArray;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeaderLayout() {
        return this.headerLayout;
    }

    public boolean isHeader() {
        return this.isHeader;
    }

    public void setHeader(boolean header) {
        this.isHeader = header;
    }

    public boolean isGroupExpanded() {
        return this.groupExpanded;
    }

    public ArrayList<Product[]> getProductsList() {
        return this.productsList;
    }

    public void setProductsList(ArrayList<Product[]> productsList) {
        this.productsList = productsList;
    }

    public void setGroupExpanded(boolean groupExpanded) {
        this.groupExpanded = groupExpanded;
    }
}
