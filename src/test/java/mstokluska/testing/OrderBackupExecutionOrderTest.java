package mstokluska.testing;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderBackupExecutionOrderTest {

    @Test
    void callingBackupWithoutCreateingFileFirstShouldThrowException(){

        //given
        OrderBackup orderBackup = new OrderBackup();

        //when
        assertThrows(IOException.class, () -> orderBackup.backupOrder(new Order()));
        ;
    }
}
