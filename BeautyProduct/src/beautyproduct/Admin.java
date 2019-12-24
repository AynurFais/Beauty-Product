
package beautyproduct;

public class Admin {
    private String name,productID,type,kkmID,stock;
    
    
   Admin(String name,String productID,String type,String kkmID,String stock){
        this.name = name;
        this.productID = productID;
        this.type = type;
        this.kkmID = kkmID;
        this.stock = stock;
    }

  
    void setName(String name){
        this.name = name;
    }
    void setProductID(String productID){
        this.productID = productID;
        
    }
    void setType(String type){
        this.type = type;
    }
    void setKkmID(String kkmID){
        this.kkmID = kkmID;
    }
    void setStock (String stock){
        this.stock = stock;
    }
    
    String getName(){
        return name;
    }
    String getProductID(){
        return productID;
    }
    String getType(){
        return type;
    }
    String getKkmID(){
        return kkmID;
    }
    String getStock(){
        return stock;
}
}

    

