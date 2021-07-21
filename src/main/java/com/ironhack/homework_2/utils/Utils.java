package com.ironhack.homework_2.utils;

import com.ironhack.homework_2.enums.Industry;
import com.ironhack.homework_2.enums.Product;

public class Utils {
    public static boolean validNumber(String num){
        try{
            Integer.parseInt(num);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean validProduct(String product){
        try{
            Product.valueOf(product);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean validIndustry(String industry){
        try{
            Industry.valueOf(industry);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public static boolean validName(String name){
        return true;
    }
    public static boolean validEmail(String email){
        return true;
    }
    public static boolean validPhone(String phone){
        return true;
    }
    public static boolean validString(String str){
        return true;
    }
}
