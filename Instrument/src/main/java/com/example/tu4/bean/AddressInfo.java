package com.example.tu4.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MR.WEN on 2016/11/8.
 */

public class AddressInfo implements Serializable{
      private List<AddressDetails> Content;

    public List<AddressDetails> getContent() {
        return Content;
    }

    public void setContent(List<AddressDetails> Content) {
        this.Content = Content;
    }

    @Override
    public String toString() {
        return "AddressInfo{" +
                "content=" + Content +
                '}';
    }
}
