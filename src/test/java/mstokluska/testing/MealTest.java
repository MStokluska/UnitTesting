package mstokluska.testing;


import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.function.Executable;
import java.util.ArrayList;
import java.util.Collection;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {

        //given
        Meal meal = new Meal(35);

        //when
        int discountedPrice  = meal.getDiscountedPrice(7);

        //then
        assertEquals(28, discountedPrice);


    }

    @Test
    void referencesTOTheSameObjectShouldBeEqual(){

        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;

        //then
        assertSame(meal1,meal2);
    }

    @Test
    void referencesTOTheDifferentObjectShouldNotBeEqual(){

        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);

        //then
        assertNotSame(meal1,meal2);

    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreSame(){

        //given
        Meal meal1 = new Meal(10, "pizza");
        Meal meal2 = new Meal(10,"pizza");

        //then
        assertEquals(meal1, meal2,"Checking if two meals are equal");
    }

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThaThePrice(){
        //given
        Meal meal = new Meal(8,"soup");

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(40));
    }

    @ParameterizedTest
    @ValueSource(ints = {5,10,15,19})
    void mealPricesShouldBeLowerThan20(int price){
        assertThat(price, lessThan(20));
    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgersShouldHaveCorrectNameAndPrice(String name, int price){
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(10));
    }

    private static Stream<Arguments> createMealsWithNameAndPrice(){
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheeseburger", 20)
        );
    }

    @Tag("Fries")
    @TestFactory
    Collection<DynamicTest> calculateMealPrices(){
        Order order = new Order();
        order.addMealToOrder(new Meal(10,2,"Hamburger"));
        order.addMealToOrder(new Meal(20,5,"Fries"));
        order.addMealToOrder(new Meal(15,3,"Fish"));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();

        for(int i = 0; i < order.getMeals().size(); i++){
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getPrice();

            Executable executable  = () -> {
                assertThat(calculatePrice(price, quantity), lessThan(402));
            };

            String name = "Test name: " + i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
            dynamicTests.add(dynamicTest);

        }

        return dynamicTests;
    }

    private int calculatePrice(int price, int quantity){
        return price * quantity;
    }
}