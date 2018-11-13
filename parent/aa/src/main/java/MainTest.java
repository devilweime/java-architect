import service.PassportService;
import service.impl.PassportServiceImpl;

public class MainTest {

    public static void main(String[] args) {
        PassportService passportService = new PassportServiceImpl();
        passportService.login("wgw");
    }
}
