package com.vitali.financetracker;

import model.Transaction;

public class Main {
    public static void main(String[] args){
        Transaction tr = new Transaction("income",1200,"dollars");
        System.out.println(tr);
        System.out.println(tr.getAmount());
    }
}
