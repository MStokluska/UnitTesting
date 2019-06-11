package mstokluska.testing.cart;

import mstokluska.testing.cart.Cart;
import mstokluska.testing.order.Order;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test cases for cart")
class CartTest {


    @Test
    @DisplayName("Cart is able to process 1000 orders in 100ms")
    void simulateLargeOrder(){

        //given
        Cart cart = new Cart();

        //when
        //then
        assertTimeout(Duration.ofMillis(100), () -> cart.simulateLargeOrder());

    }

    @Test
    void cartShouldNotBeEmptyAfterAddingOrderToCart(){
        //given
        Order order = new Order();
        Cart cart = new Cart();

        //when
        cart.addOrderToCart(order);

        //then
        assertThat(cart.getOrders(), anyOf(
           notNullValue(),
           Matchers.hasSize(3),
           is(not(Matchers.empty()))
        ));
        assertThat(cart.getOrders(), allOf(
                notNullValue(),
                Matchers.hasSize(1),
                is(not(Matchers.empty()))
        ));
    }


}