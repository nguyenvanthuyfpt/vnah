package com.form.require.requires.details;


import com.form.FSeed;

public class FDetail extends FSeed
{
    private int id;
    private String name;
    private String code;
    private int require_id;
    private int typeRequire_id;
    private int quantity;
    private long price;
    private String description;
    private int asset_id;
    private String assetName;
        private long sum;

    private String[] names;
    private int[] asset_ids;
    private String[] quantitys;
    private String[] prices;
    private String[] descriptions;
    private int fixedAssetId;
    private int soluong;
    private int nghiepvu;
    private int listId;
    private String statusName;
    private String promote;
    
    
    
    public FDetail()
    {
    }

    public int getId() {   return id;  }    public void setId(int id)  { this.id = id;  }
    public String getName() {   return name;  }    public void setName(String name)  { this.name = name;  }
    public String getCode() {   return code;  }    public void setCode(String code)  { this.code = code;  }
    public int getRequire_id() {
    return require_id;  
    }
    public void setRequire_id(int require_id)  {
    this.require_id = require_id;
    }
    public int getQuantity() {   return quantity;  }    public void setQuantity(int quantity)  { this.quantity = quantity;  }
    public long getPrice() {   return price;  }    public void setPrice(long price)  { this.price = price;  }
    public String getDescription() {   return description;  }   public void setDescription(String description)  { this.description = description; }
    public int getAsset_id() {   return asset_id;  }    public void setAsset_id(int asset_id)  { this.asset_id = asset_id;  }
    public long getSum() {   return sum;  }    public void setSum(long sum)  { this.sum = sum;  }
    public int getTypeRequire_id() {   return typeRequire_id;  }    public void setTypeRequire_id(int typeRequire_id)  { this.typeRequire_id = typeRequire_id;  }
    public String getAssetName() {   return assetName;  }   public void setAssetName(String assetName)  { this.assetName = assetName; }
    
    public String[] getNames() { return names; } public void setNames(String[] names){ this.names = names; }
    public int[] getAsset_ids() {   return asset_ids;  }    public void setAsset_ids(int[] asset_ids)  { this.asset_ids = asset_ids;  }
   
    public String[] getDescriptions() { return descriptions; } public void setDescriptions(String[] descriptions){ this.descriptions = descriptions; }

    public int getFixedAssetId() {
        return fixedAssetId;
    }

    public void setFixedAssetId(int fixedAssetId) {
        this.fixedAssetId = fixedAssetId;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getNghiepvu() {
        return nghiepvu;
    }

    public void setNghiepvu(int nghiepvu) {
        this.nghiepvu = nghiepvu;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getPromote() {
        return promote;
    }

    public void setPromote(String promote) {
        this.promote = promote;
    }

    public String[] getQuantitys() {
        return quantitys;
    }

    public void setQuantitys(String[] quantitys) {
        this.quantitys = quantitys;
    }

    public String[] getPrices() {
        return prices;
    }

    public void setPrices(String[] prices) {
        this.prices = prices;
    }
}
