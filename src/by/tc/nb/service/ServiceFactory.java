package by.tc.nb.service;


import by.tc.nb.service.impl.TestServiceImpl;
import by.tc.nb.service.impl.TutorServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private TestService tstService = new TestServiceImpl();
    private TutorService tutService = new TutorServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public TestService getTstService() {
        return tstService;
    }

    public TutorService getTutService() {
        return tutService;
    }
}
