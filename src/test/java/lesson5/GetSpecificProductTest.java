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
public class GetSpecificProductTest {

    static ProductService productService;
    int num = (int) (Math.random() * (5 - 1)) + 1;


    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @SneakyThrows
    @Test
    void getProductByIdPositiveTest() {
        System.out.println(num);
        String value;
        if (num < 4) {
            value = "Food";
        } else {
            value = "Electronic";
        }
        Response<Product> response = productService.getProductById(num).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getCategoryTitle(), CoreMatchers.is(value));
    }

    @SneakyThrows
    @Test
    void getProductByIdNegativeTest() {
        num = (int) (Math.random() * (100 - 10)) + 1;
        Response<Product> response = productService.getProductById(num).execute();
        assertThat(response.raw().code(), CoreMatchers.is(404));
    }
}

