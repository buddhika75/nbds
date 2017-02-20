/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of 
 Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
package gov.health.bean;

import gov.health.data.AreaType;
import gov.health.facade.AreaFacade;
import gov.health.entity.Area;
import gov.health.entity.Institution;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@Named
@SessionScoped
public class AreaController implements Serializable {

    @EJB
    private AreaFacade ejbFacade;
    @Inject
    SessionController sessionController;
    List<Area> lstItems;
    private Area current;
    private List<Area> items = null;
    boolean selectControlDisable = false;
    boolean modifyControlDisable = true;
    String selectText = "";
    Area superArea;
    

    String bulkText;
    AreaType bulkAreaType;
    Area bulkParentArea;
    
    public void addBulkAreas() {
        if (bulkAreaType == null) {
            JsfUtil.addErrorMessage("Type?");
            return;
        }
        if (bulkParentArea == null) {
            JsfUtil.addErrorMessage("Parent Area?");
            return;
        }
        if (bulkText.trim().equals("")) {
            JsfUtil.addErrorMessage("Institutions?");
            return;
        }
        String lines[] = bulkText.split("\\r?\\n");

        int i =0;
        
        for (String line : lines) {
            if (!line.trim().equals("")) {
                i++;
                Area ba = new Area();
                ba.setName(line);
                ba.setSuperArea(bulkParentArea);
                ba.setAreaType(bulkAreaType);
                getFacade().create(ba);
            }
        }

        bulkText = "";
        bulkAreaType = null;
        bulkParentArea = null;
        JsfUtil.addSuccessMessage(i + " Areas added.");
    }
    
    Institution institution;

    public String getBulkText() {
        return bulkText;
    }

    public void setBulkText(String bulkText) {
        this.bulkText = bulkText;
    }

    public AreaType getBulkAreaType() {
        return bulkAreaType;
    }

    public void setBulkAreaType(AreaType bulkAreaType) {
        this.bulkAreaType = bulkAreaType;
    }

    public Area getBulkParentArea() {
        return bulkParentArea;
    }

    public void setBulkParentArea(Area bulkParentArea) {
        this.bulkParentArea = bulkParentArea;
    }

    
    
    public Area getSuperArea() {
        return superArea;
    }

    public void setSuperArea(Area superArea) {
        this.superArea = superArea;
    }

    public List<Area> getMappedAreas() {
        return mappedAreas;
    }

    public void setMappedAreas(List<Area> mappedAreas) {
        this.mappedAreas = mappedAreas;
    }

    public Area getCurrentMappingArea() {
        return currentMappingArea;
    }

    public void setCurrentMappingArea(Area currentMappingArea) {
        this.currentMappingArea = currentMappingArea;
    }

    
    
    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public Area findArea(String desName, boolean createNew) {
        desName = desName.trim();
        if (desName.equals("")) {
            return null;
        }
        Map m = new HashMap();
        m.put("n", desName.toLowerCase());
        Area des = getFacade().findFirstBySQL("select d from Area d where d.retired = false and lower(d.name) =:n", m);
        if (des == null && createNew == true) {
            des = new Area();
            des.setName(desName);
            des.setCreatedAt(Calendar.getInstance().getTime());
            des.setCreater(sessionController.getLoggedUser());
            getFacade().create(des);
        }
        return des;
    }

    public List<Area> completeArea(String qry) {
        Map m = new HashMap();
        m.put("n", "%" + qry.toLowerCase() + "%");
        List<Area> des = getFacade().findBySQL("select d from Area d where d.retired = false and lower(d.name) like :n", m);
        return des;
    }
    
    public List<Area> completeAreasUnderSuperArea(String qry) {
        Map m = new HashMap();
        m.put("n", "%" + qry.toLowerCase() + "%");
        m.put("sa", superArea);
        List<Area> des = getFacade().findBySQL("select d from Area d where d.retired = false and lower(d.name) like :n and d.superArea=:sa", m);
        return des;
    }
    
     public List<Area> completeSkipedAreasUnderSuperArea(String qry) {
        Map m = new HashMap();
        m.put("n", "%" + qry.toLowerCase() + "%");
        m.put("sa2", superArea);
        List<Area> des = getFacade().findBySQL("select d from Area d where d.retired = false and lower(d.name) like :n and d.superArea.superArea.superArea=:sa2", m);
        return des;
    }

    public List<Area> completeProvinces(String qry) {
        return completeAreaByType(qry, AreaType.Province);
    }

    public List<Area> completeDistricts(String qry) {
        
        return completeAreaByType(qry, AreaType.District);
    }

    public List<Area> completeMohs(String qry) {
        return completeAreaByType(qry, AreaType.Moh);
    }
    
    public List<Area> completePhis(String qry) {
        return completeAreaByType(qry, AreaType.Phi);
    }
    
    public List<Area> completePhms(String qry) {
        return completeAreaByType(qry, AreaType.Phm);
    }
    
    public List<Area> completeGns(String qry) {
        return completeAreaByType(qry, AreaType.Gn);
    }

     public List<Area> completeAreaByType(String qry, AreaType areaType) {
        Map m = new HashMap();
        m.put("n", "%" + qry.toLowerCase() + "%");
        m.put("at", areaType);
        List<Area> des = getFacade().findBySQL("select d from Area d where d.retired = false and d.areaType=:at and lower(d.name) like :n", m);
        return des;
    }

    List<Area> mappedAreas;

    Area currentMappingArea;

    public void recreateModel() {
        items = null;
    }

    public AreaController() {
    }

    public List<Area> getLstItems() {
        return getFacade().findBySQL("Select d From Area d order by d.name");
    }

    public void setLstItems(List<Area> lstItems) {
        this.lstItems = lstItems;
    }



    public Area getCurrent() {
        if (current == null) {
            current = new Area();
        }
        return current;
    }

    public void setCurrent(Area current) {
        this.current = current;
    }

    private AreaFacade getFacade() {
        return ejbFacade;
    }

    public List<Area> getItems() {
        items = getFacade().findAll("name", true);
        return items;
    }

    public static int intValue(long value) {
        int valueInt = (int) value;
        if (valueInt != value) {
            throw new IllegalArgumentException(
                    "The long value " + value + " is not within range of the int type");
        }
        return valueInt;
    }

    public List searchItems() {
        recreateModel();
        if (items == null) {
            if (selectText.equals("")) {
                items = (getFacade().findAll("name", true));
            } else {
                items = (getFacade().findAll("name", "%" + selectText + "%",
                        true));
                if (items.size() > 0) {
                    current = (Area) items.get(0);
                    Long temLong = current.getId();
                    
                } else {
                    current = null;
                   
                }
            }
        }
        return items;

    }

    public Area searchItem(String itemName, boolean createNewIfNotPresent) {
        Area searchedItem = null;
        items = (getFacade().findAll("name", itemName, true));
        if (items.size() > 0) {
            searchedItem = (Area) items.get(0);
        } else if (createNewIfNotPresent) {
            searchedItem = new Area();
            searchedItem.setName(itemName);
            searchedItem.setCreatedAt(Calendar.getInstance().getTime());
            searchedItem.setCreater(sessionController.getLoggedUser());
            getFacade().create(searchedItem);
        }
        return searchedItem;
    }

    public void prepareSelect() {
        this.prepareModifyControlDisable();
    }

    public void prepareEdit() {
        if (current != null) {
           
            this.prepareSelectControlDisable();
        } else {
            JsfUtil.addErrorMessage(new MessageProvider().getValue("nothingToEdit"));
        }
    }

    public void prepareAdd() {
       
        current = new Area();
        this.prepareSelectControlDisable();
    }
    
    public void prepareAddDistrict(){
        prepareAdd(AreaType.District);
    }
    
     public void prepareAddProvince(){
        prepareAdd(AreaType.Province);
    }
     
      public void prepareAddMoh(){
        prepareAdd(AreaType.Moh);
    }
      
       public void prepareAddPhm(){
        prepareAdd(AreaType.Phm);
    }
       
         public void prepareAddPhi(){
        prepareAdd(AreaType.Phi);
    }
       
           public void prepareAddGn(){
        prepareAdd(AreaType.Gn);
    }
    
    public void prepareAdd(AreaType areaType) {
        current = new Area();
        current.setAreaType(areaType);
        this.prepareSelectControlDisable();
    }

    public void delete() {

        if (current != null) {
            current.setRetired(true);
            current.setRetiredAt(Calendar.getInstance().getTime());
            current.setRetirer(sessionController.getLoggedUser());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(new MessageProvider().getValue("deleteSuccessful"));
        } else {
            JsfUtil.addErrorMessage(new MessageProvider().getValue("nothingToDelete"));
        }
        recreateModel();
        getItems();
        selectText = "";
        current = null;
        this.prepareSelect();
    }
    
    public void saveSelected() {

        if (current.getId()!=null && current.getId()!= 0) {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(new MessageProvider().getValue("savedOldSuccessfully"));
        } else {
            current.setCreatedAt(Calendar.getInstance().getTime());
            current.setCreater(sessionController.getLoggedUser());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(new MessageProvider().getValue("savedNewSuccessfully"));
        }
        this.prepareSelect();
        recreateModel();
        getItems();
        selectText = "";
      
    }

    public void addDirectly() {
        JsfUtil.addSuccessMessage("1");
        try {

            current.setCreatedAt(Calendar.getInstance().getTime());
            current.setCreater(sessionController.getLoggedUser());

            getFacade().create(current);
            JsfUtil.addSuccessMessage(new MessageProvider().getValue("savedNewSuccessfully"));
            current = new Area();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Error");
        }

    }

    public void cancelSelect() {
        this.prepareSelect();
    }

    

    public boolean isModifyControlDisable() {
        return modifyControlDisable;
    }

    public void setModifyControlDisable(boolean modifyControlDisable) {
        this.modifyControlDisable = modifyControlDisable;
    }

    public boolean isSelectControlDisable() {
        return selectControlDisable;
    }

    public void setSelectControlDisable(boolean selectControlDisable) {
        this.selectControlDisable = selectControlDisable;
    }

    public String getSelectText() {
        return selectText;
    }

    public void setSelectText(String selectText) {
        this.selectText = selectText;
        searchItems();
    }

    public void prepareSelectControlDisable() {
        selectControlDisable = true;
        modifyControlDisable = false;
    }

    public void prepareModifyControlDisable() {
        selectControlDisable = false;
        modifyControlDisable = true;
    }

    public AreaFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(AreaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public AreaType[] getAreaTypes(){
        return AreaType.values();
    }
    
    public AreaType[] getUserAreaTypes(){
        AreaType[] ats = new AreaType[] {AreaType.Moh, AreaType.Phi, AreaType.Phm , AreaType.Gn};
        return ats;
    }
    
    
    
    @FacesConverter("areaConverter")
    public static class AreaConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AreaController controller = (AreaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "areaController");
            return controller.getEjbFacade().find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Area) {
                Area o = (Area) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: " + AreaController.class.getName());
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FacesConverter(forClass = Area.class)
    public static class AreaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AreaController controller = (AreaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "areaController");
            return controller.getEjbFacade().find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Area) {
                Area o = (Area) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: " + AreaController.class.getName());
            }
        }
    }
}
