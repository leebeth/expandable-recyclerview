package com.leebethanacona.expandablerecyclerview.stickyheader;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.leebethanacona.expandablerecyclerview.R;
import com.leebethanacona.expandablerecyclerview.components.MyExpandableGroup;
import com.leebethanacona.expandablerecyclerview.stickyheader.model.Category;
import com.leebethanacona.expandablerecyclerview.stickyheader.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StickyHeaderFragment extends Fragment implements StickyHeaderView {


    private RecyclerView recyclerView;
    private List<Category> categoryList;

    public StickyHeaderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sticky_header, container, false);
        recyclerView = view.findViewById(R.id.productsRecyclerVIew);

        initializeData();

        ProductAdapter productAdapter = new ProductAdapter(categoryList, this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new StickHeaderItemDecoration(productAdapter));
        return view;
    }

    private void initializeData() {

        categoryList = new ArrayList<>();
        categoryList.add(new Category("Destacados"));
        getProductsArray(getProductsFromServer("Destacados"));

        categoryList.add(new Category("Electrodomesticos"));
        getProductsArray(getProductsFromServer("Electrodomesticos"));

        categoryList.add(new Category("Helados"));
        getProductsArray(getProductsFromServer("Helados"));

        categoryList.add(new Category("Viajes"));
        getProductsArray(getProductsFromServer("Viajes"));

    }

    @Override
    public void itemSelected(Product product) {
        Toast.makeText(getContext(), "El producto seleccionado es: " + product.getName(), Toast.LENGTH_SHORT).show();
    }


    private ArrayList<Product[]> getProductsArray(ArrayList<Product> productsFromServer) {
        ArrayList<Product[]> products = new ArrayList<>();
        Product[] productArray = new Product[2];
        boolean mod = productsFromServer.size() % 2 == 0;
        for (int i = 0; i < productsFromServer.size(); i++) {
            if (i % 2 == 0) {
                productArray[0] = productsFromServer.get(i);
                if (i == productsFromServer.size() - 1 && !mod) {
                    productArray[1] = null;
                    categoryList.add(new Category(productArray));
                }
            } else {
                productArray[1] = productsFromServer.get(i);
                categoryList.add(new Category(productArray));
                productArray = new Product[2];
            }

        }
        return products;
    }

    private ArrayList<Product> getProductsFromServer(String nameCategory) {
        ArrayList<Product> products = new ArrayList<>();
        if ("Destacados".equalsIgnoreCase(nameCategory)) {
            products.add(new Product("Sandalias"));
            products.add(new Product("Camisas"));
            products.add(new Product("Sacos"));
            products.add(new Product("Tacones"));
        } else if ("Electrodomesticos".equalsIgnoreCase(nameCategory)) {
            products.add(new Product("Lavadora"));
            products.add(new Product("Computador"));
            products.add(new Product("Televisor"));
            products.add(new Product("Teatro en casa"));
            products.add(new Product("Estufa"));
            products.add(new Product("Horno"));
            products.add(new Product("Cafetera"));
            products.add(new Product("Secadora"));
            products.add(new Product("Horno microondas"));
            products.add(new Product("Plancha"));
            products.add(new Product("Portatil"));
        } else if ("Helados".equalsIgnoreCase(nameCategory)) {
            products.add(new Product("Paleta"));
            products.add(new Product("Helado"));
            products.add(new Product("Crema"));
        } else {
            products.add(new Product("Santa Marta"));
            products.add(new Product("San Andres"));
            products.add(new Product("Punta Cana"));
            products.add(new Product("Cali"));
            products.add(new Product("Amazonas"));
            products.add(new Product("Caño cristales"));
            products.add(new Product("Manizales"));
            products.add(new Product("Bucaramanga"));
            products.add(new Product("Medellín"));
            products.add(new Product("Bogotá"));
            products.add(new Product("Cucúta"));
            products.add(new Product("Cartagena"));
            products.add(new Product("Popayan"));
            products.add(new Product("Cauca"));
        }
        return products;

    }
}
