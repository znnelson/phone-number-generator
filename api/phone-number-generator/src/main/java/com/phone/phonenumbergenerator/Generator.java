package com.phone.phonenumbergenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Generator {

    private String inpuString;

    private Queue<Integer> sourceNumbers;
    private String sortedNumber;

    public Generator(String string){
        this.inpuString = string;
    }

    public Queue<Integer> getSourceNumber(){
        if(this.sourceNumbers==null){
            char[] chars = inpuString.toCharArray();
            LinkedList<Integer> numbers = new LinkedList<Integer>();
            for (char c : chars) {
                numbers.add(Character.digit(c, 10));
            }
            numbers.sort((a,b)->{return a - b;});
            this.sourceNumbers=numbers;
        }
        return this.sourceNumbers;
    }

    public String getSortedSourceNumber(){
        if(this.sortedNumber==null){
            StringBuilder s = new StringBuilder();
            Iterator iterator = getSourceNumber().iterator();
            while(iterator.hasNext()){
                s.append(iterator.next());
            }
            this.sortedNumber=s.toString();
        }   
        return this.sortedNumber;
    }

    public int getValidCombinationTotalNumber(){
        if(util.exists(getSortedSourceNumber())) {
            return getTotalFromCache(getSortedSourceNumber());
        }else{
            return getValidCombination().keySet().iterator().next();
        }
    }

    public int getTotalFromCache(String number){
        Integer result=0;
        try{
            result=util.getTotal(number);
        }catch(Exception e){
            result = getValidCombination().keySet().iterator().next();
        }
            return result;
        
    }

    public List<PhoneNumberEntity> getCombinationResult(String number, int pageSize, int pageNumber){
        List<PhoneNumberEntity> list = new ArrayList<>();
        try{
            util.getData(getSortedSourceNumber(), pageSize, pageNumber, list);
        }catch(Exception e){
        }
        return list;
    }

    public Map<Integer, Set<String>> getValidCombination (){
        Map<Integer, Set<String>> result = new HashMap<>();
        Set<String> res = new HashSet<>();
        generate(getSourceNumber(), getSourceNumber().size(), 0, new int [getSourceNumber().size()], res);
        result.put(res.size(), res);
        util.writeToFile(result, getSortedSourceNumber());
        return result;
    }

    public void generate(Queue<Integer> numbers, int l, int index, int[] temp, Set<String> res){
        if(numbers.size()==0 || l==0){
            StringBuilder s = new StringBuilder();
            for (int i: temp) {
                s.append(i);
            }
            res.add(s.toString());
            return;
        }
        int a = -1;

        for(int i = 0; i < l; i++) {
            int b = numbers.poll();
            if( valid(temp.length, index, b) ){
                if((a>-1&a!=b)||a==-1){
                    temp[index]=b;
                    generate(numbers, l-1, index+1, temp, res);
                }
            }
            a = b;
            numbers.add(b);
        }

    }

    private boolean valid(int l, int index, int number){
        if(l==7){
            if(index==0 && number<2){
                return false;
            }
        }else if(l==10){
            if(index==0 && number<2){
                return false;
            }else if(index==3 && number<2){
                return false;
            }
        }

        return true;
    }

    
}