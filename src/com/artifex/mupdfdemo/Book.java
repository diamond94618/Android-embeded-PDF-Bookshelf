package com.artifex.mupdfdemo;

public class Book {
	 
    private int id;
    private int bookNo;
    private String title;
    private int page;
 
    public Book(){}
 
    public Book(int bookNo, String title, Integer page) {
        super();
        this.bookNo = bookNo;
        this.title = title;
        this.page = page;
    }
 
    //getters & setters
    public int getID(){
        return this.id;
    }
     
    // setting id
    public void setID(int id){
        this.id = id;
    }
    // getting name
    public int getbookNo(){
        return this.bookNo;
    }
     
    // setting name
    public void setbookNo(int bookNo){
        this.bookNo = bookNo;
    }
     
     
    // getting name
    public String getTitle(){
        return this.title;
    }
     
    // setting name
    public void setTitle(String title){
        this.title = title;
    }
     
    // getting phone number
    public int getPage(){
        return this.page;
    }
     
    // setting phone number
    public void setPage(int page){
        this.page = page;
    }
 
    @Override
    public String toString() {
        return "Book [id=" + id + "bookNo=" + bookNo + ", title=" + title + ", page=" + page
                + "]";
    }
}