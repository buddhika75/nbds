package gov.health.bean;

import gov.health.data.AreaType;
import gov.health.data.NotificationCategoryType;
import gov.health.entity.Area;
import gov.health.entity.Department;
import gov.health.facade.NotificationFormFacade;
import gov.health.entity.NotificationForm;
import gov.health.entity.Institution;
import gov.health.entity.NotificationCategory;
import gov.health.entity.Person;
import gov.health.facade.AreaFacade;
import gov.health.facade.NotificationCategoryFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.TemporalType;

/**
 *
 * @author Dr. M. H. B. Ariyaratne, MBBS, PGIM Trainee for MSc(Biomedical
 * Informatics)
 */
@Named
@SessionScoped
public class NotificationFormController implements Serializable {

    @EJB
    private NotificationFormFacade facade;
    @EJB
    NotificationCategoryFacade notificationCategoryFacade;
    @EJB
    AreaFacade areaFacade;

    @Inject
    SessionController sessionController;

    private NotificationForm current;
    private List<NotificationForm> items = null;
    String selectText = "";

    Institution institution;
    Area area;
    Department department;
    @Inject
    DepartmentController departmentController;
    @Inject
    AreaController areaController;
    Date fromDate;
    Date toDate;

    NotificationCategory congenital_Abnormality;
    NotificationCategory family_History_Of_Congenital_Abnormality;
    NotificationCategory prenatal_Antenatal_Postnatal_Investigation;
    NotificationCategory therapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomaly;
    NotificationCategory underlyning_Cause_of_Death;
    NotificationCategory immediate_Cause_of_Death;
    NotificationCategory conditions_Contributing_to_Death;

    NotificationCategory removingNotificationCategory;

    public void delete(){
        if(current==null){
            return;
        }
        getFacade().remove(current);
        listAllNationalNotificationForms();
        JsfUtil.addSuccessMessage("Deleted");
    }

    public List<Area> completeMohs(String qry) {
        Map m = new HashMap();
        m.put("n", "%" + qry.toLowerCase() + "%");
        m.put("at", AreaType.Moh);
        m.put("sa", getCurrent().getResidence_Rdhs());
        List<Area> des = getAreaFacade().findBySQL("select d from Area d where d.superArea=:sa and d.retired = false and d.areaType=:at and lower(d.name) like :n", m);
        return des;
    }
    
    public List<Area> completeGnas(String qry) {
        Map m = new HashMap();
        m.put("n", "%" + qry.toLowerCase() + "%");
        m.put("at", AreaType.Gn);
        m.put("sa", getCurrent().getResidence_Moh());
        List<Area> des = getAreaFacade().findBySQL("select d from Area d where d.superArea=:sa and d.retired = false and d.areaType=:at and lower(d.name) like :n", m);
        return des;
    }

    public void removeNotificationCategory() {
        if (removingNotificationCategory == null) {
            JsfUtil.addErrorMessage("Please select");
            return;
        }
        for (NotificationCategory c : current.getCon_ab()) {
            if (c.equals(removingNotificationCategory)) {
                current.getCon_ab().remove(c);
                removingNotificationCategory = null;
                JsfUtil.addSuccessMessage("Removed");
                return;
            }
        }
        removingNotificationCategory = null;
        JsfUtil.addErrorMessage("Nothing to Remove");
    }

    public void createCreatedDates() {
        List<NotificationForm> nfs = getFacade().findAll();
        for (NotificationForm n : nfs) {
            n.setCreatedDate(n.getCreatedAt());
            getFacade().edit(n);
            System.out.println("n = " + n);
        }
        JsfUtil.addSuccessMessage("Converted");
    }

    public void addConditionsContributingToDeath() {
        getCurrent().getCon_ab().add(conditions_Contributing_to_Death);
        saveSelected();
        conditions_Contributing_to_Death = new NotificationCategory();
        conditions_Contributing_to_Death.setType(NotificationCategoryType.Causes_contributing_to_death);
    }

    public void addImmediateCauseOfDeath() {
        getCurrent().getCon_ab().add(immediate_Cause_of_Death);
        saveSelected();
        immediate_Cause_of_Death = new NotificationCategory();
        immediate_Cause_of_Death.setType(NotificationCategoryType.Immediate_courses_of_death);
    }

    public void addUnderlyingCauseOfDeath() {
        getCurrent().getCon_ab().add(underlyning_Cause_of_Death);
        saveSelected();
        underlyning_Cause_of_Death = new NotificationCategory();
        underlyning_Cause_of_Death.setType(NotificationCategoryType.Underlying_Courses_of_death);
    }

    public void addIx() {
        getCurrent().getCon_ab().add(prenatal_Antenatal_Postnatal_Investigation);
        saveSelected();
        prenatal_Antenatal_Postnatal_Investigation = new NotificationCategory();
        prenatal_Antenatal_Postnatal_Investigation.setType(NotificationCategoryType.Investigations);
    }

    public void addCongenitalAbnormality() {
        saveSelected();
        getCurrent().getCon_ab().add(congenital_Abnormality);
        congenital_Abnormality = new NotificationCategory();
        congenital_Abnormality.setType(NotificationCategoryType.Congenital_Adnormalities);
    }

    public void addFamilyHistory() {
        getCurrent().getCon_ab().add(family_History_Of_Congenital_Abnormality);
        saveSelected();
        family_History_Of_Congenital_Abnormality = new NotificationCategory();
        family_History_Of_Congenital_Abnormality.setType(NotificationCategoryType.FamilyHistory);
    }

    public String registerNotificationForm() {
        if (current == null) {
            JsfUtil.addErrorMessage("Select a Form");
            return "";
        }
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Asia/Colombo"));
        current.setRegistered(true);
        current.setRegisteredUser(getSessionController().getLoggedUser());
        current.setRegisteredAt(c.getTime());
        current.setRegisteredNumber(newRegNumber());

        getFacade().edit(current);

        JsfUtil.addSuccessMessage("Registered");

        return "view_registered_notification_form";
    }

    public String newRegNumber() {
        String year;
        String insCode;
        Long yearCount;
        String jpql;
        Map m = new HashMap();

        Calendar fd = Calendar.getInstance();
        fd.set(Calendar.MONTH, 0);
        fd.set(Calendar.DATE, 1);

        Calendar td = Calendar.getInstance();
        td.set(Calendar.YEAR, td.get(Calendar.YEAR) + 1);
        fd.set(Calendar.MONTH, 0);
        fd.set(Calendar.DATE, 1);

        m.put("fd", fd.getTime());
        m.put("td", td.getTime());

        jpql = "select count(n) from NotificationForm n where n.registered=true and n.registeredAt between :fd and :td";

        yearCount = getFacade().findLongByJpql(jpql, m, TemporalType.DATE);

        Calendar cal = Calendar.getInstance();

        year = cal.get(Calendar.YEAR) + "";
        insCode = current.getHospital().getCode();

        return year + "/" + insCode + "/" + yearCount;

    }

    public String listRegForms() {
        Map m = new HashMap();
        m.put("fwdt", fromDate);
        m.put("tdt", toDate);
        String jpql;
        if (getSessionController().getLoggedUser().getRestrictedInstitution() != null) {
            institution = getSessionController().getLoggedUser().getRestrictedInstitution();
        }
        if (institution == null) {
            jpql = "select n from NotificationForm n where n.retired=false and n.registered=true and n.createdDate between :fwdt and :tdt order by n.id desc";
        } else {
            jpql = "select n from NotificationForm n where n.retired=false and n.registered=true and n.hospital=:hsptl and n.createdDate between :fwdt and :tdt order by n.id desc";
            m.put("hsptl", institution);
        }
        items = getFacade().findBySQL(jpql, m, TemporalType.DATE);
        return "view_all_registered_notification_forms";
    }

    public String listUserNotificationForms() {
        TimeZone.setDefault(TimeZone.getTimeZone("ASIA/COLOMBO"));
        Map m = new HashMap();
        m.put("fd", JsfUtil.getDayStart(getFromDate()));
        m.put("td", JsfUtil.gmtToColombo(JsfUtil.getDayEnd(getToDate())));
        String jpql = "select n from NotificationForm n where n.retired=false  and n.createdUser=:cu and n.createdDate between :fd and :td order by n.id desc";
        m.put("cu", getSessionController().getLoggedUser());
        items = getFacade().findBySQL(jpql, m);
        return "/my_notifications";
    }
    
    public String listAllNationalNotificationForms() {
        if(sessionController.superUser || sessionController.sysAdmin){
            JsfUtil.addErrorMessage("You are NOT autherized to view this report");
            return "/index";
        }
        TimeZone.setDefault(TimeZone.getTimeZone("ASIA/COLOMBO"));
        Map m = new HashMap();
        m.put("fd", JsfUtil.getDayStart(getFromDate()));
        m.put("td", JsfUtil.gmtToColombo(JsfUtil.getDayEnd(getToDate())));
        String jpql = "select n from NotificationForm n where n.retired=false  "
                + " and n.createdDate between :fd and :td order by n.id desc";
        items = getFacade().findBySQL(jpql, m);
        return "/report_national_notification_list";
    }
    
    public String listAllResidantDistrictNotificationForms() {
        if(sessionController.superUser || sessionController.sysAdmin){
            JsfUtil.addErrorMessage("You are NOT autherized to view this report");
            return "/index";
        }
        TimeZone.setDefault(TimeZone.getTimeZone("ASIA/COLOMBO"));
        Map m = new HashMap();
        m.put("dis", getArea());
        m.put("fd", JsfUtil.getDayStart(getFromDate()));
        m.put("td", JsfUtil.gmtToColombo(JsfUtil.getDayEnd(getToDate())));
        String jpql = "select n from NotificationForm n "
                + " where n.retired=false  "
                + " and n.residence_Rdhs=:dis "
                + " and n.createdDate between :fd and :td "
                + " order by n.id desc";
        items = getFacade().findBySQL(jpql, m);
        return "/report_resident_district_notification_list";
    }
    
    public String listAllInstitutionDistrictNotificationForms() {
        if(sessionController.superUser || sessionController.sysAdmin){
            JsfUtil.addErrorMessage("You are NOT autherized to view this report");
            return "/index";
        }
        TimeZone.setDefault(TimeZone.getTimeZone("ASIA/COLOMBO"));
        Map m = new HashMap();
        m.put("dis", getArea());
        m.put("fd", JsfUtil.getDayStart(getFromDate()));
        m.put("td", JsfUtil.gmtToColombo(JsfUtil.getDayEnd(getToDate())));
        String jpql = "select n from NotificationForm n "
                + " where n.retired=false  "
                + " and n.hospital.district=:dis "
                + " and n.createdDate between :fd and :td "
                + " order by n.id desc";
        items = getFacade().findBySQL(jpql, m);
        return "/report_institution_district_notification_list";
    }

    public String listUserHospitalNotificationForms() {
        Map m = new HashMap();
        m.put("fd", fromDate);
        m.put("td", toDate);
        String jpql = "select n from NotificationForm n where n.retired=false  and n.hospital=:cu and n.createdDate between :fd and :td order by n.id desc";
        m.put("cu", getSessionController().getLoggedUser().getRestrictedInstitution());
        items = getFacade().findBySQL(jpql, m, TemporalType.DATE);
        return "/my_hospital_notifications";
    }

    public String listUnregForms() {
        Map m = new HashMap();
        m.put("fd", fromDate);
        m.put("td", toDate);
        String jpql;
        if (getSessionController().getLoggedUser().getRestrictedInstitution() != null) {
            institution = getSessionController().getLoggedUser().getRestrictedInstitution();
        }
        if (institution == null) {
            jpql = "select n from NotificationForm n where n.retired=false and n.registered=false and n.createdDate between :fd and :td order by n.id desc";
        } else {
            jpql = "select n from NotificationForm n where n.retired=false and n.registered=false and n.hospital=:hos and n.createdDate between :fd and :td order by n.id desc";
            m.put("hos", institution);
        }
        items = getFacade().findBySQL(jpql, m, TemporalType.DATE);
        return "view_all_unregistered_notification_forms";
    }

    public Date getFromDate() {
        if (fromDate == null) {
            Calendar c = Calendar.getInstance();
            Calendar today = Calendar.getInstance();
            c.set(Calendar.YEAR, today.get(Calendar.YEAR));
            c.set(Calendar.MONTH, c.getActualMinimum(Calendar.MONTH));
            c.set(Calendar.DATE, c.getActualMinimum(Calendar.DATE));
            c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
            c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));
            c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));
            c.set(Calendar.MILLISECOND, c.getActualMinimum(Calendar.MILLISECOND));
            fromDate = c.getTime();
        }
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        if (toDate == null) {
            Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Asia/Colombo"));
            c.set(Calendar.HOUR_OF_DAY, c.getActualMaximum(Calendar.HOUR_OF_DAY));
            c.set(Calendar.MINUTE, c.getActualMaximum(Calendar.MINUTE));
            c.set(Calendar.SECOND, c.getActualMaximum(Calendar.SECOND));
            c.set(Calendar.MILLISECOND, c.getActualMaximum(Calendar.MILLISECOND));
            toDate = c.getTime();
        }
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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

    public DepartmentController getDepartmentController() {
        return departmentController;
    }

    public void setDepartmentController(DepartmentController departmentController) {
        this.departmentController = departmentController;
    }

    public String listAll() {
        String jpql;
        System.out.println("getSessionController() = " + getSessionController());
        System.out.println("getSessionController().loggedUser = " + getSessionController().getLoggedUser());

        if (getSessionController().getLoggedUser().getRestrictedInstitution() == null) {
            NotificationForm n = new NotificationForm();
            jpql = "select n from NotificationForm n where n.retired = false order by n.id desc";
            items = getFacade().findBySQL(jpql);
        } else {
            Map m = new HashMap();
            m.put("h", getSessionController().getLoggedUser().getRestrictedInstitution());
            jpql = "select n from NotificationForm n Where n.hospital =:h and n.retired = false order by n.id desc";
            System.out.println("m = " + m);
            System.out.println("jpql = " + jpql);
            items = getFacade().findBySQL(jpql, m);
        }

        return "view_all_notification_form";
    }

    public String listInstitutionRegistry() {
        String jpql;
        Map m = new HashMap();
        m.put("i", institution);
        jpql = "select n from NotificationForm n Where n.hospital =:i and n.retired = false order by n.id desc";
        items = getFacade().findBySQL(jpql, m);
        return "registry_institution";
    }

    public String listDistrictRegistry() {
        String jpql;
        Map m = new HashMap();
        m.put("d", area);
        jpql = "select n from NotificationForm n Where n.district =:d and n.retired = false order by n.id desc";
        items = getFacade().findBySQL(jpql, m);
        return "registry_district";
    }

    public String listPatientProvinceRegistry() {
        String jpql;
        Map m = new HashMap();
        m.put("p", area);
        jpql = "select n from NotificationForm n Where n.district.superArea =:p and n.retired = false order by n.id desc";
        items = getFacade().findBySQL(jpql, m);
        return "registry_province_by_patients";
    }

    public String listProvinceInstitutionRegistry() {
        String jpql;
        Map m = new HashMap();
        m.put("p", area);
        jpql = "select n from NotificationForm n Where n.hospital.district.superArea =:p and n.retired = false order by n.id desc";
        items = getFacade().findBySQL(jpql, m);
        return "registry_province";
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String addNewHospitalNotificationForm() {
        current = new NotificationForm();
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Asia/Colombo"));
        current.setCreatedAt(c.getTime());
        current.setCreatedDate(c.getTime());
        System.out.println("current.getCreatedAt() = " + current.getCreatedAt());
        current.setCreatedUser(getSessionController().getLoggedUser());
        congenital_Abnormality = new NotificationCategory();
        family_History_Of_Congenital_Abnormality = new NotificationCategory();
        prenatal_Antenatal_Postnatal_Investigation = new NotificationCategory();
        therapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomaly = new NotificationCategory();
        underlyning_Cause_of_Death = new NotificationCategory();
        immediate_Cause_of_Death = new NotificationCategory();
        conditions_Contributing_to_Death = new NotificationCategory();
        if (getSessionController().getLoggedUser().getRestrictedInstitution() != null) {
            current.setHospital(getSessionController().getLoggedUser().getRestrictedInstitution());
        }
        return "/add_hospital_notification_form";
    }

    public String addNewHospitalNotificationFormLrh() {
        current = new NotificationForm();
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Asia/Colombo"));
        current.setCreatedAt(c.getTime());
        current.setCreatedDate(c.getTime());
        System.out.println("current.getCreatedAt() = " + current.getCreatedAt());
        current.setCreatedUser(getSessionController().getLoggedUser());
        congenital_Abnormality = new NotificationCategory();
        family_History_Of_Congenital_Abnormality = new NotificationCategory();
        prenatal_Antenatal_Postnatal_Investigation = new NotificationCategory();
        therapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomaly = new NotificationCategory();
        underlyning_Cause_of_Death = new NotificationCategory();
        immediate_Cause_of_Death = new NotificationCategory();
        conditions_Contributing_to_Death = new NotificationCategory();
        if (getSessionController().getLoggedUser().getRestrictedInstitution() != null) {
            current.setHospital(getSessionController().getLoggedUser().getRestrictedInstitution());
        }
        return "/add_hospital_notification_form_lrh";
    }

    
    public String viewNotificationForm() {
        if (current == null) {
            JsfUtil.addErrorMessage("Please select a notification form");
            return "";
        }
        congenital_Abnormality = new NotificationCategory();
        family_History_Of_Congenital_Abnormality = new NotificationCategory();
        prenatal_Antenatal_Postnatal_Investigation = new NotificationCategory();
        therapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomaly = new NotificationCategory();
        underlyning_Cause_of_Death = new NotificationCategory();
        immediate_Cause_of_Death = new NotificationCategory();
        conditions_Contributing_to_Death = new NotificationCategory();
        return "/add_hospital_notification_form";
    }

    public String addNewAreaNotificationForm() {
        current = new NotificationForm();
        Person infant = new Person();
        Person mother = new Person();
        return "/add_area_notification_form";
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public NotificationFormController() {
    }

    public NotificationForm getCurrent() {
        if (current == null) {
            current = new NotificationForm();
        }
        return current;
    }

    public void setCurrent(NotificationForm current) {
        this.current = current;
    }

    public List<NotificationForm> getItems() {

        return items;
    }

    public void saveSelected() {
        if (current == null) {
            JsfUtil.addErrorMessage("Error Current is Null Save Selected in Notification Controller");
            return;
        }

        if (current.getHospital() == null) {
            current.setHospital(getSessionController().getLoggedUser().getRestrictedInstitution());
        }

        if (current.getId() == null || current.getId() == 0) {
            getFacade().create(current);
        } else {
            getFacade().edit(current);
        }

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

    public NotificationFormFacade getFacade() {
        return facade;
    }

    public void setFacade(NotificationFormFacade facade) {
        this.facade = facade;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public AreaController getAreaController() {
        return areaController;
    }

    public void setAreaController(AreaController areaController) {
        this.areaController = areaController;
    }

    public NotificationCategory getCongenital_Abnormality() {
        return congenital_Abnormality;
    }

    public void setCongenital_Abnormality(NotificationCategory congenital_Abnormality) {
        this.congenital_Abnormality = congenital_Abnormality;
    }

    public NotificationCategory getFamily_History_Of_Congenital_Abnormality() {
        return family_History_Of_Congenital_Abnormality;
    }

    public void setFamily_History_Of_Congenital_Abnormality(NotificationCategory family_History_Of_Congenital_Abnormality) {
        this.family_History_Of_Congenital_Abnormality = family_History_Of_Congenital_Abnormality;
    }

    public NotificationCategory getPrenatal_Antenatal_Postnatal_Investigation() {
        return prenatal_Antenatal_Postnatal_Investigation;
    }

    public void setPrenatal_Antenatal_Postnatal_Investigation(NotificationCategory prenatal_Antenatal_Postnatal_Investigation) {
        this.prenatal_Antenatal_Postnatal_Investigation = prenatal_Antenatal_Postnatal_Investigation;
    }

    public NotificationCategory getTherapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomaly() {
        return therapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomaly;
    }

    public void setTherapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomaly(NotificationCategory therapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomaly) {
        this.therapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomaly = therapeutic_Surgical_Interventions_Referrals_Carried_Out_on_Anomaly;
    }

    public NotificationCategory getUnderlyning_Cause_of_Death() {
        return underlyning_Cause_of_Death;
    }

    public void setUnderlyning_Cause_of_Death(NotificationCategory underlyning_Cause_of_Death) {
        this.underlyning_Cause_of_Death = underlyning_Cause_of_Death;
    }

    public NotificationCategory getImmediate_Cause_of_Death() {
        return immediate_Cause_of_Death;
    }

    public void setImmediate_Cause_of_Death(NotificationCategory immediate_Cause_of_Death) {
        this.immediate_Cause_of_Death = immediate_Cause_of_Death;
    }

    public NotificationCategory getConditions_Contributing_to_Death() {
        return conditions_Contributing_to_Death;
    }

    public void setConditions_Contributing_to_Death(NotificationCategory conditions_Contributing_to_Death) {
        this.conditions_Contributing_to_Death = conditions_Contributing_to_Death;
    }

    public NotificationCategoryFacade getNotificationCategoryFacade() {
        return notificationCategoryFacade;
    }

    public NotificationCategory getRemovingNotificationCategory() {
        return removingNotificationCategory;
    }

    public void setRemovingNotificationCategory(NotificationCategory removingNotificationCategory) {
        this.removingNotificationCategory = removingNotificationCategory;
    }

    public AreaFacade getAreaFacade() {
        return areaFacade;
    }

    
    
    @FacesConverter(forClass = NotificationForm.class)
    public static class NotificationFormControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NotificationFormController controller;
            controller = (NotificationFormController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "notificationFormController");
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
            if (object instanceof NotificationForm) {
                NotificationForm o = (NotificationForm) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type "
                        + object.getClass().getName() + "; expected type: " + NotificationFormController.class.getName());
            }
        }
    }
}
