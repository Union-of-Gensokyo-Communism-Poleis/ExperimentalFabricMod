package beer.ugcp.ExperimentalFabricMod.Utilities.StringParser;

import net.minecraft.util.Pair;

import java.util.*;
import java.util.regex.*;

public final class ChemicalHelper {
    private final Pattern PATTERN = Pattern.compile("([A-Z][a-z]?)|([()\\[\\]{}])|([0-9])*");
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
                            addAtoms(buffer,j,1);
                        }else if(j.matches("[0-9]*")){
                            if(tileStack.empty()){
                                multiplyAtoms(buffer,Integer.parseInt(j));
                                break;
                            }else{
                                k = tileStack.pop();
                                addAtoms(buffer,k,Integer.parseInt(j));
                            }
                        }
                    }
                    mergeAtoms(rtn,buffer);
                    break;
                }
                if(flag){
                    multiplyAtoms(buffer,Integer.parseInt(matcher.group(0)));
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
                            addAtoms(buffer,k,Integer.parseInt(j));
                        }else if(j.matches("[A-Z][a-z]?")){
                            addAtoms(buffer,j,1);
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
    private void mergeAtoms(Vector<Pair<String,Integer>> dest,Vector<Pair<String,Integer>> src){
        for(Pair<String,Integer> i : src){
            addAtoms(dest,i.getLeft(),i.getRight());
        }
    }
    private void multiplyAtoms(Vector<Pair<String,Integer>> pairs, int factor){
        for(Pair<String,Integer> i: pairs){
            i.setRight(i.getRight()*factor);
        }
    }
    private void addAtoms(Vector<Pair<String,Integer>> pairs, String atom, int amount){
        for(Pair<String,Integer> i: pairs){
            if(i.getLeft().equals(atom)){
                i.setRight(i.getRight()+amount);
                return;
            }
        }
        pairs.add(new Pair<>(atom,amount));
    }
}
