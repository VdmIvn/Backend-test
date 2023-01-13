package lesson5;

import lesson5.api.ProductService;
import lesson5.dto.Product;
import lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import static org.hamcrest.MatcherAssert.assertThat;

public class UpdateProductTest {

        static ProductService productService;
        Product product = null;
        Product nonExistentProduct = null;

        @BeforeAll
        static void beforeAll() {
            productService = RetrofitUtils.getRetrofit()
                    .create(ProductService.class);
        }

        @SneakyThrows
        @Test
        void updateProductTest() {
            product = new Product()
                    .withId(1)
                    .withTitle("TestProduct")
                    .withCategoryTitle("Food")
                    .withPrice(123456);
            Response<Product> response = productService.modifyProduct(product)
                    .execute();
            assertThat(response.body().getTitle(), CoreMatchers.is ("TestProduct"));
            assertThat(response.body().getCategoryTitle(), CoreMatchers.is ("Food"));
            assertThat(response.body().getPrice(), CoreMatchers.is (123456));
            assertThat(response.isSuccessful(), CoreMatchers.is(true));
        }

    @SneakyThrows
    @Test
    void updateProductNegativeTest() {
           nonExistentProduct = new Product()
                    .withId(10000000)
                    .withTitle("TestProduct")
                    .withCategoryTitle("Electronic")
                    .withPrice(123456);
        Response<Product> response = productService.modifyProduct(nonExistentProduct)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.raw().code(), CoreMatchers.is(400));
    }
}


