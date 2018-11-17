package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "client_order")
@NamedQueries({
        @NamedQuery(query = "select o from OrderEntity o join o.user", name = "get_all_orders"),
        @NamedQuery(query = "select o from OrderEntity o join o.user where user_id =:id", name = "get_all_orders_by_user_id")
})
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "creation_date")
    private Timestamp creationDate;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "client_order_id")
    private List<ProductEntity> products;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserEntity user;

    public OrderEntity(String title, Double totalPrice, Timestamp creationDate, List<ProductEntity> products, UserEntity user) {
        this.title = title;
        this.totalPrice = totalPrice;
        this.creationDate = creationDate;
        this.products = products;
        this.user = user;
    }
}
