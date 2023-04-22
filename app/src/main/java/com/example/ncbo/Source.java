
package com.example.ncbo;




public class Source {


    private Object id;

    private String name;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Source() {
    }

    /**
     * 
     * @param name
     * @param id
     */
    public Source(Object id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
