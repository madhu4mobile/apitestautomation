import helpers.UserServiceHelper;
import model.User;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import static org.testng.Assert.assertNotNull;

public class TestGetUser {

    private UserServiceHelper userServiceHelper;

    @BeforeClass
    public void init() {userServiceHelper = new UserServiceHelper(); }

    @Test
    public void testGetUser(){
        User user = userServiceHelper.getUser();

        Assert.assertNotNull(user, "User object is not null");
    }

}
