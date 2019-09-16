package top.hting;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 描述:
 * 时间工具类
 *
 * @outhor lizhichao
 * @create 2018-10-26 21:14
 */
public class DateUtils {

    //获取当天的开始时间
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    //获取当天的结束时间
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }


    //获取昨天的开始时间
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    //获取昨天的结束时间
    public static Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    //获取明天的开始时间
    public static Date getBeginDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    //获取明天的结束时间
    public static Date getEndDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    //获取本周的开始时间
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    //获取本周的结束时间
    public static Date getEndDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    //获取本月的开始时间
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    //获取本月的结束时间
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    //获取本年的开始时间
    public static Date getBeginDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        return getDayStartTime(cal.getTime());
    }

    //获取本年的结束时间
    public static Date getEndDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }

    //获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d){
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    //获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if(null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    //获取今年是哪一年
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    //获取本月是哪一月
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    //两个日期相减得到的天数
    public static int getDiffDays(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        long diff = (endDate.getTime() - beginDate.getTime())/ (1000 * 60 * 60 * 24);
        int days = new Long(diff).intValue();
        return days;
    }

    //两个日期相减得到的毫秒数
    public static long dateDiff(Date beginDate, Date endDate) {
        long date1ms = beginDate.getTime();
        long date2ms = endDate.getTime();
        return date2ms - date1ms;
    }

    //两个日期相减得到的小时数
    public static long dateDiffHours(Date beginDate, Date endDate) {
        long date1ms = beginDate.getTime();
        long date2ms = endDate.getTime();
        return (date2ms - date1ms)/(1000 * 60 * 60);
    }

    //获取两个日期中的最大日期
    public static Date max(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return beginDate;
        }
        return endDate;
    }

    //获取两个日期中的最小日期
    public static Date min(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return endDate;
        }
        return beginDate;
    }

    //返回某月该季度的第一个月
    public static Date getFirstSeasonDate(Date date) {
        final int[] SEASON = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int sean = SEASON[cal.get(Calendar.MONTH)];
        cal.set(Calendar.MONTH, sean * 3 - 3);
        return cal.getTime();
    }

    //返回某个日期下几天的日期
    public static Date getNextDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
        return cal.getTime();
    }

    //返回某个日期前几天的日期
    public static Date getFrontDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
        return cal.getTime();
    }

    //获取某年某月到某年某月按天的切片日期集合（间隔天数的日期集合）
    public static List getTimeList(int beginYear, int beginMonth, int endYear,int endMonth, int k) {
        List list = new ArrayList();
        if (beginYear == endYear) {
            for (int j = beginMonth; j <= endMonth; j++) {
                list.add(getTimeList(beginYear, j, k));
            }
        } else {
            for (int j = beginMonth; j < 12; j++) {
                list.add(getTimeList(beginYear, j, k));
            }
            for (int i = beginYear + 1; i < endYear; i++) {
                for (int j = 0; j < 12; j++) {
                    list.add(getTimeList(i, j, k));
                }
            }
            for (int j = 0; j <= endMonth; j++) {
                list.add(getTimeList(endYear, j, k));
            }
        }
        return list;
    }

    //获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）
    public static List getTimeList(int beginYear, int beginMonth, int k) {
        List list = new ArrayList();
        Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
        int max = begincal.getActualMaximum(Calendar.DATE);
        for (int i = 1; i < max; i = i + k) {
            list.add(begincal.getTime());
            begincal.add(Calendar.DATE, k);
        }
        begincal = new GregorianCalendar(beginYear, beginMonth, max);
        list.add(begincal.getTime());
        return list;
    }

    //获取某年某月的第一天日期
    public static Date getStartMonthDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getTime();
    }

    //获取某年某月的最后一天日期
    public static Date getEndMonthDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }


    //获取某年最后一天日期
    public static Date getEndYearDate(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,11, 31, 23, 59, 59);
        return calendar.getTime();
    }
    //获取某年开始一天日期
    public static Date getStartYearDate(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,0, 1, 0, 0, 0);
        return calendar.getTime();
    }

        /*
         * 默认日期格式
         */
        public static String DEFAULT_FORMAT = "yyyy-MM-dd";

        /**
         * 测试主方法
         * @param args
         */
        public static void main(String[] args) {
        	System.out.println(formatDateNotTime(new Date()));
//            for(int i = 1951;i < 1960;i++){
//                System.out.println(formatDate(getYearFirst(i)));
//                System.out.println(formatDate(getYearLast(i)));
//            }
//
//            System.out.println(getCurrYearFirst());
//            System.out.println(formatDate(getCurrYearLast()));
//
//            System.out.println("=======================");
//
//            System.out.println(formatDate(getEndYearDate(2018)));
        	System.out.println("====Start====="+formatDate(getLastMonthStartTime(new Date())));
        	System.out.println("====end====="+formatDate(getLastMonthEndTime(new Date())));
            List<String> seasonBetweenDate = DateUtils.getSeasonBetweenDate("2018-1", "2019-1");

            seasonBetweenDate.stream().forEach(e->{
                System.out.println(e);
            });

        }

        /**
         * 格式化日期
         * @param date 日期对象
         * @return String 日期字符串
         */
        public static String formatDate(Date date){
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sDate = f.format(date);
            return sDate;
        }

        /**
         * 格式化日期
         * @param date 日期对象
         * @return String 日期字符串
         */
        public static String formatDateNotTime(Date date){
            SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日");
            String sDate = f.format(date);
            return sDate;
        }
        
        /**
         * 格式化日期
         * @param String 日期对象
         * @return Date 日期字符串
         */
        public static Date parseDate(String str){
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
			try {
				date = f.parse(str);
				return date;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return date;
        }
        
        /**
         * 获取当年的第一天
         * @return
         */
        public static Date getCurrYearFirst(){
            Calendar currCal=Calendar.getInstance();
            int currentYear = currCal.get(Calendar.YEAR);
            return getYearFirst(currentYear);
        }

        /**
         * 获取当年的最后一天
         * @return
         */
        public static Date getCurrYearLast(){
            Calendar currCal=Calendar.getInstance();
            int currentYear = currCal.get(Calendar.YEAR);
            return getYearLast(currentYear);
        }

        /**
         * 获取某年第一天日期
         * @param year 年份
         * @return Date
         */
        public static Date getYearFirst(int year){
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, year);
            Date currYearFirst = calendar.getTime();
            return currYearFirst;
        }

        /**
         * 获取某年最后一天日期
         * @param year 年份
         * @return Date
         */
        public static Date getYearLast(int year){
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, year);
            calendar.roll(Calendar.DAY_OF_YEAR, -1);
            Date currYearLast = calendar.getTime();

            return currYearLast;
        }


    /**
     * 获取某季度开始时间
     * @param year
     * @param quarter
     * @return
     */
    public static Date getStartQuarterDate(int year,int quarter){
        // 第一天

        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
        Calendar calendar = Calendar.getInstance();


        int month=1;
        switch (quarter){
            case 1:

                month=0;
                break;
            case 2:
                month=3;
                break;
            case 3:
                month=6;
                break;
            case 4:
                month=9;
                break;
        }
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        Date theDate = calendar.getTime();



        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first);
            str.append(" 00:00:00");
        Date parse = null;
        try {
             parse = df.parse(str.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parse;
    }


    /**
     * 获取某季度结束时间
     * @param year
     * @param quarter
     * @return
     */
    public static Date getEndQuarterDate(int year,int quarter){
        // 第一天

        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT);
        Calendar calendar = Calendar.getInstance();


        int month=1;
        switch (quarter){
            case 1:

                month=2;
                break;
            case 2:
                month=5;
                break;
            case 3:
                month=8;
                break;
            case 4:
                month=11;
                break;
        }
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);



        // 最后一天
        calendar.add(Calendar.MONTH, 1); // 加一个月
        calendar.set(Calendar.DATE, 1); // 设置为该月第一天
        calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_last);
            endStr.append(" 23:59:59");

        Date parse = null;
        try {
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            parse = df2.parse(endStr.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parse;
    }
    
    /**
     *  获取上一月的结束时间
     * @param date
     * @return
     */
    public static Date getLastMonthEndTime(Date date) {
    	Calendar calendar = new GregorianCalendar();
    	calendar.setTime(date);
    	calendar.set(Calendar.DATE, 1); // 设置为该月第一天
        calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
    	calendar.set(Calendar.HOUR_OF_DAY, 23);
    	calendar.set(Calendar.MINUTE, 59);
    	calendar.set(Calendar.SECOND, 59);
    	calendar.set(Calendar.MILLISECOND, 999);
    	return calendar.getTime();
    }
    /**
     *  获取上一月的开始时间
     * @param date
     * @return
     */
    public static Date getLastMonthStartTime(Date date) {
    	Calendar calendar = new GregorianCalendar();
    	calendar.setTime(date);
    	calendar.add(Calendar.MONTH, -1); // 减一个月
    	calendar.set(Calendar.DAY_OF_MONTH, 1);
    	calendar.set(Calendar.HOUR_OF_DAY, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.MILLISECOND, 0);
    	return calendar.getTime();
    }
    /**
     * 计算两时间之间的年份
     * @param begin
     * @param end
     * @return
     */
    public static List<String> getYearBetweenDate(String begin,String end) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy");

        ArrayList<String> result = new ArrayList<>();
        Calendar startDate = Calendar.getInstance();

        try {
            startDate.setTime(sd.parse(begin));

            Calendar endDate = Calendar.getInstance();
            endDate.setTime(sd.parse(end));

            while (true){

                Date start = startDate.getTime();
                String format = sd.format(start);
                Date endDateTime = endDate.getTime();
                String endstr = sd.format(endDateTime);
                result.add(format);
                startDate.add(Calendar.YEAR,1);
                if (format.equals(endstr)){
                    break;
                }


            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 计算两时间之间的季度
     * @param begin
     * @param end
     * @return
     */
    public static List<String> getSeasonBetweenDate(String begin,String end) {

        List<String> result = new ArrayList<>();

        String[] beginStr = begin.split("-");
        String[] endStr = end.split("-");

        int yearb = Integer.valueOf(beginStr[0]);
        int seasonb = Integer.valueOf(beginStr[1]);



        //end年
        int yeare = Integer.valueOf(endStr[0]);

        //end季
        int seasone = Integer.valueOf(endStr[1]);
        if (seasone>4) {

            return result;
        }

        //获取第一季
        result.add(yearb+"-"+seasonb);
        if (yearb<yeare||(yearb==yeare&&seasonb<seasone)){
            while (true){
                if (yearb==yeare&&seasonb==seasone){
                    break;
                }

                seasonb++;
                if (seasonb==5){
                    yearb++;
                    seasonb=1;
                }

                result.add(yearb+"-"+seasonb);
            }
        }


        return result;
    }


    /**
     * 计算两时间之间的月份
     * @param begin
     * @param end
     * @return
     */
    public static List<String> getMonthBetweenDate(String begin,String end) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");

        ArrayList<String> result = new ArrayList<>();
        Calendar startDate = Calendar.getInstance();

        try {
            startDate.setTime(sd.parse(begin));

            Calendar endDate = Calendar.getInstance();
            endDate.setTime(sd.parse(end));

            while (true){

                Date start = startDate.getTime();
                String format = sd.format(start);
                Date endDateTime = endDate.getTime();
                String endstr = sd.format(endDateTime);
                result.add(format);
                startDate.add(Calendar.MONTH,1);
                if (format.equals(endstr)){
                    break;
                }


            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }
    // 日期转化为大小写  
    public static String dataToUpper(Date date) {  
            Calendar ca = Calendar.getInstance();     
            ca.setTime(date);     
            int year = ca.get(Calendar.YEAR);     
            int month = ca.get(Calendar.MONTH) + 1;     
            int day = ca.get(Calendar.DAY_OF_MONTH);  
            return numToUpper(year) + "年" + monthToUppder(month) + "月" + dayToUppder(day) + "日";  
    }  
    // 将数字转化为大写  
    public static String numToUpper(int num) {  
           //String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};  
           String u[] = {"零","一","二","三","四","五","六","七","八","九"};  
           char[] str = String.valueOf(num).toCharArray();  
           String rstr = "";  
           for (int i = 0; i < str.length; i++) {  
                   rstr = rstr + u[Integer.parseInt(str[i] + "")];  
            }  
           return rstr;  
    }  
      
    // 月转化为大写  
    public static String monthToUppder(int month) {  
             if(month < 10) {  
                     return numToUpper(month);          
             } else if(month == 10){  
                     return "十";  
             } else {  
                     return "十" + numToUpper(month - 10);  
             }  
    }  
      
    // 日转化为大写  
    public static String dayToUppder(int day) {  
             if(day < 20) {  
                      return monthToUppder(day);  
             } else {  
                      char[] str = String.valueOf(day).toCharArray();  
                      if(str[1] == '0') {  
                               return numToUpper(Integer.parseInt(str[0] + "")) + "十";  
                      }else {  
                               return numToUpper(Integer.parseInt(str[0] + "")) + "十" + numToUpper(Integer.parseInt(str[1] + ""));  
                      }  
           }  
   }  
}
