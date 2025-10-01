package com.minhvq.orchidservice.configs;

import com.minhvq.orchidservice.entities.Category;
import com.minhvq.orchidservice.entities.Orchid;
import com.minhvq.orchidservice.repositories.CategoryRepository;
import com.minhvq.orchidservice.repositories.OrchidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final OrchidRepository orchidRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (categoryRepository.count() > 0 || orchidRepository.count() > 0) {
            System.out.println("Data already exists, skipping initialization");
            return;
        }

        Category naturalCategory = new Category();
        naturalCategory.setName("Natural");
        categoryRepository.save(naturalCategory);

        Category hybridCategory = new Category();
        hybridCategory.setName("Hybrid");
        categoryRepository.save(hybridCategory);

        Orchid orchid1 = new Orchid();
        orchid1.setName("Phalaenopsis amabilis");
        orchid1.setIsNatural(true);
        orchid1.setPrice(1000);
        orchid1.setDescription("The Moth Orchid is a natural species from Southeast Asia");
        orchid1.setUrl("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQpl8K9ukZJDHgp_tsc6oLVgyzLbvXt0rf8WS-sWpGhr0TasRv4meKwGmmeZ3DT3r6rn5m7IpQqfkI9xU7ArB7hLlaNjwqUW0tnl6D2oA");
        orchid1.setCategory(naturalCategory);
        orchidRepository.save(orchid1);

        Orchid orchid2 = new Orchid();
        orchid2.setName("Dendrobium Hybrid");
        orchid2.setIsNatural(false);
        orchid2.setPrice(2000);
        orchid2.setDescription("A beautiful hybrid Dendrobium variety");
        orchid2.setUrl("https://shopgreenlandgarden.com/cdn/shop/products/20220210_131255.jpg?v=1644524483&width=1080");
        orchid2.setCategory(hybridCategory);
        orchidRepository.save(orchid2);

        System.out.println("Categories and Orchids initialized successfully");
    }
}
