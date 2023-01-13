package lesson5;

import com.github.javafaker.Faker;
import lesson5.api.ProductService;
import lesson5.dto.Product;
import lesson5.utils.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteProductTest {
    static ProductService productService;
    Product product = null;
    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp() throws IOException {
        product = new Product()
                .withTitle("TestProduct")
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 1000));
        Response<Product> response = productService.createProduct(product)
                .execute();
        id =  response.body().getId();
        System.out.println(id);
    }

    @SneakyThrows
    @Test
    void deleteProductPositiveTest()  {
        Response<ResponseBody> response = productService.deleteProduct(id)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void deleteProductNegativeTest()  {
        int invalID = id + 1;
        System.out.println(invalID);
        Response<ResponseBody> response = productService.deleteProduct(invalID)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
        assertThat(response.raw().code(), CoreMatchers.is(500));
    }
}

