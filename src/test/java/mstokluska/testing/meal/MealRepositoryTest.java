package mstokluska.testing.meal;

import mstokluska.testing.meal.Meal;
import mstokluska.testing.meal.MealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class MealRepositoryTest {
    MealRepository mealRepository = new MealRepository();

    @BeforeEach
    void cleanUp() {
        mealRepository.getAllMeals().clear();
    }
    //TEST DRIVEN DEVELOPMENT

    @Test
    void shouldBeAbleToAddMealToRepository() {

        //given
        Meal meal = new Meal(10, "pizza");

        //when
        mealRepository.add(meal);

        //then
        assertThat(mealRepository.getAllMeals().get(0), is(meal));
    }

    @Test
    void shouldBeAbleToRemoveMealFromRepo() {
        //given
        Meal meal = new Meal(10, "pizza");
        mealRepository.add(meal);

        //when
        mealRepository.delete(meal);

        //then
        assertThat(mealRepository.getAllMeals(), not(contains(meal)));
    }

    @Test
    void shouldBeAbleToFindMealByExacName() {
        //given
        Meal meal = new Meal(10, "pizza");
        Meal meal2 = new Meal(20, "p");
        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> result = mealRepository.findByName("pizza", true);

        //then
        assertThat(result.size(), is(1));

    }

    @Test
    void shouldBeAbleToFindMealBySearchingStartingLetters() {
        //given
        Meal meal = new Meal(10, "pizza");
        Meal meal2 = new Meal(20, "pi");
        mealRepository.add(meal);
        mealRepository.add(meal2);

        //when
        List<Meal> result = mealRepository.findByName("p", false);

        //then
        assertThat(result.size(), is(2));
    }

    @Test
    void shouldBeAbleToFindMealByPrice() {
        //given
        Meal meal = new Meal(10, "pizza");
        mealRepository.add(meal);

        //when
        List<Meal> result = mealRepository.findByPrice(10);

        //then
        assertThat(result.size(), is(1));
    }
}
