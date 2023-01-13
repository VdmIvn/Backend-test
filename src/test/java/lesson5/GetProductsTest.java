package lesson5;

import com.github.javafaker.Faker;
import lesson5.api.ProductService;
import lesson5.dto.Product;
import lesson5.utils.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public class GetProductsTest {

    static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @Test
    void getProductsPositiveTest() throws IOException {
        Response<List<Product>> response = productService.getProducts().execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        List<Product> productList = response.body();
        assertThat(response.raw().code(), CoreMatchers.is(200));
        assertThat(productList.get(0).getCategoryTitle(), CoreMatchers.is("Food"));
        assertThat(productList.get(1).getCategoryTitle(), CoreMatchers.is("Food"));
        assertThat(productList.get(2).getCategoryTitle(), CoreMatchers.is("Food"));
        assertThat(productList.get(3).getCategoryTitle(), CoreMatchers.is("Electronic"));
        assertThat(productList.get(4).getCategoryTitle(), CoreMatchers.is("Electronic"));
    }
}
