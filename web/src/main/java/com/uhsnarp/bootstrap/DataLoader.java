package com.uhsnarp.bootstrap;

import com.uhsnarp.constants.Media;
import com.uhsnarp.model.*;
import com.uhsnarp.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final BusinessService businessService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ImageService imageService;


    //Befor Spring 2 @Autowired was required on constructor as well
    //@Autowired
    public DataLoader(UserService userService, BusinessService businessService, ProductService productService,
                      CategoryService categoryService, ImageService imageService) {
        this.userService = userService;
        this.businessService = businessService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.imageService = imageService;
    }

    /* public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }*/


    @Override
    public void run(String... args) throws Exception {
        int count = businessService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        CategoryBO category1 = new CategoryBO();
        category1.setName("Category1");
        category1.setDescription("Category1 Description");
        category1.setColorCode("#FF5733");

        CategoryBO category2 = new CategoryBO();
        category2.setName("Category2");
        category2.setDescription("Category2 Description");
        category2.setColorCode("#28B463");

        categoryService.save(category1);
        categoryService.save(category2);

        ImageBO image1 = new ImageBO();
        image1.setName("Image1 name");
        image1.setAlt_text("Image1 alt text");
        image1.setDescription("Image1 description");
        image1.setLink("Image1 link");
        image1.setPortraitImage(null);
        image1.setLandscapeImage(null);
        image1.setType(Media.ADVERTISEMENT);

        ImageBO image2 = new ImageBO();
        image2.setName("Image2 name");
        image2.setAlt_text("Image2 alt text");
        image2.setDescription("Image2 description");
        image2.setLink("Image2 link");
        image2.setPortraitImage(null);
        image2.setLandscapeImage(null);
        image2.setType(Media.GALLERY);

        imageService.save(image1);
        imageService.save(image2);

        Set<ImageBO> imageBOS = new HashSet<>(Arrays.asList(image1, image2));
        ProductBO product1 = new ProductBO();
        product1.setCategory(category1);
        product1.setName("Product1");
        product1.setDescription("Product1 description");
        product1.getImages().add(image1);
        product1.getImages().add(image2);
        product1.setCreated_at(new Timestamp(System.currentTimeMillis()));

        ProductBO product2 = new ProductBO();
        product2.setCategory(category2);
        product2.setName("Product2");
        product2.setDescription("Product2 description");
        product2.getImages().add(image1);
        product2.getImages().add(image2);

        product2.setCreated_at(new Timestamp(System.currentTimeMillis()));

        productService.save(product1);
        productService.save(product2);

        HashSet<ProductBO> productBOS = new HashSet<>(Arrays.asList(product1, product2));

        BusinessBO business1 = new BusinessBO();
        business1.setCategory(category1);
        business1.setName("BANK OF INDIA");
        business1.setMobileNumber(1232123423L);
        business1.setIfsc_code("BKID000ZA64");
        business1.setGst_number(12345L);
        business1.setProducts(productBOS);
        business1.setCreated_at(new Timestamp(System.currentTimeMillis()));
        business1.setTagLine("business1 tagline");
        business1.setFacebookLink("business1 fb link");

        BusinessBO business2 = new BusinessBO();
        business2.setCategory(category2);
        business2.setName("AXIS BANK");
        business2.setMobileNumber(9990766052L);
        business2.setIfsc_code("UTIB0004504");
        business2.setGst_number(22345L);
        business2.setProducts(productBOS);
        business2.setCreated_at(new Timestamp(System.currentTimeMillis()));
        business2.setTagLine("business2 tagline");
        business2.setFacebookLink("business2 fb link");

        businessService.save(business1);
        businessService.save(business2);

        System.out.println("Loaded Business...");

        CityBO city1 = new CityBO();
        city1.setCountryCode("IND");
        city1.setName("Aligarh");
        city1.setPinCode(202001);

        CityBO city2 = new CityBO();
        city1.setCountryCode("IND");
        city1.setName("Gurgaon");
        city1.setPinCode(122001);

        AddressBO address1 = new AddressBO();
        address1.setCity(city1);
        address1.setAddressLine1("AL11");
        address1.setAddressLine2("AL12");
        address1.setAddressLine3("AL13");

        AddressBO address2 = new AddressBO();
        address2.setCity(city2);
        address2.setAddressLine1("AL21");
        address2.setAddressLine2("AL22");
        address2.setAddressLine3("AL23");


        UserBO user1 = UserBO.builder().name("User1").email("user1@gmail.com").gender("Male")
                .address(address1).business(business1).build();

        UserBO user2 = UserBO.builder().name("User2").email("user2@gmail.com").gender("Female")
                .address(address2).business(business2).build();


        /*CountryBO country1 = new CountryBO();
        country1.setCountryCode("IND");
        country1.setName("India");
        country1.setContinent_name("Asia");

        CountryBO country2 = new CountryBO();
        country2.setCountryCode("USA");
        country2.setName("United States Of America");
        country2.setContinent_name("America");*/

        userService.save(user1);
        userService.save(user2);

        System.out.println("Loaded Users...");

    }
}
