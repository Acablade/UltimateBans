package me.acablade.ultimatebans.objects;

public enum MuteOption {

    SILENT("s");

    protected String optionName;

    MuteOption(String optionName){
        this.optionName = optionName;
    }
    public static MuteOption getOptionByName(String s){
        for(MuteOption option: MuteOption.values()){
            if(option.optionName.equalsIgnoreCase(s)){
                return option;
            }
        }
        return null;
    }

}
