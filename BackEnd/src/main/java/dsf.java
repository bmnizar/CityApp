import java.util.*;

class Permission {
	public Permission() {
	}

	public Permission(String role, String name, boolean active) {
		this.role = role;
		this.name = name;
		this.active = active;
	}

	public String role;
	public String name;
	public boolean active;
}

class User {
	public User() {
	}

	public User(int id, String name, List<String> roles) {
		this.id = id;
		this.name = name;
		this.roles = roles;
	}

	public int id;
	public String name;
	public List<String> roles;
}

class Authorization {
	public List<Permission> permissions;
	public List<User> users;

	public Authorization(List<Permission> permissions, List<User> users) {
		this.permissions = permissions;
		this.users = users;
	}

	/**
	 * List Permissions Names
	 * 
	 * @param userId
	 * @return
	 */
	public ArrayList<String> listPermissions(int userId) {
		ArrayList<String> listOfPermissionsRelativeToUser = new ArrayList<>();
		User foundUser = findUser(userId);
		if (foundUser == null) {
			return listOfPermissionsRelativeToUser;
		}
//user Found
		List<String> userRoles = foundUser.roles;
		for (String userRole : userRoles) {
			List<String> findRolePermission = findRolePermissions(permissions, userRole);

			listOfPermissionsRelativeToUser.addAll(findRolePermission);

		}
		return listOfPermissionsRelativeToUser;
	}

	/**
	 * Find the user
	 * 
	 * @param userId
	 * @return
	 */
	private User findUser(int userId) {
		for (User user : users) {
			if (user.id == userId) {
				return user;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param permissions
	 * @param userRole
	 * @return ist<String>
	 */
	private List<String> findRolePermissions(List<Permission> permissions, String userRole) {
		List<String> listOfPermissions = new ArrayList<>();
		for (Permission permission : permissions) {
			if (permission.role.equals(userRole) && permission.active) {
				listOfPermissions.add(permission.name);
			}
		}
		return listOfPermissions;
	}

	/**
	 * Check that the user has that permissionName
	 * 
	 * @param permissionName
	 * @param userId
	 * @return
	 */
	public boolean checkPermitted(String permissionName, int userId) {
		User foundUser = findUser(userId);
		if (foundUser == null) {
			return false;
		}
		ArrayList<String> listPermissionsOfTheUser = listPermissions(foundUser.id);
		return listPermissionsOfTheUser.contains(permissionName);
	}
}