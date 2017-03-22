
package bg.softuni.services;

import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceStubImpl implements LoginService {

    @Override
    public boolean authentice(String username, String password) {
        return Objects.equals(username, password);
    }
    
}
