package com.example.project1gem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private int price;

    @NotBlank(message = "Please provide proper Product Name")
    private String productName;
    @NotBlank(message = "Please provide proper Product Name")
    private String productDesc;

    @CreationTimestamp
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    @Column(name = "createDate", nullable = false, updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date updateDate;

    @Column(name = "active")
    private boolean isActive = Boolean.TRUE;
    @Column(name = "deleted")
    private boolean isDeleted = Boolean.FALSE;

}
