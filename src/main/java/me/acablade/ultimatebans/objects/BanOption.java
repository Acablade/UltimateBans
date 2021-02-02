package me.acablade.ultimatebans.objects;

import java.util.Arrays;

public enum BanOption {

    SILENT("s");

    protected String optionName;

    BanOption(String optionName){
        this.optionName = optionName;
    }
    public static BanOption getOptionByName(String s){
        for(BanOption option: BanOption.values()){
            if(option.optionName.equalsIgnoreCase(s)){
                return option;
            }
        }
        return null;
    }

}
