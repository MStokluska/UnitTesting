package mstokluska.testing;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AccountTest {

    @Test
    void newAccountShouldNotBeActiveAfterCreation() {
        //given + when
    Account newAccount = new Account();
    //then
        assertFalse(newAccount.isActive(), "Check if new account is not active");

    }



    @Test
    void newAccountShouldBeActiveAfterActivation() {
        //given
        Account newAccount = new Account();

        //when
        newAccount.activate();

        //then
        assertTrue(newAccount.isActive());



    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet(){

        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);

    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet(){

        //given
        Address address = new Address("AshleyCourt","67C");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);

        //when
        Address defaultAddress = account.getDefaultDeliveryAddress();

        //then
        assertNotNull(defaultAddress);

    }

    @RepeatedTest(5)
    void newAccountwithNoNullAddressShouldBeActive(){
        //given
        Address address = new Address("AshleyCourt","8A");

        //when
        Account account = new Account(address);

        //then
        assumingThat(address != null, () -> {
            assertTrue(account.isActive());
        });
    }

    @Test
    void invalidEmailShouldThrowException(){
        //given
        Account account = new Account();

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> account.setMail("example of wrong email"));

    }

    @Test
    void validEmailShouldBeSet(){
        //given
        Account account = new Account();

        //when
        account.setMail("m.stokluska@ymail.com");

        //then
        assertThat(account.getMail(), is("m.stokluska@ymail.com"));
    }


}
