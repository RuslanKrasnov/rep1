package com.geekbrains.tests;

import com.geekbrains.spring.web.cart.dto.Cart;
import com.geekbrains.spring.web.cart.dto.ProductDto;
import com.geekbrains.spring.web.cart.services.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@SpringBootTest(classes = CartService.class)
public class CartTest {

    @MockBean
    private RestTemplate restTemplate;
    @MockBean
    private CacheManager cacheManager;

    private CartService cartService;


    @BeforeEach
    public void initCart() {
        cartService = new CartService(cacheManager, restTemplate);
        Mockito.when(cacheManager.getCache("Cart")
                .get(Mockito.anyString(), Cart.class)).thenReturn(new Cart());
        cartService.clear("test_cart");
    }

    @Test
    public void addToCartTest() {
        ProductDto product = new ProductDto();
        product.setId(5L);
        product.setTitle("X");
        product.setPrice(100);

        Mockito.doReturn(Optional.of(product)).when(cartService).getCurrentCart("test_cart");
        cartService.addProductByIdToCart(5l, "test_cart");
        cartService.addProductByIdToCart(5l, "test_cart");
        cartService.addProductByIdToCart(5l, "test_cart");

        Mockito.verify(restTemplate, Mockito.times(1)).getForObject(Mockito.any(), Mockito.any());
        Assertions.assertEquals(1, cartService.getCurrentCart("test_cart").getItems().size());
    }

}
