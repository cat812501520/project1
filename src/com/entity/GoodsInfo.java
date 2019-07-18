package com.entity;

public class GoodsInfo {
    private int gid ;
    private String goodsName ;
    private String goodsPic ;
    private Double goodsPrice;
    private String goodsDescription;
    private int goodsStock;
    private String flag;

    public GoodsInfo() {
    }

    public GoodsInfo(int gid) {
        this.gid = gid;
    }

    public GoodsInfo(int gid, String goodsName, String goodsPic, Double goodsPrice, String goodsDescription, int goodsStock) {
        this.gid = gid;
        this.goodsName = goodsName;
        this.goodsPic = goodsPic;
        this.goodsPrice = goodsPrice;
        this.goodsDescription = goodsDescription;
        this.goodsStock = goodsStock;
    }

    public int getGid() {
        return gid;
    }
    public void setGid(int gid) {
        this.gid = gid;
    }
    public String getGoodsName() {
        return goodsName;
    }
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public String getGoodsPic() {
        return goodsPic;
    }
    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }
    public Double getGoodsPrice() {
        return goodsPrice;
    }
    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
    public String getGoodsDescription() {
        return goodsDescription;
    }
    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }
    public int getGoodsStock() {
        return goodsStock;
    }
    public void setGoodsStock(int goodsStock) {
        this.goodsStock = goodsStock;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
}
