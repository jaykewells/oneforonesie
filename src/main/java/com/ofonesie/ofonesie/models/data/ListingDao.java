package com.ofonesie.ofonesie.models.data;


import com.ofonesie.ofonesie.models.Listing;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListingDao extends CrudRepository<Listing, Integer> {

    List<Listing> findBySize(int size);

    List<Listing> findByColor(int color);

    List<Listing> findBySeason(int season);

    List<Listing> findByTheme(int theme);

    @Query(value = "SELECT * FROM listing ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<Listing> randomListings();

}
