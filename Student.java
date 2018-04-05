package first;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.List;

public class Student {
    private double ExerciseDistance;
    private double TotalEnergy;
    private String UserID;
    private Energy newenergy = new Energy();
    public Student(String id) {
        ExerciseDistance = 3000;
        TotalEnergy = 0;
        UserID = id;
    }
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            /*for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("GET Failed! " + e);
            e.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("POST Failed! "+e);
            e.printStackTrace();
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    private static double rad(double d){
        return d * Math.PI / 180.0;
    }
    public static double getDistance(double lng1, double lat1, double lng2, double lat2){   //输入两个经纬度信息计算距离，但目前定位问题尚未解决
        final double EARTH_RADIUS = 6378137;
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(
                Math.sqrt(
                        Math.pow(Math.sin(a/2),2)
                                + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)
                )
        );
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
    private void EnergyIncrease(double take) {
        TotalEnergy += take;
    }
    private void EnergyTran() {
        if(ExerciseDistance < 1000){
            newenergy.setEnergy(Math.random()*40 + 30);
            newenergy.setExpirationTime(86400);
        }
        else if(ExerciseDistance < 3000){
            newenergy.setEnergy(ExerciseDistance/3000*256);
            newenergy.setExpirationTime(86400);
        }
        else {
            newenergy.setEnergy(256);
            newenergy.setExpirationTime(86400);
        }
    }
    public void TakeEnergy() {
        double temp = newenergy.getEnergy();
        newenergy.setEnergy(0);
        newenergy.setExpirationTime(0);
        EnergyIncrease(temp);
    }
    public double TakeOthersEnergy(Student OtherUser) {
        if(OtherUser.newenergy.WhoSteal.containsKey(UserID)){
            System.out.println("You have stealed once, can't take twice!");
            return -1;
        }
        if(OtherUser.newenergy.getLossEnergy() / (OtherUser.newenergy.getEnergy() + OtherUser.newenergy.getLossEnergy()) >= 0.4) {
            System.out.println("Steal energy failed!");
            return -2;
        }
        else{
            double temp = Math.random()*((OtherUser.newenergy.getEnergy() + OtherUser.newenergy.getLossEnergy())*0.4) + 1;
            OtherUser.newenergy.setEnergy(OtherUser.newenergy.getEnergy() - temp);
            OtherUser.newenergy.setLossEnergy(OtherUser.newenergy.getLossEnergy() + temp);
            OtherUser.newenergy.WhoSteal.put(UserID, temp);
            EnergyIncrease(temp);
            System.out.println(UserID + " steal " + OtherUser.UserID + "'s energy.");
            return temp;
        }
    }
    public static void main(String[] args){     //以下为测试程序，可删除
        Student student1 = new Student("stu1");
        Student student2 = new Student("stu2");
        Student student3 = new Student("stu3");
        student1.EnergyTran();
        student2.EnergyTran();
        student3.EnergyTran();
        student1.TakeOthersEnergy(student2);
        student1.TakeOthersEnergy(student2);
        student1.TakeOthersEnergy(student2);
        student3.TakeOthersEnergy(student2);
        student3.TakeOthersEnergy(student2);
        student3.TakeOthersEnergy(student2);
        student1.TakeEnergy();

        String result = sendGet("http://restapi.amap.com/v3/ip", "key=ddf972324c0705533b58a47b4dde6ec8");
        result = result.replace("}", "");
        result = result.replace("\"", "");
        System.out.println(result);
        String[] results = result.split(":");
        String[] location = results[7].split(";");
        String[] string1 = location[0].split(",");
        String[] string2 = location[1].split(",");
        double longitude = (Double.parseDouble(string1[0]) + Double.parseDouble(string2[0])) / 2;
        System.out.println(longitude);
        double latitude = (Double.parseDouble(string1[1]) + Double.parseDouble(string2[1])) / 2;
        System.out.println(latitude);
        System.out.println(getDistance(Double.parseDouble(string2[0]), Double.parseDouble(string2[1]), Double.parseDouble(string1[0]), Double.parseDouble(string1[1])));
    }
}