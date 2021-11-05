package beer.ugcp.ExperimentalFabricMod.Utilities.StringParser;

import net.minecraft.util.Pair;

import java.util.*;
import java.util.regex.*;

/**
 * <h1>This class provide chemical formula handling support.</h1>
 */
public final class ChemicalHelper {
//To slice chemical formula with this pattern.
    private final Pattern PATTERN = Pattern.compile("([A-Z][a-z]?)|([()\\[\\]{}])|([0-9])*");

    /**
     * <h3>Internal parsing chemical formula string.</h3>
     * @param string Formula
     * @return Pair vector of every element atoms' amount.
     */
    public Vector<Pair<String,Integer>> parseChemFormula_(String string){
        final Vector<Pair<String,Integer>> rtn = new Vector<>();
        final String[] subFormulas = string.split("\\.");
        for (String i:subFormulas){
            Stack<String> tileStack = new Stack<>();
            Matcher matcher = PATTERN.matcher(i);
            Vector<Pair<String,Integer>> buffer = new Vector<>();
            boolean flag = false;
            while(matcher.find()){
                if(matcher.group(0).equals("")) {
                    String j,k;
                    while(true){
                        if(tileStack.empty())break;
                        j = tileStack.pop();
                        if(j.matches("[A-Z][a-z]?")){
                            addComponent(buffer,j,1);
                        }else if(j.matches("[0-9]*")){
                            if(tileStack.empty()){
                                multiplyIngredients(buffer,Integer.parseInt(j));
                                break;
                            }else{
                                k = tileStack.pop();
                                addComponent(buffer,k,Integer.parseInt(j));
                            }
                        }
                    }
                    mergeIngredients(rtn,buffer);
                    break;
                }
                if(flag){
                    multiplyIngredients(buffer,Integer.parseInt(matcher.group(0)));
                    flag = false;
                    continue;
                }
                if(matcher.group(0).equals(")")){
                    flag = true;
                    while(true){
                        String j = tileStack.pop();
                        String k;
                        if(j.matches("[0-9]*")){
                            k = tileStack.pop();
                            addComponent(buffer,k,Integer.parseInt(j));
                        }else if(j.matches("[A-Z][a-z]?")){
                            addComponent(buffer,j,1);
                        }else if(j.equals("(")){
                            break;
                        }
                    }
                }else{
                    tileStack.push(matcher.group(0));
                }
            }
        }
        return rtn;
    }

    /**
     * <h3>Internal function to merge two pair vector</h3>
     * @param dest Destination
     * @param src Source
     */
    public static void mergeIngredients(Vector<Pair<String,Integer>> dest, Vector<Pair<String,Integer>> src){
        for(Pair<String,Integer> i : src){
            addComponent(dest,i.getLeft(),i.getRight());
        }
    }

    /**
     * <h3>Internal function to multiply atoms' amount</h3>
     * @param pairs Pair vector
     * @param factor Factor
     */
    public static void multiplyIngredients(Vector<Pair<String,Integer>> pairs, int factor){
        for(Pair<String,Integer> i: pairs){
            i.setRight(i.getRight()*factor);
        }
    }

    /**
     * <h3>Internal function to add single element's atom into the vector</h3>
     * @param pairs Pair vector
     * @param atom Element
     * @param amount Amount
     */
    public static void addComponent(Vector<Pair<String,Integer>> pairs, String atom, int amount){
        for(Pair<String,Integer> i: pairs){
            if(i.getLeft().equals(atom)){
                i.setRight(i.getRight()+amount);
                return;
            }
        }
        pairs.add(new Pair<>(atom,amount));
    }
}
