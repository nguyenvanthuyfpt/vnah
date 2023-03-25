package com.startup;


import com.bo.admin.apps.BApps;
import com.bo.admin.departments.BDepartments;
import com.bo.admin.groups.BGroups;
import com.bo.admin.menu.BMenu;
import com.bo.admin.users.BUsers;

import com.exp.EException;

import com.form.admin.login.FLoginSystem;
import com.form.admin.users.FUser;

import com.lib.AppConfigs;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;


public class SAdmin {
    public boolean load(ActionForm form, ActionErrors errors) throws EException {
        boolean result = false;
        FLoginSystem bean = (FLoginSystem)form;
        HttpServletRequest request = bean.getRequest();
        try {
            BApps apps = new BApps();
            request.setAttribute("BApps", apps.getActiveRecord());
            BUsers users = new BUsers();
            FUser user = new FUser();
            user.setId(bean.getId());
            user = users.getRecordByID(user);
            request.setAttribute("BUser", user);
            BGroups groups = new BGroups();
            request.setAttribute("BGroups", groups.getAllRecord(user.getId()));
            BDepartments departments = new BDepartments();
            request.setAttribute("BDepartments", departments.getAllRecord((int)bean.me.getDepartmentID()));
            String SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ?";            
            String characters = "/ ";

            //FBeans beans = new FBeans();
            //beans = new BTreeView().getTree(0, true, SQL, characters, "");
            //request.setAttribute("BTreeTinhs", beans);
            if (user.getArea() == null) {
                user.setArea("");
            }
            if (AppConfigs.ADMIN_PANEL_ROLE)
                request.getSession().setAttribute("ADMIN.PANEL.ROLE", "TRUE");
            if (AppConfigs.ADMIN_PANEL_PRIVILEGE)
                request.getSession().setAttribute("ADMIN.PANEL.PRIVILEGE", "TRUE");
            if (AppConfigs.ADMIN_PANEL_DEPARTMENT)
                request.getSession().setAttribute("ADMIN.PANEL.DEPARTMENT", "TRUE");
            request.setAttribute("BMenus", new BMenu().getAllMenuUPermision((int)bean.me.getId(), 1));
            result = true;
        } catch (Exception ex) {
        }

        return result;
    }
}
