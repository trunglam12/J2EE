/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import POJO.HighChartReport;
import SessionBean.DetailreceiptFacade;
import SessionBean.ProductFacade;
import SessionBean.ReceiptFacade;
import entities.Product;
import entities.Receipt;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import com.google.gson.Gson;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author belsh
 */
@ManagedBean(name = "reportBean")
@RequestScoped
public class ReportManagedBean {

    public enum ViewMode {
        ThisMonth("Tháng này"),
        ThisWeek("Tuần này"),
        ThisYear("Năm nay");

        private String label;

        private ViewMode(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

    }

    @EJB
    private DetailreceiptFacade detailreceiptFacade;

    @EJB
    private ProductFacade productFacade;

    @EJB
    private ReceiptFacade receiptFacade;

    private Date startDate;

    private Date endDate;

    private ViewMode viewMode;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public ViewMode getViewMode() {
        return viewMode;
    }

    public void setViewMode(ViewMode viewMode) {
        this.viewMode = viewMode;
    }

    private SelectItem[] viewModelValues;

    public SelectItem[] getViewModelValues() {
        SelectItem[] items = new SelectItem[ViewMode.values().length];
        int i = 0;
        for (ViewMode vm : ViewMode.values()) {
            items[i++] = new SelectItem(vm, vm.getLabel());
        }
        return items;
    }

    public void setViewModelValues(SelectItem[] viewModelValues) {
        this.viewModelValues = viewModelValues;
    }

    public ReportManagedBean() {
        viewMode = ViewMode.ThisMonth;
        //enđate = current date + 1 
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 1);
        endDate = calendar.getTime();
    }

    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    public String getCurrentListDateString() {
        ArrayList<String> result = new ArrayList<String>();
        if (viewMode != ViewMode.ThisYear) {
            List<Date> dates = getDaysBetweenDates(startDate, endDate);
            Calendar cal = Calendar.getInstance();
            for (Date date : dates) {
                cal.setTime(date);
                result.add(cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1));
            }
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDate);
            int currentMonth = cal.get(Calendar.MONTH) + 1;
            for (int i = 0; i < currentMonth; i++) {
                result.add(Integer.toString(i + 1));
            }
        }
        Gson gson = new Gson();
        return gson.toJson(result);
    }

    public String getSaleReport() {
        ArrayList<HighChartReport> result = new ArrayList<HighChartReport>();
        Calendar calendar = Calendar.getInstance();
        if (viewMode != ViewMode.ThisYear) {
            switch (viewMode) {
                case ThisMonth:
                    calendar.add(Calendar.DAY_OF_MONTH, -30);
                    startDate = calendar.getTime();
                    break;
                case ThisWeek:
                    calendar.add(Calendar.DAY_OF_MONTH, -7);
                    startDate = calendar.getTime();
                    break;
            }
            List<Date> listDay = getDaysBetweenDates(startDate, endDate);
            List<Product> products = productFacade.findAll();
            for (Product product : products) {
                HighChartReport item = new HighChartReport(product.getProductName());
                for (Date day : listDay) {
                    int sum = 0;
                    List<Receipt> dateRecipes = receiptFacade.GetDateRecipe(day);
                    for (Receipt recipe : dateRecipes) {
                        sum += detailreceiptFacade.GetDetailByReceiptIdAndProductId(recipe.getReceiptId(), product.getProductId())
                                .stream().mapToInt(o -> o.getCount()).sum();
                    }
                    item.getData().add(new Long(sum));
                }
                result.add(item);
            }
        } else {
            List<Product> products = productFacade.findAll();
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDate);
            int currentMonth = cal.get(Calendar.MONTH) + 1;
            int currentYear = cal.get(Calendar.YEAR);
            for (Product product : products) {
                HighChartReport item = new HighChartReport(product.getProductName());
                for (int i = 0; i < currentMonth; i++) {
                    int sum = 0;
                    List<Receipt> dateRecipes = receiptFacade.GetDateRecipeByMonth(i + 1, currentYear);
                    for (Receipt recipe : dateRecipes) {
                        sum += detailreceiptFacade.GetDetailByReceiptIdAndProductId(recipe.getReceiptId(), product.getProductId())
                                .stream().mapToInt(o -> o.getCount()).sum();
                    }
                    item.getData().add(new Long(sum));
                }
                result.add(item);
            }
        }
        Gson gson = new Gson();
        return gson.toJson(result);
    }

    public String getRevenueReport() {
        ArrayList<HighChartReport> result = new ArrayList<HighChartReport>();
        Calendar calendar = Calendar.getInstance();
        if (viewMode != ViewMode.ThisYear) {
            switch (viewMode) {
                case ThisMonth:
                    calendar.add(Calendar.DAY_OF_MONTH, -30);
                    startDate = calendar.getTime();
                    break;
                case ThisWeek:
                    calendar.add(Calendar.DAY_OF_MONTH, -7);
                    startDate = calendar.getTime();
                    break;
            }
            List<Date> dates = getDaysBetweenDates(startDate, endDate);
            HighChartReport item = new HighChartReport("Doanh thu");
            for (Date date : dates) {
                List<Receipt> dateRecipes = receiptFacade.GetDateRecipe(date);
                long sum = dateRecipes.stream().mapToLong(o -> o.getTotalPrice()).sum();
                item.getData().add(sum);
            }
            result.add(item);
        }
        else{
            calendar.setTime(endDate);
            int currentMonth = calendar.get(Calendar.MONTH) + 1;
            int currentYear = calendar.get(Calendar.YEAR);
            HighChartReport item = new HighChartReport("Doanh thu");
            for (int i = 0; i < currentMonth; i++) {
                List<Receipt> dateRecipes = receiptFacade.GetDateRecipeByMonth(i + 1, currentYear);
                long sum = dateRecipes.stream().mapToLong(o -> o.getTotalPrice()).sum();
                item.getData().add(sum);
            }
            result.add(item);
        }
        Gson gson = new Gson();
        return gson.toJson(result);
    }
}
