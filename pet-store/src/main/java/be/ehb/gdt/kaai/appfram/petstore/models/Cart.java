package be.ehb.gdt.kaai.appfram.petstore.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private AppUser user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductItem> items;

    public double getTotal() {
        return items.stream().map(ProductItem::getSubtotal).reduce(0.0, Double::sum);
    }

    public int getTotalAmount() {
        return items.stream().map(ProductItem::getAmount).reduce(0, Integer::sum);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Set<ProductItem> getItems() {
        return items;
    }

    public void setItems(Set<ProductItem> items) {
        this.items = items;
    }

    public void addProduct(ProductItem item) {
        items.add(item);
    }

    public void addProduct(Product product, int amount) {
        ProductItem item = new ProductItem(product, amount);
        items.add(item);
    }

    public void empty() {
        items.clear();
    }
}
