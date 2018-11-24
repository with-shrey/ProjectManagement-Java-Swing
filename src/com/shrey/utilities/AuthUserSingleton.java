package com.shrey.utilities;

import com.shrey.models.Project;
import com.shrey.models.User;

public class AuthUserSingleton {
    private static User user = null;

    public static  synchronized void userLoggedIn(User u){
        user = u;
        fetchUserDetails();
    }

    public static User getUser() {
        return user;
    }

    public static  synchronized void logout(User u){
        user = null;
    }

    public static void fetchUserDetails(){
        if (user.getType() == 1){
            user.setProject(Project.getProjects(user.getProjectId()));
        }else{
            user.setProject(Project.getProjects());
        }
    }
}
