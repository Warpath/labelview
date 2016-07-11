package com.warpath.labelview;

import java.io.Serializable;

/**
 * Author:tc
 * Date:2015/9/17.
 */
public class LabelBean implements Serializable {
    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_RIGHT = 2;
    public static final int LABEL_TYPE_TOPIC = 1;
    public static final int LABEL_TYPE_BRAND = 2;
    public static final int LABEL_TYPE_LOCATION = 3;
    private int type;
    private int direction;
    private int x;//左上角x轴
    private int y;//左上角y轴
    private int width;//label宽度
    private int height;//label高度
    private String label;
    private int image_width;//图片宽度
    private int image_height;//图片高度
    private int brand_id;//品牌Id
    private String logo;//品牌logo

    public LabelBean() {

    }

    public LabelBean(int type, int direction, int x, int y, int width, int height, String label, int image_width, int image_height, int brand_id, String logo) {
        this.type = type;
        this.direction = direction;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
        this.image_width = image_width;
        this.image_height = image_height;
        this.brand_id = brand_id;
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getImage_height() {
        return image_height;
    }

    public void setImage_height(int image_height) {
        this.image_height = image_height;
    }

    public int getImage_width() {
        return image_width;
    }

    public void setImage_width(int image_width) {
        this.image_width = image_width;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public LabelBean clone() {
        return new LabelBean(this.type, this.direction, this.x, this.y, this.width, this.height, this.label, this.image_width, this.image_height, this.brand_id, this.logo);
    }
}
