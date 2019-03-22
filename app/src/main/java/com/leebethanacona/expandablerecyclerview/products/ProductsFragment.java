package com.leebethanacona.expandablerecyclerview.products;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.leebethanacona.expandablerecyclerview.R;
import com.leebethanacona.expandablerecyclerview.components.MyExpandableGroup;
import com.leebethanacona.expandablerecyclerview.products.model.Product;
import com.thoughtbot.expandablerecyclerview.listeners.GroupExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;

public class ProductsFragment extends Fragment implements FragmentProductView {

    RecyclerView recycler;
    private TuCashAdapter productsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        recycler = view.findViewById(R.id.recyclerProducts);

        ArrayList<MyExpandableGroup> myExpandableGroups = new ArrayList<>();

        //Init data
        ArrayList<Product> productsFromServer = getProductsFromServer("Destacados");
        ArrayList<Product[]> products = getProductsArray(productsFromServer);
        myExpandableGroups.add(new MyExpandableGroup("Destacados", products));

        productsFromServer = getProductsFromServer("Electrodomesticos");
        products = getProductsArray(productsFromServer);
        myExpandableGroups.add(new MyExpandableGroup("Electrodomesticos", products));

        productsFromServer = getProductsFromServer("Helados");
        products = getProductsArray(productsFromServer);
        myExpandableGroups.add(new MyExpandableGroup("Helados", products));

        productsFromServer = getProductsFromServer("Viajes");
        products = getProductsArray(productsFromServer);
        myExpandableGroups.add(new MyExpandableGroup("Viajes", products));

        //initialize recycler
        productsAdapter = new TuCashAdapter(myExpandableGroups, this);
        RecyclerView.ItemAnimator animator = recycler.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            (( DefaultItemAnimator ) animator).setSupportsChangeAnimations(true);
        }
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(productsAdapter);
        productsAdapter.toggleGroup(0);
        productsAdapter.setLastIndexExpanded(0);
        return view;
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
                    products.add(productArray);
                }
            } else {
                productArray[1] = productsFromServer.get(i);
                products.add(productArray);
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

    @Override
    public void itemSelected(Product product) {
        Toast.makeText(getContext(), "El producto seleccionado es: " + product.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshRecycler() {
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                productsAdapter.notifyDataSetChanged();
            }
        };
        handler.postDelayed(r, 400);
    }
}
