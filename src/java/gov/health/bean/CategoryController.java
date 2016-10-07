package gov.health.bean;

import gov.health.entity.Category;
import gov.health.bean.util.JsfUtil;
import gov.health.bean.util.JsfUtil.PersistAction;
import gov.health.data.CategoryType;
import gov.health.facade.CategoryFacade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("categoryController")
@SessionScoped
public class CategoryController implements Serializable {

    @EJB
    private gov.health.facade.CategoryFacade ejbFacade;
    private List<Category> items = null;
    private List<Category> icd10s = null;
    private List<Category> rcpchExtensions = null;
    private List<Category> relationsships = null;
    private List<Category> investigations = null;
    private List<Category> surgicalProcedures = null;

    private Category selected;

    public List<Category> getIcd10s() {
        if (icd10s == null) {
            icd10s = getCategoryByType(CategoryType.ICD10);
        }
        return icd10s;
    }

    public void setIcd10s(List<Category> icd10s) {
        this.icd10s = icd10s;
    }

    public List<Category> getCategoryByType(CategoryType type) {
        String j;
        Map m = new HashMap();
        j = "select c "
                + " from Category c "
                + " where c.retired=:ret "
                + " and c.type=:type "
                + " order by c.name";
        m.put("ret", false);
        m.put("type", type);
        return getFacade().findBySQL(j, m);
    }

    public List<Category> getRcpchExtensions() {
        if (rcpchExtensions == null) {
            rcpchExtensions = getCategoryByType(CategoryType.RCPCH_Extension);
        }
        return rcpchExtensions;
    }

    public void setRcpchExtensions(List<Category> rcpchExtensions) {
        this.rcpchExtensions = rcpchExtensions;
    }

    public List<Category> getRelationsships() {
        if (relationsships == null) {
            relationsships = getCategoryByType(CategoryType.ICD10);
        }
        return relationsships;
    }

    public void setRelationsships(List<Category> relationsships) {
        this.relationsships = relationsships;
    }

    public List<Category> getInvestigations() {
        if (investigations == null) {
            investigations = getCategoryByType(CategoryType.ICD10);
        }
        return investigations;
    }

    public void setInvestigations(List<Category> investigations) {
        this.investigations = investigations;
    }

    public List<Category> getSurgicalProcedures() {
        if (surgicalProcedures == null) {
            surgicalProcedures = getCategoryByType(CategoryType.SurgicalProcedure);
        }
        return surgicalProcedures;
    }

    public void setSurgicalProcedures(List<Category> surgicalProcedures) {
        this.surgicalProcedures = surgicalProcedures;
    }

    public CategoryController() {
    }

    public Category getSelected() {
        return selected;
    }

    public void setSelected(Category selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CategoryFacade getFacade() {
        return ejbFacade;
    }

    public Category prepareCreate() {
        selected = new Category();
        initializeEmbeddableKey();
        return selected;
    }

    public void makeItemsNull() {
        icd10s = null;
        rcpchExtensions = null;
        relationsships = null;
        investigations = null;
        surgicalProcedures = null;
        items = null;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleCat").getString("CategoryCreated"));
        if (!JsfUtil.isValidationFailed()) {
            makeItemsNull();
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleCat").getString("CategoryUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleCat").getString("CategoryDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            makeItemsNull();   // Invalidate list of items to trigger re-query.
        }
    }

    public List<Category> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleCat").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleCat").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Category getCategory(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Category> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Category> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Category.class)
    public static class CategoryControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CategoryController controller = (CategoryController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "categoryController");
            return controller.getCategory(getKey(value));
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
            if (object instanceof Category) {
                Category o = (Category) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Category.class.getName()});
                return null;
            }
        }

    }

}
