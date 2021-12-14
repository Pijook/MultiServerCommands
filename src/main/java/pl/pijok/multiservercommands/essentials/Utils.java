package pl.pijok.multiservercommands.essentials;

import org.bukkit.Material;

public class Utils {

    public static boolean isMaterial(String a){
        try{
            Material.valueOf(a);
            return true;
        }
        catch (IllegalArgumentException e){
            return false;
        }
    }

}
