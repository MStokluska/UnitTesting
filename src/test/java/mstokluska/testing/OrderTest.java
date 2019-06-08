package mstokluska.testing;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(BeforeAfterExtension.class)
class OrderTest {

    private Order order;

    @BeforeEach
    void initializeOrder() {
        System.out.println("before each");
        order = new Order();
    }

    @AfterEach
    void cleanUp() {
        System.out.println("After Each");
        order.cancel();
    }

    @Test
    void testAssertArrayEquals() {
        System.out.println("Inside Test");
        //given
        int[] ints1 = {1, 2, 3};
        int[] ints2 = {1, 2, 3};

        //then
        assertArrayEquals(ints1, ints2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfTheOrder() {
        System.out.println("Inside Test");
        //then using Hamcrest
        assertThat(order.getMeals(), empty());
    }

    @Test
    void addingMealToOrderShouldIncreseOrderSize() {
        System.out.println("Inside Test");
        //given
        Meal meal = new Meal(15, "Burger");
        Meal meal2 = new Meal(15, "Pizza");

        //when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);

        //then
        assertThat(order.getMeals(), hasSize(2));
        assertThat(order.getMeals(), Matchers.contains(meal, meal2));
        assertThat(order.getMeals(), Matchers.hasItem(meal2));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize() {
        System.out.println("Inside Test");
        //given
        Meal meal = new Meal(15, "Burger");
        Meal meal2 = new Meal(15, "Pizza");

        //when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);

        order.removeMealFromOrder(meal2);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), not(contains(meal2)));

    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToList() {
        System.out.println("Inside Test");
        //given
        Meal meal = new Meal(15, "Burger");
        Meal meal2 = new Meal(15, "Pizza");


        //when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);

        //then
        assertThat(order.getMeals().get(0), is(meal));
        assertThat(order.getMeals().get(1), is(meal2));
        assertThat(order.getMeals(), Matchers.contains(meal, meal2));
        assertThat(order.getMeals(), Matchers.containsInAnyOrder(meal2, meal));


    }

    @Test
    void testIfTwoMealListsAreSame() {
        System.out.println("Inside Test");
        //given
        Meal meal = new Meal(15, "Burger");
        Meal meal2 = new Meal(15, "Pizza");
        Meal meal3 = new Meal(20, "Kebab");

        List<Meal> meals1 = Arrays.asList(meal, meal2, meal3);
        List<Meal> meals2 = Arrays.asList(meal, meal2, meal3);

        //then
        assertThat(meals1, is(meals2));
    }

    @Test
    void orderTotalPriceShouldNotExceedMaxIntValue() {

        //given
        Meal meal = new Meal(Integer.MAX_VALUE, "Burger");
        Meal meal2 = new Meal(Integer.MAX_VALUE, "Pizza");

        //when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);

        //then
        assertThrows(IllegalStateException.class, () -> order.totalPrice());

    }

    @Test
    void emptyOrderTotalPriceShouldEqualZero(){
        //given
        //order is created in BeforeEach
        assertThat(order.totalPrice(), is(0));
    }

    @Test
    void cancelingOrderShouldRemoveAllItemsFromMealsList(){
        //given
        Meal meal = new Meal(15, "Burger");
        Meal meal2 = new Meal(15, "Pizza");

        //when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);
        order.cancel();

        //then
        assertThat(order.getMeals().size(), is(0));
    }

}