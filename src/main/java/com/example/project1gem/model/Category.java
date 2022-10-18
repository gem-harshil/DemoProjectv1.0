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
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @NotNull(message = "Category Name can not be null")
    @NotBlank(message = "Category Name can not be blank")
    private String categoryName;

    @NotBlank(message = "Category Description can not be Blank")
    private String categoryDescription;

    @JsonIgnore
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    @Column(name = "createDate", nullable = false, updatable = false)
    private Date createDate;

    @JsonIgnore
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date updateDate;

    @Column(name = "active")
    private boolean isActive = Boolean.TRUE;

    @Column(name = "deleted")
    private boolean isDeleted = Boolean.FALSE;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    private List<Product> products;
}
