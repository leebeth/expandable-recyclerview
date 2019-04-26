package com.leebethanacona.expandablerecyclerview.stickyheader;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.leebethanacona.expandablerecyclerview.R;
import com.leebethanacona.expandablerecyclerview.stickyheader.model.Category;
import com.leebethanacona.expandablerecyclerview.stickyheader.model.Product;
import java.util.ArrayList;
import java.util.List;

public class StickyHeaderFragment extends Fragment implements StickyHeaderView {
    private List<Category> categoryList;
    private ProductAdapter productAdapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sticky_header, container, false);
        this.recyclerView =  view.findViewById(R.id.productsRecyclerVIew);
        initializeData();
        this.productAdapter = new ProductAdapter(this.categoryList, this);
        this.recyclerView.setAdapter(this.productAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerView.addItemDecoration(new StickHeaderItemDecoration(this.productAdapter));
        initializeExpand();
        return view;
    }

    private void initializeData() {
        this.categoryList = new ArrayList<>();
        this.categoryList.add(new Category("Destacados", getProductsArray(getProductsFromServer("Destacados")), true));
        this.categoryList.add(new Category("Electrodomesticos", getProductsArray(getProductsFromServer("Electrodomesticos"))));
        this.categoryList.add(new Category("Helados", getProductsArray(getProductsFromServer("Helados"))));
        this.categoryList.add(new Category("Viajes", getProductsArray(getProductsFromServer("Viajes"))));
        this.categoryList.add(new Category("aaa", getProductsArray(getProductsFromServer("Destacados"))));
        this.categoryList.add(new Category("bbb", getProductsArray(getProductsFromServer("Electrodomesticos"))));
        this.categoryList.add(new Category("ccc", getProductsArray(getProductsFromServer("Helados"))));
        this.categoryList.add(new Category("ddd", getProductsArray(getProductsFromServer("Viajes"))));
        this.categoryList.add(new Category("eee", getProductsArray(getProductsFromServer("Destacados"))));
        this.categoryList.add(new Category("fff", getProductsArray(getProductsFromServer("Electrodomesticos"))));
        this.categoryList.add(new Category("ggg", getProductsArray(getProductsFromServer("Helados"))));
        this.categoryList.add(new Category("hhh", getProductsArray(getProductsFromServer("Viajes"))));
        this.categoryList.add(new Category("iii", getProductsArray(getProductsFromServer("Destacados"))));
        this.categoryList.add(new Category("jjj", getProductsArray(getProductsFromServer("Electrodomesticos"))));
        this.categoryList.add(new Category("kkk", getProductsArray(getProductsFromServer("Helados"))));
        this.categoryList.add(new Category("lll", getProductsArray(getProductsFromServer("Viajes"))));
        this.categoryList.add(new Category("mmm", getProductsArray(getProductsFromServer("Destacados"))));
        this.categoryList.add(new Category("nnn", getProductsArray(getProductsFromServer("Electrodomesticos"))));
        this.categoryList.add(new Category("ooo", getProductsArray(getProductsFromServer("Helados"))));
        this.categoryList.add(new Category("ppp", getProductsArray(getProductsFromServer("Viajes"))));
        this.categoryList.add(new Category("qqq", getProductsArray(getProductsFromServer("Viajes"))));
        this.categoryList.add(new Category("rrr", getProductsArray(getProductsFromServer("Viajes"))));
        this.categoryList.add(new Category("sss", getProductsArray(getProductsFromServer("Viajes"))));
        this.categoryList.add(new Category("ttt", getProductsArray(getProductsFromServer("Viajes"))));
        this.categoryList.add(new Category("uuu", getProductsArray(getProductsFromServer("Viajes"))));
        this.categoryList.add(new Category("vvv", getProductsArray(getProductsFromServer("Viajes"))));
        this.categoryList.add(new Category("www", getProductsArray(getProductsFromServer("Viajes"))));
        this.categoryList.add(new Category("xxx", getProductsArray(getProductsFromServer("Viajes"))));
        this.categoryList.add(new Category("yyy", getProductsArray(getProductsFromServer("Viajes"))));
        this.categoryList.add(new Category("zzz", getProductsArray(getProductsFromServer("Viajes"))));
    }

    public void itemSelected(Product product) {
        Context context = getContext();
        String stringBuilder = "El producto seleccionado es: " +
                product.getName();
        Toast.makeText(context, stringBuilder, Toast.LENGTH_SHORT).show();
    }

    public void setExpandedIcon(boolean expanded, ImageView ivArrowHeader) {
        if (expanded) {
            ivArrowHeader.setImageResource(R.drawable.btn_menos_producto);
        } else {
            ivArrowHeader.setImageResource(R.drawable.btn_mas_producto);
        }
    }

    public void initializeExpand() {
        int i = 0;
        while (i < this.categoryList.size()) {
            if (( this.categoryList.get(i)).isHeader() && ( this.categoryList.get(i)).isGroupExpanded()) {
                int position = i;
                if (!( this.categoryList.get(i)).getProductsList().isEmpty()) {
                    position++;
                    for (int j = 0; j < ( this.categoryList.get(i)).getProductsList().size(); j++) {
                        this.categoryList.add(position + j, new Category( ( this.categoryList.get(i)).getProductsList().get(j)));
                    }
                }
            }
            i++;
        }
        this.productAdapter.notifyDataSetChanged();
    }

    private ArrayList<Product[]> getProductsArray(ArrayList<Product> productsFromServer) {
        ArrayList<Product[]> products = new ArrayList<>();
        Product[] productArray = new Product[2];
        boolean mod = productsFromServer.size() % 2 == 0;
        Product[] productArray2 = productArray;
        for (int i = 0; i < productsFromServer.size(); i++) {
            if (i % 2 == 0) {
                productArray2[0] =  productsFromServer.get(i);
                if (i == productsFromServer.size() - 1 && !mod) {
                    productArray2[1] = null;
                    products.add(productArray2);
                }
            } else {
                productArray2[1] =  productsFromServer.get(i);
                products.add(productArray2);
                productArray2 = new Product[2];
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
