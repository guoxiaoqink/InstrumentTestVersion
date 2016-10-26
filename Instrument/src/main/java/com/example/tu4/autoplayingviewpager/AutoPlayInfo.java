package com.example.tu4.autoplayingviewpager;

/**
 * 项目名称 : AutoPlayingViewPager<br>
 * 创建人 : skycracks<br>
 * 创建时间 : 2016-5-18下午7:18:18<br>
 * 版本 :	[v1.0]<br>
 * 类描述 : 广告轮播图文信息<br>
 */
public class AutoPlayInfo {
    //轮播图片URL
    private String imageUrl;
    //轮播本地图片资源Id
    private int imageId;
    //链接
    private String adLinks;
    //图片对应的标题
    private String title;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getAdLinks() {
        return adLinks;
    }

    public void setAdLinks(String adLinks) {
        this.adLinks = adLinks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
