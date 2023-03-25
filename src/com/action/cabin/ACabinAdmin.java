package com.action.cabin;


import com.action.ACore;

import com.bo.admin.users.BUsers;
import com.bo.cabin.BCabin;
import com.bo.cabin.cabinType.BCabinType;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.users.FUser;
import com.form.cabin.FCabin;

import com.inf.cabin.IKeyCabin;

import com.lib.AppConfigs;

import java.io.File;
import java.io.IOException;

import java.net.URLEncoder;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ACabinAdmin extends ACore {
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws EException, IOException, ServletException, SQLException {
		final String LOCATION = this + "->executeAction()";
		String target = _SUCCESS;
		FCabin bean = (FCabin) form;
		String anchor = bean.getValue(APP_ANCHOR, "");
		bean.setType(IKeyCabin.CABIN_PUBLIC);
		ActionErrors errors = new ActionErrors();
		BCabin bo = new BCabin();
		target = validate(bean, anchor, errors);
		if (!errors.isEmpty()) {
			if (anchor.equals(_EDIT) || anchor.equals(_CREATE)) {
				target = _PREPARE;
			}
		} else if (anchor.equals(_EDIT)) {
			String dirs = AppConfigs.APP_SYSTEM_PATH
					+ AppConfigs.CABIN_FILE_PATH + AppConfigs.SYSTEM_FILE_SCHIP;
			(new File(dirs)).mkdirs();
			String filename = "";
			boolean haveFile = bean.getUpFile().getFileSize() > 0;
			if (haveFile) {
				filename = bean.me.getUsername() + AppConfigs.SYSTEM_FILE_SCHIP
						+ encodeFileName(bean.me.getId());
				bean.upload(bean.getUpFile(), dirs + filename);
				bean.setFileStore(filename);
				bean.setRealName(new String((bean.getUpFile().getFileName()
						.getBytes()), "UTF-8"));
				if (bo.update(bean)) {
					errors.add("alert", new ActionError(
							"alert.cabin.update.successfull"));
				} else {
					errors.add("alert", new ActionError(
							"errors.cabin.insert.exitsName"));
				}
			} else {
				FCabin beantemp = bo.getById(bean.getId());
				bean.setFileStore(beantemp.getFileStore());
				bean.setRealName(beantemp.getRealName());
				if (bo.update(bean)) {
					errors.add("alert", new ActionError(
							"alert.cabin.update.successfull"));
				} else {
					errors.add("alert", new ActionError(
							"errors.cabin.insert.exitsName"));
				}
			}
			bean.setType(IKeyCabin.CABIN_PUBLIC);
			request.setAttribute("BCabins", new BCabin().getAllByType(bean));
			request.setAttribute("BCabinTypes", new BCabinType().getAll(
					me.getId(), IKeyCabin.CABIN_PUBLIC,
					(int) me.getDepartmentID()));
			request.setAttribute("cabinList", bean);
			target = _SUCCESS;
		} else if (anchor.equals(_DOWNLOAD)) {
			FCabin beanTemp = new FCabin();
			beanTemp = bo.getById(bean.getId());
			BUsers boUsers = new BUsers();
			FUser beanUsers = new FUser();
			beanUsers.setId(beanTemp.getUserId());
			beanUsers = boUsers.getRecordByID(beanUsers);
			String filePath = "";
			if (beanTemp.getFileStore() != null
					&& !beanTemp.getFileStore().equals("")) {
				filePath = AppConfigs.APP_SYSTEM_PATH
						+ AppConfigs.CABIN_FILE_PATH
						+ AppConfigs.SYSTEM_FILE_SCHIP
						+ beanTemp.getFileStore();

			}
			File file = new File(filePath);
			if (file.exists()) {
				bean.download(filePath,
						URLEncoder.encode(beanTemp.getRealName(), "UTF-8"),
						null);
			} else {
				bean.download(AppConfigs.APP_SYSTEM_PATH
						+ AppConfigs.DOC_NOIMAGER_PATH_FILE, "NoFile", null);
			}
		} else if (anchor.equals(_PREPARED_EDIT)) {
			FCabin beantemp = new FCabin();
			beantemp = bo.getById(bean.getId());
			request.setAttribute("BCabinTypes", new BCabinType().getAll(
					me.getId(), IKeyCabin.CABIN_PUBLIC,
					(int) me.getDepartmentID()));
			request.setAttribute("cabinAdmin", beantemp);
			target = _PREPARE;

		} else if (anchor.equals(_PREPARED_CREATE)) {
			bean.reset();
			bean.setUserId((int) bean.me.getId());
			bean.setId(0);
			request.setAttribute("BCabinTypes", new BCabinType().getAll(
					me.getId(), IKeyCabin.CABIN_PUBLIC,
					(int) me.getDepartmentID()));
			request.setAttribute("cabinList", bean);
			target = _PREPARE;
		} else if (anchor.equals(_CREATE)) {

			String dirs = AppConfigs.APP_SYSTEM_PATH
					+ AppConfigs.CABIN_FILE_PATH + AppConfigs.SYSTEM_FILE_SCHIP;
			(new File(dirs)).mkdirs();
			String filename = "";
			boolean haveFile = bean.getUpFile().getFileSize() > 0;
			if (haveFile) {
				filename = bean.me.getUsername() + AppConfigs.SYSTEM_FILE_SCHIP
						+ encodeFileName(bean.me.getId());
				bean.upload(bean.getUpFile(), dirs + filename);
				bean.setFileStore(filename);
				bean.setRealName(new String((bean.getUpFile().getFileName()
						.getBytes()), "UTF-8"));
			}
			if (bo.insert(bean)) {
				errors.add("alert", new ActionError(
						"alert.cabin.insert.successfull"));
			} else {
				errors.add("alert", new ActionError(
						"errors.cabin.insert.exitsName"));
			}

			request.setAttribute("BCabinTypes", new BCabinType().getAll(
					me.getId(), IKeyCabin.CABIN_PUBLIC,
					(int) me.getDepartmentID()));
			bean.setType(IKeyCabin.CABIN_PUBLIC);
			request.setAttribute("BCabins", new BCabin().getAllByType(bean));
			request.setAttribute("cabinList", bean);
			target = _SUCCESS;

		} else if (anchor.equals(_SHOW)) {

			if (bean.getDepartmentID() > 0) {
				FBeans beans = new BUsers().getUserByDepartmentID(
						bean.getDepartmentID(), 0);
				if (beans != null && beans.size() > 0) {
					request.setAttribute("BUsersDep", beans);
				}
			}
			target = _SHOW;
		} else if (anchor.equals(_DELETE)) {

			if (bo.delete(bean.getId())) {
				errors.add("alert", new ActionError(
						"alert.cabin.delete.successfull"));
			} else {
				errors.add("alert", new ActionError("errors.cabin.delete.fail"));
			}

			request.setAttribute("BCabins", bo.getAllByType(bean));
			request.setAttribute("BCabinTypes", new BCabinType().getAll(
					me.getId(), IKeyCabin.CABIN_PUBLIC,
					(int) me.getDepartmentID()));
			request.setAttribute("cabinList", bean);
			target = _SUCCESS;

		} else if (anchor.equals(_SEARCH)) {
			request.setAttribute("cabinList", bean);
			anchor = _SUCCESS;
		}
		if (anchor.equals(_SUCCESS)) {
			request.setAttribute("BCabins", bo.getAllByType(bean));
			request.setAttribute("BCabinTypes", new BCabinType().getAll(
					me.getId(), bean.getType(), (int) me.getDepartmentID()));
			target = _SUCCESS;
		}
		if (!errors.isEmpty())
			saveErrors(request, errors);
		return mapping.findForward(target);
	}

	private String validate(FCabin bean, String anchor, ActionErrors errors) {
		if (anchor.equals(_EDIT) || anchor.equals(_CREATE)) {
			if (bean.getName().trim().equals(_BLANK)) {
				errors.add("alert", new ActionError(
						"errors.cabin.edit.name.null"));
			}
		}
		return anchor;
	}

	private String encodeFileName(long userID) {
		return userID + "." + System.currentTimeMillis();
	}

}
