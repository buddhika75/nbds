package gov.health.bean;

/*
 * MSc(Biomedical Informatics) Project
 * 
 * Development and Implementation of a Web-based Combined Data Repository of 
 Genealogical, Clinical, Laboratory and Genetic Data 
 * and
 * a Set of Related Tools
 */
import gov.health.bean.*;
import gov.health.entity.Area;
import gov.health.facade.AbstractionFormFacade;
import gov.health.entity.AbstractionForm;
import gov.health.entity.Department;
import gov.health.entity.DysmorphologyExamination;
import gov.health.entity.Institution;
import gov.health.entity.LabTest;
import gov.health.entity.NotificationForm;
import gov.health.entity.Person;
import java.io.Serializable;
import java.util.ArrayList;
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
public class AbstractionFormController implements Serializable {

    @EJB
    private AbstractionFormFacade facade;

    @Inject
    SessionController sessionController;

    private AbstractionForm current;
    private List<AbstractionForm> items = null;
    String selectText = "";

    Institution institution;
    Area area;
    NotificationForm notificationForm;
    Department department;
    @Inject
    DepartmentController departmentController;
    @Inject
    AreaController areaController;

    public String addNewAbstractionForm() {
        current = new AbstractionForm();
        Person infant = new Person();
        Person mother = new Person();
        current.setMother(mother);
        current.setInfant(infant);
        Person jmo = new Person();
        Person mo = new Person();
        Person hoi = new Person();
        DysmorphologyExamination dysmorphologyExamination = new DysmorphologyExamination();
        current.setDysmorphologyExamination(dysmorphologyExamination);
        LabTest labTest = new LabTest();
        current.setLabTest(labTest);
        current.getLabTest().setJmo(jmo);
        current.getLabTest().setMo(mo);
        current.getLabTest().setHoi(hoi);
        //current.setInfant(infant);
        //current.setMother(mother);

        return "birth_diffect_abstraction_form";
    }

    public String addNewAbstractionFormFromNotificationForm() {
        if (notificationForm == null || notificationForm.getId() == null) {
            return "";
        }

        if (notificationForm.getAbstractionForm() == null) {
            current = new AbstractionForm();
            notificationForm.setAbstractionForm(current);
            current.setNotificationForm(notificationForm);

            DysmorphologyExamination dysmorphologyExamination = new DysmorphologyExamination();
            current.setDysmorphologyExamination(dysmorphologyExamination);

            LabTest labTest = new LabTest();
            current.setLabTest(labTest);

        } else {
            current = notificationForm.getAbstractionForm();
        }

        if (current.getLabTest() == null) {
            LabTest labTest = new LabTest();
            current.setLabTest(labTest);

        }

        current.setMother(notificationForm.getMother());
        current.setInfant(notificationForm.getInfant());
        current.setHospital(notificationForm.getHospital());
        current.setDistrict(notificationForm.getDistrict());
        current.setWard(notificationForm.getWard());
        current.setRdhsArea(notificationForm.getRdhsArea());
        current.setMohArea(notificationForm.getMohArea());
        current.setGnArea(notificationForm.getGnArea());
        current.setCaseIdentifiedDate(notificationForm.getCaseIdentifiedDate());
        current.setBhtno(notificationForm.getBhtNo());
        current.setDiagnosis(notificationForm.getDiagnosis());
        current.setDiagnosisDate(notificationForm.getDiagnosisDate());
        current.setTp1(notificationForm.getTp1());
        current.setTp2(notificationForm.getTp2());
        current.setLive(notificationForm.getLive());

        current.getLabTest().setInfantAodYrs(notificationForm.getInfantAodYrs());
        current.getLabTest().setInfantAodMnths(notificationForm.getInfantAodMnths());
        current.getLabTest().setInfantAodDys(notificationForm.getInfantAodDys());
        current.getLabTest().setPlaceOfDead(notificationForm.getPlaceOfDead());
        current.getLabTest().setPostMortem(notificationForm.getPostMortem());
        current.getLabTest().setJmo(notificationForm.getJmo());
        current.getLabTest().setUnderlyingCause(notificationForm.getUnderlyingCause());
        current.getLabTest().setImmediateCause(notificationForm.getImmediateCause());
        current.getLabTest().setConDeath(notificationForm.getConDeath());
        current.getLabTest().setHoi(notificationForm.getHoi());

        return "birth_diffect_abstraction_form";
    }

    public List<Department> completeOfficialDepartments(String qry) {
        System.out.println("Complete");
        System.out.println("current = " + current);
        if (current == null || current.getHospital() == null) {
            System.out.println("Null");
            return new ArrayList<Department>();
        } else {
            System.out.println("Hospital name" + current.getHospital().getName());
            getDepartmentController().setInstitution(current.getHospital());
            return getDepartmentController().completeInstitutionDepartments(qry);
        }

    }

    public List<Area> completeMohAreas(String qry) {
        if (current == null || current.getDistrict() == null) {
            return new ArrayList<Area>();
        } else {
            getAreaController().setSuperArea(current.getDistrict());
            return getAreaController().completeAreasUnderSuperArea(qry);
        }

    }

    public List<Area> completeGnAreas(String qry) {
        if (current == null || current.getMohArea() == null) {
            return new ArrayList<Area>();
        } else {
            getAreaController().setSuperArea(current.getMohArea());
            return getAreaController().completeSkipedAreasUnderSuperArea(qry);
        }
    }

    public List<Area> completeRdhsAreas(String qry) {
        if (current == null || current.getDistrict() == null) {
            return new ArrayList<Area>();
        } else {
            getAreaController().setSuperArea(current.getDistrict());
            return getAreaController().completeAreasUnderSuperArea(qry);
        }

    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public AbstractionFormController() {
    }

    public AbstractionForm getCurrent() {
        System.out.println("current = " + current);
        if (current == null) {
            current = new AbstractionForm();
            Person mother = new Person();
            Person infant = new Person();
            Person jmo = new Person();
            Person mo = new Person();
            Person hoi = new Person();
            current.setMother(mother);
            current.setInfant(infant);

            LabTest labTest = new LabTest();
            current.setLabTest(labTest);

            current.getLabTest().setJmo(jmo);
            current.getLabTest().setMo(mo);
            current.getLabTest().setHoi(hoi);
            DysmorphologyExamination dysmorphologyExamination = new DysmorphologyExamination();
            current.setDysmorphologyExamination(dysmorphologyExamination);

        }
        if (current.getDysmorphologyExamination() == null) {
            DysmorphologyExamination dysmorphologyExamination = new DysmorphologyExamination();
            current.setDysmorphologyExamination(dysmorphologyExamination);
        }
        if (current.getLabTest() == null) {
            Person jmo = new Person();
            Person mo = new Person();
            Person hoi = new Person();
            LabTest labTest = new LabTest();
            current.setLabTest(labTest);
            current.getLabTest().setJmo(jmo);
            current.getLabTest().setMo(mo);
            current.getLabTest().setHoi(hoi);
        }

        if (current.getInfant() == null) {
            Person i = new Person();
            current.setInfant(i);
        }

        if (current.getMother() == null) {
            Person m = new Person();
            current.setMother(m);
        }

        if (current.getLabTest().getJmo() == null) {
            Person j = new Person();
            current.getLabTest().setJmo(j);
        }
        if (current.getLabTest().getMo() == null) {
            Person m = new Person();
            current.getLabTest().setMo(m);
        }

        if (current.getLabTest().getHoi() == null) {
            Person h = new Person();
            current.getLabTest().setHoi(h);
        }

        return current;
    }

    public String listAll() {

        String jpql;

//        items = getFacade().findAll();
//        return "view_all_abstraction_form";
        Map m;
        m = new HashMap();
        if (getSessionController().getLoggedUser().getRestrictedInstitution() == null) {
            AbstractionForm a = new AbstractionForm();
            jpql = "select a from AbstractionForm a  Where a.retired=false order by a.id desc";
            System.out.println("m = " + m);
            System.out.println("jpql = " + jpql);
            items = getFacade().findBySQL(jpql);
        } else {
            m.put("h", getSessionController().getLoggedUser().getRestrictedInstitution());
            jpql = "select a from AbstractionForm a Where a.hospital=:h and a.retired=false order by a.id desc";
            System.out.println("m = " + m);
            System.out.println("jpql = " + jpql);
            items = getFacade().findBySQL(jpql, m);
        }

        return "view_all_abstraction_form";
    }

    public String listInstitutional() {
        String jpql;
        Map m;
        m = new HashMap();
        if (institution == null) {
            institution = getSessionController().getLoggedUser().getRestrictedInstitution();
        }
        m.put("h", institution);
        jpql = "select a from AbstractionForm a Where a.hospital=:h order by a.id";
        System.out.println("m = " + m);
        System.out.println("jpql = " + jpql);
        items = getFacade().findBySQL(jpql, m);

        return "registry_institution_abstraction_forms";
    }

    public String listDistrict() {
        String jpql;
        Map m;
        m = new HashMap();
        m.put("h", area);
        jpql = "select a from AbstractionForm a Where a.hospital.district=:h order by a.id";
        System.out.println("m = " + m);
        System.out.println("jpql = " + jpql);
        items = getFacade().findBySQL(jpql, m);
        return "registry_district_abstraction_forms";
    }

    public void setCurrent(AbstractionForm current) {
        this.current = current;
    }

    public List<AbstractionForm> getItems() {

        return items;
    }

    public void saveSelected() {
        if (getCurrent() == null) {
            JsfUtil.addErrorMessage("Error Current is Null");
            return;
        }
        NotificationForm n = current.getNotificationForm();
        current.setNotificationForm(null);

        if (current.getId() == null || current.getId() == 0) {
            getFacade().create(current);
        } else {
            getFacade().edit(current);
        }

        current.setNotificationForm(n);
        getFacade().edit(current);

        JsfUtil.addSuccessMessage("Saved");

    }

    public void retiredRecord() {
        if (current != null) {
            current.setRetired(true);
            current.setRetiredAt(Calendar.getInstance().getTime());
            current.setRetiredUser(getSessionController().getLoggedUser());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(new MessageProvider().getValue("deleteSuccessful"));
        } else {
            JsfUtil.addErrorMessage(new MessageProvider().getValue("nothingToDelete"));
        }
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public DepartmentController getDepartmentController() {
        return departmentController;
    }

    public void setDepartmentController(DepartmentController departmentController) {
        this.departmentController = departmentController;
    }

    public AreaController getAreaController() {
        return areaController;
    }

    public void setAreaController(AreaController areaController) {
        this.areaController = areaController;
    }

    public String getSelectText() {
        return selectText;
    }

    public void setSelectText(String selectText) {
        this.selectText = selectText;

    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public AbstractionFormFacade getFacade() {
        return facade;
    }

    public void setFacade(AbstractionFormFacade facade) {
        this.facade = facade;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public NotificationForm getNotificationForm() {
        return notificationForm;
    }

    public void setNotificationForm(NotificationForm notificationForm) {
        this.notificationForm = notificationForm;
    }

    @FacesConverter(forClass = AbstractionForm.class)
    public static class AbstractionFormControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AbstractionFormController controller;
            controller = (AbstractionFormController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "abstractionFormController");
            return controller.getFacade().find(getKey(value));
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
            if (object instanceof AbstractionForm) {
                AbstractionForm o = (AbstractionForm) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: " + AbstractionFormController.class.getName());
            }
        }
    }
}
