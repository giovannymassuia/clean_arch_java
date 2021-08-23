package integration;

import io.giovannymassuia.cleanarch.infra.factory.MemoryRepositoryFactory;

class PlaceOrderMemoryTest extends PlaceOrderTest {

    protected PlaceOrderMemoryTest() {
        super(MemoryRepositoryFactory.getInstance());
    }
    
}