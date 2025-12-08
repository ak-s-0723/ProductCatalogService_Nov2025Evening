package org.example.productcatalogservice_nov2025evening.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Setter
@Getter
@Entity(name="categories")
public class Category extends BaseModel {
    private String name;
    private String description;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size=4)
    private List<Product> products;
}


//1 from cat + 6 for each cat
//1 + 2 batches (when batchsize =4)
//1 + 3 batches (when batchsize=2)

//https://hackernoon.com/3-ways-to-deal-with-hibernate-n1-problem
//https://dheerajgopinath.medium.com/the-issue-with-fetchmode-subselect-and-onetomany-mappings-in-hibernate-and-jpa-f79724068897
//https://itecnote.com/tecnote/hibernate-subselect-vs-batch-fetching/
//https://www.baeldung.com/hibernate-fetchmode