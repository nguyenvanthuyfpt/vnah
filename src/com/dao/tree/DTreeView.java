package com.dao.tree;


import com.dao.DSqlAdmin;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.categorys.FTinh;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DTreeView extends DSqlAdmin
{

    public FBeans getTree(Connection cnn, FBeans beans, int id, int level,int space,boolean showParent, String SQL, String characters, String member)
        throws EException
    {
        String LOCATION;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        LOCATION = (new StringBuilder()).append(toString()).append("getTree()").toString();        
        try
        {           
            prstm = cnn.prepareStatement(SQL);  
            prstm.setInt(1, id);
            prstm.executeQuery();
            FTinh bean;
            String nameParent ="";
            // System.out.println("SQL " + SQL);
            for(rs = prstm.executeQuery(); rs != null && rs.next(); getTree(cnn, beans, bean.getId(), level + 1,space, showParent, SQL, characters, member))
            {
                bean = new FTinh();
                bean.setId(rs.getInt(1));
                bean.setParentID(rs.getInt(2));
                bean.setName(rs.getString(3));
                bean.setLevel(level);
               
                bean.setParentName(0);
                if(beans.size() > 0)
                {
                    FTinh beant = new FTinh();
                    for(int k = 0; k < beans.size(); k++)
                    {
                        beant = (FTinh)beans.get(k);
                        if(bean.getParentID() == beant.getId()){
                            beant.setParentName(1);
                           
                            if (level%2>0){
                               if ((level==1))
                                    if(showParent)
                                        nameParent =  "--- " + beant.getName(); 
                                    else 
                                        nameParent =  "--- ";
                               else if ((level==3))
                                   if(showParent)
                                       nameParent =  "--- --- ---" + beant.getName(); 
                                   else 
                                       nameParent =  "--- --- ---";    
                               else 
                                    nameParent = beant.getName();    
                                    
                            } else if (level%2==0){
                                nameParent = "--- ";  
                                characters = "" ;  
                                space = beant.getLevel();
                                
                            } else if (level%3==0){
                                nameParent = "--- ";  
                                characters = "" ;  
                                space = beant.getLevel();
                                
                            }else {
                                nameParent = "" ;                                 
                                characters=""; 
                            }                            
                        }
                    }
                }
                   
                if (nameParent.equals("--- ")){
                    for(int i = 0; i < space; i++) {                        
                        nameParent = nameParent + "--- ";
                    }
                }
                   
                if (level>0){
                    bean.setName((new StringBuilder()).append(nameParent + characters).append(bean.getName()).toString());
                }
                 
                characters= showParent ? "/" : "";
                
                if(member.equals(""))
                    beans.add(bean);
                else
                    if(member.indexOf((new StringBuilder()).append(",").append(bean.getId()).append(",").toString()) >= 0)
                        beans.add(bean);
            }

        }
        catch(SQLException sqle)
        {
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
        }
        finally
        {
          closeResultSet(rs);
          closePreparedStatement(prstm);
        }
      
        return beans;
    }
    
    
    public FBeans getTreeList(Connection cnn, FBeans beans, int id, int level,int space, String SQL, String characters, String member)
        throws EException
    {
        String LOCATION;
        PreparedStatement prstm;
        ResultSet rs;
        
        //.println(SQL);
        LOCATION = (new StringBuilder()).append(toString()).append("getTree()").toString();
        prstm = null;
      
        rs = null;
        try
        {
            prstm = cnn.prepareStatement(SQL);
            prstm.setInt(1, id);
            FTinh bean;
            for(rs = prstm.executeQuery(); rs != null && rs.next(); getTreeList(cnn, beans, bean.getId(), level + 1,space, SQL, characters, member))
            {
                bean = new FTinh();
                bean.setId(rs.getInt(1));
                bean.setParentID(rs.getInt(2));
                bean.setName(rs.getString(3));
                bean.setLevel(level);
                
                for(int i = 0; i < level; i++)  bean.setName((new StringBuilder()).append(characters).append(bean.getName()).toString());
                    bean.setParentName(0);
                    if(beans.size() > 0){
                        FTinh beant = new FTinh();
                        for(int k = 0; k < beans.size(); k++)
                        {
                            beant = (FTinh)beans.get(k);
                            if(bean.getParentID() == beant.getId())
                                beant.setParentName(1);
                        }
                
                }
                if(member.equals(""))
                    beans.add(bean);
                else
                    if(member.indexOf((new StringBuilder()).append(",").append(bean.getId()).append(",").toString()) >= 0)
                    beans.add(bean); 
               
            }

        }
        catch(SQLException sqle)
        {
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
        }
        finally
        {
          closeResultSet(rs);
          closePreparedStatement(prstm);
        }
      
        return beans;
    }

    public String getTrees(FBeans inBeans, int id, String check, boolean isRoot)
    {
        String LOCATION = (new StringBuilder()).append(toString()).append("getTree()").toString();
        FTinh bean = null;
        String outBeans = "";
        for(int j = 0; j < inBeans.size(); j++)
        {
            bean = (FTinh)inBeans.get(j);
            if(bean.getParentID() == id)
            {
                if(isRoot)
                    outBeans = (new StringBuilder()).append(outBeans).append("<ul class=\"unorderedlisttree\" id=\"docheckchildren\">").toString();
                else
                    outBeans = (new StringBuilder()).append(outBeans).append("<ul class=\"unorderedlisttree\" id=\"docheckchildren\">").toString();
                if(check.indexOf((new StringBuilder()).append(",").append(bean.getId()).append(",").toString()) >= 0)
                {
                    outBeans = (new StringBuilder()).append(outBeans).append("<li><input name=\"areaTree[]\" value=\"").append(bean.getId()).append("\" type=\"checkbox\" checked><label>").append(bean.getName()).append("</label>").toString();
                    inBeans.remove(j);
                    j--;
                    outBeans = (new StringBuilder()).append(outBeans).append(getTrees(inBeans, bean.getId(), check, false)).toString();
                    outBeans = (new StringBuilder()).append(outBeans).append("</li></ul>").toString();
                } else
                {
                    outBeans = (new StringBuilder()).append(outBeans).append("<li><input name=\"areaTree[]\" value=\"").append(bean.getId()).append("\" type=\"checkbox\" ><label>").append(bean.getName()).append("</label>").toString();
                    inBeans.remove(j);
                    j--;
                    outBeans = (new StringBuilder()).append(outBeans).append(getTrees(inBeans, bean.getId(), check, false)).toString();
                    outBeans = (new StringBuilder()).append(outBeans).append("</li></ul>").toString();
                }
            }
        }

        return outBeans;
    }

    public DTreeView()
    {
    }
}
