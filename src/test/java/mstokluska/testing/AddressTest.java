package mstokluska.testing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @ParameterizedTest
    @CsvSource({"AshleyMews, 10", "AshleyCourt, 29"})
    void givenAddressesShouldNotBeEmptyAndHaveProperNames(String street, String number){
        assertThat(street, notNullValue());
        assertThat(street, containsString("A"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(80));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addresses.csv")
    void addressesFromFileShouldNotBeEmptyAndHaveProperNames(String street, String number){
        assertThat(street, notNullValue());
        assertThat(street, containsString("A"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(80));
    }


}