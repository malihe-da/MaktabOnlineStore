package view;

import model.entity.Admin;
import service.AdminService;

import java.util.Scanner;

import static view.StoreMain.admins;

public  class AdminView {
    AdminService adminService = new AdminService();

    public void checkAdmin() {
        Scanner scn = new Scanner(System.in);
        Admin tryAdmin = null;
        boolean flag = false;
        while (true) {
            System.out.println("Please enter your user name:");
            String userName = scn.nextLine();
            for (Admin admin :
                    admins) {
                if (admin.getUserName().equals(userName)) {
                    tryAdmin = admin;
                    flag = true;
                }
            }
            if (flag == false)
                continue;

            System.out.println(userName + ", please enter your passWord:");
            String passWord = scn.nextLine();
            if (tryAdmin.getPassword().equals(passWord))
                break;
        }
        adminService.adminController(tryAdmin);

    }
}
