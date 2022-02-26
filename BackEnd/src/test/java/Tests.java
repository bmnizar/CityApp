import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class Tests {
  private Authorization authorization;

  @Before
  public void setUp() {
    List<User> users = new ArrayList<User>();

    users.add(new User(1, "Anna Administrator", List.of("superuser")));
    users.add(new User(2, "Charles N. Charge", List.of("charger", "rider")));
    users.add(new User(7, "Ryder", List.of("rider")));
    users.add(new User(11, "Unregistered Ulysses", List.of()));
    users.add(new User(18, "Tessa Tester", List.of("beta tester")));

    List<Permission> permissions = new ArrayList<Permission>();

    permissions.add(new Permission("superuser", "lock user account", true));
    permissions.add(new Permission("superuser", "unlock user account", true));
    permissions.add(new Permission("superuser", "purchase widgets", false));
    permissions.add(new Permission("charger", "view pick up locations", true));
    permissions.add(new Permission("rider", "view my profile", true));
    permissions.add(new Permission("rider", "scooters near me", true));

    authorization = new Authorization(permissions, users);
  }

  @Test
  public void listPermissionsReturnsCorrectPermissionNamesWhenThereIsOneRole() {
    ArrayList<String> result = authorization.listPermissions(7);
    assertEquals(true, result.contains("view my profile"));
    assertEquals(true, result.contains("scooters near me"));
    assertEquals(2, result.size());
  }

  @Test
  public void checkPermittedReturnsTrueForAllOfThePermissionsThatExistForTheUser() {
    assertEquals(true, authorization.checkPermitted("view pick up locations", 2));
    assertEquals(true, authorization.checkPermitted("view my profile", 2));
    assertEquals(true, authorization.checkPermitted("scooters near me", 2));
  }
}

