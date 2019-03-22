package com.leebethanacona.expandablerecyclerview.products;

import com.leebethanacona.expandablerecyclerview.products.model.Product;

public interface FragmentProductView {
    void itemSelected(Product product);

    void refreshRecycler();
}
