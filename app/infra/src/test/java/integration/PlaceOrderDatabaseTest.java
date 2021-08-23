package integration;

import io.giovannymassuia.cleanarch.infra.factory.DatabaseRepositoryFactory;

class PlaceOrderDatabaseTest extends PlaceOrderTest {

    protected PlaceOrderDatabaseTest() {
        super(DatabaseRepositoryFactory.getInstance());
    }
    
}